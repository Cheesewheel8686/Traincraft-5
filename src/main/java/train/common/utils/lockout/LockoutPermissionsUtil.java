package train.common.utils.lockout;

import com.google.gson.*;
import cpw.mods.fml.common.Loader;
import train.common.Traincraft;
import train.common.enums.LockoutGroup;

import java.io.FileReader;
import java.util.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LockoutPermissionsUtil
{
    private String LockoutFolder = null;

    private final String LockoutGroups = "LockoutGroups";
    private final String LockoutUsers = "LockoutUsers";

    private HashMap<String, ILockoutGroup> lockGroupsReg = new HashMap<>();

    public HashMap<String, ILockoutGroup> GetLockoutGroupReg()
    {
        return lockGroupsReg;
    }

    public void AddLockGroup(ILockoutGroup lockoutGroup)
    {
        lockGroupsReg.put(lockoutGroup.name().toUpperCase(), lockoutGroup);
    }

    public void AddLockGroups(ILockoutGroup[] lockoutGroups)
    {
        for (ILockoutGroup lockoutGroup : lockoutGroups)
        {
            lockGroupsReg.put(lockoutGroup.name().toUpperCase(), lockoutGroup);
        }
    }

    public LockoutPermissionsUtil()
    {
        LockoutFolder = Loader.instance().getConfigDir() + File.separator + "traincraft" + File.separator + "Lockout";

        AddLockGroup(LockoutGroup.DEFAULT);
        AddLockGroup(LockoutGroup.SPR);
        for (LockoutGroup group : LockoutGroup.values())
        {
            AddLockGroup(group);
        }
    }

    public String[] GetAllLockoutGroupNames()
    {
        String[] lockoutGroups = new String[lockGroupsReg.size()];
        return new ArrayList<String>(lockGroupsReg.keySet()).toArray(lockoutGroups);
    }

    public ArrayList<String> GetGroupsOwnedBy(UUID uuid)
    {
        ArrayList<String> ownedGroups = new ArrayList<>();
        String uuidString = uuid.toString().trim();
        for (String group : lockGroupsReg.keySet())
        {
            if (GetGroupOwner(group).trim().equalsIgnoreCase(uuidString))
            {
                ownedGroups.add(group);
            }
        }
        Collections.sort(ownedGroups);
        return ownedGroups;
    }

    public ArrayList<KnownLockoutUser> GetKnownUsers()
    {
        HashMap<String, KnownLockoutUser> knownUsers = new HashMap<>();
        File usersFolder = new File(LockoutFolder + File.separator + LockoutUsers);
        File[] files = usersFolder.listFiles();
        if (files != null)
        {
            for (File file : files)
            {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".json"))
                {
                    try (FileReader fileReader = new FileReader(file))
                    {
                        JsonObject jsonObject = Traincraft.jsonParser.parse(fileReader).getAsJsonObject();
                        String uuid = jsonObject.get("uuid").getAsString();
                        String username = jsonObject.has("username") ? jsonObject.get("username").getAsString() : "";
                        knownUsers.put(uuid.toLowerCase(), new KnownLockoutUser(uuid, username));
                    }
                    catch (Exception e)
                    {
                        Traincraft.tcLog.info(e.getMessage());
                    }
                }
            }
        }

        ArrayList<KnownLockoutUser> users = new ArrayList<>(knownUsers.values());
        Collections.sort(users, new Comparator<KnownLockoutUser>() {
            @Override
            public int compare(KnownLockoutUser o1, KnownLockoutUser o2) {
                return o1.getDisplayName().compareToIgnoreCase(o2.getDisplayName());
            }
        });
        return users;
    }

    public boolean isValidGroup(String key)
    {
        return lockGroupsReg.get(key.toUpperCase()) != null;
    }

    public boolean CanManageGroup(UUID actorUuid, boolean isAdmin, String group)
    {
        if (isAdmin)
        {
            return true;
        }

        if (actorUuid == null || group == null || isValidGroup(group) == false)
        {
            return false;
        }

        return GetGroupOwner(group).trim().equalsIgnoreCase(actorUuid.toString().trim());
    }

    public void AddUserToGroupManaged(UUID actorUuid, boolean isAdmin, String targetName, String targetUuid, String group)
    {
        String normalizedGroup = ValidateManagedGroup(actorUuid, isAdmin, group);
        String normalizedTargetUuid = ValidateManagedTargetUuid(targetUuid);
        AddUserToGroup(targetName, normalizedTargetUuid, normalizedGroup);
    }

    public void RemoveUserFromGroupManaged(UUID actorUuid, boolean isAdmin, String targetName, String targetUuid, String group)
    {
        String normalizedGroup = ValidateManagedGroup(actorUuid, isAdmin, group);
        String normalizedTargetUuid = ValidateManagedTargetUuid(targetUuid);
        String owner = GetGroupOwner(normalizedGroup);
        if (owner.trim().equalsIgnoreCase(normalizedTargetUuid))
        {
            throw new ProjectLockoutErrorException("The group owner cannot be removed from their lockout group.");
        }

        RemoveUserFromGroup(targetName, normalizedTargetUuid, normalizedGroup);
    }

    private String ValidateManagedGroup(UUID actorUuid, boolean isAdmin, String group)
    {
        String normalizedGroup = group == null ? "" : group.toUpperCase();
        if (isValidGroup(normalizedGroup) == false)
        {
            throw new ProjectLockoutErrorException("Invalid lockout group.");
        }

        if (CanManageGroup(actorUuid, isAdmin, normalizedGroup) == false)
        {
            throw new ProjectLockoutErrorException("You do not own that lockout group.");
        }

        return normalizedGroup;
    }

    private String ValidateManagedTargetUuid(String targetUuid)
    {
        try
        {
            return UUID.fromString(targetUuid).toString();
        }
        catch (Exception e)
        {
            throw new ProjectLockoutErrorException("Invalid lockout user.");
        }
    }

    public String GetGroupOwner(String key)
    {
        String uuid = lockGroupsReg.get(key.toUpperCase()).groupUUIDOwner();
        if (uuid.trim().equalsIgnoreCase("SYSTEM"))
        {
            return GetGroupUUIDOwnerFromJson(key);
        }
        return lockGroupsReg.get(key.toUpperCase()).groupUUIDOwner();
    }

    private File BuildLockGroupFolderPath(String key)
    {
        return new File(LockoutFolder + File.separator + LockoutGroups + File.separator + key.toUpperCase() + ".json");
    }

    private String GetGroupUUIDOwnerFromJson(String key)
    {
        File lockoutSkinGroup = BuildLockGroupFolderPath(key);
        if (lockoutSkinGroup.exists())
        {
            try (FileReader fileReader = new FileReader(lockoutSkinGroup))
            {
                JsonObject jsonObject = Traincraft.jsonParser.parse(fileReader).getAsJsonObject();
                String uuid = jsonObject.get("uuid").getAsString();
                fileReader.close();
                return uuid;
            }
            catch (Exception e)
            {
                Traincraft.tcLog.info(e.getMessage());
                throw new ProjectLockoutErrorException("A error occurred when reading the group");
            }
        }

        return "SYSTEM";
    }

    public String[] FindGroupsUserIsMemberOf(UUID uuid, ArrayList<String> lockoutGroups)
    {
        File filePath = BuildUserFolderPath(uuid.toString());
        if (filePath.exists() == false)
        {
            return new String[] {};
        }

        try (FileReader fileReader = new FileReader(BuildUserFolderPath(uuid.toString())))
        {
            JsonObject jsonObject = Traincraft.jsonParser.parse(fileReader).getAsJsonObject();
            JsonArray array = jsonObject.get("groups").getAsJsonArray();

            ArrayList<String> groupsUserIsPartOf = new ArrayList<>();
            for(String lockoutGroup : lockoutGroups)
            {
                if (IsUserMemberOfGroup(array, lockoutGroup))
                {
                    groupsUserIsPartOf.add(lockoutGroup);
                }
            }

            return groupsUserIsPartOf.toArray(new String[0]);
        }
        catch (Exception e)
        {

        }

        return new String[] {};
    }


    public boolean IsUserMemberOfGroup(UUID uuid, String lockoutGroup)
    {
        File filePath = BuildUserFolderPath(uuid.toString());
        if (filePath.exists() == false)
        {
            return false;
        }

        try (FileReader fileReader = new FileReader(BuildUserFolderPath(uuid.toString())))
        {
            JsonObject jsonObject = Traincraft.jsonParser.parse(fileReader).getAsJsonObject();
            JsonArray array = jsonObject.get("groups").getAsJsonArray();

            return IsUserMemberOfGroup(array, lockoutGroup);
        }
        catch (Exception e)
        {

        }

        return false;
    }

    public void AddUserToGroup(String username, String uuid, String lockoutGroup)
    {
        lockoutGroup = lockoutGroup.toUpperCase();
        File user = BuildUserFolderPath(uuid);
        if (user.exists() == false)
        {
            boolean didProfileCreate = SetupUserProfile(username, uuid);
            if (didProfileCreate == false)
            {
                throw new ProjectLockoutErrorException("Error User Profile Creation Failed Please. Please report this to TBEA.", new Object[0]);
            }
        }

        JsonObject jsonObject;
        JsonArray array;
        try (FileReader fileReader = new FileReader(user))
        {
            jsonObject = Traincraft.jsonParser.parse(fileReader).getAsJsonObject();
            array = jsonObject.get("groups").getAsJsonArray();
        }
        catch (Exception e)
        {
            Traincraft.tcLog.info(e.getMessage());
            throw new ProjectLockoutErrorException("A error occurred when reading the group");
        }

        if (IsUserMemberOfGroup(array, lockoutGroup) == false)
        {
            try
            {
                array.add(new JsonPrimitive(lockoutGroup));
                jsonObject.addProperty("username", username);
                jsonObject.add("groups", array);
                writeChangesToFile(user, jsonObject);
            }
            catch (Exception e)
            {
                throw new ProjectLockoutErrorException("A error occurred when writing the group");
            }
        }
        else
        {
            throw new ProjectLockoutErrorException("User is already a member");
        }
    }

    private File BuildUserFolderPath(String uuid)
    {
        return new File(LockoutFolder + File.separator + LockoutUsers + File.separator + uuid + ".json");
    }

    public void SetupLockoutFolders()
    {
        File lockoutFolder = new File(LockoutFolder);
        if (lockoutFolder.exists() == false)
        {
            lockoutFolder.mkdir();
        }
        lockoutFolder = null;

        File usersFolder = new File(LockoutFolder + File.separator + LockoutUsers);
        if (usersFolder.exists() == false)
        {
            usersFolder.mkdir();
        }

        usersFolder = null;

        File groupsFolder = new File(LockoutFolder + File.separator + LockoutGroups);
        if (groupsFolder.exists() == false)
        {
            groupsFolder.mkdir();
        }
        groupsFolder = null;
    }

    private boolean IsUserMemberOfGroup(JsonArray groups, String group)
    {
        for (JsonElement element : groups)
        {
            if (group.equalsIgnoreCase(element.getAsString()))
            {
                return true;
            }
        }

        return false;
    }

    public void SetSkinGroupOwner(String uuid, String lockoutGroup)
    {
        File file = BuildLockGroupFolderPath(lockoutGroup);
        try (FileReader fileReader = new FileReader(file))
        {
            JsonObject jsonObject = Traincraft.jsonParser.parse(fileReader).getAsJsonObject();
            jsonObject.addProperty("uuid", uuid);
            fileReader.close();

            writeChangesToFile(file, jsonObject);

        }
        catch (Exception e)
        {
            Traincraft.tcLog.info(e.getMessage());
            throw new ProjectLockoutErrorException("A error occurred when reading the group");
        }
    }

    public void RemoveUserFromGroup(String username, String uuid, String lockoutGroup)
    {
        lockoutGroup = lockoutGroup.toUpperCase();
        File user = BuildUserFolderPath(uuid);
        if (user.exists() == false)
        {
            throw new ProjectLockoutErrorException("User is not a member of this group.", new Object[0]);
        }

        JsonObject jsonObject;
        JsonArray array;
        try (FileReader fileReader = new FileReader(user))
        {
            jsonObject = Traincraft.jsonParser.parse(fileReader).getAsJsonObject();
            array = jsonObject.get("groups").getAsJsonArray();
            fileReader.close();
        }
        catch (Exception e)
        {
            Traincraft.tcLog.info(e.getMessage());
            throw new ProjectLockoutErrorException("A error occurred when reading the group");
        }

        if (IsUserMemberOfGroup(array, lockoutGroup))
        {
            try
            {
                JsonArray jsonArrayFiltered = new JsonArray();
                for (int i = 0; i < array.size(); i++)
                {

                    if (array.get(i).getAsJsonPrimitive().getAsString().trim().equalsIgnoreCase(lockoutGroup.trim()) == false)
                    {
                        jsonArrayFiltered.add(array.get(i));
                    }
                }

                jsonObject.addProperty("username", username);
                jsonObject.add("groups", jsonArrayFiltered);
                writeChangesToFile(user, jsonObject);
            }
            catch (Exception e)
            {
                throw new ProjectLockoutErrorException("A error occurred when writing the group");
            }
        }
        else
        {
            throw new ProjectLockoutErrorException("User is not a member of this group.", new Object[0]);
        }
    }

    public void SetupSkinGroup(String groupName, String uuid)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("GroupName", groupName.toUpperCase());
        jsonObject.addProperty("uuid", uuid.toString());

        File lockoutSkinGroup = new File(LockoutFolder + File.separator + LockoutGroups + File.separator + groupName.toUpperCase() + ".json");
        FileWriter fileWriter = null;
        try
        {
            if (lockoutSkinGroup.exists() == false)
            {
                lockoutSkinGroup.createNewFile();
                fileWriter = new FileWriter(lockoutSkinGroup);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(jsonObject, fileWriter);
                fileWriter.close();

                if (uuid != "SYSTEM" && uuid.isEmpty() == false)
                {
                    Traincraft.lockoutPermissionsUtil.AddUserToGroup("", uuid, groupName.toUpperCase());
                }
            }
        }
        catch (Exception e)
        {

        }
    }

    public boolean SetupUserProfile(String username, String uuid)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("uuid", uuid.toString());
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive("DEFAULT"));
        jsonObject.add("groups", jsonArray);

        File lockoutUserProfile = new File(LockoutFolder + File.separator + LockoutUsers + File.separator + uuid.toString() + ".json");
        FileWriter fileWriter = null;
        try
        {
            if (lockoutUserProfile.exists() == false)
            {
                lockoutUserProfile.createNewFile();
            }

            fileWriter = new FileWriter(lockoutUserProfile);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(jsonObject, fileWriter);
            fileWriter.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    private void writeChangesToFile(File path, JsonObject jsonObject) throws IOException
    {
        FileWriter fileWriter = new FileWriter(path);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonObject, fileWriter);
        fileWriter.close();
    }

    public static class KnownLockoutUser
    {
        public final String uuid;
        public final String username;

        public KnownLockoutUser(String uuid, String username)
        {
            this.uuid = uuid;
            this.username = username == null ? "" : username;
        }

        public String getDisplayName()
        {
            return username.trim().length() == 0 ? uuid : username;
        }
    }
}

