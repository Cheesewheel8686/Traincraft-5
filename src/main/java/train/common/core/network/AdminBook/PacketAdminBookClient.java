package train.common.core.network.AdminBook;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import train.common.Traincraft;
import train.common.adminbook.ServerLogger;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PacketAdminBookClient implements IMessage
{
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
            File f = new File(Traincraft.configDirectory.getAbsolutePath() + "/traincraft/" + message.id);
            if (f.exists())
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
                            String document = new String(Files.readAllBytes(Paths.get(Traincraft.configDirectory.getAbsolutePath() + "/traincraft/" + message.id)), "UTF-8");
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
                                    List<ItemStack> items = ServerLogger.getItems(new String(Files.readAllBytes(Paths.get(Traincraft.configDirectory.getAbsolutePath() + "/traincraft/" + message.id)), "UTF-8"));
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
                        }
                    } catch (Exception e){}
                }
                else
                {
                    if(!message.id.equals(""))
                    {
                        sb.append(",");
                    }
                    File[] folder = f.listFiles();
                    if (folder !=null)
                    {
                        for (File file : folder)
                        {
                            if (shouldShowInAdminBook(message.id, file))
                            {
                                sb.append(message.id);
                                if(!message.id.equals(""))
                                {
                                    sb.append("/");
                                }
                                sb.append(file.getName());
                                sb.append(",");
                            }
                        }
                    }
                }
                if(sb.toString().length()<5)
                {
                    return null;
                }
                for (WorldServer world : DimensionManager.getWorlds())
                {
                    if (world.getEntityByID(message.player) != null)
                    {
                        Traincraft.keyChannel.sendTo(new PacketAdminBook(1, -1, sb.toString()), (EntityPlayerMP) world.getEntityByID(message.player));
                        return null;
                    }
                }
            }
            return null;
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
            return file.isFile() && isStockBackupFile(file);
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
                if (file != null && file.isFile() && isStockBackupFile(file))
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
