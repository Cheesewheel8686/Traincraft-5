package train.client.render.models.blocks.track.turn.degree90;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;
import train.common.items.RailVariants;

@SideOnly(Side.CLIENT)
public class ModelLeftTurnTCTrack extends AbstractBase90DegreeTurnTCTrack
{
	public ModelLeftTurnTCTrack() {
		super();
	}

	private void setRotation(byte facing)
	{
		switch(facing)
		{
			case 0:
				GL11.glRotatef(180, 0, 1, 0);
				break;
			case 1:
				GL11.glRotatef(90, 0, 1, 0);
				break;
			case 2:
				GL11.glRotatef(0, 0, 1, 0);
				break;
			case 3:
				GL11.glRotatef(-90, 0, 1, 0);
				break;
			default:
			break;
		}
	}

	private void beginRender(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a) {
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
		GL11.glTranslatef((float) x + 1.5f, (float) y, (float) z + 5.5f);

		// Bind the texture, so that OpenGL properly textures our block.
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(train.common.enums.TrackResourceLocations.GetResourceLocation(variants));

		GL11.glColor4f(r, g, b, a);
		//GL11.glScalef(0.5f, 0.5f, 0.5f);


		setRotation((byte)facing);
	}

	private void applySmallTurnTranslation(int facing)
	{
		switch (facing)
		{
			case 3:
				GL11.glTranslatef(-5.5f,0,1.5f);
				break;
			case 1:
				GL11.glTranslatef(4.5f,0,-0.5f);
				break;
			case 2:
				GL11.glTranslatef(-1.5f,0,-4.5f);
				break;
			default:
				GL11.glTranslatef(0.5f,0,5.5f);
				break;
		}
	}

	public void render1x(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a) {
		beginRender(variants, facing, x, y, z, r, g, b, a);
		applySmallTurnTranslation(facing);
		render1X();
		GL11.glPopMatrix();
	}

	private void applyMediumTurnTranslation(int facing)
	{
				switch (facing)
				{
					case 3:
						GL11.glTranslatef(-8.0f, 0.0f, 2.0f);
						break;
					case 1:
						GL11.glTranslatef(2.0f, 0.0f, 0.0f);
						break;
					case 2:
						GL11.glTranslatef(-4.0f, 0.0f, -4.0f);
						break;
					default:
						GL11.glTranslatef(-2.0f, 0.0f, 6.0f);
						break;
				}
	}

	public void render3x(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a) {
		beginRender(variants, facing, x, y, z, r, g, b, a);
		applyMediumTurnTranslation(facing);
		this.renderMedium();
		GL11.glPopMatrix();
	}

	public void render5x(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a) {
		beginRender(variants, facing, x, y, z, r, g, b, a);
		applySmallTurnTranslation(facing);
		this.renderLarge();
		GL11.glPopMatrix();
	}

	public void render10x(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a) {
		beginRender(variants, facing, x, y, z, r, g, b, a);
		applySmallTurnTranslation(facing);
		this.renderVeryLarge();
		GL11.glPopMatrix();
	}

	public void render16x(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a) {
		beginRender(variants, facing, x, y, z, r, g, b, a);
		applySmallTurnTranslation(facing);
		this.renderSuperLarge();
		GL11.glPopMatrix();
	}

	public void render29x(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a) {
		beginRender(variants, facing, x, y, z, r, g, b, a);
		applySmallTurnTranslation(facing);
		render29X();
		GL11.glPopMatrix();
	}

	public void render32x(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a) {
		beginRender(variants, facing, x, y, z, r, g, b, a);
		applySmallTurnTranslation(facing);
		render32X();
		GL11.glPopMatrix();
	}
}
