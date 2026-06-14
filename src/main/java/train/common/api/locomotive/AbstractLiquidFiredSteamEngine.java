package train.common.api.locomotive;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import train.common.api.INoFuelTransferEntity;
import train.common.api.LiquidManager;
import train.common.api.LiquidTank;
import train.common.api.Tender;
import train.common.entity.rollingStock.EntityBUnitDD35;
import train.common.slots.IFluidContainerSlotValidator;

import static train.common.api.LiquidManager.dieselFilter;

/**
 * Liquid-fired steam locomotive.
 *
 * This is a steam locomotive with:
 * - inherited boiler/water tank from AbstractBoilerLocomotive
 * - separate liquid fuel tank
 *
 * It does NOT inherit from SteamTrain, so it does not inherit:
 * - coal consumption
 * - wood consumption
 * - tender solid fuel scanning
 */
public abstract class AbstractLiquidFiredSteamEngine extends AbstractBoilerLocomotive implements IFluidContainerSlotValidator {

    private static final String NBT_LIQUID_FUEL_TANK = "LiquidFiredSteamFuelTank";

    private static final int DW_SECONDARY_TANK_STRING = 29;

    private final LiquidManager.FilteredTank liquidFuelTank;

    private int fluidContainerUpdate = 8;
    private int coolantContainerUpdate = 8;

    public AbstractLiquidFiredSteamEngine(World world) {
        this(world, null);
    }

    public AbstractLiquidFiredSteamEngine(World world, FluidStack waterFilter) {
        super(world, waterFilter);

        liquidFuelTank = LiquidManager.getInstance().new FilteredTank(
                getSecondaryTankCapacity(),
                dieselFilter()
        );
        this.dataWatcher.addObject(DW_SECONDARY_TANK_STRING, buildSecondaryTankSyncString());

        numCargoSlots = 3;
        numCargoSlots1 = 0;
        numCargoSlots2 = 0;
        inventorySize = 5;
        locoInvent = new ItemStack[inventorySize];


        this.fuelTrain = 0;
    }

    protected int getLiquidFuelTransferAmount(Fluid fluid) {
        if (fluid == LiquidManager.REFINED_FUEL) {
            return 50;
        }

        return 100;
    }

    public LiquidManager.StandardTank getLiquidFuelTank() {
        return liquidFuelTank;
    }

    public FluidStack getLiquidFuel() {
        return liquidFuelTank.getFluid();
    }

    public int getLiquidFuelAmount() {
        return getSecondaryTankAmount();
    }

    public int getSecondaryTankCapacity()
    {
        return trainSpec.getSecondaryTankCapacity();
    }

    public int getLiquidFuelItemID() {
        FluidStack fuel = liquidFuelTank.getFluid();
        return fuel != null && fuel.getFluid() != null ? fuel.getFluid().getID() : 0;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if (worldObj.isRemote)
        {
            return;
        }

        syncFuelTrainFromLiquidTank();
        syncLiquidFuelTankToClient();
    }

    private void syncLiquidFuelTankToClient() {
        this.fuelTrain = liquidFuelTank.getFluidAmount();

        if (!worldObj.isRemote) {
            this.dataWatcher.updateObject(
                    DW_SECONDARY_TANK_STRING,
                    buildSecondaryTankSyncString()
            );

            /*
             * 24 is already registered by Locomotive.
             * We only update it, never add it.
             */
            this.dataWatcher.updateObject(24, this.fuelTrain);
        }
    }

    private void syncFuelTrainFromLiquidTank() {
        this.fuelTrain = liquidFuelTank.getFluidAmount();
    }

    @Override
    protected void checkBoilerInventory(ItemStack fuelSlotStack, ItemStack waterSlotStack) {
        if (!this.canCheckInvent) {
            return;
        }

        if (fuelSlotStack != null) {
            processFilteredContainerInSlot(
                    FUEL_SLOT_INDEX,
                    fuelSlotStack,
                    liquidFuelTank,
                    true
            );
        }

        if (waterSlotStack != null) {
            processFilteredContainerInSlot(
                    WATER_SLOT_INDEX,
                    waterSlotStack,
                    coolantTank,
                    false
            );
        }

        if (isLocoTurnedOn() && ticksExisted % 10 == 0) {
            pullWaterFromNearbyAndLinkedTenders();
            pullLiquidFuelFromSources();
        }

        if (getFuel() <= 0) {
            motionX *= 0.88;
            motionZ *= 0.88;
        }
    }

