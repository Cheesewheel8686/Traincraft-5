package tmt;

import fexcraft.fvtm.BOBRollingStockModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import train.common.api.EntityRollingStock;
import train.common.api.IRollingStockLightControls;
import train.common.core.handlers.ConfigHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Makes large rolling-stock bodies cheaper to render.
 *
 * <p>The old generated models render every little {@link ModelRendererTurbo} box one at a
 * time. Some passenger cars have hundreds of body boxes, so a long consist can make thousands
 * of tiny OpenGL calls every frame. This class reduces that cost by drawing the large static
 * body first as a few merged display lists.</p>
 *
 * <p>It is deliberately not a full replacement for the generated model renderer. The generated
 * render method still runs afterward. We only skip the exact body parts already drawn here.
 * Trucks, bogies, texture swaps, cargo, rotary pieces, overlays, and other custom sections
 * continue to render the old way. This boundary is what keeps bogies from moving to the wrong
 * place and keeps custom model code from losing its original texture/light/matrix state.</p>
 */
public final class ModelRendererTurboBatch {

	private static final int MIN_BATCH_SIZE = 64;
	private static final int FVTM_RUNTIME_MIN_BATCH_SIZE = 8;
	private static final int FVTM_RUNTIME_BATCH_INDEX = -1;
	private static final ThreadLocal<Context> ACTIVE = new ThreadLocal<Context>();
	private static final Map<BatchKey, CompiledBatch> CACHE = new HashMap<BatchKey, CompiledBatch>();
	private static final Map<ModelRendererTurbo, RenderGroup> GROUP_CACHE = new IdentityHashMap<ModelRendererTurbo, RenderGroup>();
	private static final Map<Class<?>, List<StaticBodyField>> STATIC_BODY_FIELDS = new HashMap<Class<?>, List<StaticBodyField>>();

	private ModelRendererTurboBatch() {
	}

	public static void begin(Object owner) {
		begin(owner, null);
	}

	public static void begin(Object owner, Entity entity) {
		if (!ConfigHandler.ENABLE_TMT_MODEL_BATCHING || owner == null) {
			return;
		}
		ACTIVE.set(new Context(owner, entity));
	}

	public static void end() {
		Context context = ACTIVE.get();
		if (context == null) {
			return;
		}
		try {
			flushActive();
		}
		finally {
			ACTIVE.remove();
		}
	}

	/**
	 * Called by every {@link ModelRendererTurbo#render(float, boolean)} while a batch is active.
	 *
	 * <p>Think of this as the gatekeeper for each model part:</p>
	 *
	 * <ul>
	 *   <li>If this part was already drawn by the body prebatch, return true so the normal
	 *   render call is skipped. That prevents drawing the same body box twice.</li>
	 *   <li>If {@code suppressOnly} is true, the body prebatch is finished. From that point on,
	 *   we do not collect new parts, because later parts may be bogies or custom sections that
	 *   need their exact original transform and render state.</li>
	 *   <li>Collection is disabled by default. It is only allowed while an explicit body-source
	 *   helper owns the render boundary. That prevents a random truck or cargo render call from
	 *   being delayed until after its GL matrix has changed.</li>
	 * </ul>
	 */
	public static boolean capture(ModelRendererTurbo turbo, float scale, boolean rotorder) {
		Context context = ACTIVE.get();
		if (context != null && context.suppressed.contains(turbo)) {
			return true;
		}
		if (context == null || context.suppressOnly || !context.captureEnabled || context.flushing || !ConfigHandler.ENABLE_TMT_MODEL_BATCHING) {
			return false;
		}
		if (!isBatchCompatible(turbo)) {
			return false;
		}
		context.entries.add(new Entry(turbo, scale, rotorder, classify(turbo)));
		return true;
	}

