package train.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import train.common.items.RailVariants;
import train.common.library.track.EnumCoreTrack;
import train.common.library.track.ITrackDefinition;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public final class TrackRenderContext
{
    public final TileTCRail railTile;
    public final ITrackDefinition track;
    public final EnumCoreTrack effectiveCore;
    public final RailVariants variant;
    public final int facing;
    public final double x;
    public final double y;
    public final double z;
    public final float r;
    public final float g;
    public final float b;
    public final float a;

    public TrackRenderContext(TileTCRail railTile, ITrackDefinition track, int facing, double x, double y, double z, float r, float g, float b, float a)
    {
        this(railTile, track, track.getCoreTrack(), facing, x, y, z, r, g, b, a);
    }

    public TrackRenderContext(TileTCRail railTile, ITrackDefinition track, EnumCoreTrack effectiveCore, int facing, double x, double y, double z, float r, float g, float b, float a)
    {
        this.railTile = railTile;
        this.track = track;
        this.effectiveCore = effectiveCore;
        this.variant = track.getVariant();
        this.facing = facing;
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public TrackRenderContext offset(double dx, double dy, double dz)
    {
        return new TrackRenderContext(railTile, track, effectiveCore, facing, x + dx, y + dy, z + dz, r, g, b, a);
    }

    public TrackRenderContext withFacing(int newFacing)
    {
        return new TrackRenderContext(railTile, track, effectiveCore, newFacing, x, y, z, r, g, b, a);
    }
}
