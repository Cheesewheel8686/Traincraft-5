package tb.dev.rollingstockicon;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * ItemBlock name override for the dev table's inventory/creative tooltip.
 *
 * Minecraft 1.7 item stacks usually ask the ItemBlock for their display name, so the block's
 * localized-name override alone is not enough when no lang entry exists.
 */
public class ItemBlockRollingStockIconGenerator extends ItemBlock {
	public ItemBlockRollingStockIconGenerator(Block block) {
		super(block);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return BlockRollingStockIconGenerator.DISPLAY_NAME;
	}
}
