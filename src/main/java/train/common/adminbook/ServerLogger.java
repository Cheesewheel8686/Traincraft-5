package train.common.adminbook;

import cpw.mods.fml.common.registry.GameData;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import train.common.Traincraft;
import train.common.api.EntityRollingStock;
import train.common.api.LiquidTank;
import train.common.items.ItemAbstractRollingStock;
import train.common.utils.TrainSaveLifecycleLogger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author EternalBlueFlame
 */
public class ServerLogger {
    public static final String BACKUP_ROOT_DIRECTORY = "backups";
    public static final String ADMIN_BACKUP_DIRECTORY = "rollingstockbackup";

    /*
    --------------------------------------------------
    Writing
    --------------------------------------------------
     */

    //run this on server side when inventory opens or player mounts, maybe other common but not constant events.
    public static void writeWagonToFolder(EntityRollingStock wagon){
        try {
            //make a stringbuilder to build the filename, faster than string+string+string+string etc. MUCH faster.
            File backupFile = getWagonBackupFile(wagon);
            if (backupFile.getParentFile() != null && !backupFile.getParentFile().exists()){
                backupFile.getParentFile().mkdirs();
            }
            //gen the file with the path
            FileOutputStream fileoutputstream = new FileOutputStream(backupFile);
            //reset the string builder then add all the data in an XML seeming format.
            //you don't HAVE to do XML, you can use other formats and use libraries, i just like this way because its easy to read.
            StringBuilder sb = new StringBuilder();
            sb.append("<xmlRoot>\n   <uuid>");
            sb.append(wagon.getUniqueID());
            sb.append("</uuid>\n   <delegate>");
            sb.append(wagon.getCartItem().getItem().delegate.name());
            sb.append("</delegate>\n   <cartItemNbt>");
            sb.append(writeItemStackToBase64(buildCartItemBackup(wagon)));
            sb.append("</cartItemNbt>\n   <pos_x>");
            sb.append(wagon.posX);
            sb.append("</pos_x>\n   <pos_y>");
            sb.append(wagon.posY);
            sb.append("</pos_y>\n   <pos_z>");
            sb.append(wagon.posZ);
            sb.append("</pos_z>\n    <inventory>\n");
            //cover inventory from a loop of an external function to simplify code
            if (wagon instanceof IInventory) {
                if (wagon.getInventory() != null) {
                    for (ItemStack stack : wagon.getInventory()) {
                        addItemXML(sb, stack);
                    }
                }
                if (wagon instanceof LiquidTank) {
                    for (FluidTankInfo tank : ((LiquidTank)wagon).getTankInfo(ForgeDirection.UNKNOWN)) {
                        addFluidXML(sb, tank.fluid);
                    }
                }
                sb.append("   </inventory>");
            }
            sb.append("\n</xmlRoot>");//seemingly unnecessary new line added to the end, linux needs this sometimes.
            fileoutputstream.write(sb.toString().getBytes());
            fileoutputstream.close();
        } catch (Exception e){
            //apparently we don't have permission, so, nevermind.

        }
    }

    //run this on attack entity event if the entity dies
    public static void deleteWagon(EntityRollingStock wagon){
        File f = getWagonBackupFile(wagon);
        boolean existed = f.exists() && !f.isDirectory();
        boolean deleted = false;
        try {
            if (existed) {
                deleted = f.delete();
            }
            TrainSaveLifecycleLogger.log("ADMIN_BACKUP_DELETE", wagon, "path=" + f.getAbsolutePath() + " existed=" + existed + " deleted=" + deleted + " deathDim=" + wagon.dimension + " deathPos=(" + wagon.posX + "," + wagon.posY + "," + wagon.posZ + ") deathChunk=(" + wagon.chunkCoordX + "," + wagon.chunkCoordZ + ")");
        } catch (Exception e){
            TrainSaveLifecycleLogger.log("ADMIN_BACKUP_DELETE_FAILED", wagon, "path=" + f.getAbsolutePath() + " existed=" + existed + " exception=" + e.getClass().getName() + " deathDim=" + wagon.dimension + " deathPos=(" + wagon.posX + "," + wagon.posY + "," + wagon.posZ + ") deathChunk=(" + wagon.chunkCoordX + "," + wagon.chunkCoordZ + ")");
        }//if it fails there was nothing to delete, so same result
    }

    public static File getAdminBookRootDirectory()
    {
        return new File(Traincraft.configDirectory.getAbsolutePath() + "/traincraft/");
    }

