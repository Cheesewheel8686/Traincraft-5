package train.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;
import train.common.Traincraft;
import train.common.core.network.lockout.PacketLockoutBookAction;
import train.common.core.network.lockout.PacketLockoutBookData;
import train.common.core.network.lockout.PacketLockoutBookRequest;
import train.common.library.Info;

import java.util.ArrayList;
import java.util.HashMap;

@SideOnly(Side.CLIENT)
public class GuiLockoutBook extends GuiScreen
{
    private static final int GROUPS_PER_PAGE = 9;
    private static final int USERS_PER_PAGE = 9;
    private static final int BOOK_WIDTH = 456;
    private static final int BOOK_HEIGHT = 260;
    private static final int PAGE_WIDTH = 228;
    private static final int COLUMN_WIDTH = 104;
    private static final int ROW_HEIGHT = 13;
    private static final int ROW_SPACING = 14;
    private static final int TEXT_DARK = 0x2f2116;
    private static final int TEXT_HEADING = 0x6f2b1f;

    private ArrayList<String> allGroups = new ArrayList<>();
    private ArrayList<String> ownedGroups = new ArrayList<>();
    private ArrayList<String> groups = new ArrayList<>();
    private ArrayList<PacketLockoutBookData.UserRecord> knownUsers = new ArrayList<>();
    private HashMap<String, ArrayList<PacketLockoutBookData.UserRecord>> membersByGroup = new HashMap<>();
    private String status = "Loading lockout groups...";
    private HashMap<Integer, PacketLockoutBookData.UserRecord> userButtonRecords = new HashMap<>();
    private boolean ownedOnly;

    private int selectedGroup = -1;
    private int selectedUser = -1;
    private int selectedMember = -1;
    private int groupPage = 0;
    private int userPage = 0;
    private int memberPage = 0;

    @Override
    public void initGui()
    {
        buildButtons();
        Traincraft.lockoutCommChannel.sendToServer(new PacketLockoutBookRequest());
    }

    public void loadData(PacketLockoutBookData data)
    {
        String selectedGroupName = getSelectedGroupName();
        this.allGroups = data.groups;
        this.ownedGroups = data.ownedGroups;
        this.knownUsers = data.knownUsers;
        this.membersByGroup = data.membersByGroup;
        applyGroupFilter(selectedGroupName);
        if (data.status != null && data.status.trim().length() > 0)
        {
            this.status = data.status;
        }
        else if (groups.isEmpty())
        {
            this.status = "No lockout groups available to manage.";
        }
        else
        {
            this.status = "Select a group and user.";
        }

        selectedUser = clampSelection(selectedUser, knownUsers.size());
        selectedMember = clampSelection(selectedMember, getCurrentMembers().size());
        buildButtons();
    }

    private void applyGroupFilter(String selectedGroupName)
    {
        groups = new ArrayList<>();
        if (ownedOnly)
        {
            groups.addAll(ownedGroups);
        }
        else
        {
            groups.addAll(allGroups);
        }

        if (selectedGroupName != null && groups.contains(selectedGroupName))
        {
            selectedGroup = groups.indexOf(selectedGroupName);
        }
        else
        {
            selectedGroup = groups.isEmpty() ? -1 : 0;
        }
        groupPage = selectedGroup < 0 ? 0 : selectedGroup / GROUPS_PER_PAGE;
        selectedMember = clampSelection(selectedMember, getCurrentMembers().size());
    }

    private String getSelectedGroupName()
    {
        if (selectedGroup >= 0 && selectedGroup < groups.size())
        {
            return groups.get(selectedGroup);
        }
        return null;
    }

    private int clampSelection(int selected, int size)
    {
        if (size <= 0)
        {
            return -1;
        }
        if (selected < 0)
        {
            return 0;
        }
        return Math.min(selected, size - 1);
    }

    private void buildButtons()
    {
        buttonList.clear();
        userButtonRecords.clear();
        int top = getBookTop();
        int left = getBookLeft();
        int groupsX = left + 42;
        int usersX = left + 176;
        int membersX = left + 310;
        int rowsY = top + 43;
        addListButtons(0, groupsX, rowsY, groups, groupPage, GROUPS_PER_PAGE, selectedGroup);
        addUserButtons(100, usersX, rowsY, knownUsers, userPage, selectedUser);
        addUserButtons(200, membersX, rowsY, getCurrentMembers(), memberPage, selectedMember);

        addPageButton(20, groupsX + 15, top + 174, false, groupPage > 0);
        addPageButton(21, groupsX + 66, top + 174, true, groupPage < getMaxPage(groups.size(), GROUPS_PER_PAGE));
        addPageButton(120, usersX + 15, top + 174, false, userPage > 0);
        addPageButton(121, usersX + 66, top + 174, true, userPage < getMaxPage(knownUsers.size(), USERS_PER_PAGE));
        addPageButton(220, membersX + 15, top + 174, false, memberPage > 0);
        addPageButton(221, membersX + 66, top + 174, true, memberPage < getMaxPage(getCurrentMembers().size(), USERS_PER_PAGE));

        buttonList.add(new BookButton(400, groupsX, top + 198, COLUMN_WIDTH, 15, (ownedOnly ? "[x] " : "[ ] ") + "Owned Only", BookButton.Style.ACTION));
        buttonList.add(new BookButton(300, usersX, top + 198, COLUMN_WIDTH, 15, "Add", BookButton.Style.ACTION));
        buttonList.add(new BookButton(301, membersX, top + 198, COLUMN_WIDTH, 15, "Remove", BookButton.Style.DANGER));
        buttonList.add(new BookButton(303, groupsX, top + 216, COLUMN_WIDTH, 15, "Done", BookButton.Style.ACTION));
        buttonList.add(new BookButton(302, usersX, top + 216, membersX + COLUMN_WIDTH - usersX, 15, "Refresh", BookButton.Style.ACTION));
    }

