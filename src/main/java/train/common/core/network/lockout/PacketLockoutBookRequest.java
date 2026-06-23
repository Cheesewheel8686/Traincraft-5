package train.common.core.network.lockout;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import train.common.Traincraft;
import train.common.utils.lockout.LockoutPermissionsUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PacketLockoutBookRequest implements IMessage
{
    @Override
    public void fromBytes(ByteBuf buf)
    {
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
    }

    public static PacketLockoutBookData buildData(EntityPlayerMP player, String status)
    {
        boolean isAdmin = player.canCommandSenderUseCommand(2, "");
        ArrayList<String> ownedGroups = Traincraft.lockoutPermissionsUtil.GetGroupsOwnedBy(player.getUniqueID());
        ArrayList<String> groups = isAdmin
                ? new ArrayList<String>(Traincraft.lockoutPermissionsUtil.GetLockoutGroupReg().keySet())
                : new ArrayList<String>(ownedGroups);
        java.util.Collections.sort(groups);
        java.util.Collections.sort(ownedGroups);

        HashMap<String, PacketLockoutBookData.UserRecord> membersByUuid = new HashMap<>();
        for (LockoutPermissionsUtil.KnownLockoutUser user : Traincraft.lockoutPermissionsUtil.GetKnownUsers())
        {
            membersByUuid.put(user.uuid.toLowerCase(), new PacketLockoutBookData.UserRecord(user.uuid, user.username));
        }

        ArrayList<PacketLockoutBookData.UserRecord> knownUsers = new ArrayList<>();
        for (Object playerObject : MinecraftServer.getServer().getConfigurationManager().playerEntityList)
        {
            EntityPlayerMP onlinePlayer = (EntityPlayerMP) playerObject;
            PacketLockoutBookData.UserRecord onlineUser = new PacketLockoutBookData.UserRecord(onlinePlayer.getUniqueID().toString(), onlinePlayer.getDisplayName());
            knownUsers.add(onlineUser);
            membersByUuid.put(onlinePlayer.getUniqueID().toString().toLowerCase(), onlineUser);
        }

        java.util.Collections.sort(knownUsers, new java.util.Comparator<PacketLockoutBookData.UserRecord>() {
            @Override
            public int compare(PacketLockoutBookData.UserRecord o1, PacketLockoutBookData.UserRecord o2) {
                return o1.getDisplayName().compareToIgnoreCase(o2.getDisplayName());
            }
        });

        ArrayList<PacketLockoutBookData.UserRecord> possibleMembers = new ArrayList<>(membersByUuid.values());
        HashMap<String, ArrayList<PacketLockoutBookData.UserRecord>> membersByGroup = new HashMap<>();
        for (String group : groups)
        {
            ArrayList<PacketLockoutBookData.UserRecord> members = new ArrayList<>();
            for (PacketLockoutBookData.UserRecord user : possibleMembers)
            {
                try
                {
                    if (Traincraft.lockoutPermissionsUtil.IsUserMemberOfGroup(UUID.fromString(user.uuid), group))
                    {
                        members.add(user);
                    }
                }
                catch (Exception e)
                {
                    Traincraft.tcLog.info(e.getMessage());
                }
            }
            membersByGroup.put(group, members);
        }

        return new PacketLockoutBookData(groups, ownedGroups, knownUsers, membersByGroup, status);
    }

    public static class Handler implements IMessageHandler<PacketLockoutBookRequest, IMessage>
    {
        @Override
        public IMessage onMessage(PacketLockoutBookRequest message, MessageContext ctx)
        {
            Traincraft.lockoutCommChannel.sendTo(buildData(ctx.getServerHandler().playerEntity, ""), ctx.getServerHandler().playerEntity);
            return null;
        }
    }
}
