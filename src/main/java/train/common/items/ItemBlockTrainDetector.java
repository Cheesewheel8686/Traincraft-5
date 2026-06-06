package train.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class ItemBlockTrainDetector extends ItemBlock
{
    public ItemBlockTrainDetector(Block block)
    {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced)
    {
        super.addInformation(stack, player, tooltip, advanced);

        tooltip.add(EnumChatFormatting.WHITE + "Detects trains passing over paired tracks.");
        tooltip.add(EnumChatFormatting.YELLOW + "Use: Composite Wrench to pair it to a track.");
        tooltip.add(EnumChatFormatting.AQUA + "Note: Can be placed nearby, not directly underneath.");
        tooltip.add(EnumChatFormatting.GRAY + "Reset: Sneak-use with the wrench to clear pairings.");
        tooltip.add(EnumChatFormatting.GRAY + "Right-click to manage lock settings.");
    }
}
