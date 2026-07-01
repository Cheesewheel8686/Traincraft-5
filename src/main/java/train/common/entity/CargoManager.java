package train.common.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tmt.Tessellator;
import train.common.api.AbstractTrains;

/**
 * @author 02skaplan
 * <p>Stores and retrieves cargo information for carts with changable display models & textures.</p>
 */
public class CargoManager {
    private int selectedCargo = 0;
    private int defaultOverride = -1;
    private boolean unselectedSlotEnabled = true;

    public int GetDefaultOverride()
    {
        return defaultOverride;
    }

    private final CargoSpecification[][] cargoSpecificationList;

    private ModelBase[] renderModels;

    /**
     * @author 02skaplan
     * @param cargoSpecificationList List of CargoSpecifications detailing possible cargo options.
     */
    public CargoManager(CargoSpecification[][] cargoSpecificationList) {
        this.cargoSpecificationList = cargoSpecificationList;
    }

    public CargoManager setDefaultOverride(int defaultCargoState)
    {
        this.defaultOverride = defaultCargoState;
        return this;
    }

    public CargoManager disableUnselectedSlot()
    {
        this.unselectedSlotEnabled = false;
        this.defaultOverride = 1;
        if (selectedCargo == 0) {
            setSelectedCargo(defaultOverride);
        }
        return this;
    }

    public boolean isUnselectedSlotEnabled()
    {
        return unselectedSlotEnabled;
    }

    public boolean isValidCargoSelection(int selectedCargo)
    {
        int minimumSelection = unselectedSlotEnabled ? 0 : 1;
        return selectedCargo >= minimumSelection && selectedCargo < getCargoSpecificationList().length + 1;
    }

    public CargoSpecification[][] getCargoSpecificationList() {
        return cargoSpecificationList;
    }

    public int getSelectedCargo()
    {
        return selectedCargo;
    }

    public void setSelectedCargo(int selectedCargo) {
        if (!isValidCargoSelection(selectedCargo)) {
            return;
        }
        this.selectedCargo = selectedCargo;

        // Clear old cache whenever the selected cargo changes.
        renderModels = null;

        if (selectedCargo > 0) {
            int cargoIndex = selectedCargo - 1;

            if (cargoIndex >= 0 && cargoIndex < getCargoSpecificationList().length) {
                CargoSpecification[] specs = getCargoSpecificationList()[cargoIndex];

                if (specs != null) {
                    renderModels = new ModelBase[specs.length];

                    for (int i = 0; i < renderModels.length; i++) {
                        if (specs[i] != null) {
                            renderModels[i] = specs[i].getModel();
                        }
                    }
                }
            }
        }
    }

    /**
     * @author 02skaplan
     * <p>This method needs to be called at the bottom of the render method of the model file of a supported entity.</p>
     * @param entity Train entity on which to render the cargo.
     */
    public void renderCargo(AbstractTrains entity, float f, float f1, float f2, float f3, float f4, float f5) {
        if (getSelectedCargo() > 0) {
            int cargoNumber = getSelectedCargo();

            // This if statement should always be activated, but is useful in case a CargoSpec is removed from the list.
            if (cargoNumber - 1 < cargoSpecificationList.length)
            {
                CargoSpecification[] specs = getCargoSpecificationList()[cargoNumber - 1];
                for (int i = 0; i < specs.length; i++)
                {
                    CargoSpecification spec = specs[i];

                    if (!spec.textureFile.isEmpty()) {
                        Tessellator.bindTexture(
                                new ResourceLocation(
                                        spec.resourceDomain,
                                        "textures/" + spec.textureFile + ".png"
                                )
                        );
                    }

                    GL11.glPushMatrix();
                    CargoSpecification.RenderParameters renderParameters = spec.renderParameters;
                    GL11.glTranslated(
                            renderParameters.getOffsetX(),
                            renderParameters.getOffsetY() - 3,
                            renderParameters.getOffsetZ()
                    );
                    GL11.glScaled(
                            renderParameters.getScaleX() + 1,
                            renderParameters.getScaleY() + 1,
                            renderParameters.getScaleZ() + 1
                    );
                    for (CargoSpecification.RenderParameters.Rotation rotation : renderParameters.getRotations()) {
                        GL11.glRotated(
                                rotation.rotateAngle,
                                rotation.rotateX ? 1 : 0,
                                rotation.rotateY ? 1 : 0,
                                rotation.rotateZ ? 1 : 0
                        );
                    }
                    renderModels[i].render(entity, f, f1, f2, f3, f4, f5);
                    GL11.glPopMatrix();
                }
            }
        }
    }

    public void exportToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger("cargoLoad", selectedCargo);
    }

    public void importFromNBTTagCompound(NBTTagCompound nbtTagCompound) {
        if (nbtTagCompound.hasKey("cargoLoad")) {
            if (nbtTagCompound.getInteger("cargoLoad") != 0) {
                setSelectedCargo(nbtTagCompound.getInteger("cargoLoad"));
            }
        }
    }
}
