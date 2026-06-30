package train.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.library.ItemIDs;

import java.util.List;

public class ItemInterchangeTransferReportBoard extends Item
{
    public static final String NBT_RAILROAD_NAME = "railroadName";
    public static final String DEFAULT_RAILROAD_NAME = "YOUR RAILROAD HERE";

    public static String railroadName;
    public static String railroadAlias;

    public ItemInterchangeTransferReportBoard()
    {
        super();
        setMaxDamage(2);
        maxStackSize = 1;
        setCreativeTab(Traincraft.tcTab);
    }

    public static String getRailroadName(ItemStack stack)
    {
        if (stack != null && stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_RAILROAD_NAME))
        {
            String value = stack.getTagCompound().getString(NBT_RAILROAD_NAME);
            if (value != null && value.trim().length() > 0)
            {
                return value;
            }
        }

        return DEFAULT_RAILROAD_NAME;
    }

    public static void setRailroadName(ItemStack stack, String railroadName)
    {
        if (stack == null)
        {
            return;
        }

        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null)
        {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }

        tag.setString(NBT_RAILROAD_NAME, railroadName == null ? "" : railroadName);
    }

    //@Override
    //public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    //{
    //    NBTTagCompound nbtTagCompound = stack.getTagCompound();
//
    //    if (nbtTagCompound == null)
    //    {
    //        nbtTagCompound = new NBTTagCompound();
    //        stack.setTagCompound(nbtTagCompound);
    //        stack.getTagCompound().setString("railroadName", "Railroad");
    //        stack.getTagCompound().setString("railroadAlias", "Alias");
    //    }
    //    else
    //    {
    //        railroadName = stack.getTagCompound().getString("railroadName");
    //        railroadAlias = stack.getTagCompound().getString("railroadAlias");
    //        //stack.getTagCompound().setString("railroadAlias", railroadAlias);
    //    }
//
    //    return true;
    //}

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (player.isSneaking())
        {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }
        //else
        //{
        //    player.openGui(Traincraft.instance, GuiIDs.INTERCHANGE_TRANSFER_REPORT, world, (int) player.posX, (int) player.posY, (int) player.posZ);
        //}

        return stack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List list, boolean par4)
    {
        list.add("\u00a77" + "Creates a railroad Interchange Transfer Report");
        list.add("\u00a77" + "Railroad: " + getRailroadName(par1ItemStack));
        //list.add("\u00a77" + "Railroad: " + railroadName);
        //list.add("\u00a77" + "RailroadAlias: " + railroadAlias);
    }

    /**
     * If this function returns true (or the item is damageable), the ItemStack's NBT tag will be sent to the client.
     */
    @Override
    public boolean getShareTag() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Info.modID.toLowerCase() + ":" + ItemIDs.interchangeTransferReportBoard.iconName);
    }

}
