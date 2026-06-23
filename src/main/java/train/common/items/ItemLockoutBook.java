package train.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.GuiIDs;
import train.common.library.Info;

import java.util.List;

public class ItemLockoutBook extends Item
{
    public ItemLockoutBook()
    {
        super();
        maxStackSize = 1;
        setCreativeTab(Traincraft.tcTab);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        player.openGui(Traincraft.instance, GuiIDs.LOCKOUT_BOOK, world, (int) player.posX, (int) player.posY, (int) player.posZ);
        return stack;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced)
    {
        list.add(EnumChatFormatting.GRAY + "Manage skin lockout groups you own.");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(Info.modID.toLowerCase() + ":parts/item_book_red");
    }
}
