package tmt;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import train.common.api.EntityRollingStock;
import train.common.api.IRollingStockLightControls;
import train.common.core.handlers.ConfigHandler;

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
	private static final ThreadLocal<Context> ACTIVE = new ThreadLocal<Context>();
	private static final Map<BatchKey, CompiledBatch> CACHE = new HashMap<BatchKey, CompiledBatch>();
	private static final Map<ModelRendererTurbo, RenderGroup> GROUP_CACHE = new IdentityHashMap<ModelRendererTurbo, RenderGroup>();

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
	 *   <li>If neither of those applies, a simple compatible part may be collected and drawn
	 *   later as part of a batch.</li>
	 * </ul>
	 */
	public static boolean capture(ModelRendererTurbo turbo, float scale, boolean rotorder) {
		Context context = ACTIVE.get();
		if (context != null && context.suppressed.contains(turbo)) {
			return true;
		}
		if (context == null || context.suppressOnly || context.flushing || !ConfigHandler.ENABLE_TMT_MODEL_BATCHING) {
			return false;
		}
		if (!isBatchCompatible(turbo)) {
			return false;
		}
		context.entries.add(new Entry(turbo, scale, rotorder, classify(turbo)));
		return true;
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
			if (isBatchCompatible(turbo)) {
				entries.add(new Entry(turbo, scale, rotorder, classify(turbo)));
			}
		}
		if (entries.size() >= MIN_BATCH_SIZE) {
			for (Entry entry : entries) {
				context.suppressed.add(entry.turbo);
			}
			renderEntries(context, entries);
		}
		context.suppressOnly = true;
		return !entries.isEmpty();
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
		context.flushing = true;
		try {
			if (entries.size() < MIN_BATCH_SIZE) {
				renderImmediate(entries);
			}
			else {
				renderGroup(context, entries, RenderGroup.NORMAL);
				renderGroup(context, entries, RenderGroup.CULL);
				renderGroup(context, entries, RenderGroup.LAMP);
				renderGroup(context, entries, RenderGroup.DITCH);
				renderGroup(context, entries, RenderGroup.COMMANDER);
				renderGroup(context, entries, RenderGroup.PRIME1);
				renderGroup(context, entries, RenderGroup.PRIME2);
				renderGroup(context, entries, RenderGroup.PRIME3);
				renderGroup(context, entries, RenderGroup.PRIME4);
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
	private static void renderGroup(Context context, List<Entry> entries, RenderGroup group) {
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
			callCompiledBatch(context, groupEntries, group);
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
		BatchKey key = new BatchKey(System.identityHashCode(context.owner), context.flushIndex, group);
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
		private boolean flushing;
		private List<Entry> entries = new ArrayList<Entry>();
		private final Set<ModelRendererTurbo> suppressed = Collections.newSetFromMap(new IdentityHashMap<ModelRendererTurbo, Boolean>());

		private Context(Object owner, Entity entity) {
			this.owner = owner;
			this.entity = entity;
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
