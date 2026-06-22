package train.client.render.models.blocks.track.s_curve;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;
import train.common.items.RailVariants;

@SideOnly(Side.CLIENT)
public class ModelLeftParallelCurveTCTrack extends AbstractSCurve
{
    public ModelLeftParallelCurveTCTrack()
    {
        bake("left");
    }

    private void setRotation(byte facing)
    {
        switch(facing)
        {
            case 0:
                GL11.glRotatef(180, 0, 1, 0);
                GL11.glTranslatef(0, 0.0f, -2.0f);
                break;
            case 1:
                GL11.glRotatef(90, 0, 1, 0);
                GL11.glTranslatef(-2f, 0.0f, 0f);
                break;
            case 3:
                GL11.glRotatef(-90, 0, 1, 0);
                GL11.glTranslatef(2.0f, 0.0f, 0);
                break;
            default:
                GL11.glTranslatef(0, 0.0f, 2.0f);
            break;
        }
    }

    public void render2x8(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a)
    {
        beginRender(variants, facing, x, y, z, r, g, b, a);
        this.render2x8SCurve();
        GL11.glPopMatrix();
    }

    public void render3x12(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a)
    {
        beginRender(variants, facing, x, y, z, r, g, b, a);
        this.render3x12SCurve();
        GL11.glPopMatrix();
    }

    public void render4x16(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a)
    {
        beginRender(variants, facing, x, y, z, r, g, b, a);
        this.render4x16SCurve();
        GL11.glPopMatrix();
    }

    public void render20x2(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a)
    {
        beginRender(variants, facing, x, y, z, r, g, b, a);
        this.render2x20SCurve();
        GL11.glPopMatrix();
    }

    private void beginRender(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a)
    {

        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z - 1.5f);

        // Bind the texture, so that OpenGL properly textures our block.
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(train.common.enums.TrackResourceLocations.GetResourceLocation(variants));

        GL11.glColor4f(r, g, b, a);
        //GL11.glScalef(0.5f, 0.5f, 0.5f);
        /** where l = 0 is SOUTH
         *        l = 1 is WEST
         *        l = 2 is NORTH
         *        l = 3 is EAST
         */
        setRotation((byte)facing);
    }

}