    private void addListButtons(int idBase, int x, int y, ArrayList<String> values, int page, int perPage, int selected)
    {
        for (int i = 0; i < perPage; i++)
        {
            int index = page * perPage + i;
            String text = index < values.size() ? values.get(index) : "";
            if (index == selected)
            {
                text = "> " + text;
            }
            GuiButton button = new BookButton(idBase + i, x, y + i * ROW_SPACING, COLUMN_WIDTH, ROW_HEIGHT, trim(text, 16), BookButton.Style.ROW);
            button.enabled = index < values.size();
            buttonList.add(button);
        }
    }

    private void addUserButtons(int idBase, int x, int y, ArrayList<PacketLockoutBookData.UserRecord> values, int page, int selected)
    {
        for (int i = 0; i < USERS_PER_PAGE; i++)
        {
            int index = page * USERS_PER_PAGE + i;
            String text = index < values.size() ? values.get(index).getListName() : "";
            if (index == selected)
            {
                text = "> " + text;
            }
            GuiButton button = new BookButton(idBase + i, x, y + i * ROW_SPACING, COLUMN_WIDTH, ROW_HEIGHT, trim(text, 16), BookButton.Style.ROW);
            button.enabled = index < values.size();
            if (index < values.size())
            {
                userButtonRecords.put(button.id, values.get(index));
            }
            buttonList.add(button);
        }
    }

    private String trim(String text, int length)
    {
        if (text == null)
        {
            return "";
        }
        return text.length() <= length ? text : text.substring(0, length - 3) + "...";
    }

    private void addPageButton(int id, int x, int y, boolean next, boolean enabled)
    {
        GuiButtonNextPage button = new GuiButtonNextPage(id, x, y, 23, 13, next);
        button.showButton = enabled;
        button.enabled = enabled;
        buttonList.add(button);
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.id >= 0 && button.id < GROUPS_PER_PAGE)
        {
            selectedGroup = groupPage * GROUPS_PER_PAGE + button.id;
            selectedMember = clampSelection(selectedMember, getCurrentMembers().size());
            buildButtons();
            return;
        }
        if (button.id >= 100 && button.id < 100 + USERS_PER_PAGE)
        {
            selectedUser = userPage * USERS_PER_PAGE + (button.id - 100);
            buildButtons();
            return;
        }
        if (button.id >= 200 && button.id < 200 + USERS_PER_PAGE)
        {
            selectedMember = memberPage * USERS_PER_PAGE + (button.id - 200);
            buildButtons();
            return;
        }

