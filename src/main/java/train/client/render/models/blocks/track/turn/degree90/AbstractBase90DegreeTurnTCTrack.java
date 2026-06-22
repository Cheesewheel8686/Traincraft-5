package train.client.render.models.blocks.track.turn.degree90;

import org.lwjgl.opengl.GL11;
import train.client.render.models.blocks.track.AbstractTrackModel;

public abstract class AbstractBase90DegreeTurnTCTrack extends AbstractTrackModel
{
    public AbstractBase90DegreeTurnTCTrack()
    {
        list1XTurn = getDisplayList("track/curve/90-deg/1x1.obj");
        list3x3Turn = getDisplayList("track/curve/90-deg/3x3.obj");
        list5x5Turn = getDisplayList("track/curve/90-deg/5x5.obj");
        list10x10Turn = getDisplayList("track/curve/90-deg/10x10.obj");
        list16x16Turn = getDisplayList("track/curve/90-deg/16x16.obj");
        list29x29Turn = getDisplayList("track/curve/90-deg/29x29.obj");
        list32x32Turn = getDisplayList("track/curve/90-deg/32x32.obj");
    }

    protected static int list1XTurn = -1;
    protected static int list3x3Turn = -1;
    protected static int list5x5Turn = -1;
    protected static int list10x10Turn = -1;
    protected static int list16x16Turn = -1;
    protected static int list29x29Turn = -1;
    protected  static int list32x32Turn = -1;

    public final void render1X()
    {
        GL11.glCallList(list1XTurn);
    }
    public final void renderMedium()
    {
        GL11.glCallList(list3x3Turn);
    }
    public final void renderLarge()
    {
        GL11.glCallList(list5x5Turn);
    }
    public final void renderVeryLarge()
    {
        GL11.glCallList(list10x10Turn);
    }
    public final void renderSuperLarge()
    {
        GL11.glCallList(list16x16Turn);
    }
    public final void render29X()
    {
        GL11.glCallList(list29x29Turn);
    }
    public final void render32X()
    {
        GL11.glCallList(list32x32Turn);
    }
}
