package train.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public interface TrackRenderRoute
{
    void render(TrackRenderContext context);
}
