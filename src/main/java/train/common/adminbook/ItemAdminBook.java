package train.common.adminbook;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.core.network.AdminBook.PacketAdminBook;
import train.common.library.Info;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <h1>Key Item</h1>
 * the key used to allow people other than the owner to interact with a locked train or rollingstock.
 * @author Eternal Blue Flame
 */
public class ItemAdminBook extends Item {

    public ItemAdminBook(){
        setCreativeTab(Traincraft.tcTab);
        setUnlocalizedName("adminbook");
    }

    /**
     * <h2>Description text</h2>
     * Allows items to add custom lines of information to the mouseover description, by adding new lines to stringList.
     * Each string added defines a new line.
     * We can cover the key and ticket description here, to simplify other classes.
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack thisStack, EntityPlayer player, List stringList, boolean p_77624_4_) {
        stringList.add("This book is for Operators ONLY, and allows the following:");
        stringList.add("- drop trains/rollingstock and their inventory lost during a crash");
        stringList.add("- Lock or unlock trains/rollingstock");
    }
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        try {
            if (world.isRemote) {
                return stack;
            } else if (!player.canCommandSenderUseCommand(2, "")) {
                return stack;
            }

            if (new File(Traincraft.configDirectory.getAbsolutePath() + "/traincraft/").exists()) {
                //if player wasin't looking at a train
                StringBuilder sb = new StringBuilder();
                File[] list = new File(Traincraft.configDirectory.getAbsolutePath() + "/traincraft/").listFiles();
                if (list!=null) {
                    Arrays.sort(list, new compareFile());
                    for (File f : list) {
                        if (f.isDirectory() && f.list() != null && f.list().length > 0) {
                            sb.append(f.getName());
                            sb.append(",");
                        }
                    }
                    //wrong player or something....?
                    Traincraft.keyChannel.sendTo(new PacketAdminBook(1, -1, sb.toString()), (EntityPlayerMP) player);
                }
                return stack;
            } else {
                return stack;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return super.onItemRightClick(stack, world, player);
    }


    class compareFile implements Comparator<File> {
        // Overriding the compare method to sort the age
        public int compare(File d, File d1) {
            return d.getName().toLowerCase().compareTo(d1.getName().toLowerCase());
        }
    }



    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Info.modID.toLowerCase() + ":parts/item_book_black");
    }
}
