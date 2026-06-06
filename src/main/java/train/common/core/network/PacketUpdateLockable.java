package train.common.core.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import train.common.entity.TrustedPlayer;
import train.common.library.ILockable;

import java.util.ArrayList;
import java.util.List;

public class PacketUpdateLockable implements IMessage {

    boolean isLocked;
    int tileX;
    int tileY;
    int tileZ;
    List<TrustedPlayer> trustedList = new ArrayList<>();

    /**
     * <p>Empty constructor is VERY NECESSARY. Do not REMOVE or Forge will get very angry!</p>
     */
    public PacketUpdateLockable(){}

    /**
     * <p>Client -> Server communication packet to update lock and trusted list for lockables.</p>
     * <p>Used to update the server to changes to the lock status of locked lockables.</p>
     * <p>Server handles syncing from server -> client by use of getDescriptionPacket() S35TileEntityUpdate packet.</p>
     * @param isLocked Locked status. True for locked, false for unlocked.
     * @param trustedList Trusted players list.
     * @param lockable A tile implementing the lockable interface.
     */
    public PacketUpdateLockable(boolean isLocked, List<TrustedPlayer> trustedList, ILockable lockable) {
        this.isLocked = isLocked;
        this.trustedList = trustedList;
        TileEntity lockableTile = lockable.getLockableTile();
        tileX = lockableTile.xCoord;
        tileY = lockableTile.yCoord;
        tileZ = lockableTile.zCoord;
    }

    @Override
    public void fromBytes(ByteBuf bbuf) {
        isLocked = bbuf.readBoolean();
        tileX = bbuf.readInt();
        tileY = bbuf.readInt();
        tileZ = bbuf.readInt();
        if (bbuf.readBoolean()) {
            NBTTagCompound nbtTagCompound = ByteBufUtils.readTag(bbuf);
            if (nbtTagCompound.hasKey("trustedList")) {
                NBTTagList trustedList = nbtTagCompound.getTagList("trustedList", Constants.NBT.TAG_COMPOUND);
                for (int i = 0; i < trustedList.tagCount(); i++) {
                    this.trustedList.add(new TrustedPlayer(trustedList.getCompoundTagAt(i).getString("playerName"), trustedList.getCompoundTagAt(i).getBoolean("breakAccess")));
                }
            }
        }
    }

    @Override
    public void toBytes(ByteBuf bbuf) {
        bbuf.writeBoolean(isLocked);
        bbuf.writeInt(tileX);
        bbuf.writeInt(tileY);
        bbuf.writeInt(tileZ);
        bbuf.writeBoolean(!trustedList.isEmpty());
        if (!trustedList.isEmpty()) {
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            TrustedPlayer.exportToNBT(trustedList, nbtTagCompound);
            ByteBufUtils.writeTag(bbuf, nbtTagCompound);
        }
    }

    public static class Handler implements IMessageHandler<PacketUpdateLockable, IMessage> {

        @Override
        public IMessage onMessage(PacketUpdateLockable message, MessageContext context) {
            // Always runs on the server side as defined in the packet handler.
            TileEntity tile = context.getServerHandler().playerEntity.worldObj.getTileEntity(message.tileX, message.tileY, message.tileZ);
            ILockable lockable = ((ILockable) tile);
            lockable.setLocked(message.isLocked);
            lockable.setTrustedList(message.trustedList);
            /* By marking the block for update and marking the TE dirty, we can force the server to handle the return message
            through its default method of S35TileEntityUpdate packets. */
            context.getServerHandler().playerEntity.worldObj.markBlockForUpdate(message.tileX, message.tileY, message.tileZ);
            tile.markDirty();
            return null;
        }
    }
}