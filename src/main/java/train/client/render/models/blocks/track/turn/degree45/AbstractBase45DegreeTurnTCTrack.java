package train.client.render.models.blocks.track.turn.degree45;

import org.lwjgl.opengl.GL11;
import train.client.render.models.blocks.track.AbstractTrackModel;

public abstract class AbstractBase45DegreeTurnTCTrack extends AbstractTrackModel
{
    protected int list3x4_45DegreeTurn = -1;
    protected int list3x6_45DegreeTurn = -1;
    protected int list4x8_45DegreeTurn = -1;
    protected int list5x11_45DegreeTurn = -1;
    protected int list9x20_45DegreeTurn = -1;
    protected int list10x22_45DegreeTurn = -1;

    protected final void bake(String rotation)
    {
        list3x4_45DegreeTurn = getDisplayList("track/curve/45-deg/3x4_" + rotation + ".obj");
        list3x6_45DegreeTurn = getDisplayList("track/curve/45-deg/3x6_" + rotation + ".obj");
        list4x8_45DegreeTurn = getDisplayList("track/curve/45-deg/4x8_" + rotation + ".obj");
        list5x11_45DegreeTurn = getDisplayList("track/curve/45-deg/5x11_" + rotation + ".obj");
        list9x20_45DegreeTurn = getDisplayList("track/curve/45-deg/9x20_" + rotation + ".obj");
        list10x22_45DegreeTurn = getDisplayList("track/curve/45-deg/10x22_" + rotation + ".obj");
    }

    public final void render3x4() { GL11.glCallList(list3x4_45DegreeTurn);}
    public final void render3x6() {GL11.glCallList(list3x6_45DegreeTurn);}
    public final void render4x8() {GL11.glCallList(list4x8_45DegreeTurn);}
    public final void render5x11() {GL11.glCallList(list5x11_45DegreeTurn);}
    public final void render9x20() {GL11.glCallList(list9x20_45DegreeTurn);}
    public final void render10x22(){GL11.glCallList(list10x22_45DegreeTurn);}
}
