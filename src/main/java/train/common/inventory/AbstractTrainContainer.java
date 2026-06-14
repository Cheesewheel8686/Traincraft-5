package train.common.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public abstract class AbstractTrainContainer extends Container
{
    protected final void addPlayerInventory(InventoryPlayer inventory)
    {
        addPlayerInventory(inventory, 8, 84, 142);
    }

    protected final void addPlayerInventory(
            InventoryPlayer inventory,
            int startX,
            int inventoryStartY,
            int hotbarY
    ) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlotToContainer(new Slot(
                        inventory,
                        column + row * 9 + 9,
                        startX + column * 18,
                        inventoryStartY + row * 18
                ));
            }
        }

        for (int column = 0; column < 9; column++) {
            addSlotToContainer(new Slot(
                    inventory,
                    column,
                    startX + column * 18,
                    hotbarY
            ));
        }
    }
}