    public static File getAdminBookBackupFile(String path)
    {
        try
        {
            File root = getAdminBookRootDirectory().getCanonicalFile();
            File file = new File(root, path == null ? "" : path).getCanonicalFile();
            return file.getPath().startsWith(root.getPath()) ? file : null;
        }
        catch (IOException e)
        {
            return null;
        }
    }

    public static String getAdminBookRelativePath(File file)
    {
        try
        {
            File root = getAdminBookRootDirectory().getCanonicalFile();
            File canonicalFile = file.getCanonicalFile();
            String rootPath = root.getPath();
            String filePath = canonicalFile.getPath();
            if (!filePath.startsWith(rootPath))
            {
                return "";
            }
            String relative = filePath.substring(rootPath.length());
            while (relative.startsWith(File.separator))
            {
                relative = relative.substring(1);
            }
            return relative.replace(File.separatorChar, '/');
        }
        catch (IOException e)
        {
            return "";
        }
    }

    public static File getRollingStockBackupDirectory()
    {
        return new File(getBackupRootDirectory(), ADMIN_BACKUP_DIRECTORY);
    }

    public static File getBackupRootDirectory()
    {
        return new File(getAdminBookRootDirectory(), BACKUP_ROOT_DIRECTORY);
    }

    public static File getCurrentSaveBackupDirectory()
    {
        return new File(getRollingStockBackupDirectory(), getSaveFolderName());
    }

    private static File getWagonBackupFile(EntityRollingStock wagon)
    {
        return new File(getWagonBackupDirectory(wagon), getWagonBackupFileName(wagon));
    }

    private static File getWagonBackupDirectory(EntityRollingStock wagon)
    {
        return new File(getCurrentSaveBackupDirectory(), getOwnerFolderName(wagon));
    }

    private static String getWagonBackupFileName(EntityRollingStock wagon)
    {
        return wagon.getCartItem().getItem().delegate.name().replace(":", "~").toLowerCase() + "_" + wagon.getUniqueID().toString().toLowerCase() + ".txt";
    }

    private static String getOwnerFolderName(EntityRollingStock wagon)
    {
        if(wagon.getOwner() != null && wagon.getOwner().getName()!=null && !wagon.getOwner().getName().equals("")){
            return sanitizePathPart(wagon.getOwner().getName().toLowerCase());
        } else if (wagon.getTransportOwner() !=null && !wagon.getTransportOwner().equals("")){
            return sanitizePathPart(wagon.getTransportOwner().toLowerCase());
        }
        return "unknown_player";
    }

    private static String getSaveFolderName()
    {
        File saveRoot = DimensionManager.getCurrentSaveRootDirectory();
        if (saveRoot != null && saveRoot.getName() != null && saveRoot.getName().length() > 0)
        {
            return sanitizePathPart(saveRoot.getName().toLowerCase());
        }
        return "unknown_world";
    }

    private static String sanitizePathPart(String value)
    {
        return value == null ? "" : value.replaceAll("[^a-zA-Z0-9._-]", "_");
    }



    private static void addItemXML(StringBuilder string, ItemStack item){
        if (item == null || item.getItem() == null || item.stackSize<=0){
            return;
        }
        string.append("        <ItemStack>\n            <ID>");
        string.append(Item.getIdFromItem(item.getItem()));
        string.append("</ID>\n            <delegate>");
        string.append(item.getItem().delegate.name());
        string.append("</delegate>\n            <meta>");
        string.append(item.getItemDamage());
        string.append("</meta>\n            <StackSize>");
        string.append(item.stackSize);
        string.append("</StackSize>\n            <itemNbt>");
        string.append(writeItemStackToBase64(item));
        string.append("</itemNbt>\n        </ItemStack>\n");
    }

    private static void addFluidXML(StringBuilder string, FluidStack item){
        if (item == null || item.getFluid() == null || item.amount<=0){
            return;
        }

        int fill=1000;
        while(fill < item.amount) {
         addItemXML(string, FluidContainerRegistry.fillFluidContainer(item, new ItemStack(Items.bucket)));
         fill +=1000;
        }
    }




    /*
    --------------------------------------------------
    Reading
    --------------------------------------------------
     */