    protected void processFilteredContainerInSlot(
            int slot,
            ItemStack itemstack,
            LiquidManager.StandardTank targetTank,
            boolean fuelSlot
    ) {
        if (worldObj.isRemote) {
            return;
        }

        if (fuelSlot) {
            this.fluidContainerUpdate++;

            if (this.fluidContainerUpdate % 8 != 0) {
                return;
            }
        }
        else {
            this.coolantContainerUpdate++;

            if (this.coolantContainerUpdate % 8 != 0) {
                return;
            }
        }

        if (itemstack == null || targetTank == null) {
            return;
        }

        FluidStack contained = FluidContainerRegistry.getFluidForFilledItem(itemstack);

        if (contained != null && !targetTank.acceptsFluid(contained)) {
            return;
        }

        if (contained == null && !FluidContainerRegistry.isEmptyContainer(itemstack)) {
            return;
        }

        ItemStack result = LiquidManager.getInstance().processContainer(
                this,
                slot,
                new SingleTankFluidHandler(targetTank),
                itemstack
        );

        if (result != null) {
            placeReturnedContainer(result, 2);
        }
    }

    private String buildSecondaryTankSyncString() {
        FluidStack fuel = liquidFuelTank != null ? liquidFuelTank.getFluid() : null;

        if (fuel == null || fuel.getFluid() == null) {
            return NULL_FLUID_NAME + FLUID_SYNC_SEPARATOR + "0";
        }

        return fuel.getFluid().getName()
                + FLUID_SYNC_SEPARATOR
                + fuel.amount;
    }

    @Override
    public boolean isContainerValidForInputSlot(int slot, ItemStack stack) {
        LiquidManager.StandardTank targetTank = getInputTank(slot);
        return targetTank != null && isContainerAcceptedByTank(targetTank, stack);
    }

    private boolean isContainerAcceptedByTank(LiquidManager.StandardTank tank, ItemStack stack) {
        if (stack == null) {
            return false;
        }

        if (FluidContainerRegistry.isEmptyContainer(stack)) {
            return true;
        }

        FluidStack contained = FluidContainerRegistry.getFluidForFilledItem(stack);
        return contained != null && tank.acceptsFluid(contained);
    }

    private LiquidManager.StandardTank getInputTank(int slot) {
        if (slot == FUEL_SLOT_INDEX) {
            return liquidFuelTank;
        }

        if (slot == WATER_SLOT_INDEX) {
            return coolantTank;
        }

        return null;
    }

    protected boolean isAcceptedLiquidSteamFuel(FluidStack stack) {
        if (stack == null || stack.getFluid() == null) {
            return false;
        }

        return isAcceptedLiquidSteamFuel(stack.getFluid());
    }

    protected boolean isAcceptedLiquidSteamFuel(Fluid fluid) {
        if (fluid == null) {
            return false;
        }

        FluidStack[] accepted = liquidFuelTank.getMultiFilter();

        for (int i = 0; i < accepted.length; i++) {
            if (accepted[i] != null && accepted[i].getFluid() == fluid) {
                return true;
            }
        }

        return false;
    }

    private class SingleTankFluidHandler implements IFluidHandler {
        private final LiquidManager.StandardTank tank;

        private SingleTankFluidHandler(LiquidManager.StandardTank tank) {
            this.tank = tank;
        }

        @Override
        public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
            return resource == null ? 0 : tank.fill(resource, doFill);
        }

        @Override
        public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
            if (resource == null || tank.getFluid() == null || !resource.isFluidEqual(tank.getFluid())) {
                return null;
            }

