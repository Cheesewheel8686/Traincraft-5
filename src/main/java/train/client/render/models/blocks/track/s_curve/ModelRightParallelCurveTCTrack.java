package train.client.render.models.blocks.track.s_curve;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;
import train.common.items.RailVariants;

@SideOnly(Side.CLIENT)
public class ModelRightParallelCurveTCTrack extends AbstractSCurve {
    public ModelRightParallelCurveTCTrack() 
    {
        bake("right");
    }

    public void render2x8(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a)
    {
        beginRender(variant, facing, x, y, z, r, g, b, a);
        this.render2x8SCurve();
        GL11.glPopMatrix();
    }

    public void render3x12(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a)
    {
        beginRender(variant, facing, x, y, z, r, g, b, a);
        this.render3x12SCurve();
        GL11.glPopMatrix();
    }

    public void render4x16(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a)
    {
        beginRender(variant, facing, x, y, z, r, g, b, a);
        this.render4x16SCurve();
        GL11.glPopMatrix();
    }

    public void render20x2(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a)
    {
        beginRender(variant, facing, x, y, z, r, g, b, a);
        this.render2x20SCurve();
        GL11.glPopMatrix();
    }

    private void beginRender(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a)
    {

        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
        GL11.glTranslatef((float) x + 1.5f, (float) y, (float) z + 5.5f);

        // Bind the texture, so that OpenGL properly textures our block.
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(train.common.enums.TrackResourceLocations.GetResourceLocation(variant));

        GL11.glColor4f(r, g, b, a);
        //GL11.glScalef(0.5f, 0.5f, 0.5f);
        /** where l = 0 is SOUTH
         *        l = 1 is WEST
         *        l = 2 is NORTH
         *        l = 3 is EAST
         */
        switch (facing)
        {
            case 0:
                GL11.glRotatef(180, 0, 1, 0);
                GL11.glTranslatef(1, 0.0f, 5);
                break;
            case 1:
                GL11.glRotatef(90, 0, 1, 0);
                GL11.glTranslatef(5, 0.0f, - 1);
                break;
            case 2:
                GL11.glRotatef(0, 0, 1, 0);
                GL11.glTranslatef(-1,0.0f, - 5);
                break;
            case 3:
                GL11.glRotatef(-90, 0, 1, 0);
                GL11.glTranslatef(-5, 0.0f, 1);
                break;
        }
    }

}
