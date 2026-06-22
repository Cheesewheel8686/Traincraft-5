package train.client.render.models.blocks.track;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import train.common.enums.TrackResourceLocations;
import train.common.items.BallastTypes;
import train.common.items.RailVariants;
import train.common.library.Info;
import train.common.tile.TileTCRail;


@SideOnly(Side.CLIENT)
public class ModelSlopeTCTrack extends AbstractTrackModel
{
    private int listTrack = -1;
    private int listSlopeWood = -1;
    private int listSlopeBallast = -1;

    public ModelSlopeTCTrack(String trackOBJ, String slopeBallastOBJ)
    {
        listTrack = getDisplayList(trackOBJ);
        listSlopeBallast = getDisplayList(slopeBallastOBJ);
    }

    public ModelSlopeTCTrack(String trackOBJ, String slopeWoodSupportOBJ, String slopeBallastOBJ)
    {
        listTrack = getDisplayList(trackOBJ);
        listSlopeWood = getDisplayList(slopeWoodSupportOBJ);
        listSlopeBallast = getDisplayList(slopeBallastOBJ);
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
        tmt.Tessellator.bindTexture(TrackResourceLocations.GetResourceLocation(variants));
        GL11.glCallList(listTrack);
        tmt.Tessellator.bindTexture(DynamicBallastTextureCache.get(ballastTextureInput));
        SetupDynamicBallastColour(ballastColour);
        GL11.glCallList(listSlopeBallast);
        GL11.glColor4f(1, 1, 1, 1);
    }

    public void render(RailVariants variants, BallastTypes ballast)
    {
        tmt.Tessellator.bindTexture(TrackResourceLocations.GetResourceLocation(variants));
        GL11.glCallList(listTrack);

        tmt.Tessellator.bindTexture(TrackResourceLocations.GetBallasetResourceLocation(ballast));
        if (BallastTypes.WOODSUPPORT.equals(ballast))
        {
            GL11.glCallList(listSlopeWood);
        }
        else
        {
            GL11.glCallList(listSlopeBallast);
        }
        GL11.glColor4f(1, 1, 1, 1);
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

    public void render(TileTCRail tcRail, double x, double y, double z)
    {
        render(tcRail.getTrackType().getVariant(), tcRail.getTrackType().getBallastType(), getRailDirection(tcRail), x, y, z, 1, 1, 1, 1);
    }

    private void setupRender(int facing, double x, double y, double z, float r, float g, float b, float a)
    {
        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because the OBJ's origin is the
        // center of the object)
        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);

        GL11.glColor4f(r, g, b, a);
        // GL11.glScalef(0.5f, 0.5f, 0.5f);

        switch (facing) {
            case 0:
                GL11.glRotatef(180,0,1,0);
                break;
            case 7:
                GL11.glTranslatef(-0.5f, 0, -0.5f);
                GL11.glRotatef(180,0,1,0);
                break;
            case 1:
                GL11.glRotatef(90,0,1,0);
                break;
            case 4:
                GL11.glTranslatef(0.5f, 0, -0.5f);
                GL11.glRotatef(90,0,1,0);
                break;
            case 3:
                GL11.glRotatef(-90, 0 , 1, 0);
                break;
            case 6:
                GL11.glTranslatef(-0.5f, 0, 0.5f);
                GL11.glRotatef(-90, 0 , 1, 0);
                break;
            case 5:
                GL11.glTranslatef(0.5f,0,0.5f);
                break;
        }

    }

    public void renderDynamic(RailVariants variants, int facing, double x, double y, double z, float r, float g, float b, float a, String ballastTexture, int colour)
    {
        setupRender(facing, x, y, z, r, g, b, a);

        // GL11.glTranslatef(0.0f, 0.0f, -1.0f);
        renderDynamic(variants, ballastTexture, colour);

        // Pop this matrix from the stack.
        GL11.glPopMatrix();
    }

    public void render(RailVariants variants, BallastTypes ballastType, int facing, double x, double y, double z, float r, float g, float b, float a)
    {
        setupRender(facing, x, y, z, r, g, b, a);

        // GL11.glTranslatef(0.0f, 0.0f, -1.0f);
        render(variants, ballastType);

        // Pop this matrix from the stack.
        GL11.glPopMatrix();
    }
}