	/**
	 * Finds and pre-renders the static body parts for model types that are not necessarily
	 * {@link ModelConverter}.
	 *
	 * <p>This method is intentionally conservative. It only batches arrays or groups we can
	 * identify before the generated model render method starts. That is the safety boundary
	 * that keeps trucks and bogies from being captured inside a temporary GL transform and
	 * replayed later after the transform has been popped.</p>
	 */
	public static boolean renderStaticBodySources(Object owner, Entity entity, float scale, boolean rotorder) {
		Context context = ACTIVE.get();
		if (context == null || context.flushing || owner == null || !ConfigHandler.ENABLE_TMT_MODEL_BATCHING) {
			if (context != null) {
				context.suppressOnly = true;
			}
			return false;
		}
		boolean rendered = false;
		if (owner instanceof BOBRollingStockModel) {
			rendered |= renderFVTMGroups(owner, ((BOBRollingStockModel)owner).getBaseModel(), scale, rotorder);
		}
		else if (owner instanceof ModelConverter) {
			rendered |= renderArray(owner, ((ModelConverter)owner).bodyModel, scale, rotorder);
		}
		else if (owner instanceof FVTMFormatBase) {
			rendered |= renderFVTMGroups(owner, (FVTMFormatBase)owner, scale, rotorder);
		}
		else {
			for (StaticBodyField source : getStaticBodyFields(owner.getClass())) {
				ModelRendererTurbo[] model = source.get(owner);
				if (model != null && model.length >= MIN_BATCH_SIZE) {
					rendered |= renderArray(owner, model, scale, rotorder);
				}
			}
		}
		context.suppressOnly = true;
		return rendered;
	}

	/**
	 * Explicit FVTM group prebatching for callers that have already decided the groups are
	 * static body geometry. This is used for the base model inside {@link BOBRollingStockModel}
	 * and for rolling-stock models that directly extend {@link FVTMFormatBase}.
	 *
	 * <p>FVTM models tend to be organized as many named {@code TurboList} groups. Batching each
	 * group by itself is safe, but it gives away much of the FPS win because many groups are too
	 * small to cross the batch threshold. Instead, this method flattens safe-looking static body
	 * groups into one large explicit body source. The later generated/FVTM render call will skip
	 * only the exact parts drawn here, so bogies and custom detail groups still render normally.</p>
	 *
	 * <p>This is not called from {@link FVTMFormatBase#render(Entity, float, float, float, float, float, float)}
	 * because FVTM subclasses can animate groups during render. The safe boundary is the explicit
	 * rolling-stock prebatch helper, before model render code starts changing matrices or textures.</p>
	 */
	public static boolean renderFVTMGroups(Object owner, FVTMFormatBase model, float scale, boolean rotorder) {
		Context context = ACTIVE.get();
		if (context == null || context.flushing || model == null || model.groups == null) {
			return false;
		}
		List<Entry> entries = new ArrayList<Entry>();
		for (FVTMFormatBase.TurboList group : model.groups) {
			if (group == null || !isSafeFVTMGroupName(group.name)) {
				continue;
			}
			for (ModelRendererTurbo turbo : group) {
				if (isBatchCompatible(turbo) && isSafeFVTMPartName(turbo)) {
					entries.add(new Entry(turbo, scale, rotorder, classify(turbo)));
				}
			}
		}
		if (entries.size() < MIN_BATCH_SIZE) {
			return false;
		}
		for (Entry entry : entries) {
			context.suppressed.add(entry.turbo);
		}
		renderEntries(context, entries);
		return true;
	}

	/**
	 * Batches FVTM models that are rendered from inside another model's custom render code.
	 *
	 * <p>This recovers the fast pre-bogie-fix behavior for BOB details and other nested FVTM
	 * models, but without the old transform bug. The batch is emitted immediately while the
	 * caller's current GL matrix, texture, and light state are still active. Then the normal
	 * FVTM loop runs and skips only the parts drawn by this local batch.</p>
	 *
	 * <p>The suppressions returned from this method are temporary. They must be released when
	 * that specific FVTM render call ends, otherwise the same detail model reused elsewhere in
	 * the same rolling-stock render could disappear.</p>
	 */
	public static ArrayList<ModelRendererTurbo> renderFVTMRuntimeGroups(Object owner, List<FVTMFormatBase.TurboList> groups, float scale, boolean rotorder) {
		Context context = ACTIVE.get();
		ArrayList<ModelRendererTurbo> runtimeSuppressed = new ArrayList<ModelRendererTurbo>();
		if (context == null || context.flushing || groups == null || !ConfigHandler.ENABLE_TMT_MODEL_BATCHING) {
			return runtimeSuppressed;
		}
		List<Entry> entries = new ArrayList<Entry>();
		for (FVTMFormatBase.TurboList group : groups) {
			if (group == null) {
				continue;
			}
			for (ModelRendererTurbo turbo : group) {
				if (!context.suppressed.contains(turbo) && isBatchCompatible(turbo)) {
					entries.add(new Entry(turbo, scale, rotorder, classify(turbo)));
				}
			}
		}
		if (entries.size() < FVTM_RUNTIME_MIN_BATCH_SIZE) {
			return runtimeSuppressed;
		}
		for (Entry entry : entries) {
			context.suppressed.add(entry.turbo);
			runtimeSuppressed.add(entry.turbo);
		}
		renderEntries(context, entries, FVTM_RUNTIME_MIN_BATCH_SIZE, System.identityHashCode(owner), FVTM_RUNTIME_BATCH_INDEX);
		return runtimeSuppressed;
	}

