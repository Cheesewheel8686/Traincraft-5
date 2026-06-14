package train.client.gui;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import train.common.api.DieselTrain;
import train.common.api.ElectricTrain;
import train.common.api.LiquidManager;
import train.common.api.Locomotive;
import train.common.api.SteamTrain;
import train.common.inventory.InventoryLoco;
import train.common.library.Info;

public class GuiLoco2 extends AbstractGuiLocomotive
{
    public GuiLoco2(InventoryPlayer inventory, Entity entity) {
        this(inventory, (Locomotive) entity);
    }

    public GuiLoco2(InventoryPlayer inventory, Locomotive locomotive) {
        super(new InventoryLoco(inventory, locomotive), locomotive);
    }

    @Override
    protected String getGuiTexture() {
        if (loco instanceof ElectricTrain) {
            return Info.guiPrefix + "gui_tram.png";
        }
        if (loco instanceof SteamTrain) {
            return Info.guiPrefix + "gui_loco_steam.png";
        }
        if (loco instanceof DieselTrain) {
            return Info.guiPrefix + "gui_loco_diesel.png";
        }
        return Info.guiPrefix + "gui_loco.png";
    }

    @Override
    protected void drawLocomotiveContents(int left, int top, String guiTexture) {
        if (loco instanceof SteamTrain) {
            SteamTrain steam = (SteamTrain) loco;
            int capacity = steam.getCartTankCapacity();
            int waterHeight = capacity > 0 ? Math.abs((steam.getWaterAmount() * 50) / capacity) : 0;

            if (steam.getLiquidItemID() == LiquidManager.WATER_FILTER.getFluidID()) {
                drawTexturedModalRect(
                        left + 143,
                        (top + 68) - waterHeight,
                        190,
                        69 - waterHeight,
                        18,
                        waterHeight + 1
                );
            }

            drawFuelIndicator(left + 8, top, 176);
            return;
        }

        if (loco instanceof DieselTrain) {
            DieselTrain diesel = (DieselTrain) loco;
            int capacity = diesel.getCartTankCapacity();
            int fuelHeight = capacity > 0 ? Math.abs((diesel.getDiesel() * 50) / capacity) : 0;

            drawTexturedModalRect(
                    left + 143,
                    (top + 68) - fuelHeight,
                    192,
                    120 - fuelHeight,
                    18,
                    fuelHeight
            );

            if (loco.getIsFuelled()) {
                int fuel = loco.getFuelDiv(12);
                drawTexturedModalRect(left + 10, (top + 49) - fuel, 178, 12 - fuel, 14, fuel + 2);
            }
            return;
        }

        for (int column = loco.numCargoSlots; column < 5; column++) {
            drawTexturedModalRect(left + 79 + 18 * column, top + 17, 190, 0, 18, 18);
        }
        for (int column = loco.numCargoSlots1; column < 5; column++) {
            drawTexturedModalRect(left + 79 + 18 * column, top + 35, 190, 0, 18, 18);
        }
        for (int column = loco.numCargoSlots2; column < 5; column++) {
            drawTexturedModalRect(left + 79 + 18 * column, top + 53, 190, 0, 18, 18);
        }

        drawFuelIndicator(left + 8, top, 176);
    }

    private void drawFuelIndicator(int x, int top, int textureX) {
        if (!loco.getIsFuelled()) {
            return;
        }

        int fuel = loco.getFuelDiv(12);
        drawTexturedModalRect(x, (top + 48) - fuel, textureX, 12 - fuel, 14, fuel + 2);
    }
}
