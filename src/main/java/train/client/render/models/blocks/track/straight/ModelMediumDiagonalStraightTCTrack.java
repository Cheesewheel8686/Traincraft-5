package train.client.render.models.blocks.track.straight;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import train.client.render.models.blocks.track.AbstractTrackModel;
import train.common.items.RailVariants;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public class ModelMediumDiagonalStraightTCTrack extends AbstractTrackModel {

    private static int listMediumDiagonalStraight = -1;

    public ModelMediumDiagonalStraightTCTrack()
    {
        listMediumDiagonalStraight = getDisplayList("track/straight/1x3_diagonal.obj");
    }

    public void render() {
        GL11.glCallList(listMediumDiagonalStraight);
    }

    public void render(TileTCRail tcRail, double x, double y, double z) {
        render(tcRail, getRailDirection(tcRail), x, y, z, 1, 1, 1, 1 );
    }

    public void render(TileTCRail tcRail, int facing, double x, double y, double z, float r, float g, float b, float a )
    {
        render(tcRail.getTrackType().getVariant(), facing, x, y, z, r, g, b, a);
    }

    public void render(RailVariants variant, int facing, double x, double y, double z, float r, float g, float b, float a )
    {
        // Bind the texture, so that OpenGL properly textures our block.
        tmt.Tessellator.bindTexture(train.common.enums.TrackResourceLocations.GetResourceLocation(variant));
        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
        GL11.glTranslatef((float) x + 0f, (float) y, (float) z + 0f);
        GL11.glColor4f(r, g, b, a);

        switch (facing)
        {
            case 4:
                GL11.glTranslatef(1f,0,0f);
                GL11.glRotatef(-90, 0, 1,0);
            break;
            case 5:
                GL11.glTranslatef(1f,0,1f);
                GL11.glRotatef(180, 0, 1,0f);
            break;
            case 6:
                GL11.glTranslatef(0f,0,1f);
                GL11.glRotatef(90, 0, 1,0);
            break;
            case 7:
                GL11.glTranslatef(0f,0,0f);
                GL11.glRotatef(0, 0, 1,0f);
            break;
        }

        render();

        // Pop this matrix from the stack.
        GL11.glPopMatrix();
    }

}