    //parses the document for itemstacks
    public static List<ItemStack> getItems(String doc){
        try {
            ArrayList<ItemStack> itemStacks = new ArrayList<ItemStack>();
            ItemStack cartStack = parseCartItemFromXML(doc);
            if (cartStack != null)
            {
                itemStacks.add(cartStack);
            }

            List<String> stacks = new ArrayList<String>();
            while (doc.contains("<ItemStack>")){
                stacks.add(doc.substring(doc.indexOf("<ItemStack>")+11, doc.indexOf("</ItemStack>")));
                doc = doc.substring(doc.indexOf("</ItemStack>")+12);
            }


            for (String s : stacks){
                ItemStack stack = parseItemFromXML(s);
                if (stack != null){
                    itemStacks.add(stack);
                }
            }
            return itemStacks;

        } catch (Exception e){
            return new ArrayList<ItemStack>();
        }
    }

    private static ItemStack parseCartItemFromXML(String doc) {
        ItemStack stack = parseItemNbtTag(doc, "cartItemNbt");
        if (stack != null) {
            return stack;
        }
        try {
            return new ItemStack(GameData.getItemRegistry().getObject(doc.substring(doc.indexOf("<delegate>") + 10, doc.indexOf("</delegate>"))));
        } catch (Exception e) {
            return null;
        }
    }

    //parses the individual item
    public static ItemStack parseItemFromXML(String doc){
        ItemStack stackFromNbt = parseItemNbtTag(doc, "itemNbt");
        if (stackFromNbt != null) {
            return stackFromNbt;
        }
        try {
            ItemStack stack = new ItemStack(
                    GameData.getItemRegistry().getObject(doc.substring(doc.indexOf("<delegate>")+10, doc.indexOf("</delegate>"))),//get item by delegate name since it's static
                    Integer.parseInt(doc.substring(doc.indexOf("<StackSize>")+11, doc.indexOf("</StackSize>")))//we always get strings so gotta parse.
            );

            stack.setItemDamage(Integer.parseInt(doc.substring(doc.indexOf("<meta>")+6, doc.indexOf("</meta>"))));

            return stack;
        } catch (Exception e){
            return null;
        }
    }

    private static ItemStack buildCartItemBackup(EntityRollingStock wagon) {
        try {
            for (ItemStack item : wagon.getItemsDropped()) {
                if (item != null && item.getItem() instanceof ItemAbstractRollingStock) {
                    ItemStack stack = ItemAbstractRollingStock.setPersistentData(item, wagon, wagon.getUniqueTrainID(), wagon.trainOwner, wagon.trainCreator, wagon.getColor(), wagon.trainNote);
                    NBTTagCompound tag = getOrCreateTag(stack);
                    wagon.exportTrustedListToNBT(tag);
                    if (wagon.getCargoManager() != null && wagon.getCargoManager().getSelectedCargo() != 0) {
                        tag.setInteger("cargoSelection", wagon.getCargoManager().getSelectedCargo());
                    }
                    if (wagon.acceptsOverlayTextures() && wagon.getOverlayTextureContainer() != null && wagon.getOverlayTextureContainer().hasActiveOverlays()) {
                        tag.setTag("overlayTextureConfigTag", wagon.getOverlayTextureContainer().getOverlayConfigTag());
                    }
                    return stack;
                }
            }
        } catch (Exception e) {
        }
        return wagon.getCartItem();
    }

    private static NBTTagCompound getOrCreateTag(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }
        return tag;
    }

    private static String writeItemStackToBase64(ItemStack stack) {
        if (stack == null || stack.getItem() == null || stack.stackSize <= 0) {
            return "";
        }
        try {
            NBTTagCompound itemTag = new NBTTagCompound();
            stack.writeToNBT(itemTag);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            CompressedStreamTools.writeCompressed(itemTag, bytes);
            return Base64.getEncoder().encodeToString(bytes.toByteArray());
        } catch (Exception e) {
            return "";
        }
    }

    private static ItemStack parseItemNbtTag(String doc, String tagName) {
        try {
            String startTag = "<" + tagName + ">";
            String endTag = "</" + tagName + ">";
            if (!doc.contains(startTag) || !doc.contains(endTag)) {
                return null;
            }
            String encoded = doc.substring(doc.indexOf(startTag) + startTag.length(), doc.indexOf(endTag)).trim();
            if (encoded.length() == 0) {
                return null;
            }
            byte[] bytes = Base64.getDecoder().decode(encoded);
            NBTTagCompound itemTag = CompressedStreamTools.readCompressed(new ByteArrayInputStream(bytes));
            return ItemStack.loadItemStackFromNBT(itemTag);
        } catch (Exception e) {
            return null;
        }
    }



}
