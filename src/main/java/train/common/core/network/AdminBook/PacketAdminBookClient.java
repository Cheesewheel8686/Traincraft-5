package train.common.core.network.AdminBook;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import train.common.Traincraft;
import train.common.adminbook.ItemAdminBook;
import train.common.adminbook.ServerLogger;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeSet;

public class PacketAdminBookClient implements IMessage
{
    private static final String EMPTY_PAGE = "!empty";
    private String id;
    private int player;

    public PacketAdminBookClient()
    {

    }

    public PacketAdminBookClient(String data, int playerID)
    {
        this.id = data;
        this.player = playerID;
    }

    /**reads the packet on server to get the variables from the Byte Buffer*/
    @Override
    public void fromBytes(ByteBuf bbuf)
    {
        try
        {
            String data = new String(bbuf.array(), "UTF-8");
            if (!data.equals(""))
            {
                player = Integer.parseInt(data.substring(data.indexOf(":") + 1, data.indexOf(",")));
                id = data.substring(data.indexOf(",")+1, data.length());
            }
        }
        catch (UnsupportedEncodingException e)
        {

        }
    }
    /**puts the variables into a Byte Buffer so they can be sent to server*/
    @Override
    public void toBytes(ByteBuf bbuf)
    {
        try
        {
            bbuf.writeBytes((":" + player +"," + id).getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {

        }
    }
    public static class Handler implements IMessageHandler<PacketAdminBookClient, IMessage>
    {
        private static final String VIRTUAL_BACKUP_PREFIX = "backup:";

        @Override
        public IMessage onMessage(PacketAdminBookClient message, MessageContext context)
        {
            int event=0;
            if(message.id.length()>2 && message.id.substring(0,2).equals("0:"))
            {
                message.id = message.id.substring(2, message.id.length());
                event=1;
            }
            if(message.id.length()>2 && message.id.substring(0,2).equals("1:"))
            {
                message.id = message.id.substring(2, message.id.length());
                event=2;
            }
            if(message.id.length()>2 && message.id.substring(0,2).equals("2:"))
            {
                message.id = message.id.substring(2, message.id.length());
                event=3;
            }
            message.id = normalizeBookPath(message.id);
            if (event == 0 && (message.id.equals("") || isVirtualUserPath(message.id)))
            {
                sendAdminBookPage(message.player, message.id);
                return null;
            }
            File f = resolveAdminBookPath(message.id);
            if (f != null && f.exists())
            {
                //if player wasin't looking at a train
                StringBuilder sb = new StringBuilder();
                if (!f.isDirectory())
                {
                    try
                    {
                        if(event==0)
                        {
                            sb.append("<");
                            sb.append(message.id);
                            sb.append(",");
                            sb.append(message.id.substring(0, message.id.indexOf("/") + 1));
                            sb.append(",");
                            String document = new String(Files.readAllBytes(f.toPath()), "UTF-8");
                            sb.append("Delegate:,");
                            sb.append(document.substring(document.indexOf("<delegate>") + 10, document.indexOf("</delegate>")));
                            sb.append(",UUID:,");
                            sb.append(document.substring(document.indexOf("<uuid>") + 6, document.indexOf("</uuid>")).substring(0, 19));
                            sb.append(",");
                            sb.append(document.substring(document.indexOf("<uuid>") + 6, document.indexOf("</uuid>")).substring(19));
                            sb.append(",Last Known Position:,");
                            sb.append("X:,");
                            sb.append(document.substring(document.indexOf("<pos_x>") + 7, document.indexOf("</pos_x>")));
                            sb.append(",");
                            sb.append("Y:,");
                            sb.append(document.substring(document.indexOf("<pos_y>") + 7, document.indexOf("</pos_y>")));
                            sb.append(",");
                            sb.append("Z:,");
                            sb.append(document.substring(document.indexOf("<pos_z>") + 7, document.indexOf("</pos_z>")));
                            if (document.contains("<fuel>"))
                            {
                                sb.append(",Fuel amount: ");
                                sb.append(document.substring(document.indexOf("<fuel>") + 6, document.indexOf("</fuel>")));
                            }
                            if (document.contains("<inventory>"))
                            {
                                sb.append(",");
                                sb.append(document.substring(document.indexOf("<inventory>"), document.indexOf("</inventory>") + 12));
                            }
                        }
                        else if (event==1)
                        {
                            for (WorldServer world : DimensionManager.getWorlds())
                            {
                                if (world.getEntityByID(message.player) != null)
                                {
                                    EntityPlayerMP p = (EntityPlayerMP) world.getEntityByID(message.player);
                                    List<ItemStack> items = ServerLogger.getItems(new String(Files.readAllBytes(f.toPath()), "UTF-8"));
                                    for (ItemStack i : items)
                                    {
                                        if (i.stackSize != 0 && i.getItem() != null)
                                        {
                                            EntityItem entityitem = new EntityItem(world, p.posX, p.posY + 3, p.posZ, i);
                                            entityitem.delayBeforeCanPickup = 120;
                                            if (p.captureDrops)
                                            {
                                                p.capturedDrops.add(entityitem);
                                            }
                                            else
                                            {
                                                world.spawnEntityInWorld(entityitem);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else if (event==2)
                        {
                            if (f.exists())
                            {
                                f.delete();
                            }
                            sendAdminBookPage(message.player, getParentBookPath(message.id));
                            return null;
                        }
                        else if (event==3)
                        {
                            armAdminBookRestore(context.getServerHandler().playerEntity, message.id);
                            return null;
                        }
                    } catch (Exception e){}
                }
                else
                {
                    sb.append(buildAdminBookList(message.id, f));
                }
                if(sb.toString().length()<5)
                {
                    if (!sendAdminBookPage(message.player, ""))
                    {
                        sendAdminBookPayload(message.player, EMPTY_PAGE);
                    }
                    return null;
                }
                sendAdminBookPayload(message.player, sb.toString());
            }
            else if (event == 3)
            {
                armAdminBookRestore(context.getServerHandler().playerEntity, message.id);
            }
            return null;
        }

        private static void armAdminBookRestore(EntityPlayerMP admin, String backupPath)
        {
            if (admin == null || !admin.canCommandSenderUseCommand(2, ""))
            {
                return;
            }
            ItemStack held = admin.getHeldItem();
            if (held == null || !(held.getItem() instanceof ItemAdminBook))
            {
                admin.addChatMessage(new ChatComponentText("Hold the admin book to load a restore target."));
                return;
            }
            File backup = ServerLogger.getAdminBookBackupFile(backupPath);
            if (backup == null || !backup.exists() || !backup.isFile())
            {
                backup = resolveAdminBookPath(backupPath);
            }
            if (backup == null || !backup.exists() || !backup.isFile())
            {
                ItemAdminBook.clearLoadedRestore(held);
                admin.inventoryContainer.detectAndSendChanges();
                admin.addChatMessage(new ChatComponentText("That rolling stock backup no longer exists."));
                return;
            }
            String resolvedPath = ServerLogger.getAdminBookRelativePath(backup);
            ItemAdminBook.setLoadedRestorePath(held, resolvedPath.length() == 0 ? backupPath : resolvedPath);
            admin.inventoryContainer.detectAndSendChanges();
            admin.addChatMessage(new ChatComponentText("Loaded rolling stock restore. Right-click valid track with this book to place it."));
        }

        public static boolean sendAdminBookPage(int playerId, String currentPath)
        {
            currentPath = normalizeBookPath(currentPath);
            if (currentPath.equals(""))
            {
                String payload = buildMergedBackupUserList();
                sendAdminBookPayload(playerId, payload.length() < 5 ? EMPTY_PAGE : payload);
                return true;
            }
            if (isVirtualUserPath(currentPath))
            {
                String payload = buildMergedBackupStockList(currentPath);
                sendAdminBookPayload(playerId, payload.length() < 5 ? EMPTY_PAGE : payload);
                return true;
            }

            File folder = new File(ServerLogger.getAdminBookRootDirectory(), currentPath);
            if (!folder.exists() || !folder.isDirectory())
            {
                return false;
            }

            String payload = buildAdminBookList(currentPath, folder);
            if (payload.length() < 5 && currentPath != null && currentPath.length() > 0)
            {
                return sendAdminBookPage(playerId, "");
            }
            if (payload.length() < 5)
            {
                payload = EMPTY_PAGE;
            }
            sendAdminBookPayload(playerId, payload);
            return true;
        }

        private static void sendAdminBookPayload(int playerId, String payload)
        {
            for (WorldServer world : DimensionManager.getWorlds())
            {
                if (world.getEntityByID(playerId) != null)
                {
                    Traincraft.keyChannel.sendTo(new PacketAdminBook(1, -1, payload), (EntityPlayerMP) world.getEntityByID(playerId));
                    return;
                }
            }
        }

        private static String buildAdminBookList(String currentPath, File folder)
        {
            String normalizedPath = normalizeBookPath(currentPath);
            StringBuilder sb = new StringBuilder();
            if(!normalizedPath.equals(""))
            {
                sb.append(",");
            }
            File[] files = folder.listFiles();
            if (files !=null)
            {
                for (File file : files)
                {
                    if (shouldShowInAdminBook(normalizedPath, file))
                    {
                        if(!normalizedPath.equals(""))
                        {
                            sb.append(normalizedPath);
                            sb.append("/");
                        }
                        sb.append(file.getName());
                        sb.append(",");
                    }
                }
            }
            return sb.toString();
        }

        private static String buildMergedBackupUserList()
        {
            TreeSet<String> users = new TreeSet<String>();
            addUserFolders(users, ServerLogger.getAdminBookRootDirectory(), true);
            addUserFolders(users, ServerLogger.getCurrentSaveBackupDirectory(), false);

            StringBuilder sb = new StringBuilder();
            for (String user : users)
            {
                sb.append(VIRTUAL_BACKUP_PREFIX);
                sb.append(user);
                sb.append(",");
            }
            return sb.toString();
        }

        private static void addUserFolders(TreeSet<String> users, File parent, boolean legacyRoot)
        {
            File[] files = parent.listFiles();
            if (files == null)
            {
                return;
            }
            for (File file : files)
            {
                if (file == null || !file.isDirectory())
                {
                    continue;
                }
                if (legacyRoot && isReservedLegacyRootFolder(file.getName()))
                {
                    continue;
                }
                if (hasStockBackups(file))
                {
                    users.add(file.getName());
                }
            }
        }

        private static boolean isReservedLegacyRootFolder(String folderName)
        {
            return folderName.equalsIgnoreCase("lockout")
                    || folderName.equalsIgnoreCase("adminBookData")
                    || folderName.equalsIgnoreCase("admin_book_backups")
                    || folderName.equalsIgnoreCase(ServerLogger.BACKUP_ROOT_DIRECTORY)
                    || folderName.equalsIgnoreCase(ServerLogger.ADMIN_BACKUP_DIRECTORY);
        }

        private static String buildMergedBackupStockList(String currentPath)
        {
            String user = getVirtualUser(currentPath);
            TreeSet<String> stockFiles = new TreeSet<String>();
            addStockFiles(stockFiles, new File(ServerLogger.getCurrentSaveBackupDirectory(), user));
            addStockFiles(stockFiles, new File(ServerLogger.getAdminBookRootDirectory(), user));

            StringBuilder sb = new StringBuilder();
            sb.append(",");
            for (String fileName : stockFiles)
            {
                sb.append(currentPath);
                sb.append("/");
                sb.append(fileName);
                sb.append(",");
            }
            return sb.toString();
        }

        private static void addStockFiles(TreeSet<String> stockFiles, File folder)
        {
            File[] files = folder.listFiles();
            if (files == null)
            {
                return;
            }
            for (File file : files)
            {
                if (file != null && file.isFile() && isStockBackupFile(file))
                {
                    stockFiles.add(file.getName());
                }
            }
        }

        private static String normalizeBookPath(String path)
        {
            if (path == null)
            {
                return "";
            }
            while (path.endsWith("/") || path.endsWith("\\"))
            {
                path = path.substring(0, path.length() - 1);
            }
            return path;
        }

        private static String getParentBookPath(String path)
        {
            path = normalizeBookPath(path);
            int separator = path.lastIndexOf("/");
            if (separator < 0)
            {
                separator = path.lastIndexOf("\\");
            }
            return separator >= 0 ? path.substring(0, separator) : "";
        }

        private static boolean isVirtualUserPath(String path)
        {
            return path != null && path.startsWith(VIRTUAL_BACKUP_PREFIX) && path.indexOf("/") < 0 && path.indexOf("\\") < 0;
        }

        private static boolean isVirtualStockPath(String path)
        {
            return path != null && path.startsWith(VIRTUAL_BACKUP_PREFIX) && (path.indexOf("/") >= 0 || path.indexOf("\\") >= 0);
        }

        private static String getVirtualUser(String path)
        {
            String value = path.substring(VIRTUAL_BACKUP_PREFIX.length());
            int separator = value.indexOf("/");
            if (separator < 0)
            {
                separator = value.indexOf("\\");
            }
            return separator >= 0 ? value.substring(0, separator) : value;
        }

        private static String getVirtualFileName(String path)
        {
            int separator = Math.max(path.lastIndexOf("/"), path.lastIndexOf("\\"));
            return separator >= 0 ? path.substring(separator + 1) : "";
        }

        private static File resolveAdminBookPath(String path)
        {
            path = normalizeBookPath(path);
            if (isVirtualStockPath(path))
            {
                String user = getVirtualUser(path);
                String fileName = getVirtualFileName(path);
                File file = firstExistingFile(
                        new File(new File(ServerLogger.getCurrentSaveBackupDirectory(), user), fileName),
                        new File(new File(ServerLogger.getAdminBookRootDirectory(), user), fileName)
                );
                return file != null ? file : ServerLogger.getAdminBookBackupFile(path);
            }
            if (isVirtualUserPath(path))
            {
                return null;
            }
            return ServerLogger.getAdminBookBackupFile(path);
        }

        private static File firstExistingFile(File first, File second)
        {
            if (first.exists() && first.isFile())
            {
                return first;
            }
            if (second.exists() && second.isFile())
            {
                return second;
            }
            return null;
        }

        private static void handleContainerEscrow(PacketAdminBookClient message, MessageContext context)
        {
            EntityPlayerMP admin = context.getServerHandler().playerEntity;
            if (admin == null || !admin.canCommandSenderUseCommand(2, ""))
            {
                return;
            }

            String status = "";
            if (message.id.startsWith("tc_container_escrow_collect:"))
            {
                status = collectEscrowRecord(admin, message.id.substring("tc_container_escrow_collect:".length()));
            }
            else if (message.id.startsWith("tc_container_escrow_recover:"))
            {
                status = recoverEscrowRecord(admin, message.id.substring("tc_container_escrow_recover:".length()));
            }
            sendContainerEscrowPage(admin, status);
        }

        private static String recoverEscrowRecord(EntityPlayerMP admin, String recordToken)
        {
            int index;
            try
            {
                index = Integer.parseInt(recordToken);
            }
            catch (NumberFormatException ignored)
            {
                return "Invalid escrow record.";
            }

            ContainerDeathEscrowData data = ContainerDeathEscrowData.get(admin.worldObj);
            List<ContainerDeathEscrowData.Record> records = data.getAllRecords();
            if (index < 0 || index >= records.size())
            {
                return "Escrow record no longer exists.";
            }

            ContainerDeathEscrowData.Record record = records.get(index);
            EntityPlayerMP target = findOnlinePlayer(record.playerName);
            if (target == null || !target.getUniqueID().equals(record.playerId))
            {
                return "Player " + record.playerName + " must be online to receive this container.";
            }

            ItemStack stack = record.stack.copy();
            if (!ContainerCustody.canCarryAnotherContainer(target, stack) || !target.inventory.addItemStackToInventory(stack))
            {
                return "Could not recover for " + record.playerName + ". They need space and cannot already carry a loaded container.";
            }

            data.remove(record);
            target.inventoryContainer.detectAndSendChanges();
            ContainerCustody.sendEscrowLog(target, PacketContainerEscrowLog.ACTION_RECOVERED, record.dimensionId, record.x, record.y, record.z, 1);
            ContainerCustody.sendEscrowWaypoints(target);
            target.addChatMessage(new net.minecraft.util.ChatComponentText("An admin recovered a loaded container from death escrow into your inventory."));
            return "Recovered container for " + record.playerName + ".";
        }

        private static String collectEscrowRecord(EntityPlayerMP admin, String recordToken)
        {
            int index;
            try
            {
                index = Integer.parseInt(recordToken);
            }
            catch (NumberFormatException ignored)
            {
                return "Invalid escrow record.";
            }

            ContainerDeathEscrowData data = ContainerDeathEscrowData.get(admin.worldObj);
            List<ContainerDeathEscrowData.Record> records = data.getAllRecords();
            if (index < 0 || index >= records.size())
            {
                return "Escrow record no longer exists.";
            }

            ContainerDeathEscrowData.Record record = records.get(index);
            ItemStack stack = record.stack.copy();
            if (!ContainerCustody.canCarryAnotherContainer(admin, stack) || !admin.inventory.addItemStackToInventory(stack))
            {
                return "Could not collect record for " + record.playerName + ". You need space and cannot already carry a loaded container.";
            }

            data.remove(record);
            admin.inventoryContainer.detectAndSendChanges();
            ContainerCustody.sendEscrowWaypoints(admin);
            EntityPlayerMP target = findOnlinePlayer(record.playerName);
            if (target != null && target.getUniqueID().equals(record.playerId))
            {
                ContainerCustody.sendEscrowWaypoints(target);
                target.addChatMessage(new net.minecraft.util.ChatComponentText("An admin collected one of your loaded containers from death escrow."));
            }
            return "Collected container for " + record.playerName + " into your inventory.";
        }

        private static EntityPlayerMP findOnlinePlayer(String playerName)
        {
            if (playerName == null)
            {
                return null;
            }
            for (Object playerObject : MinecraftServer.getServer().getConfigurationManager().playerEntityList)
            {
                if (playerObject instanceof EntityPlayerMP)
                {
                    EntityPlayerMP player = (EntityPlayerMP) playerObject;
                    if (player.getCommandSenderName().equalsIgnoreCase(playerName))
                    {
                        return player;
                    }
                }
            }
            return null;
        }

        public static void sendContainerEscrowPage(EntityPlayerMP admin, String status)
        {
            ContainerDeathEscrowData data = ContainerDeathEscrowData.get(admin.worldObj);
            List<ContainerDeathEscrowData.Record> records = data.getAllRecords();
            StringBuilder sb = new StringBuilder();
            sb.append("!containerEscrow,");
            sb.append(clean(status));
            for (int i = 0; i < records.size(); i++)
            {
                ContainerDeathEscrowData.Record record = records.get(i);
                sb.append(",");
                sb.append(clean(record.playerName));
                sb.append("|").append(i);
                sb.append("|").append(record.dimensionId);
                sb.append("|").append(format(record.x));
                sb.append("|").append(format(record.y));
                sb.append("|").append(format(record.z));
                sb.append("|").append(record.timestamp);
                sb.append("|").append(clean(getContainerNumber(record.stack)));
                sb.append("|").append(record.playerId == null ? "" : record.playerId.toString());
            }
            Traincraft.keyChannel.sendTo(new PacketAdminBook(1, -1, sb.toString()), admin);
        }

        private static String getContainerNumber(ItemStack stack)
        {
            return UniversalContainerNBT.getContainerNumber(stack);
        }

        private static String clean(String value)
        {
            return value == null ? "" : value.replace(',', ' ').replace('|', ' ').replace('\n', ' ').replace('\r', ' ').trim();
        }

        private static String format(double value)
        {
            return String.format("%.2f", value);
        }

        private static boolean shouldShowInAdminBook(String currentPath, File file)
        {
            if (file == null)
            {
                return false;
            }
            if (currentPath == null || currentPath.equals(""))
            {
                if (file.getName().equalsIgnoreCase("lockout"))
                {
                    return false;
                }
                return file.isDirectory() && hasStockBackups(file);
            }
            return (file.isDirectory() && hasStockBackups(file)) || (file.isFile() && isStockBackupFile(file));
        }

        private static boolean hasStockBackups(File folder)
        {
            File[] files = folder.listFiles();
            if (files == null)
            {
                return false;
            }
            for (File file : files)
            {
                if (file != null && ((file.isFile() && isStockBackupFile(file)) || (file.isDirectory() && hasStockBackups(file))))
                {
                    return true;
                }
            }
            return false;
        }

        private static boolean isStockBackupFile(File file)
        {
            String name = file.getName();
            int uuidStart = name.lastIndexOf("_") + 1;
            int extensionStart = name.lastIndexOf(".txt");
            return extensionStart == name.length() - 4 && uuidStart > 0 && extensionStart - uuidStart == 36;
        }
    }
}
