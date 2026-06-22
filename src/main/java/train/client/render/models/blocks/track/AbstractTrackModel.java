package train.client.render.models.blocks.track;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import train.common.library.Info;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public abstract class AbstractTrackModel
{
    public final int getRailDirection(TileTCRail tcRail)
    {
        return tcRail.getWorldObj().getBlockMetadata(tcRail.xCoord, tcRail.yCoord, tcRail.zCoord);
    }

    protected static int getDisplayList(String modelPath)
    {
        return TrackDisplayListCache.get(Info.modelPrefix + modelPath);
    }
}
