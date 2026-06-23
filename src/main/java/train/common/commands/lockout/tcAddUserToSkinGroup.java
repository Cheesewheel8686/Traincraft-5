package train.common.commands.lockout;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import train.common.Traincraft;

import java.util.List;
import java.util.UUID;

public class tcAddUserToSkinGroup extends CommandBase
{
    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_)
    {
        return true;
    }

    @Override
    public String getCommandName()
    {
        return "addUserToSkinGroup";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/addUserToSkinGroup [user] [group]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] strings)
    {
        if (strings.length <= 0)
        {
            throw new WrongUsageException("Invalid Input", new Object[0]);
        }

        EntityPlayerMP test = getPlayer(sender, strings[0]);

        UUID userID = test.getUniqueID();

        if (strings.length <= 1)
        {
            throw new WrongUsageException("Invalid Group", new Object[0]);
        }

        boolean isAdmin = sender.canCommandSenderUseCommand(2, "");
        UUID actorID = isAdmin ? null : getPlayer(sender, sender.getCommandSenderName()).getUniqueID();
        Traincraft.lockoutPermissionsUtil.AddUserToGroupManaged(actorID, isAdmin, test.getDisplayName(), userID.toString(), strings[1]);
        sender.addChatMessage(new ChatComponentText("Lockout: Added User to group."));
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        if (p_71516_2_.length == 1)
        {
            return getListOfStringsMatchingLastWord(p_71516_2_, this.getAllUsernames());
        }
        else if (p_71516_2_.length == 2)
        {
            return getListOfStringsMatchingLastWord(p_71516_2_, Traincraft.lockoutPermissionsUtil.GetAllLockoutGroupNames());
        }

        return null;
    }

    protected String[] getAllUsernames()
    {
        return MinecraftServer.getServer().getAllUsernames();
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     */
    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
    {
        return p_82358_2_ == 0;
    }
}
