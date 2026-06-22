package train.client.render.models.blocks.track.straight;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import train.client.render.models.blocks.track.AbstractTrackModel;
import train.client.render.models.blocks.track.DynamicBallastTextureCache;
import train.common.items.RailVariants;
import train.common.library.Info;
import train.common.library.track.ITrackDefinition;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public class ModelSmallStraightTCTrack extends AbstractTrackModel {

	private static final ResourceLocation ROAD_CROSSING_TEXTURE = new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_roadcrossing.png");
	private static final ResourceLocation ROAD_CROSSING_1_TEXTURE = new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_roadcrossing_1.png");
	private static final ResourceLocation ROAD_CROSSING_2_TEXTURE = new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_roadcrossing_2.png");
	private static final ResourceLocation ROAD_CROSSING_BASE_TEXTURE = new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_roadcrossing_base.png");

	private static int listSmallStraight = -1;
	private static int listRoadCrossing = -1;
	private static int listRoadCrossingDynamic = -1;

	public ModelSmallStraightTCTrack()
	{
		listSmallStraight = getDisplayList("track/straight/1x1.obj");
		listRoadCrossing = getDisplayList("track/straight/1x1_crossing.obj");
		listRoadCrossingDynamic = getDisplayList("track/straight/track_roadcrossing_dynamic.obj");
	}

	private void setupRender(int facing, double x, double y, double z, float r, float g, float b, float a)
	{
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);
		GL11.glColor4f(r, g, b, a);

		switch (facing)
		{
			case 3:
				GL11.glRotatef(90, 0, 1, 0);
				break;
			case 1:
				GL11.glRotatef(90, 0, 1, 0);
				break;
		}
	}

	protected void SetupDynamicBallastColour(int ballastColour)
	{
		float r = (float)(ballastColour >> 16 & 255) / 255.0F;
		float g = (float)(ballastColour >> 8 & 255) / 255.0F;
		float b = (float)(ballastColour & 255) / 255.0F;
		GL11.glColor4f(r,g,b,1);
	}

	public void renderDynamic(RailVariants variants, String ballastTextureInput, int ballastColour)
	{
		tmt.Tessellator.bindTexture(ROAD_CROSSING_BASE_TEXTURE);
		GL11.glCallList(listRoadCrossing);
		tmt.Tessellator.bindTexture(DynamicBallastTextureCache.get(ballastTextureInput));
		SetupDynamicBallastColour(ballastColour);
		GL11.glCallList(listRoadCrossingDynamic);
		GL11.glColor4f(1, 1, 1, 1);

	}

	public void renderDynamic(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a, String ballastTexture, int colour)
	{
		setupRender(facing, x, y, z, r, g, b, a);

		// GL11.glTranslatef(0.0f, 0.0f, -1.0f);
		renderDynamic(variants, ballastTexture, colour);

		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}

	public void renderDynamic(TileTCRail tcRail, double x, double y, double z)
	{
		renderDynamic(tcRail.getTrackType().getVariant(), tcRail, getRailDirection(tcRail), x, y, z);
	}

	public void renderDynamic(RailVariants variants, TileTCRail tcRail, int facing, double x, double y, double z)
	{
		String iconName;
		Block block = Block.getBlockById(tcRail.getBallastMaterial());
		IIcon icon = block.getIcon(1, tcRail.ballastMetadata);
		int colour = block.colorMultiplier(tcRail.getWorldObj(), tcRail.xCoord, tcRail.yCoord - 1, tcRail.zCoord);
		if (icon != null) {
			iconName = icon.getIconName();
		}
		else {
			iconName = "tc:ballast_test";
			colour = 16777215;
		}
		renderDynamic(variants, facing, x, y, z, 1, 1, 1, 1, iconName, colour);
	}

	public void renderStraight(ITrackDefinition enumTracks, int facing, double x, double y, double z, float r, float g, float b, float a)
	{
		setupRender(facing, x, y, z, r, g, b, a);
		tmt.Tessellator.bindTexture(train.common.enums.TrackResourceLocations.GetResourceLocation(enumTracks.getVariant()));
		GL11.glCallList(listSmallStraight);
		GL11.glPopMatrix();
	}

	public void renderCrossing(int facing, double x, double y, double z, float r, float g, float b, float a)
	{
		renderFixedCrossing(ROAD_CROSSING_TEXTURE, facing, x, y, z, r, g, b, a);
	}

	public void renderCrossing1(int facing, double x, double y, double z, float r, float g, float b, float a)
	{
		renderFixedCrossing(ROAD_CROSSING_1_TEXTURE, facing, x, y, z, r, g, b, a);
	}

	public void renderCrossing2(int facing, double x, double y, double z, float r, float g, float b, float a)
	{
		renderFixedCrossing(ROAD_CROSSING_2_TEXTURE, facing, x, y, z, r, g, b, a);
	}

	private void renderFixedCrossing(ResourceLocation texture, int facing, double x, double y, double z, float r, float g, float b, float a)
	{
		setupRender(facing, x, y, z, r, g, b, a);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		GL11.glCallList(listRoadCrossing);
		GL11.glPopMatrix();
	}
}
