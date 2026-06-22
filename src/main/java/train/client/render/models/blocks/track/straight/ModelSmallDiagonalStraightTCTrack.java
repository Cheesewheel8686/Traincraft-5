package train.client.render.models.blocks.track.straight;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import train.client.render.models.blocks.track.AbstractTrackModel;
import train.common.items.RailVariants;

@SideOnly(Side.CLIENT)
public class ModelSmallDiagonalStraightTCTrack extends AbstractTrackModel
{

    private static int listSmallDiagonalStraight = -1;

    public ModelSmallDiagonalStraightTCTrack()
    {
        listSmallDiagonalStraight = getDisplayList("track/straight/1x1_diagonal.obj");
    }

    private void render()
    {
        GL11.glCallList(listSmallDiagonalStraight);
    }

    public void renderDiagonal(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a)
    {
        // Bind the texture, so that OpenGL properly textures our block.
        tmt.Tessellator.bindTexture(train.common.enums.TrackResourceLocations.GetResourceLocation(variants));
        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
        GL11.glTranslatef((float) x + 0f, (float) y, (float) z + 0f);
        GL11.glColor4f(r, g, b, a);

        switch (facing)
        {
            case 4:
            case 6:
                GL11.glTranslatef(0f,0,1f);
                GL11.glRotatef(90, 0, 1,0);
                break;
            case 5:
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
