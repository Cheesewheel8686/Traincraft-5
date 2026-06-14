package train.common.slots;

import net.minecraft.item.ItemStack;

public interface IFluidContainerSlotValidator
{
    boolean isContainerValidForInputSlot(int slot, ItemStack stack);
}
