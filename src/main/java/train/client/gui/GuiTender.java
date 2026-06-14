package train.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import train.client.core.helpers.FluidRenderHelper;
import train.client.gui.specialbuttons.TransportLockGuiHandler;
import train.common.Traincraft;
import train.common.api.LiquidManager;
import train.common.api.Tender;
import train.common.core.network.PacketAddNote;
import train.common.core.network.PacketTenderStorageMode;
import train.common.api.stock.TenderStorageMode;
import train.common.inventory.InventoryTender;
import train.common.library.Info;

import java.util.Collections;

public class GuiTender extends GuiContainer {
	private static final int SECONDARY_TANK_X = 62;
	private static final int SECONDARY_TANK_Y = 19;
	private static final int SECONDARY_TANK_WIDTH = 69;
	private static final int SECONDARY_TANK_HEIGHT = 50;

	private Tender tender;
	private EntityPlayer player;
	private GuiButton storageModeButton;

	public GuiTender(EntityPlayer player, InventoryPlayer inventoryplayer, Entity entityminecart) {
		super(new InventoryTender(inventoryplayer, (Tender) entityminecart));
		tender = (Tender) entityminecart;
		this.player = player;
	}

	@Override
	public void initGui() {
		super.initGui();
		buttonList.clear();
		storageModeButton = null;
		int var1 = (this.width-xSize) / 2;
		int var2 = (this.height-ySize) / 2;

		GuiButton lockButton = TransportLockGuiHandler.createLockButton(
				tender,
				player,
				var1,
				var2,
				124,
				-10,
				51
		);

		if (lockButton != null) {
			this.buttonList.add(lockButton);
		}

		if (tender.isStorageModeSwitchAvailable()) {
			String modeLabel = tender.isDualChamberMode() ? "Use Coal Bunker" : "Use Dual Tanks";
			storageModeButton = new GuiButton(4, var1 + 8, var2 - 10, 110, 10, modeLabel);
			this.buttonList.add(storageModeButton);
		}

		tender.guiTCTextFieldTrainNote = new GuiTCTextField(fontRendererObj, width/2 - 85, var2 - 26, 170,15);
		tender.guiTCTextFieldTrainNote.setText(tender.getTrainNote());
	}

	@Override
	protected void actionPerformed(GuiButton guibutton)
	{
		switch (guibutton.id)
		{
			case 3:
				TransportLockGuiHandler.handleLockButton(this, guibutton, player, tender, isShiftKeyDown());
			break;
			case 4:
				TenderStorageMode nextMode = tender.isDualChamberMode()
						? TenderStorageMode.COAL_BUNKER
						: TenderStorageMode.DUAL_CHAMBER;
				Traincraft.modChannel.sendToServer(new PacketTenderStorageMode(tender.getEntityId(), nextMode));
				player.closeScreen();
			break;
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);

		fontRendererObj.drawString(tender.getCommandSenderName(), 34, 1, 0x000000);
		fontRendererObj.drawString(tender.getCommandSenderName(), 36, 3, 0x000000);
		fontRendererObj.drawString(tender.getCommandSenderName(), 34, 3, 0x000000);
		fontRendererObj.drawString(tender.getCommandSenderName(), 36, 1, 0x000000);

		fontRendererObj.drawString(tender.getCommandSenderName(), 34, 2, 0x000000);
		fontRendererObj.drawString(tender.getCommandSenderName(), 36, 2, 0x000000);
		fontRendererObj.drawString(tender.getCommandSenderName(), 35, 3, 0x000000);
		fontRendererObj.drawString(tender.getCommandSenderName(), 35, 1, 0x000000);
		fontRendererObj.drawString(tender.getCommandSenderName(), 35, 2, 0xd3a900);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		if (intersectsWith(i, j)) {
			drawCreativeTabHoveringText("When a tender is locked,", i, j);
		}
	}
	
