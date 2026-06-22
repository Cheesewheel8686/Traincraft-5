package train.client.render.models.blocks.track.straight;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;
import train.client.render.models.blocks.track.AbstractTrackModel;
import train.common.items.RailVariants;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public class ModelMediumStraightTCTrack extends AbstractTrackModel {
	private static int listMediumStraight = -1;

	public ModelMediumStraightTCTrack()
	{
		listMediumStraight = getDisplayList("track/straight/1x1.obj");
	}


	private void render() {
		GL11.glCallList(listMediumStraight);
	}

	public void render(TileTCRail tcRail, double x, double y, double z)
	{
		render(tcRail.getTrackType().getVariant(), getRailDirection(tcRail), x, y, z, 1, 1, 1, 1);
	}

	public void render(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a)
	{
		// Bind the texture, so that OpenGL properly textures our block.
		tmt.Tessellator.bindTexture(train.common.enums.TrackResourceLocations.GetResourceLocation(variant));

		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);

		GL11.glColor4f(r, g, b, a);
		//GL11.glScalef(0.5f, 0.5f, 0.5f);

		switch (facing)
		{
			case 3:
				GL11.glRotatef(-90, 0, 1, 0);
			break;
			case 1:
				GL11.glRotatef(90, 0, 1, 0);
			break;
			case 0:
				GL11.glRotatef(180, 0, 1, 0);
			break;
		}

		for (int i = 0; i < 3; i++)
		{
			render();
			GL11.glTranslatef(0.0f, 0.0f, -1.0f);
		}

		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}
}
