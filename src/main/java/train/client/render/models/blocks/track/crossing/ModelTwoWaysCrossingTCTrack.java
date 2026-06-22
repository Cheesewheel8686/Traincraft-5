package train.client.render.models.blocks.track.crossing;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;
import train.client.render.models.blocks.track.AbstractTrackModel;
import train.common.items.RailVariants;

@SideOnly(Side.CLIENT)
public class ModelTwoWaysCrossingTCTrack extends AbstractTrackModel {
	private final int listTwoWaysCrossing;
	private final int listDoubleDiamondCrossing;
	private final int listDiagonalTwoWaysCrossing;
	private final int listFourWaysCrossing;

	public ModelTwoWaysCrossingTCTrack()
	{
		listTwoWaysCrossing = getDisplayList("track/crossing/standard.obj");
		listDoubleDiamondCrossing = getDisplayList("track/crossing/double_diamond.obj");
		listDiagonalTwoWaysCrossing = getDisplayList("track/crossing/45-deg_standard.obj");
		listFourWaysCrossing = getDisplayList("track/crossing/double_diamond_plus.obj");
	}

	public void renderTwoWays(int facing, RailVariants variants, double x, double y, double z, float r, float g, float b, float a)
	{
		renderList(listTwoWaysCrossing, false, facing, variants, x, y, z, r, g, b, a);
	}

	public void renderDoubleDiamond(int facing, RailVariants variants, double x, double y, double z, float r, float g, float b, float a)
	{
		renderList(listDoubleDiamondCrossing, true, facing, variants, x, y, z, r, g, b, a);
	}

	public void renderDiagonalTwoWays(int facing, RailVariants variants, double x, double y, double z, float r, float g, float b, float a)
	{
		renderList(listDiagonalTwoWaysCrossing, false, facing, variants, x, y, z, r, g, b, a);
	}

	public void renderFourWays(int facing, RailVariants variants, double x, double y, double z, float r, float g, float b, float a)
	{
		renderList(listFourWaysCrossing, false, facing, variants, x, y, z, r, g, b, a);
	}

	private void renderList(int list, boolean rotateDiamond, int facing, RailVariants variants, double x, double y, double z, float r, float g, float b, float a)
	{
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);

		// Bind the texture, so that OpenGL properly textures our block.
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(train.common.enums.TrackResourceLocations.GetResourceLocation(variants));

		GL11.glColor4f(r, g, b, a);
		//GL11.glScalef(0.5f, 0.5f, 0.5f);
		if (rotateDiamond && (facing == 1 || facing == 3))
		{
			GL11.glRotatef(90, 0, 1,0);
		}

		GL11.glCallList(list);
		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}
}
