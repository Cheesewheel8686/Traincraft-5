package train.client.render.models.blocks.track.turn.degree45;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import train.common.items.RailVariants;

@SideOnly(Side.CLIENT)

public class ModelLeft45DegreeTurnTCTrack extends AbstractBase45DegreeTurnTCTrack
{
    public ModelLeft45DegreeTurnTCTrack()
    {
        bake("left");
    }

    private void beginRender(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a) {

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
            case 0:
                GL11.glRotatef(180, 0, 1, 0);
                GL11.glTranslatef(0.5f,0,0.5f);
                break;
            case 1:
                GL11.glRotatef(90, 0, 1, 0);
                GL11.glTranslatef(0.5f,0,0.5f);
                break;
            case 2:
                GL11.glTranslatef(0.5f,0,0.5f);
                break;
            case 3:
                GL11.glRotatef(-90, 0, 1, 0);
                GL11.glTranslatef(0.5f,0,0.5f);
                break;
        }
    }

    public void render3x4(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a) {
        beginRender(variant, facing, x, y, z, r, g, b, a);
        this.render3x4();
        GL11.glPopMatrix();
    }

    public void render3x6(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a) {
        beginRender(variant, facing, x, y, z, r, g, b, a);
        this.render3x6();
        GL11.glPopMatrix();
    }

    public void render4x8(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a) {
        beginRender(variant, facing, x, y, z, r, g, b, a);
        this.render4x8();
        GL11.glPopMatrix();
    }

    public void render5x11(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a) {
        beginRender(variant, facing, x, y, z, r, g, b, a);
        this.render5x11();
        GL11.glPopMatrix();
    }

    public void render9x20(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a) {
        beginRender(variant, facing, x, y, z, r, g, b, a);
        this.render9x20();
        GL11.glPopMatrix();
    }

    public void render10x22(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a) {
        beginRender(variant, facing, x, y, z, r, g, b, a);
        this.render10x22();
        GL11.glPopMatrix();
    }
}