	public static void releaseRuntimeSuppressions(ArrayList<ModelRendererTurbo> runtimeSuppressed) {
		Context context = ACTIVE.get();
		if (context == null || runtimeSuppressed == null || runtimeSuppressed.isEmpty()) {
			return;
		}
		for (ModelRendererTurbo turbo : runtimeSuppressed) {
			context.suppressed.remove(turbo);
		}
	}

	/**
	 * Fast path for large generated {@code ModelConverter.bodyModel} arrays.
	 *
	 * <p>This is the main performance win. Instead of rendering hundreds of body boxes one by
	 * one, we collect the compatible body boxes and draw them as a few larger display lists.
	 * The amount of geometry is mostly the same, but the number of Java/OpenGL calls drops
	 * hard.</p>
	 *
	 * <p>After this, the generated model still runs like normal. The trick is that every body
	 * part drawn here is stored in {@code suppressed}, so when the generated loop reaches that
	 * same Java object, it is skipped. Then {@code suppressOnly} is set so later parts are not
	 * collected. That protects parts whose render position or texture depends on custom code
	 * inside the generated model.</p>
	 *
	 * <p>Even in {@code bodyModel}, some generated parts are not really static body shell. The
	 * rotary snowplow keeps its spinning blade parts in {@code bodyModel} with box name
	 * {@code "rotary"}. Those must stay out of this prebatch so the special rotary renderer can
	 * draw exactly one animated blade set instead of one frozen prebatched copy plus one spinning
	 * copy.</p>
	 */
	public static boolean renderArray(Object owner, ModelRendererTurbo[] model, float scale, boolean rotorder) {
		Context context = ACTIVE.get();
		if (context == null || context.flushing || owner == null || model == null || model.length < MIN_BATCH_SIZE) {
			if (context != null) {
				context.suppressOnly = true;
			}
			return false;
		}
		List<Entry> entries = new ArrayList<Entry>(model.length);
		for (ModelRendererTurbo turbo : model) {
			if (isBatchCompatible(turbo) && isSafeStaticPartName(turbo)) {
				entries.add(new Entry(turbo, scale, rotorder, classify(turbo)));
			}
		}
		if (entries.size() >= MIN_BATCH_SIZE) {
			for (Entry entry : entries) {
				context.suppressed.add(entry.turbo);
			}
			renderEntries(context, entries);
			context.suppressOnly = true;
			return true;
		}
		context.suppressOnly = true;
		return false;
	}

	private static List<StaticBodyField> getStaticBodyFields(Class<?> type) {
		List<StaticBodyField> cached = STATIC_BODY_FIELDS.get(type);
		if (cached != null) {
			return cached;
		}
		List<StaticBodyField> fields = new ArrayList<StaticBodyField>();
		Field bodyModel = findField(type, "bodyModel");
		if (bodyModel != null && bodyModel.getType().isArray() && bodyModel.getType().getComponentType() == ModelRendererTurbo.class) {
			fields.add(new StaticBodyField(bodyModel));
		}
		else {
			addSafeModelArrays(type, fields);
		}
		STATIC_BODY_FIELDS.put(type, fields);
		return fields;
	}

	private static void addSafeModelArrays(Class<?> type, List<StaticBodyField> fields) {
		Class<?> current = type;
		while (current != null) {
			Field[] declared = current.getDeclaredFields();
			for (Field field : declared) {
				if (!field.getType().isArray()
						|| field.getType().getComponentType() != ModelRendererTurbo.class
						|| !isSafeStaticBodyFieldName(field.getName())
						|| containsField(fields, field)) {
					continue;
				}
				field.setAccessible(true);
				fields.add(new StaticBodyField(field));
			}
			current = current.getSuperclass();
		}
	}

