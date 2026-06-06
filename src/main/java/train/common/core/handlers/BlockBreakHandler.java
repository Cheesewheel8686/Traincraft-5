package train.common.core.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.world.BlockEvent;
import train.common.blocks.BlockSwitchStand;
import train.common.blocks.BlockTrainDetector;
import train.common.entity.TrustedPlayer;
import train.common.items.ItemPadlock;
import train.common.items.ItemWrench;
import train.common.library.ILockable;

public class BlockBreakHandler {
    @SubscribeEvent
    public void onBlockBreakEvent(BlockEvent.BreakEvent breakEvent) {
        if (breakEvent.block instanceof BlockSwitchStand || breakEvent.world.getBlock(breakEvent.x, breakEvent.y + 1, breakEvent.z) instanceof  BlockSwitchStand
                || breakEvent.block instanceof BlockTrainDetector) {
            ILockable lockable = null;
            if (breakEvent.block instanceof BlockSwitchStand | breakEvent.block instanceof BlockTrainDetector) {
                lockable =  ((ILockable) breakEvent.world.getTileEntity(breakEvent.x, breakEvent.y, breakEvent.z));
            } else if (breakEvent.world.getBlock(breakEvent.x, breakEvent.y + 1, breakEvent.z) instanceof BlockSwitchStand) {
                lockable =  ((ILockable) breakEvent.world.getTileEntity(breakEvent.x, breakEvent.y + 1, breakEvent.z));
            }
            if (lockable != null && lockable.isLocked() &&
                    (!(breakEvent.getPlayer().getDisplayName().equalsIgnoreCase(lockable.getOwner())) && !TrustedPlayer.isPlayerTrustedToBreak(breakEvent.getPlayer().getDisplayName(), lockable.getTrustedList()))) {
                if (breakEvent.getPlayer().canCommandSenderUseCommand(2, "") && breakEvent.getPlayer().inventory.getCurrentItem() != null &&
                        (breakEvent.getPlayer().inventory.getCurrentItem().getItem() instanceof ItemPadlock) || breakEvent.getPlayer().inventory.getCurrentItem().getItem() instanceof ItemWrench) {
                    breakEvent.getPlayer().addChatMessage(new ChatComponentText("Broke block owned by " + lockable.getOwner() + " with operator permission."));
                } else {
                    breakEvent.setCanceled(true);
                    breakEvent.getPlayer().addChatMessage(new ChatComponentText("This block is locked by " + lockable.getOwner() + "!"));
                }
            }
        }
    }
}