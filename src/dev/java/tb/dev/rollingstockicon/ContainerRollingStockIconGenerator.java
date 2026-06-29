package tb.dev.rollingstockicon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import train.common.items.ItemAbstractRollingStock;

public class ContainerRollingStockIconGenerator extends Container {
	public static final int INPUT_SLOT_X = 18;
	public static final int INPUT_SLOT_Y = 32;
	public static final int PLAYER_INV_X = 62;
	public static final int PLAYER_INV_Y = 266;
	public static final int HOTBAR_Y = 324;
	private static final int PLAYER_INVENTORY_ROWS = 3;
	private static final int PLAYER_HOTBAR_SLOTS = 9;
	private static final int SLOT_SIZE = 18;
	private static final int TABLE_SLOT_COUNT = 1;
	private static final int PLAYER_INV_FIRST_SLOT = TABLE_SLOT_COUNT;
	private static final int PLAYER_INV_SLOT_COUNT = PLAYER_INVENTORY_ROWS * PLAYER_HOTBAR_SLOTS;
	private static final int PLAYER_HOTBAR_FIRST_SLOT = PLAYER_INV_FIRST_SLOT + PLAYER_INV_SLOT_COUNT;
	private static final int CONTAINER_SLOT_COUNT = PLAYER_HOTBAR_FIRST_SLOT + PLAYER_HOTBAR_SLOTS;
	private final TileRollingStockIconGenerator tile;

	public ContainerRollingStockIconGenerator(InventoryPlayer inventory, TileRollingStockIconGenerator tile) {
		this.tile = tile;
		addSlotToContainer(new SlotRollingStock(tile, 0, INPUT_SLOT_X, INPUT_SLOT_Y));

		for (int row = 0; row < PLAYER_INVENTORY_ROWS; row++) {
			for (int column = 0; column < PLAYER_HOTBAR_SLOTS; column++) {
				addSlotToContainer(new Slot(inventory, column + row * PLAYER_HOTBAR_SLOTS + PLAYER_HOTBAR_SLOTS, PLAYER_INV_X + column * SLOT_SIZE, PLAYER_INV_Y + row * SLOT_SIZE));
			}
		}

		for (int column = 0; column < PLAYER_HOTBAR_SLOTS; column++) {
			addSlotToContainer(new Slot(inventory, column, PLAYER_INV_X + column * SLOT_SIZE, HOTBAR_Y));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tile.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack result = null;
		Slot slot = (Slot) inventorySlots.get(index);
		if (slot == null || !slot.getHasStack()) {
			return null;
		}

		ItemStack stack = slot.getStack();
		result = stack.copy();

		if (index == 0) {
			if (!mergeItemStack(stack, PLAYER_INV_FIRST_SLOT, CONTAINER_SLOT_COUNT, true)) {
				return null;
			}
		} else if (stack.getItem() instanceof ItemAbstractRollingStock) {
			if (!mergeItemStack(stack, 0, TABLE_SLOT_COUNT, false)) {
				return null;
			}
		} else if (index >= PLAYER_INV_FIRST_SLOT && index < PLAYER_HOTBAR_FIRST_SLOT) {
			if (!mergeItemStack(stack, PLAYER_HOTBAR_FIRST_SLOT, CONTAINER_SLOT_COUNT, false)) {
				return null;
			}
		} else if (index >= PLAYER_HOTBAR_FIRST_SLOT && index < CONTAINER_SLOT_COUNT && !mergeItemStack(stack, PLAYER_INV_FIRST_SLOT, PLAYER_HOTBAR_FIRST_SLOT, false)) {
			return null;
		}

		if (stack.stackSize == 0) {
			slot.putStack(null);
		} else {
			slot.onSlotChanged();
		}

		return stack.stackSize == result.stackSize ? null : result;
	}

	private static class SlotRollingStock extends Slot {
		private SlotRollingStock(TileRollingStockIconGenerator inventory, int slot, int x, int y) {
			super(inventory, slot, x, y);
		}

		@Override
		public boolean isItemValid(ItemStack stack) {
			return stack != null && stack.getItem() instanceof ItemAbstractRollingStock;
		}

		@Override
		public int getSlotStackLimit() {
			return 1;
		}
	}
}
