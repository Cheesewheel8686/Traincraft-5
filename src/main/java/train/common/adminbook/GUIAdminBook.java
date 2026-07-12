package train.common.adminbook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import tmt.Tessellator;
import train.client.gui.GuiShadedButton;
import train.common.Traincraft;
import train.common.core.network.AdminBook.PacketAdminBookClient;
import train.common.core.network.AdminBook.PacketAdminBookToggleChunkLoading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Set;

/**
 * <h1>Transport GUI</h1>
 * used to draw the GUI for trains and rollingstock (the menu with the inventory).
 * @author Eternal Blue Flame
 */
public class GUIAdminBook extends GuiScreen
{
    /**the amount to scale the GUI by, same as vanilla*/
    private static final float guiScaler = 0.00390625F;
    private static final int PANEL_WIDTH = 420;
    private static final int PANEL_HEIGHT = 220;
    private static final int ROW_HEIGHT = 22;
    private static final int ROWS_PER_PAGE = 7;
    private static final int PLAYER_COLUMNS = 2;
    private static final int BUTTON_SEARCH_CLEAR = -2;
    private static final int BUTTON_CLONE_INVENTORY = -1;
    private static final int BUTTON_DELETE_ENTRY = 0;
    private static final int BUTTON_BACK = 1;
    private static final int BUTTON_NEXT_PAGE = 2;
    private static final int BUTTON_CLONE_AND_DELETE = 3;
    private static final int BUTTON_TOGGLE_HANDLED = 4;
    private static final int BUTTON_STOP_CHUNK_LOADING = 5;
    private static final int BUTTON_LOAD_RESTORE = 8;
    private static final int BUTTON_TOGGLE_ROW_HANDLED_BASE = 10000;
    private static final int BUTTON_OPEN_ROW_BASE = 20000;
    private static final String EMPTY_PAGE = "!empty";
    private static final Set<String> handledStock = new HashSet<String>();

    private String[] list;
    private boolean isTrainPage = false;
    private boolean isEscrowPage = false;
    private int guiLeft;
    private int guiTop;
    private int page=0;
    private List<ItemStack> items = new ArrayList<ItemStack>();
    private String searchQuery = "";
    private int searchIndex = 0;
    private boolean pageTurnConsumed = false;

