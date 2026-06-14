package train.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import train.common.api.EntityRollingStock;
import train.common.api.Tender;
import train.common.slots.SlotTender;
import train.common.slots.SpecialSlots;
import train.common.slots.StandardRollingStockSlot;

public class InventoryTender extends AbstractTrainContainer {

	private Tender tender;
	private InventoryPlayer player;
	private int inventorySize;

	public InventoryTender(InventoryPlayer iinventory, EntityRollingStock entityminecart) {
		player = iinventory;
		tender = (Tender) entityminecart;
		inventorySize = tender.tenderItems.length;
		int i = 1;
		int numCargoSlots = 5;
		SpecialSlots specialSlots = SpecialSlots.getInstance();
		addSlotToContainer(specialSlots.new SlotFilteredLiquid(
				(IInventory) entityminecart, 0, 8, 53, tender
		));

		switch (tender.getStorageMode())
		{
			case DUAL_CHAMBER:
				addSlotToContainer(specialSlots.new SlotFilteredLiquid(
						(IInventory) entityminecart, 1, 8, 18, tender
				));
				addSlotToContainer(new StandardRollingStockSlot((IInventory) entityminecart, 2, 44, 18));
				addSlotToContainer(new StandardRollingStockSlot((IInventory) entityminecart, 3, 44, 36));
				addSlotToContainer(new StandardRollingStockSlot((IInventory) entityminecart, 4, 44, 53));
			break;
			default:
			{
				for (int j = 0; j < numCargoSlots; j++) {
					addSlotToContainer(new SlotTender((IInventory) entityminecart, i, 44 + j * 18, 18));
					i++;
				}
				for (int k = 0; k < numCargoSlots; k++) {
					addSlotToContainer(new SlotTender((IInventory) entityminecart, i, 44 + k * 18, 36));
					i++;
				}
				for (int l = 0; l < numCargoSlots; l++) {
					addSlotToContainer(new SlotTender((IInventory) entityminecart, i, 44 + l * 18, 54));
					i++;
				}
			}
		}

		addPlayerInventory(iinventory);
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return !tender.isDead;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(i);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (i < inventorySize) {
				if (!mergeItemStack(itemstack1, inventorySize, inventorySlots.size(), true)) {
					return null;
				}
			}
			else {
				Slot primaryLiquidSlot = (Slot) inventorySlots.get(0);

				if (primaryLiquidSlot.isItemValid(itemstack1)) {
					if (!mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				}
				else if (tender.isDualChamberMode()
						&& ((Slot) inventorySlots.get(1)).isItemValid(itemstack1)) {
					if (!mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
				}
				else if (!mergeItemStack(
						itemstack1,
						tender.isDualChamberMode() ? 2 : 1,
						inventorySize,
						false
				)) {
					return null;
				}
			}
			if (itemstack1.stackSize == 0) {

				slot.putStack(null);
				if (i < inventorySize) {
					tender.tenderItems[i] = null;
				}
			}
			else {

				slot.onSlotChanged();
			}
		}
		return itemstack;
	}
}