	@Override
	protected void drawCreativeTabHoveringText(String str, int t, int g) {

		//int liqui = (dieselInventory.getLiquidAmount() * 50) / dieselInventory.getTankCapacity();
		String state = "";
		if (tender.getTrainLockedFromPacket()) {
			if (tender.getTransportOwner().equalsIgnoreCase(player.getDisplayName()))
				state = "Locked";
			else if (tender.isPlayerTrusted(player.getDisplayName()))
				if (tender.isPlayerTrustedToBreak(player.getDisplayName()))
					state = "Trusted Access+";
				else
					state = "Trusted Access";
		} else
			state = "Unlocked";
		
		int textWidth = fontRendererObj.getStringWidth("the GUI, change speed, destroy it.");
		int startX = 90;
		int startY = 5;

		int i4 = 0xf0100010;
		drawGradientRect(startX - 3, startY - 4, startX + textWidth + 3, startY + 8 + 4 + 40, i4, i4);
		drawGradientRect(startX - 4, startY - 3, startX + textWidth + 4, startY + 8 + 3 + 40, i4, i4);
		int colour1 = 0x505000ff;
		int colour2 = (colour1 & 0xfefefe) >> 1 | colour1 & 0xff000000;
		drawGradientRect(startX - 3, startY - 3, startX + textWidth + 3, startY + 8 + 3 + 40, colour1, colour2);
		drawGradientRect(startX - 2, startY - 2, startX + textWidth + 2, startY + 8 + 2 + 40, i4, i4);
		fontRendererObj.drawStringWithShadow(str, startX, startY, -1);
		fontRendererObj.drawStringWithShadow("only its owner can open", startX, startY + 10, -1);
		fontRendererObj.drawStringWithShadow("the GUI and destroy it.", startX, startY + 20, -1);
		fontRendererObj.drawStringWithShadow("Current state: "+state, startX, startY+30, -1);
		fontRendererObj.drawStringWithShadow("Owner: "+tender.getTransportOwner().trim(), startX, startY+40, -1);
	}
	public boolean intersectsWith(int mouseX, int mouseY) {
		//System.out.println(mouseX+" "+mouseY);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		return  (mouseX >= j + 124 && mouseX <= j + 174 && mouseY >= k-10 && mouseY <= k);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float par3){
		super.drawScreen(mouseX, mouseY,par3);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		if (mouseX>j+143 && mouseX<j+161 && mouseY>k+18 && mouseY<k+68){
			drawHoveringText(Collections.singletonList("Water: " + (tender.getWater()) + "mb / " + (tender.getCartTankCapacity()) +"mb"),
					mouseX, mouseY, fontRendererObj);
		}

		if (tender.isDualChamberMode()
				&& mouseX > j + SECONDARY_TANK_X
				&& mouseX < j + SECONDARY_TANK_X + SECONDARY_TANK_WIDTH
				&& mouseY > k + SECONDARY_TANK_Y
				&& mouseY < k + SECONDARY_TANK_Y + SECONDARY_TANK_HEIGHT) {
			int amount = tender.getSyncedSecondaryFluidAmount();
			int capacity = tender.getSecondaryTankCapacity();
			Fluid fluid = FluidRegistry.getFluid(tender.getSyncedSecondaryFluidId());
			String fluidName = fluid == null
					? "Fuel"
					: fluid.getLocalizedName(new FluidStack(fluid, Math.max(1, amount)));

			drawHoveringText(Collections.singletonList(
					fluidName + ": " + amount + "mb / " + capacity + "mb"
			), mouseX, mouseY, fontRendererObj);
		}

		tender.guiTCTextFieldTrainNote.drawTextBox();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int t, int g) {
		String i = Info.guiPrefix + (tender.isDualChamberMode()
				? "gui_dualchambertender.png"
				: "gui_tender.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation,i));
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

		if (tender != null) {
			int load = (tender.getWater());
			int lo = Math.abs(((load * 50) / (tender.getCartTankCapacity())));

			if (tender.getLiquidItemID() == LiquidManager.WATER_FILTER.getFluidID()) {

				drawTexturedModalRect(j + 143, (k + 69) - lo, 190, 69 - lo, 18, lo);
			}

			if (tender.isDualChamberMode()) {
				drawSecondaryTankFluid(j, k, i);
			}
		}
	}

	private void drawSecondaryTankFluid(int guiLeft, int guiTop, String guiTexture) {
		int amount = tender.getSyncedSecondaryFluidAmount();
		int capacity = tender.getSecondaryTankCapacity();
		Fluid fluid = FluidRegistry.getFluid(tender.getSyncedSecondaryFluidId());

		if (fluid == null || amount <= 0 || capacity <= 0) {
			return;
		}

		int fillHeight = Math.min(
				SECONDARY_TANK_HEIGHT,
				Math.max(1, (amount * SECONDARY_TANK_HEIGHT) / capacity)
		);

		IIcon icon = FluidRenderHelper.getFluidTexture(fluid, false);

		if (icon == null) {
			return;
		}

		mc.renderEngine.bindTexture(FluidRenderHelper.getFluidSheet(fluid));
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.72F);

		int x = guiLeft + SECONDARY_TANK_X;
		int y = guiTop + SECONDARY_TANK_Y + SECONDARY_TANK_HEIGHT - fillHeight;
		drawFluidArea(x, y, SECONDARY_TANK_WIDTH, fillHeight, icon);

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, guiTexture));
	}

	private void drawFluidArea(int x, int y, int width, int height, IIcon icon) {
		for (int remainingHeight = height, drawY = y + height; remainingHeight > 0;) {
			int tileHeight = Math.min(16, remainingHeight);
			drawY -= tileHeight;

			for (int remainingWidth = width, drawX = x; remainingWidth > 0;) {
				int tileWidth = Math.min(16, remainingWidth);
				drawFluidIcon(drawX, drawY, tileWidth, tileHeight, icon);
				drawX += tileWidth;
				remainingWidth -= tileWidth;
			}

			remainingHeight -= tileHeight;
		}
	}

	private void drawFluidIcon(int x, int y, int width, int height, IIcon icon) {
		double minU = icon.getMinU();
		double minV = icon.getMinV();
		double maxU = minU + ((icon.getMaxU() - minU) * width / 16.0D);
		double maxV = minV + ((icon.getMaxV() - minV) * height / 16.0D);
		Tessellator tessellator = Tessellator.instance;

		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, y + height, zLevel, minU, maxV);
		tessellator.addVertexWithUV(x + width, y + height, zLevel, maxU, maxV);
		tessellator.addVertexWithUV(x + width, y, zLevel, maxU, minV);
		tessellator.addVertexWithUV(x, y, zLevel, minU, minV);
		tessellator.draw();
	}

	@Override
	public void updateScreen() {
		super.updateScreen();

		if (storageModeButton != null && !tender.isStorageModeSwitchAvailable()) {
			storageModeButton.visible = false;
			storageModeButton.enabled = false;
		}

		if (tender.guiTCTextFieldTrainNote.isFocused()) {
			tender.guiTCTextFieldTrainNote.updateCursorCounter();
		}
	}

	@Override
	protected void keyTyped(char par1, int par2) {


		if (tender.guiTCTextFieldTrainNote.isFocused()) {
			tender.guiTCTextFieldTrainNote.textboxKeyTyped(par1, par2);
		} else if (par1 == 1 || (par2 == this.mc.gameSettings.keyBindInventory.getKeyCode() || par2 == Keyboard.KEY_ESCAPE)){
			Traincraft.lockChannel.sendToServer(new PacketAddNote(tender.getEntityId(), tender.guiTCTextFieldTrainNote.getText()));
			mc.thePlayer.closeScreen();
		} else {
			super.keyTyped(par1, par2);
		}
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		tender.guiTCTextFieldTrainNote.mouseClicked(par1, par2, par3);
		super.mouseClicked(par1, par2, par3);
	}
}
