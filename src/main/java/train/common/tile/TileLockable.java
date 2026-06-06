package train.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import train.common.entity.TrustedPlayer;
import train.common.library.ILockable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 02skaplan
 * <p>TileLockable contains all functionality needed to implement the ILockable interface and is designed
 * for use by any tile within the mod that needs to be locked. It takes care of </p>
 */
public class TileLockable extends TileEntity implements ILockable {
    private boolean locked = false;
    private String owner = "";
    private List<TrustedPlayer> trustedPlayerList = new ArrayList<>();

    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {
        super.readFromNBT(nbtTag);
        trustedPlayerList.clear();
        locked = nbtTag.getBoolean("locked");
        owner = nbtTag.getString("owner");
        trustedPlayerList = TrustedPlayer.importTrustedListFromNBT(nbtTag, "");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        super.writeToNBT(nbtTag);
        nbtTag.setBoolean("locked", locked);
        nbtTag.setString("owner", owner);
        TrustedPlayer.exportToNBT(trustedPlayerList, nbtTag);
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public void setLocked(boolean isLocked) {
        this.locked = isLocked;
    }

    @Override
    public TileEntity getLockableTile() {
        return this;
    }

    @Override
    public List<TrustedPlayer> getTrustedList() {
        return trustedPlayerList;
    }

    @Override
    public void setTrustedList(List<TrustedPlayer> trustedList) {
        this.trustedPlayerList.clear();
        this.trustedPlayerList.addAll(trustedList);
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String getOwner() {
        return this.owner;
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        if (packet != null){
            this.readFromNBT(packet.func_148857_g());
        }
    }
}