            return tank.drain(resource.amount, doDrain);
        }

        @Override
        public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
            return tank.drain(maxDrain, doDrain);
        }

        @Override
        public boolean canFill(ForgeDirection from, Fluid fluid) {
            return fluid != null && tank.fill(new FluidStack(fluid, 1), false) > 0;
        }

        @Override
        public boolean canDrain(ForgeDirection from, Fluid fluid) {
            return fluid != null && tank.getFluid() != null && tank.getFluid().getFluid() == fluid;
        }

        @Override
        public FluidTankInfo[] getTankInfo(ForgeDirection from) {
            return new FluidTankInfo[] { tank.getInfo() };
        }
    }

    @Override
    protected void updateFuelTrain(int amount) {
        if (!this.isLocoTurnedOn() && !this.canBePulled) {
            motionX *= 0.8;
            motionZ *= 0.8;
            return;
        }

        if (this.isLocoTurnedOn() && liquidFuelTank.getFluidAmount() > 0) {
            liquidFuelTank.drain(amount, true);
            syncFuelTrainFromLiquidTank();

            if (fuelTrain <= 0) {
                fuelTrain = 0;
            }
        }
    }

    private void pullLiquidFuelFromSources() {
        pullLiquidFuelFromLinkedTender(cartLinked1);
        pullLiquidFuelFromLinkedTender(cartLinked2);

        pullLiquidFuelFromNearbyFluidHandlers();

        pullLiquidFuelFromLinkedLiquidTank(cartLinked1);
        pullLiquidFuelFromLinkedLiquidTank(cartLinked2);
    }

    private void pullLiquidFuelFromLinkedTender(Object linkedCart) {
        if (!(linkedCart instanceof Tender)) {
            return;
        }

        IFluidHandler handler = (IFluidHandler) linkedCart;
        FluidStack drained = tryDrainAcceptedFuel(handler, ForgeDirection.UNKNOWN, true);

        if (drained != null) {
            liquidFuelTank.fill(drained, true);
            syncFuelTrainFromLiquidTank();
        }
    }

    private void pullLiquidFuelFromLinkedLiquidTank(Object linkedCart) {
        if (!(linkedCart instanceof LiquidTank)) {
            return;
        }

        if (linkedCart instanceof INoFuelTransferEntity) {
            return;
        }

        if (linkedCart instanceof EntityBUnitDD35) {
            return;
        }

        IFluidHandler handler = (IFluidHandler) linkedCart;
        FluidStack drained = tryDrainAcceptedFuel(handler, ForgeDirection.UNKNOWN, true);

        if (drained != null) {
            liquidFuelTank.fill(drained, true);
            syncFuelTrainFromLiquidTank();
        }
    }

    private void pullLiquidFuelFromNearbyFluidHandlers() {
        if (!canAcceptAnyLiquidFuel()) {
            return;
        }

        FluidStack drain = null;

        blocksToCheck = new TileEntity[] {
                worldObj.getTileEntity(MathHelper.floor_double(posX), MathHelper.floor_double(posY - 1), MathHelper.floor_double(posZ)),
                worldObj.getTileEntity(MathHelper.floor_double(posX), MathHelper.floor_double(posY + 2), MathHelper.floor_double(posZ)),
                worldObj.getTileEntity(MathHelper.floor_double(posX), MathHelper.floor_double(posY + 3), MathHelper.floor_double(posZ)),
                worldObj.getTileEntity(MathHelper.floor_double(posX), MathHelper.floor_double(posY + 4), MathHelper.floor_double(posZ))
        };

        for (TileEntity block : blocksToCheck) {
            if (drain != null || !(block instanceof IFluidHandler)) {
                continue;
            }

            IFluidHandler handler = (IFluidHandler) block;

            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                drain = tryDrainAcceptedFuel(handler, direction, true);

                if (drain != null) {
                    break;
                }
            }
        }

        if (drain != null) {
            liquidFuelTank.fill(drain, true);
            syncFuelTrainFromLiquidTank();
        }
    }

    private boolean canAcceptAnyLiquidFuel() {
        FluidStack[] fuels = liquidFuelTank.getMultiFilter();

        for (int i = 0; i < fuels.length; i++) {
            FluidStack fuel = fuels[i];

            if (fuel == null || fuel.getFluid() == null) {
                continue;
            }

            int amount = getLiquidFuelTransferAmount(fuel.getFluid());
            FluidStack test = new FluidStack(fuel.getFluid(), amount);

            if (liquidFuelTank.fill(test, false) > 0) {
                return true;
            }
        }

        return false;
    }

    private FluidStack tryDrainAcceptedFuel(IFluidHandler handler, ForgeDirection direction, boolean doDrain) {
        if (handler == null) {
            return null;
        }

        FluidStack[] accepted = liquidFuelTank.getMultiFilter();

        for (int i = 0; i < accepted.length; i++) {
            FluidStack acceptedFuel = accepted[i];

            if (acceptedFuel == null || acceptedFuel.getFluid() == null) {
                continue;
            }

            Fluid fluid = acceptedFuel.getFluid();
            int amount = getLiquidFuelTransferAmount(fluid);

            FluidStack request = new FluidStack(fluid, amount);
            int acceptedAmount = liquidFuelTank.fill(request, false);

            if (acceptedAmount <= 0) {
                continue;
            }

            request.amount = acceptedAmount;
            FluidStack test = handler.drain(direction, request, false);

            if (test != null
                    && test.getFluid() == fluid
                    && test.amount > 0) {
                return handler.drain(direction, request, doDrain);
            }
        }

        return null;
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (resource == null || resource.getFluid() == null) {
            return 0;
        }

        if (resource.getFluid() == FluidRegistry.WATER) {
            return super.fill(from, resource, doFill);
        }

        if (isAcceptedLiquidSteamFuel(resource)) {
            return liquidFuelTank.fill(resource, doFill);
        }

        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || resource.getFluid() == null) {
            return null;
        }

        if (resource.getFluid() == FluidRegistry.WATER) {
            return super.drain(from, resource, doDrain);
        }

        if (isAcceptedLiquidSteamFuel(resource)) {
            FluidStack currentFuel = liquidFuelTank.getFluid();

            if (currentFuel == null || !resource.isFluidEqual(currentFuel)) {
                return null;
            }

            return liquidFuelTank.drain(resource.amount, doDrain);
        }

        return null;
    }

    /**
     * Untyped drain remains WATER.
     *
     * The boiler water consumption path uses drain(from, amount, doDrain).
     */
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return super.drain(from, maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return fluid == FluidRegistry.WATER || isAcceptedLiquidSteamFuel(fluid);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return fluid == FluidRegistry.WATER || isAcceptedLiquidSteamFuel(fluid);
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] {
                super.getTank().getInfo(),
                liquidFuelTank.getInfo()
        };
    }

    @Override
    protected String BUILD_DW_TANK_DATA_STRING()
    {
        String fullTankString;
        if (coolantTank != null && coolantTank.getFluid() != null)
        {
            fullTankString = coolantTank.getFluid().getFluidID() + FLUID_SYNC_SEPARATOR + coolantTank.getFluid().amount;
        }
        else
        {
            fullTankString =  ("0" + FLUID_SYNC_SEPARATOR + "0");
        }

        fullTankString += FLUID_SYNC_SEPARATOR;

        if (liquidFuelTank != null && liquidFuelTank.getFluid() != null)
        {
            fullTankString += liquidFuelTank.getFluid().getFluidID() + FLUID_SYNC_SEPARATOR +  liquidFuelTank.getFluid().amount;
        }
        else
        {
            fullTankString += ("0" + FLUID_SYNC_SEPARATOR + "0");
        }

        return fullTankString;
    }

    public int getSecondaryLiquidId() {
        String[] fluidData = getFluidData().split(FLUID_SYNC_SEPARATOR);

        if (fluidData.length <= 2) {
            return 0;
        }

        try {
            return Integer.parseInt(fluidData[2]);
        }
        catch (NumberFormatException ignored) {
            return 0;
        }
    }

    public String getSecondaryLiquidName() {
        String[] fuelData = getSecondaryFuelData();
        return fuelData.length > 0 ? fuelData[0] : NULL_FLUID_NAME;
    }

    public int getSecondaryTankAmount() {
        String[] fuelData = getSecondaryFuelData();

        if (fuelData.length > 1) {
            try {
                return Integer.parseInt(fuelData[1]);
            }
            catch (NumberFormatException ignored) {
                // Fall through to the combined tank watcher.
            }
        }

        String[] fluidData = getFluidData().split(FLUID_SYNC_SEPARATOR);

        if (fluidData.length <= 3) {
            return 0;
        }

        try {
            return Integer.parseInt(fluidData[3]);
        }
        catch (NumberFormatException ignored) {
            return 0;
        }
    }

    private String[] getSecondaryFuelData() {
        String value = this.dataWatcher.getWatchableObjectString(DW_SECONDARY_TANK_STRING);

        if (value == null || value.length() == 0) {
            return new String[0];
        }

        return value.split(FLUID_SYNC_SEPARATOR);
    }

    @Override
    public int getFuelDiv(int i) {
        int maxFuel = getSecondaryTankCapacity();

        if (maxFuel <= 0) {
            return 0;
        }

        return (getSecondaryTankAmount() * i) / maxFuel;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);

        NBTTagCompound fuelTag = new NBTTagCompound();
        liquidFuelTank.writeToNBT(fuelTag);
        tag.setTag(NBT_LIQUID_FUEL_TANK, fuelTag);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);

        if (tag.hasKey(NBT_LIQUID_FUEL_TANK)) {
            liquidFuelTank.readFromNBT(tag.getCompoundTag(NBT_LIQUID_FUEL_TANK));
        }

        syncFuelTrainFromLiquidTank();
    }
}
