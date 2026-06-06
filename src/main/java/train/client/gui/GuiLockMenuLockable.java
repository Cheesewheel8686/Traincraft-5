package train.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import train.common.Traincraft;
import train.common.core.network.PacketUpdateLockable;
import train.common.library.ILockable;

/**
 * @author 02skaplan
 * <p>Lock and Trusted Players Menu</p>
 * <p>Allows players to lock and unlock a piece of rolling stock and add & remove trusted individuals from using the rolling stock.</p>
 */
@SideOnly(Side.CLIENT)
public class GuiLockMenuLockable extends GuiLockMenuAbstract {
    private final ILockable lockable;

    /**
     * @author 02skaplan
     */
    public GuiLockMenuLockable(EntityPlayer editingPlayer, ILockable lockable) {
        super(editingPlayer);
        this.lockable = lockable;
        currentTrustees.addAll(lockable.getTrustedList());
    }

    @Override
    protected void updateButtons() {
        super.updateButtons();
        closeAndSavetoAll.showButton = false;
        closeAndSavetoAll.visible = false;
    }
    @Override
    public boolean getLocked() {
        return lockable.isLocked();
    }

    @Override
    public void setLocked(boolean locked) {
        lockable.setLocked(locked);
    }

    @Override
    public void sendUpdatePacket(boolean propagate) {
        Traincraft.switchStandLockChannel.sendToServer(new PacketUpdateLockable(lockable.isLocked(), exportTrustedPlayers(), lockable));
    }
}