        switch (button.id)
        {
            case 20:
                groupPage = Math.max(0, groupPage - 1);
                break;
            case 21:
                groupPage = nextPage(groupPage, groups.size(), GROUPS_PER_PAGE);
                break;
            case 120:
                userPage = Math.max(0, userPage - 1);
                break;
            case 121:
                userPage = nextPage(userPage, knownUsers.size(), USERS_PER_PAGE);
                break;
            case 220:
                memberPage = Math.max(0, memberPage - 1);
                break;
            case 221:
                memberPage = nextPage(memberPage, getCurrentMembers().size(), USERS_PER_PAGE);
                break;
            case 300:
                sendAction(PacketLockoutBookAction.ACTION_ADD, selectedUser, knownUsers);
                break;
            case 301:
                sendAction(PacketLockoutBookAction.ACTION_REMOVE, selectedMember, getCurrentMembers());
                break;
            case 302:
                status = "Refreshing...";
                Traincraft.lockoutCommChannel.sendToServer(new PacketLockoutBookRequest());
                break;
            case 303:
                mc.displayGuiScreen(null);
                break;
            case 400:
                ownedOnly = !ownedOnly;
                applyGroupFilter(getSelectedGroupName());
                break;
        }
        buildButtons();
    }

    private int nextPage(int currentPage, int count, int perPage)
    {
        int maxPage = getMaxPage(count, perPage);
        return Math.min(maxPage, currentPage + 1);
    }

    private int getMaxPage(int count, int perPage)
    {
        return Math.max(0, (count - 1) / perPage);
    }

    private void sendAction(int action, int selected, ArrayList<PacketLockoutBookData.UserRecord> users)
    {
        if (selectedGroup < 0 || selectedGroup >= groups.size())
        {
            status = "Select a group first.";
            return;
        }
        if (selected < 0 || selected >= users.size())
        {
            status = "Select a user first.";
            return;
        }
        PacketLockoutBookData.UserRecord user = users.get(selected);
        status = "Sending update...";
        Traincraft.lockoutCommChannel.sendToServer(new PacketLockoutBookAction(action, groups.get(selectedGroup), user.uuid, user.username));
    }

    private ArrayList<PacketLockoutBookData.UserRecord> getCurrentMembers()
    {
        if (selectedGroup < 0 || selectedGroup >= groups.size())
        {
            return new ArrayList<>();
        }
        ArrayList<PacketLockoutBookData.UserRecord> members = membersByGroup.get(groups.get(selectedGroup));
        return members == null ? new ArrayList<PacketLockoutBookData.UserRecord>() : members;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        int top = getBookTop();
        int left = getBookLeft();
        drawBookBackground(left, top);
        drawCenteredPlainString("Skin Lockout Manager", width / 2, top + 10, TEXT_DARK);
        fontRendererObj.drawString("Groups", left + 42, top + 28, TEXT_HEADING);
        fontRendererObj.drawString("Known Users", left + 176, top + 28, TEXT_HEADING);
        fontRendererObj.drawString("Members", left + 310, top + 28, TEXT_HEADING);
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawCenteredPlainString(trim(status, 62), width / 2, top + 242, TEXT_DARK);
        drawUserTooltip(mouseX, mouseY);
    }

    private void drawCenteredPlainString(String text, int x, int y, int color)
    {
        fontRendererObj.drawString(text, x - fontRendererObj.getStringWidth(text) / 2, y, color, false);
    }

    private int getBookLeft()
    {
        return width / 2 - BOOK_WIDTH / 2;
    }

    private int getBookTop()
    {
        return height / 2 - BOOK_HEIGHT / 2;
    }

    private void drawBookBackground(int left, int top)
    {
        mc.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, Info.bookPrefix + "lockout_book_inner.png"));
        drawTexturedRect(left, top, BOOK_WIDTH, BOOK_HEIGHT);
    }

    private void drawTexturedRect(int x, int y, int textureWidth, int textureHeight)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + textureHeight, zLevel, 0.0D, 1.0D);
        tessellator.addVertexWithUV(x + textureWidth, y + textureHeight, zLevel, 1.0D, 1.0D);
        tessellator.addVertexWithUV(x + textureWidth, y, zLevel, 1.0D, 0.0D);
        tessellator.addVertexWithUV(x, y, zLevel, 0.0D, 0.0D);
        tessellator.draw();
    }

    private void drawUserTooltip(int mouseX, int mouseY)
    {
        for (Object object : buttonList)
        {
            GuiButton button = (GuiButton) object;
            PacketLockoutBookData.UserRecord record = userButtonRecords.get(button.id);
            if (record != null && button.visible && mouseX >= button.xPosition && mouseY >= button.yPosition && mouseX < button.xPosition + button.width && mouseY < button.yPosition + button.height)
            {
                ArrayList<String> tooltip = new ArrayList<>();
                tooltip.add(record.getListName());
                tooltip.add(EnumChatFormatting.GRAY + record.uuid);
                drawHoveringText(tooltip, mouseX, mouseY, fontRendererObj);
                return;
            }
        }
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    private static class BookButton extends GuiButton
    {
        private enum Style
        {
            ROW,
            ACTION,
            DANGER
        }

        private final Style style;

        public BookButton(int id, int x, int y, int width, int height, String text, Style style)
        {
            super(id, x, y, width, height, text);
            this.style = style;
        }

        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY)
        {
            if (visible == false)
            {
                return;
            }

            boolean hover = enabled && mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
            boolean selected = displayString.startsWith("> ");
            int fill = getFillColor(hover, selected);
            int border = selected ? 0x99a23a2a : 0x66442b1e;
            drawRect(xPosition, yPosition, xPosition + width, yPosition + height, fill);
            drawRect(xPosition, yPosition, xPosition + width, yPosition + 1, border);
            drawRect(xPosition, yPosition + height - 1, xPosition + width, yPosition + height, border);
            drawRect(xPosition, yPosition, xPosition + 1, yPosition + height, border);
            drawRect(xPosition + width - 1, yPosition, xPosition + width, yPosition + height, border);

            int color = enabled ? TEXT_DARK : 0x8f7d6c;
            if (style == Style.DANGER && enabled)
            {
                color = 0x7f241d;
            }
            if (selected)
            {
                color = 0xefe2c4;
            }

            int textX = xPosition + (width - mc.fontRenderer.getStringWidth(displayString)) / 2;
            int textY = yPosition + (height - 8) / 2;
            mc.fontRenderer.drawString(displayString, textX, textY, color, false);
        }

        private int getFillColor(boolean hover, boolean selected)
        {
            if (enabled == false)
            {
                return style == Style.ROW ? 0x22664a36 : 0x33705a44;
            }
            if (selected)
            {
                return 0xcc7b2b24;
            }
            if (hover)
            {
                return style == Style.DANGER ? 0x77a64636 : 0x66b77558;
            }
            return style == Style.ROW ? 0x446f5137 : 0x557d5a3c;
        }
    }
}
