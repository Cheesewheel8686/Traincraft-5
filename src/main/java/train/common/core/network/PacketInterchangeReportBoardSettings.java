package train.common.core.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import train.common.items.ItemInterchangeTransferReportBoard;
import train.common.library.ItemIDs;
import train.common.utils.interchangetransferreport.InterchangeTransferReportGenerator;

public class PacketInterchangeReportBoardSettings implements IMessage
{
    /*
     * Client-to-server board preference sync.
     *
     * Railroad name is edited in GuiInterchangeReport, but the board item owns the persistent value.
     * The slot is sent so the server can update the same board stack that opened the report.
     */
    private int boardSlot;
    private String railroadName;

    public PacketInterchangeReportBoardSettings()
    {

    }

    public PacketInterchangeReportBoardSettings(int boardSlot, String railroadName)
    {
        this.boardSlot = boardSlot;
        this.railroadName = railroadName;
    }

    @Override
    public void fromBytes(ByteBuf bbuf)
    {
        boardSlot = bbuf.readInt();
        railroadName = ByteBufUtils.readUTF8String(bbuf);
    }

    @Override
    public void toBytes(ByteBuf bbuf)
    {
        bbuf.writeInt(boardSlot);
        ByteBufUtils.writeUTF8String(bbuf, railroadName);
    }

    public static class Handler implements IMessageHandler<PacketInterchangeReportBoardSettings, IMessage>
    {
        @Override
        public IMessage onMessage(PacketInterchangeReportBoardSettings message, MessageContext context)
        {
            EntityPlayerMP player = context.getServerHandler().playerEntity;
            ItemStack stack = getBoardStack(player, message.boardSlot);
            if (stack != null)
            {
                ItemInterchangeTransferReportBoard.setRailroadName(stack, InterchangeTransferReportGenerator.limit(message.railroadName, 32));
            }

            return null;
        }

        private ItemStack getBoardStack(EntityPlayerMP player, int slot)
        {
            ItemStack stack = null;
            if (slot >= 0 && slot < player.inventory.mainInventory.length)
            {
                stack = player.inventory.getStackInSlot(slot);
            }
            // Fall back to the equipped item in case inventory layout changed while the GUI was open.
            if (!isReportBoard(stack))
            {
                stack = player.getCurrentEquippedItem();
            }

            return isReportBoard(stack) ? stack : null;
        }

        private boolean isReportBoard(ItemStack stack)
        {
            return stack != null && stack.getItem() == ItemIDs.interchangeTransferReportBoard.item;
        }
    }
}
