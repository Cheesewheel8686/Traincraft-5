package tb.dev.rollingstockicon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import train.common.items.ItemAbstractRollingStock;

public class TileRollingStockIconGenerator extends TileEntity implements IInventory {
	private ItemStack stack;

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return slot == 0 ? stack : null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (slot != 0 || stack == null) {
			return null;
		}

		if (stack.stackSize <= amount) {
			ItemStack result = stack;
			stack = null;
			markDirty();
			return result;
		}

		ItemStack result = stack.splitStack(amount);
		if (stack.stackSize == 0) {
			stack = null;
		}
		markDirty();
		return result;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (slot != 0 || stack == null) {
			return null;
		}

		ItemStack result = stack;
		stack = null;
		return result;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		if (slot == 0) {
			stack = itemStack;
			if (stack != null && stack.stackSize > getInventoryStackLimit()) {
				stack.stackSize = getInventoryStackLimit();
			}
			markDirty();
		}
	}

	@Override
	public String getInventoryName() {
		return "RollingStockIconGenerator";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj != null
				&& worldObj.getTileEntity(xCoord, yCoord, zCoord) == this
				&& player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		return slot == 0 && itemStack != null && itemStack.getItem() instanceof ItemAbstractRollingStock;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		stack = null;
		NBTTagList list = tag.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		if (list.tagCount() > 0) {
			NBTTagCompound itemTag = list.getCompoundTagAt(0);
			stack = ItemStack.loadItemStackFromNBT(itemTag);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		NBTTagList list = new NBTTagList();
		if (stack != null) {
			NBTTagCompound itemTag = new NBTTagCompound();
			itemTag.setByte("Slot", (byte) 0);
			stack.writeToNBT(itemTag);
			list.appendTag(itemTag);
		}
		tag.setTag("Items", list);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}
}
