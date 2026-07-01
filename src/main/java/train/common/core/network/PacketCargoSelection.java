package train.common.core.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import train.common.Traincraft;
import train.common.api.EntityRollingStock;

/**
 * @author 02skaplan
 * <p>Client to Server Sync Packet for Cargo Changes</p>
 */
public class PacketCargoSelection implements IMessage {

    int cargoSelection;
    int	entityID;
    int dimensionID;


    public PacketCargoSelection() {} // Do not remove default constructor! Needed or Forge will get very mad.

    /**
     * @author 02skaplan
     * <p>Server <-> client communication packet for cargo information.</p>
     * <p><b>This constructor is used to send information back and forth!</b></p>
     * @param cargoSelection Number of selected cargo.
     * @param trainEntity Entity ID of rolling stock; recommended to retrieve by invoking rollingStock.getEntityId().
     * @param dimensionID Dimension ID of player world; recommended to retrieve by accessing Minecraft.getMinecraft().thePlayer.worldObj.provider.dimensionId.
     */
    public PacketCargoSelection(int cargoSelection, int trainEntity, int dimensionID) {
        this.cargoSelection = cargoSelection;
        this.entityID = trainEntity;
        this.dimensionID = dimensionID;
    }

    @Override
    public void fromBytes(ByteBuf bbuf) {
        entityID = bbuf.readInt();
        dimensionID = bbuf.readInt();
        cargoSelection = bbuf.readInt();
    }

    @Override
    public void toBytes(ByteBuf bbuf) {
        bbuf.writeInt(entityID);
        bbuf.writeInt(dimensionID);
        bbuf.writeInt(cargoSelection);
    }

    public static class Handler implements IMessageHandler<PacketCargoSelection, IMessage> {
        @Override
        public IMessage onMessage(PacketCargoSelection message, MessageContext context) {
            Entity rollingStockEntity;
            // If on the server side, distribute the packet to clients before updating local changes.
            if (context.side == Side.SERVER) { // I think it is necessary to grab the world through the server handler if the context side is server. Don't quote me on that, but it doesn't work without it.
                rollingStockEntity = context.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityID);
                Traincraft.cargoSelectionChannel.sendToAllAround(new PacketCargoSelection(message.cargoSelection, message.entityID, message.dimensionID), new NetworkRegistry.TargetPoint(message.dimensionID, rollingStockEntity.posX, rollingStockEntity.posY, rollingStockEntity.posZ, 256D));
            } else {
                rollingStockEntity = Minecraft.getMinecraft().theWorld.getEntityByID(message.entityID);
            }
            if (rollingStockEntity instanceof EntityRollingStock) {
                if (((EntityRollingStock) rollingStockEntity).getCargoManager().isValidCargoSelection(message.cargoSelection)) {
                    ((EntityRollingStock) rollingStockEntity).getCargoManager().setSelectedCargo(message.cargoSelection);
                }
            }
            return null;
        }
    }
}
