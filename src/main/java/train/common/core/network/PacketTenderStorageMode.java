package train.common.core.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import train.common.api.Tender;
import train.common.api.stock.TenderStorageMode;

public class PacketTenderStorageMode implements IMessage
{
    private int entityId;
    private int modeId;

    public PacketTenderStorageMode()
    {
    }

    public PacketTenderStorageMode(int entityId, TenderStorageMode mode)
    {
        this.entityId = entityId;
        this.modeId = mode.getId();
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        entityId = buffer.readInt();
        modeId = buffer.readInt();
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeInt(entityId);
        buffer.writeInt(modeId);
    }

    public static class Handler implements IMessageHandler<PacketTenderStorageMode, IMessage>
    {
        @Override
        public IMessage onMessage(PacketTenderStorageMode message, MessageContext context)
        {
            EntityPlayerMP player = context.getServerHandler().playerEntity;
            Entity entity = player.worldObj.getEntityByID(message.entityId);

            if (!(entity instanceof Tender) || player.getDistanceSqToEntity(entity) > 64.0D)
            {
                return null;
            }

            Tender tender = (Tender) entity;

            if (!tender.canPlayerChangeStorageMode(player))
            {
                player.addChatMessage(new ChatComponentText(
                        tender.isStorageModeSwitchAvailable()
                                ? "You cannot change this tender's storage mode."
                                : "The tender can only be switched within 15 seconds of being placed."
                ));
                return null;
            }

            TenderStorageMode requestedMode = TenderStorageMode.fromId(message.modeId);

            if (!tender.setStorageMode(requestedMode))
            {
                player.addChatMessage(new ChatComponentText(
                        "Empty the tender inventory and secondary fuel tank before changing storage mode."
                ));
            }

            player.closeScreen();
            return null;
        }
    }
}