    public GUIAdminBook(String csv){
        //if its the xml enable train page mode.
        if (EMPTY_PAGE.equals(csv)){
            list = new String[0];
            return;
        }
        if (csv.length()<3){
            list=null;
            return;
        }
        isTrainPage = csv.charAt(0) == '<';
        list = csv.split(",");
    }
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }


    @Override
    public void actionPerformed(GuiButton button)
    {
        if (button.id >= BUTTON_OPEN_ROW_BASE) {
            int listIndex = button.id - BUTTON_OPEN_ROW_BASE;
            if (listIndex >= 0 && listIndex < list.length) {
                Traincraft.keyChannel.sendToServer(new PacketAdminBookClient(list[listIndex], Minecraft.getMinecraft().thePlayer.getEntityId()));
            }
            return;
        }
        if (button.id >= BUTTON_TOGGLE_ROW_HANDLED_BASE) {
            int listIndex = button.id - BUTTON_TOGGLE_ROW_HANDLED_BASE;
            if (listIndex >= 0 && listIndex < list.length) {
                toggleHandled(list[listIndex]);
                initGui();
            }
            return;
        }

        switch (button.id)
        {
            case BUTTON_SEARCH_CLEAR:
            { // Search query button, used to clear query.
                searchQuery = "";
                searchIndex = 0;
                setPage(0);
                initGui();
                break;
            }
            case BUTTON_CLONE_INVENTORY:
            { // Clone inventory button.
                Traincraft.keyChannel.sendToServer(new PacketAdminBookClient( "0:"+list[0].substring(1), Minecraft.getMinecraft().thePlayer.getEntityId()));//tell server to drop items
                break;
            }
            case BUTTON_DELETE_ENTRY:
            { // Delete entry button.
                Traincraft.keyChannel.sendToServer(new PacketAdminBookClient( "1:"+list[0].substring(1), Minecraft.getMinecraft().thePlayer.getEntityId()));//tell server to drop items
                break;
            }
            case BUTTON_BACK:
            { // Back button.
                goBack();
                break;
            }
            case BUTTON_NEXT_PAGE:
            { // Next page button.
                turnPage(1);
                break;
            }
            case BUTTON_CLONE_AND_DELETE:
            {
                if(list[0]!=null && list[0].length()>1) {
                    Traincraft.keyChannel.sendToServer(new PacketAdminBookClient("0:" + list[0].substring(1), Minecraft.getMinecraft().thePlayer.getEntityId()));//tell server to drop items
                    Traincraft.keyChannel.sendToServer(new PacketAdminBookClient("1:" + list[0].substring(1), Minecraft.getMinecraft().thePlayer.getEntityId()));//tell server to drop items
                }
                break;
            }

            case BUTTON_TOGGLE_HANDLED:
                toggleHandled(getCurrentStockPath());
                initGui();
                break;
            case BUTTON_LOAD_RESTORE:
                if(list[0]!=null && list[0].length()>1) {
                    Traincraft.keyChannel.sendToServer(new PacketAdminBookClient("2:" + list[0].substring(1), Minecraft.getMinecraft().thePlayer.getEntityId()));
                }
                break;
            case BUTTON_STOP_CHUNK_LOADING:
                Traincraft.toggleChunkLoadingChannel.sendToServer(new PacketAdminBookToggleChunkLoading(false));//tell server to drop items
            break;

            default:
            {
                break;
            }
        }

    }

    @Override
    public void initGui()
    {
        super.initGui();
        this.buttonList.clear();
        if(list==null)
        {
            return;
        }

        this.guiLeft = (this.width - getPanelWidth()) / 2;
        this.guiTop = (this.height - PANEL_HEIGHT) / 2;
        clampPage();

        if(!isTrainPage)
        {
            initListButtons();
        }
        else
        {
            try
            {
                initStockButtons();
                items = ServerLogger.getItems(getInventoryItemsDocument());
            }
            catch (Exception e)
            {

            }
        }

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float par3){
        drawDefaultBackground();
        drawPanel();
        super.drawScreen(mouseX, mouseY, par3);
        if(list==null){
            return;
        }
        if(isTrainPage) {
            drawStockDetails(mouseX, mouseY);
        } else {
            drawListDetails();
        }

    }

    private void initListButtons() {
        boolean drawingPlayerList = isPlayerList();
        int listWidth = getPanelWidth();
        int rowLeft = guiLeft + 10;
        int rowTop = guiTop + 38;
        int openWidth = drawingPlayerList ? (listWidth - 30) / PLAYER_COLUMNS : listWidth - 56;
        int entriesPerPage = getEntriesPerPage();
        int start = entriesPerPage * page;
        int end = Math.min(start + entriesPerPage, list.length);

        for (int i = start; i < end; i++) {
            int offset = i - start;
            int column = drawingPlayerList ? offset / ROWS_PER_PAGE : 0;
            int row = drawingPlayerList ? offset % ROWS_PER_PAGE : offset;
            String path = list[i];
            String label = getRowTitle(path);
            int buttonX = rowLeft + (column * (openWidth + 10));
            GuiButton button = shadedButton(BUTTON_OPEN_ROW_BASE + i, buttonX, rowTop + (row * ROW_HEIGHT), openWidth, 20, label);

            if (drawingPlayerList && !searchQuery.equals("") && i == searchIndex) {
                button.packedFGColour = 0xFFFF55;
            } else if (drawingPlayerList && !searchQuery.equals("") && isPlayerSearchMatch(path, searchQuery.toLowerCase())) {
                button.packedFGColour = 7855479;
            }

            this.buttonList.add(button);

            if (!drawingPlayerList && isStockEntry(path)) {
                this.buttonList.add(shadedButton(BUTTON_TOGGLE_ROW_HANDLED_BASE + i, rowLeft + openWidth + 6,
                        rowTop + (row * ROW_HEIGHT), 30, 20, getHandledLabel(path)));
            }
        }

        int footerY = guiTop + PANEL_HEIGHT - 28;
        if(page > 0) {
            this.buttonList.add(shadedButton(BUTTON_BACK, guiLeft + 10, footerY, 70, 20, "back"));
        }
        if(list.length - entriesPerPage - (page * entriesPerPage) > 0) {
            this.buttonList.add(shadedButton(BUTTON_NEXT_PAGE, guiLeft + getPanelWidth() - 80, footerY, 70, 20, "next"));
        }
        if (drawingPlayerList && !searchQuery.equals("")) {
            GuiButton searchButton = shadedButton(BUTTON_SEARCH_CLEAR, guiLeft + (getPanelWidth() - 120) / 2, footerY, 120, 20,
                    "clear: " + fontRendererObj.trimStringToWidth(searchQuery, 72));
            searchButton.packedFGColour = 16777215;
            this.buttonList.add(searchButton);
        }

        this.buttonList.add(dangerButton(BUTTON_STOP_CHUNK_LOADING, guiLeft + getPanelWidth() - 200, guiTop + PANEL_HEIGHT + 6,
                190, 20, "Release ALL TC Chunk Tickets"));
    }

    private void initEscrowButtons() {
        int footerY = guiTop + PANEL_HEIGHT - 28;
        this.buttonList.add(shadedButton(BUTTON_BACK, guiLeft + 10, footerY, 54, 20, "back"));
        this.buttonList.add(shadedButton(BUTTON_REFRESH_ESCROW, guiLeft + getPanelWidth() - 80, footerY, 70, 20, "refresh"));

        List<EscrowRecord> records = getEscrowRecords();
        int start = ROWS_PER_PAGE * page;
        int end = Math.min(start + ROWS_PER_PAGE, records.size());
        for (int i = start; i < end; i++) {
            int row = i - start;
            EscrowRecord record = records.get(i);
            this.buttonList.add(coloredButton(BUTTON_RECOVER_ESCROW_BASE + record.index,
                    guiLeft + getPanelWidth() - 142, guiTop + 51 + (row * ROW_HEIGHT), 64, 16, "to user",
                    ESCROW_RECOVER_COLOR, ESCROW_RECOVER_HOVER_COLOR));
            this.buttonList.add(coloredButton(BUTTON_COLLECT_ESCROW_BASE + record.index,
                    guiLeft + getPanelWidth() - 74, guiTop + 51 + (row * ROW_HEIGHT), 64, 16, "to admin",
                    ESCROW_COLLECT_COLOR, ESCROW_COLLECT_HOVER_COLOR));
        }
    }

    private void initStockButtons() {
        int footerY = guiTop + PANEL_HEIGHT - 28;
        this.buttonList.add(shadedButton(BUTTON_BACK, guiLeft + 10, footerY, 54, 20, "back"));
        this.buttonList.add(dangerButton(BUTTON_DELETE_ENTRY, guiLeft + 70, footerY, 78, 20, "delete entry"));
        this.buttonList.add(shadedButton(BUTTON_CLONE_INVENTORY, guiLeft + 154, footerY, 98, 20, "clone inventory"));
        this.buttonList.add(dangerButton(BUTTON_CLONE_AND_DELETE, guiLeft + 258, footerY, 92, 20, "clone & delete"));
        this.buttonList.add(shadedButton(BUTTON_TOGGLE_HANDLED, guiLeft + 356, footerY, 54, 20, getHandledLabel(getCurrentStockPath())));
        this.buttonList.add(shadedButton(BUTTON_LOAD_RESTORE, guiLeft + 154, footerY - 22, 98, 20, "load restore"));
    }

    private GuiButton shadedButton(int id, int x, int y, int width, int height, String label) {
        return new GuiShadedButton(id, x, y, width, height, label);
    }

    private GuiButton dangerButton(int id, int x, int y, int width, int height, String label) {
        return new GuiShadedButton(id, x, y, width, height, label, GuiShadedButton.Style.DANGER);
    }

    private GuiButton coloredButton(int id, int x, int y, int width, int height, String label, int background, int hoverBackground) {
        return new GuiShadedButton(id, x, y, width, height, label, background, hoverBackground);
    }

    private void drawPanel() {
        int panelWidth = getPanelWidth();
        drawRect(guiLeft - 2, guiTop - 2, guiLeft + panelWidth + 2, guiTop + PANEL_HEIGHT + 2, 0xCC111111);
        drawRect(guiLeft, guiTop, guiLeft + panelWidth, guiTop + PANEL_HEIGHT, 0xAA2B2B2B);
        drawRect(guiLeft, guiTop, guiLeft + panelWidth, guiTop + 28, 0xCC000000);
    }

    private void drawListDetails() {
        boolean drawingPlayerList = isPlayerList();
        int titleColor = 0xFFFFFF;
        String title = list.length == 0 ? "Admin Book: No Backups" : (drawingPlayerList ? "Admin Book: Players" : "Admin Book: Rollingstock");
        drawCenteredString(fontRendererObj, title, guiLeft + getPanelWidth() / 2, guiTop + 8, titleColor);

        String status;
        if (list.length == 0)
        {
            status = "No train or rollingstock backups found.";
        }
        else if (drawingPlayerList)
        {
            status = "Page " + (page + 1) + " / " + Math.max(1, getPageCount()) + " - " + list.length + " entries";
            status = searchQuery.equals("") ? status + " - type to search" : status + " - Search: " + searchQuery;
        }
        else
        {
            status = "Page " + (page + 1) + " / " + Math.max(1, getPageCount()) + " - " + (list.length - 1) + " entries";
            status = status + " - [x] marks handled";
        }
        drawCenteredString(fontRendererObj, status, guiLeft + getPanelWidth() / 2, guiTop + 24, 0xD0D0D0);
    }

    private void drawStockDetails(int mouseX, int mouseY) {
        drawCenteredString(fontRendererObj, "Admin Book: Stock Backup", guiLeft + getPanelWidth() / 2, guiTop + 8, 0xFFFFFF);
        drawTextOutlined(fontRendererObj, "Status: " + (handledStock.contains(getCurrentStockPath()) ? "Handled" : "Needs review"), guiLeft + 12, guiTop + 34, 16777215);

        drawDetailLine("Delegate:", getDetailValue("Delegate:"), guiTop + 58);
        drawUuidDetail(guiTop + 82);
        drawTextOutlined(fontRendererObj, "Last Pos:", guiLeft + 12, guiTop + 124, 16777215);
        drawDetailLine("X:", getDetailValue("X:"), guiTop + 134);
        drawDetailLine("Y:", getDetailValue("Y:"), guiTop + 144);
        drawDetailLine("Z:", getDetailValue("Z:"), guiTop + 154);

        drawTextOutlined(fontRendererObj, I18n.format("container.inventory", new Object()), guiLeft + 278, guiTop + 34, 16777215);
        int index=0;
        for (int y =0; y<6; y++){
            for(int x=0; x<9; x++){
                if(items.size()>index && items.get(index) !=null) {
                    int itemX = guiLeft + 258 + (x * 16);
                    int itemY = guiTop + 50 + (y * 16);
                    func_146977_a(items.get(index), itemX, itemY);
                    if (mouseX >= itemX && mouseX < itemX + 16 && mouseY >= itemY && mouseY < itemY + 16) {
                        renderToolTip(items.get(index), mouseX, mouseY);
                    }
                }
                index++;
            }
        }
    }

    private int getPanelWidth() {
        return Math.min(PANEL_WIDTH, Math.max(260, this.width - 20));
    }

    private int getPageCount() {
        int entriesPerPage = getEntriesPerPage();
        return (list.length + entriesPerPage - 1) / entriesPerPage;
    }

    private void setPage(int newPage) {
        page = newPage;
        clampPage();
    }

    private void turnPage(int direction) {
        if (pageTurnConsumed) {
            return;
        }
        int oldPage = page;
        setPage(page + direction);
        if (page != oldPage) {
            pageTurnConsumed = true;
            initGui();
        }
    }

    private void clampPage() {
        int pageCount = Math.max(1, getPageCount());
        if (page < 0) {
            page = 0;
        } else if (page >= pageCount) {
            page = pageCount - 1;
        }
    }

    private int getEntriesPerPage() {
        return isPlayerList() ? ROWS_PER_PAGE * PLAYER_COLUMNS : ROWS_PER_PAGE;
    }

    private boolean isPlayerList() {
        for (String entry : list) {
            if (isStockEntry(entry)) {
                return false;
            }
        }
        return true;
    }

    private boolean isStockEntry(String entry) {
        return entry != null && entry.lastIndexOf("_") > 0 && entry.indexOf(".txt") > 0;
    }

    private String getRowTitle(String entry) {
        if (entry == null || entry.isEmpty()) {
            return "back";
        }
        if (isStockEntry(entry)) {
            String fileName = entry.substring(entry.lastIndexOf("/") + 1);
            int nameEnd = fileName.lastIndexOf("_");
            if (nameEnd > 0) {
                String stockName = fileName.substring(0, nameEnd).replace("~", ":");
                return fontRendererObj.trimStringToWidth(stockName + " - " + getRowUuid(entry), getPanelWidth() - 84);
            }
        }
        int width = isPlayerList() ? ((getPanelWidth() - 30) / PLAYER_COLUMNS) - 8 : getPanelWidth() - 28;
        return fontRendererObj.trimStringToWidth(getPathDisplayName(entry), width);
    }

    private String getPathDisplayName(String entry) {
        if (entry.startsWith("backup:")) {
            entry = entry.substring("backup:".length());
        }
        int separator = Math.max(entry.lastIndexOf("/"), entry.lastIndexOf("\\"));
        return separator >= 0 ? entry.substring(separator + 1) : entry;
    }

    private String getRowUuid(String entry) {
        if (!isStockEntry(entry)) {
            return "";
        }
        return entry.substring(entry.lastIndexOf("_") + 1, entry.indexOf(".txt"));
    }

    private String getHandledLabel(String stockPath) {
        return handledStock.contains(stockPath) ? "[x]" : "[ ]";
    }

    private String getCurrentStockPath() {
        if (list != null && list.length > 0 && list[0] != null && list[0].length() > 1) {
            return list[0].substring(1);
        }
        return "";
    }

    private String getCurrentStockParentPath() {
        String stockPath = getCurrentStockPath();
        int separator = stockPath.lastIndexOf("/");
        if (separator >= 0) {
            return stockPath.substring(0, separator + 1);
        }
        if (list != null && list.length > 1 && list[1] != null) {
            return list[1];
        }
        return "";
    }

    private void goBack() {
        if (isEscrowPage)
        {
            Traincraft.keyChannel.sendToServer(new PacketAdminBookClient("", Minecraft.getMinecraft().thePlayer.getEntityId()));
        }
        else if (!isTrainPage)
        {
            turnPage(-1);
        }
        else
        {
            Traincraft.keyChannel.sendToServer(new PacketAdminBookClient(getCurrentStockParentPath(), Minecraft.getMinecraft().thePlayer.getEntityId()));//tell server to send a new gui
        }
    }

    private String getInventoryDocument() {
        if (list == null) {
            return "";
        }
        for (String entry : list) {
            if (entry != null && entry.contains("<inventory>")) {
                return entry.substring(entry.indexOf("<inventory>"));
            }
        }
        return "";
    }

    private String getInventoryItemsDocument() {
        String delegate = getDetailValue("Delegate:");
        String inventory = getInventoryDocument();
        if (delegate.length() == 0) {
            return inventory;
        }
        return "<delegate>" + delegate + "</delegate>" + inventory;
    }

    private String getDetailValue(String label) {
        if (list == null) {
            return "";
        }
        for (int i = 0; i < list.length - 1; i++) {
            if (label.equals(list[i])) {
                return stripInventory(list[i + 1]);
            }
        }
        return "";
    }

    private String stripInventory(String value) {
        if (value == null) {
            return "";
        }
        int inventoryStart = value.indexOf("<inventory>");
        return inventoryStart >= 0 ? value.substring(0, inventoryStart) : value;
    }

    private String getStockUuid() {
        String uuid = getDetailValue("UUID:");
        if (uuid.length() < 36 && list != null) {
            for (int i = 0; i < list.length; i++) {
                if ("UUID:".equals(list[i]) && i + 2 < list.length) {
                    uuid = list[i + 1] + list[i + 2];
                    break;
                }
            }
        }
        return uuid;
    }

    private void drawDetailLine(String label, String value, int y) {
        drawTextOutlined(fontRendererObj, label, guiLeft + 12, y, 16777215);
        drawTextOutlined(fontRendererObj, fontRendererObj.trimStringToWidth(value, 188), guiLeft + 100, y, 16777215);
    }

    private void drawUuidDetail(int y) {
        String uuid = getStockUuid();
        drawTextOutlined(fontRendererObj, "UUID:", guiLeft + 12, y, 16777215);
        if (uuid.length() > 18) {
            drawTextOutlined(fontRendererObj, uuid.substring(0, 18), guiLeft + 100, y, 16777215);
            drawTextOutlined(fontRendererObj, uuid.substring(18), guiLeft + 100, y + 14, 16777215);
        } else {
            drawTextOutlined(fontRendererObj, uuid, guiLeft + 100, y, 16777215);
        }
    }

    private void toggleHandled(String stockPath) {
        if (stockPath == null || stockPath.length() == 0 || !isStockEntry(stockPath)) {
            return;
        }
        if (handledStock.contains(stockPath)) {
            handledStock.remove(stockPath);
        } else {
            handledStock.add(stockPath);
        }
    }

    public static void drawTextOutlined(FontRenderer font, String string, int x, int y, int color){
        //bottom left
        font.drawString(string, x-1, y+1, 0);
        //bottom
        font.drawString(string, x, y+1, 0);
        //bottom right
        font.drawString(string, x+1, y+1, 0);
        //left
        font.drawString(string, x-1, y, 0);
        //right
        font.drawString(string, x+1, y, 0);
        //top left
        font.drawString(string, x-1, y-1, 0);
        //top
        font.drawString(string, x, y-1, 0);
        //top right
        font.drawString(string, x+1, y-1, 0);


        font.drawString(string,x,y,color);
    }

    /**
     * <h2>Draw Texture</h2>
     * This replaces the base class and allows us to draw textures that are stretched to the shape defined in a more efficient manner.
     * NOTE: all textures must be divisible by 256x256
     * @param posX the X position on screen to draw at.
     * @param posY the Y position on screen to draw at.
     * @param posU the X position of the texture to start from.
     * @param posV the Y position of the texture to start from.
     * @param width the width of the box.
     * @param height the height of the box.
     * @param widthUV defines the X size of the texture part used
     * @param heightUV defines the X Y size of the texture part used
     */
    public static void drawTexturedRect(int posX, int posY, int posU, int posV, int width, int height, int widthUV, int heightUV) {
        Tessellator tessellator = Tessellator.getInstance();
        tessellator.startDrawing(GL11.GL_QUADS);
        tessellator.addVertexWithUV(posX, posY + height, 0, posU * guiScaler, (posV + heightUV) * guiScaler);
        tessellator.addVertexWithUV(posX + width, posY + height, 0, (posU + widthUV) * guiScaler, (posV + heightUV) * guiScaler);
        tessellator.addVertexWithUV(posX + width, posY, 0, (posU + widthUV) * guiScaler, posV * guiScaler);
        tessellator.addVertexWithUV(posX, posY, 0, posU * guiScaler, posV * guiScaler);
        tessellator.draw();
    }
    public static void drawTexturedRect(int posX, int posY, int posU, int posV, int width, int height) {
        Tessellator tessellator = Tessellator.getInstance();
        tessellator.startDrawing(GL11.GL_QUADS);
        tessellator.addVertexWithUV(posX, posY + height, 0, posU * guiScaler, (posV + height) * guiScaler);
        tessellator.addVertexWithUV(posX + width, posY + height, 0, (posU + width) * guiScaler, (posV + height) * guiScaler);
        tessellator.addVertexWithUV(posX + width, posY, 0, (posU + width) * guiScaler, posV * guiScaler);
        tessellator.addVertexWithUV(posX, posY, 0, posU * guiScaler, posV * guiScaler);
        tessellator.draw();
    }



    private void func_146977_a(ItemStack p_146977_1_, int xDisplayPosition, int yDisplayPosition) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        itemRender.renderItemAndEffectIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), p_146977_1_, xDisplayPosition, yDisplayPosition);
        itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, this.mc.getTextureManager(), p_146977_1_, xDisplayPosition, yDisplayPosition, null);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    @Override
    protected void mouseMovedOrUp(int mouseX, int mouseY, int state) {
        super.mouseMovedOrUp(mouseX, mouseY, state);
        pageTurnConsumed = false;
    }

    @Override
    public void keyTyped(char eventChar, int eventKey) {
        if (isCloseKey(eventKey) && (isTrainPage || isEscrowPage)) {
            goBack();
            return;
        }
        if (!isTrainPage && !isEscrowPage) {
            if (isEscapeKey(eventKey)) { // If "ESC", exit from the GUI.
                if (searchQuery.isEmpty()) { // If search query is empty, exit.
                    this.mc.displayGuiScreen(null);
                    this.mc.setIngameFocus();
                } else { // If there is a search query, clear it.
                    setPage(0);
                    searchQuery = "";
                    searchIndex = 0;
                }
            } else if (isEnterKey(eventChar, eventKey)) {
                searchIndex = getPlayerSearchResultIndex();
                openPlayerSearchResult();
            } else if (eventChar != '\u0000') { // If character is not a modifier key...
                if (eventChar == '\b') { // If character is backspace...
                    searchQuery = searchQuery.substring(0, Math.max(0, searchQuery.length() - 1));
                    if (searchQuery.isEmpty()) {
                        searchIndex = 0;
                    }
                }
                else { // If character is not a backspace...
                    searchQuery += Character.toString(eventChar);
                }
                searchIndex = getPlayerSearchResultIndex();
                if (searchIndex >= 0) {
                    setPage(searchIndex / getEntriesPerPage());
                }
            }
            initGui();
        }
    }

    private boolean isEnterKey(char eventChar, int eventKey) {
        return eventChar == '\r' || eventChar == '\n' || eventKey == Keyboard.KEY_RETURN || eventKey == Keyboard.KEY_NUMPADENTER;
    }

    private boolean isEscapeKey(int eventKey) {
        return eventKey == 1;
    }

    private void openPlayerSearchResult() {
        if (searchQuery.length() == 0) {
            return;
        }
        int resultIndex = getPlayerSearchResultIndex();
        if (resultIndex >= 0 && resultIndex < list.length) {
            Traincraft.keyChannel.sendToServer(new PacketAdminBookClient(list[resultIndex], Minecraft.getMinecraft().thePlayer.getEntityId()));
        }
    }

    private int getPlayerSearchResultIndex() {
        if (list == null || searchQuery.length() == 0) {
            return -1;
        }
        String lowerQuery = searchQuery.toLowerCase();
        for (int i = 0; i < list.length; i++) {
            if (getPlayerSearchText(list[i]).startsWith(lowerQuery)) {
                return i;
            }
        }
        for (int i = 0; i < list.length; i++) {
            if (isPlayerSearchMatch(list[i], lowerQuery)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isPlayerSearchMatch(String entry, String lowerQuery) {
        return getPlayerSearchText(entry).contains(lowerQuery);
    }

    private String getPlayerSearchText(String entry) {
        if (entry == null || entry.length() == 0) {
            return "";
        }
        if (entry.startsWith("backup:")) {
            entry = entry.substring("backup:".length());
        }
        int slash = Math.max(entry.lastIndexOf("/"), entry.lastIndexOf("\\"));
        String name = slash >= 0 ? entry.substring(slash + 1) : entry;
        return name.toLowerCase();
    }

    private boolean isCloseKey(int eventKey) {
        return isEscapeKey(eventKey);
    }

}
