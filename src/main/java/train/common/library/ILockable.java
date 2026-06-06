package train.common.library;

import net.minecraft.tileentity.TileEntity;
import train.common.entity.TrustedPlayer;

import java.util.List;

public interface ILockable {
    boolean isLocked();
    void setLocked(boolean isLocked);
    TileEntity getLockableTile();
    List<TrustedPlayer> getTrustedList();
    void setTrustedList(List<TrustedPlayer> trustedList);
    void setOwner(String owner);
    String getOwner();
}
