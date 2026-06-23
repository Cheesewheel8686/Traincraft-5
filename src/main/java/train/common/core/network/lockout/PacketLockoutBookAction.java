package train.common.core.network.lockout;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import train.common.Traincraft;

public class PacketLockoutBookAction implements IMessage
{
    public static final int ACTION_ADD = 0;
    public static final int ACTION_REMOVE = 1;

    private int action;
    private String group;
    private String targetUuid;
    private String targetName;

    public PacketLockoutBookAction()
    {
    }

    public PacketLockoutBookAction(int action, String group, String targetUuid, String targetName)
    {
        this.action = action;
        this.group = group == null ? "" : group.toUpperCase();
        this.targetUuid = targetUuid == null ? "" : targetUuid;
        this.targetName = targetName == null ? "" : targetName;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        action = buf.readInt();
        group = ByteBufUtils.readUTF8String(buf);
        targetUuid = ByteBufUtils.readUTF8String(buf);
        targetName = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(action);
        ByteBufUtils.writeUTF8String(buf, group);
        ByteBufUtils.writeUTF8String(buf, targetUuid);
        ByteBufUtils.writeUTF8String(buf, targetName);
    }

    public static class Handler implements IMessageHandler<PacketLockoutBookAction, IMessage>
    {
        @Override
        public IMessage onMessage(PacketLockoutBookAction message, MessageContext ctx)
        {
            EntityPlayerMP sender = ctx.getServerHandler().playerEntity;
            String status;
            String group = message.group.toUpperCase();

            try
            {
                boolean isAdmin = sender.canCommandSenderUseCommand(2, "");
                if (message.action == ACTION_ADD)
                {
                    Traincraft.lockoutPermissionsUtil.AddUserToGroupManaged(sender.getUniqueID(), isAdmin, message.targetName, message.targetUuid, group);
                    status = "Added " + displayName(message) + " to " + group + ".";
                }
                else if (message.action == ACTION_REMOVE)
                {
                    Traincraft.lockoutPermissionsUtil.RemoveUserFromGroupManaged(sender.getUniqueID(), isAdmin, message.targetName, message.targetUuid, group);
                    status = "Removed " + displayName(message) + " from " + group + ".";
                }
                else
                {
                    status = "Unknown lockout action.";
                }
            }
            catch (Exception e)
            {
                status = cleanStatus(e);
            }

            Traincraft.lockoutCommChannel.sendTo(PacketLockoutBookRequest.buildData(sender, status), sender);
            return null;
        }

        private String displayName(PacketLockoutBookAction message)
        {
            return message.targetName.trim().length() == 0 ? message.targetUuid : message.targetName;
        }

        private String cleanStatus(Exception e)
        {
            if (e.getMessage() == null)
            {
                return "Lockout update failed.";
            }

            String message = e.getMessage();
            return message.startsWith("Lockout: ") ? message.substring("Lockout: ".length()) : message;
        }
    }
}