	private static boolean containsField(List<StaticBodyField> fields, Field field) {
		for (StaticBodyField existing : fields) {
			if (existing.field.equals(field)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isSafeStaticBodyFieldName(String name) {
		return !containsUnsafeStaticBodyName(name);
	}

	private static boolean isSafeFVTMGroupName(String name) {
		return !containsUnsafeStaticBodyName(name);
	}

	private static boolean isSafeFVTMPartName(ModelRendererTurbo turbo) {
		return isSafeStaticPartName(turbo);
	}

	private static boolean isSafeStaticPartName(ModelRendererTurbo turbo) {
		return turbo == null || !containsUnsafeStaticBodyName(turbo.boxName);
	}

	private static boolean containsUnsafeStaticBodyName(String name) {
		String lower = name == null ? "" : name.toLowerCase();
		if (lower.equals("open")
				|| lower.equals("closed")
				|| lower.equals("rotaryblades")
				|| lower.contains("bogie")
				|| lower.contains("truck")
				|| lower.contains("wheel")
				|| lower.contains("door")
				|| lower.contains("blade")
				|| lower.contains("rotary")
				|| lower.contains("arm")
				|| lower.contains("cargo")
				|| lower.contains("load")
				|| lower.contains("coal")
				|| lower.contains("detail")
				|| lower.contains("overlay")
				|| lower.contains("coupler")
				|| lower.contains("turret")
				|| lower.contains("barrel")
				|| lower.contains("track")
				|| lower.contains("trailer")
				|| lower.contains("steering")) {
			return true;
		}
		return false;
	}

	private static Field findField(Class<?> type, String name) {
		Class<?> current = type;
		while (current != null) {
			try {
				Field field = current.getDeclaredField(name);
				field.setAccessible(true);
				return field;
			}
			catch (NoSuchFieldException ignored) {
				current = current.getSuperclass();
			}
		}
		return null;
	}

	/**
	 * Draws any parts that were collected but not yet emitted.
	 *
	 * <p>This is called before texture changes and at batch end. It is important because a part
	 * collected while texture A is active must not accidentally be drawn after texture B was
	 * bound. Flushing before those boundaries keeps the old visual behavior.</p>
	 */
	public static void flushActive() {
		Context context = ACTIVE.get();
		if (context == null || context.entries.isEmpty()) {
			return;
		}
		List<Entry> entries = context.entries;
		context.entries = new ArrayList<Entry>();
		renderEntries(context, entries);
	}

	/**
	 * Draws a group of collected parts.
	*
	 * <p>Small groups are drawn the old way because making a new display list for a tiny number
	 * of parts is not worth the overhead. Large groups are split by how they need to be drawn:
	 * normal body parts, parts that need culling disabled, and light parts. The shape-box
	 * triangle shading fix is not handled here; it lives in {@link ModelRendererTurbo}, where
	 * each part builds clean batch faces and robust normals.</p>
	 */
	private static void renderEntries(Context context, List<Entry> entries) {
		renderEntries(context, entries, MIN_BATCH_SIZE);
	}

	private static void renderEntries(Context context, List<Entry> entries, int minBatchSize) {
		renderEntries(context, entries, minBatchSize, System.identityHashCode(context.owner), context.flushIndex);
	}

	private static void renderEntries(Context context, List<Entry> entries, int minBatchSize, int cacheOwnerId, int cacheBatchIndex) {
		context.flushing = true;
		try {
			if (entries.size() < minBatchSize) {
				renderImmediate(entries);
			}
			else {
				renderGroup(context, entries, RenderGroup.NORMAL, cacheOwnerId, cacheBatchIndex);
				renderGroup(context, entries, RenderGroup.CULL, cacheOwnerId, cacheBatchIndex);
				renderGroup(context, entries, RenderGroup.LAMP, cacheOwnerId, cacheBatchIndex);
				renderGroup(context, entries, RenderGroup.DITCH, cacheOwnerId, cacheBatchIndex);
				renderGroup(context, entries, RenderGroup.COMMANDER, cacheOwnerId, cacheBatchIndex);
				renderGroup(context, entries, RenderGroup.PRIME1, cacheOwnerId, cacheBatchIndex);
				renderGroup(context, entries, RenderGroup.PRIME2, cacheOwnerId, cacheBatchIndex);
				renderGroup(context, entries, RenderGroup.PRIME3, cacheOwnerId, cacheBatchIndex);
				renderGroup(context, entries, RenderGroup.PRIME4, cacheOwnerId, cacheBatchIndex);
			}
		}
		finally {
			context.flushing = false;
			context.flushIndex++;
		}
	}

	/**
	 * Checks whether a part is safe enough to put in a batch.
	*
	 * <p>This intentionally allows shape boxes. Many of the expensive rolling-stock models are
	 * made mostly from shape boxes; rejecting them would remove most of the FPS gain. The risky
	 * shape-box edge cases are handled inside {@link ModelRendererTurbo} by skipping only faces
	 * with no real area and by fixing bad normals on collapsed wedge faces.</p>
	 */
	private static boolean isBatchCompatible(ModelRendererTurbo turbo) {
		return turbo != null
				&& !turbo.field_1402_i
				&& turbo.showModel
				&& turbo.useLegacyCompiler
				&& !turbo.forcedRecompile
				&& turbo.childModels == null;
	}

	/**
	 * Decides what drawing state a part needs based on its name.
	*
	 * <p>The shape of a lamp can be compiled once, but whether that lamp should glow can change
	 * every frame. That is why light names are split into separate buckets instead of compiling
	 * them all as one generic "lit" group. The geometry is cached; the light state is decided
	 * later when the batch is actually drawn.</p>
	 */
	private static RenderGroup classify(ModelRendererTurbo turbo) {
		RenderGroup cached = GROUP_CACHE.get(turbo);
		if (cached != null) {
			return cached;
		}
		String name = turbo.boxName == null ? "" : turbo.boxName.toLowerCase();
		if (name.contains("cull")) {
			GROUP_CACHE.put(turbo, RenderGroup.CULL);
			return RenderGroup.CULL;
		}
		if (name.contains("lamp")) {
			GROUP_CACHE.put(turbo, RenderGroup.LAMP);
			return RenderGroup.LAMP;
		}
		if (name.contains("ditch")) {
			GROUP_CACHE.put(turbo, RenderGroup.DITCH);
			return RenderGroup.DITCH;
		}
		if (name.contains("commander")) {
			GROUP_CACHE.put(turbo, RenderGroup.COMMANDER);
			return RenderGroup.COMMANDER;
		}
		if (name.contains("prime1")) {
			GROUP_CACHE.put(turbo, RenderGroup.PRIME1);
			return RenderGroup.PRIME1;
		}
		if (name.contains("prime2")) {
			GROUP_CACHE.put(turbo, RenderGroup.PRIME2);
			return RenderGroup.PRIME2;
		}
		if (name.contains("prime3")) {
			GROUP_CACHE.put(turbo, RenderGroup.PRIME3);
			return RenderGroup.PRIME3;
		}
		if (name.contains("prime4")) {
			GROUP_CACHE.put(turbo, RenderGroup.PRIME4);
			return RenderGroup.PRIME4;
		}
		GROUP_CACHE.put(turbo, RenderGroup.NORMAL);
		return RenderGroup.NORMAL;
	}

	/**
	 * Draws one bucket of parts with the temporary GL state it needs.
	*
	 * <p>Some parts need special state. For example, cull parts need backfaces visible, so face
	 * culling is disabled only while that group is drawn. Light parts may need fullbright, so
	 * the Minecraft lightmap is disabled only while those light batches are drawn and only if
	 * the current entity says the light is on. The state is restored afterward so it cannot
	 * leak into trucks, body parts, or the next model.</p>
	 */
	private static void renderGroup(Context context, List<Entry> entries, RenderGroup group, int cacheOwnerId, int cacheBatchIndex) {
		List<Entry> groupEntries = new ArrayList<Entry>();
		for (Entry entry : entries) {
			if (entry.group == group) {
				groupEntries.add(entry);
			}
		}
		if (groupEntries.isEmpty()) {
			return;
		}
		if (group == RenderGroup.CULL) {
			GL11.glDisable(GL11.GL_CULL_FACE);
		}
		else if (isFullbright(context, group)) {
			Minecraft.getMinecraft().entityRenderer.disableLightmap(1D);
		}
		try {
			callCompiledBatch(context, groupEntries, group, cacheOwnerId, cacheBatchIndex);
		}
		finally {
			if (group == RenderGroup.CULL) {
				GL11.glEnable(GL11.GL_CULL_FACE);
			}
			else if (isFullbright(context, group)) {
				Minecraft.getMinecraft().entityRenderer.enableLightmap(1D);
			}
		}
	}

	/**
	 * Decides whether a light part should glow for this specific entity right now.
	*
	 * <p>The display list only stores the lamp's shape. It does not store "on" or "off".
	 * Locomotives that implement {@link IRollingStockLightControls} decide that here every
	 * frame. Models without those controls keep the older behavior where lamp-named parts are
	 * always fullbright, which is needed for some decorative passenger lights.</p>
	 */
	private static boolean isFullbright(Context context, RenderGroup group) {
		if (!group.isLightGroup()) {
			return false;
		}
		if (!(context.entity instanceof IRollingStockLightControls)) {
			return true;
		}
		IRollingStockLightControls lights = (IRollingStockLightControls)context.entity;
		switch (group) {
			case LAMP:
				return lights.isLightsEnabled();
			case DITCH:
				return lights.isDitchLightsEnabled();
			case COMMANDER:
				return lights.isBeaconEnabled()
						&& context.entity instanceof EntityRollingStock
						&& ((EntityRollingStock)context.entity).ticksExisted % 30 == 0;
			case PRIME1:
				return lights.isBeaconEnabled() && lights.getBeaconCycleIndex() == 0;
			case PRIME2:
				return lights.isBeaconEnabled() && lights.getBeaconCycleIndex() == 1;
			case PRIME3:
				return lights.isBeaconEnabled() && lights.getBeaconCycleIndex() == 2;
			case PRIME4:
				return lights.isBeaconEnabled() && lights.getBeaconCycleIndex() == 3;
			default:
				return false;
		}
	}

	/**
	 * Gets the compiled display list for this group, or builds it if it is missing/stale.
	*
	 * <p>The cache key separates model instance, flush number, and render bucket. The signature
	 * then checks whether the same parts and transforms are still being used. If the geometry
	 * or transform data changes, {@link ModelRendererTurbo#batchTransformHash()} must change
	 * too, otherwise an old display list could be reused by mistake.</p>
	 */
	private static void callCompiledBatch(Context context, List<Entry> entries, RenderGroup group) {
		callCompiledBatch(context, entries, group, System.identityHashCode(context.owner), context.flushIndex);
	}

	private static void callCompiledBatch(Context context, List<Entry> entries, RenderGroup group, int cacheOwnerId, int cacheBatchIndex) {
		BatchKey key = new BatchKey(cacheOwnerId, cacheBatchIndex, group);
		long signature = signature(entries);
		CompiledBatch batch = CACHE.get(key);
		if (batch == null || batch.signature != signature) {
			if (batch != null) {
				GL11.glDeleteLists(batch.displayList, 1);
			}
			batch = compile(entries, signature);
			CACHE.put(key, batch);
		}
		GL11.glCallList(batch.displayList);
	}

	/**
	 * Builds one merged display list for a bucket of parts.
	*
	 * <p>Normal quads and triangles are emitted through {@link ModelRendererTurbo#appendBatchGeometry}.
	 * That path uses cleaned batch faces, including the fix for collapsed shape boxes whose
	 * old normals caused dark diagonal triangle edges. Odd polygons that are not quads or
	 * triangles still use the legacy path so unusual model details are not lost.</p>
	 */
	private static CompiledBatch compile(List<Entry> entries, long signature) {
		int displayList = GLAllocation.generateDisplayLists(1);
		GL11.glNewList(displayList, GL11.GL_COMPILE);
		compileMode(entries, GL11.GL_QUADS);
		compileMode(entries, GL11.GL_TRIANGLES);
		for (Entry entry : entries) {
			entry.turbo.renderBatchGeometryRemainder(entry.scale, entry.rotorder);
		}
		GL11.glEndList();
		return new CompiledBatch(displayList, signature);
	}

	/**
	 * Adds either all quad faces or all triangle faces to the display list being built.
	*
	 * <p>This method deliberately asks {@code ModelRendererTurbo} for batch geometry instead
	 * of reading raw polygons here. That keeps the shape-box cleanup and robust normal logic
	 * in one place.</p>
	 */
	private static void compileMode(List<Entry> entries, int mode) {
		Tessellator tessellator = Tessellator.getInstance();
		tessellator.startDrawing(mode);
		for (Entry entry : entries) {
			entry.turbo.appendBatchGeometry(tessellator, entry.scale, entry.rotorder, mode);
		}
		tessellator.draw();
	}

	private static void renderImmediate(List<Entry> entries) {
		for (Entry entry : entries) {
			entry.turbo.render(entry.scale, entry.rotorder);
		}
	}

	/**
	 * Builds a fingerprint for the contents of a batch.
	*
	 * <p>If this fingerprint changes, the cached display list is rebuilt. This prevents stale
	 * geometry when a part's transform, visibility, face count, or cleaned batch-face data is
	 * different from the last compile.</p>
	 */
	static long signature(List<Entry> entries) {
		long result = 1125899906842597L;
		for (Entry entry : entries) {
			result = 31L * result + System.identityHashCode(entry.turbo);
			result = 31L * result + Float.floatToIntBits(entry.scale);
			result = 31L * result + (entry.rotorder ? 1 : 0);
			result = 31L * result + entry.turbo.batchTransformHash();
		}
		return result;
	}

	/**
	 * Buckets for parts that need different temporary draw state.
	*
	 * <p>Do not merge the light buckets back together just because the geometry is static.
	 * The same lamp shape may be drawn fullbright for one entity/frame and normally lit for
	 * another. Keeping these groups separate is what prevents locomotive lights from getting
	 * stuck on.</p>
	 */
	enum RenderGroup {
		NORMAL,
		CULL,
		LAMP,
		DITCH,
		COMMANDER,
		PRIME1,
		PRIME2,
		PRIME3,
		PRIME4;

		boolean isLightGroup() {
			return this != NORMAL && this != CULL;
		}
	}

	/**
	 * Temporary state for one model render.
	*
	 * <p>The most important fields are {@code suppressed} and {@code suppressOnly}. Suppressed
	 * parts were already drawn by the body prebatch and must be skipped when the generated
	 * model reaches them later. Suppress-only mode means "only skip already-drawn body parts;
	 * do not collect anything new." That keeps unknown/custom model sections on the old safe
	 * path.</p>
	 */
	private static final class Context {
		private final Object owner;
		private final Entity entity;
		private int flushIndex;
		private boolean suppressOnly;
		private boolean captureEnabled;
		private boolean flushing;
		private List<Entry> entries = new ArrayList<Entry>();
		private final Set<ModelRendererTurbo> suppressed = Collections.newSetFromMap(new IdentityHashMap<ModelRendererTurbo, Boolean>());

		private Context(Object owner, Entity entity) {
			this.owner = owner;
			this.entity = entity;
		}
	}

	private static final class StaticBodyField {
		private final Field field;

		private StaticBodyField(Field field) {
			this.field = field;
		}

		private ModelRendererTurbo[] get(Object owner) {
			try {
				return (ModelRendererTurbo[])field.get(owner);
			}
			catch (IllegalAccessException ignored) {
				return null;
			}
		}
	}

	static final class Entry {
		final ModelRendererTurbo turbo;
		final float scale;
		final boolean rotorder;
		final RenderGroup group;

		private Entry(ModelRendererTurbo turbo, float scale, boolean rotorder, RenderGroup group) {
			this.turbo = turbo;
			this.scale = scale;
			this.rotorder = rotorder;
			this.group = group;
		}
	}

	private static final class BatchKey {
		private final int ownerId;
		private final int flushIndex;
		private final RenderGroup group;

		private BatchKey(int ownerId, int flushIndex, RenderGroup group) {
			this.ownerId = ownerId;
			this.flushIndex = flushIndex;
			this.group = group;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof BatchKey)) {
				return false;
			}
			BatchKey other = (BatchKey)obj;
			return ownerId == other.ownerId && flushIndex == other.flushIndex && group == other.group;
		}

		@Override
		public int hashCode() {
			int result = ownerId;
			result = 31 * result + flushIndex;
			result = 31 * result + group.hashCode();
			return result;
		}
	}

	private static final class CompiledBatch {
		private final int displayList;
		private final long signature;

		private CompiledBatch(int displayList, long signature) {
			this.displayList = displayList;
			this.signature = signature;
		}
	}
}
