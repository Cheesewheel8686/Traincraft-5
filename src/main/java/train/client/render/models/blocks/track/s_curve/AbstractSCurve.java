package train.client.render.models.blocks.track.s_curve;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import train.client.render.models.blocks.track.AbstractTrackModel;

@SideOnly(Side.CLIENT)
public abstract class AbstractSCurve extends AbstractTrackModel
{
    protected int list2x8SCurve = -1;
    protected int list3x12SCurve = -1;
    protected int list4x16SCurve = -1;
    protected int list2x20SCurve = -1;

    protected final void bake(String rotation)
    {
        list2x8SCurve = getDisplayList("track/curve/s/2x8_" + rotation + ".obj");
        list3x12SCurve = getDisplayList("track/curve/s/3x12_" + rotation + ".obj");
        list4x16SCurve = getDisplayList("track/curve/s/4x16_" + rotation + ".obj");
        list2x20SCurve = getDisplayList("track/curve/s/20x2_" + rotation + ".obj");
    }

    public void render2x8SCurve() {GL11.glCallList(list2x8SCurve);}
    public void render3x12SCurve() {GL11.glCallList(list3x12SCurve);}
    public void render4x16SCurve() {GL11.glCallList(list4x16SCurve);}
    public void render2x20SCurve() { GL11.glCallList(list2x20SCurve); }
}
