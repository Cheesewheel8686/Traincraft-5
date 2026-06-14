package train.common.library.register;

import net.minecraft.item.Item;
import train.common.api.stock.TenderStorageMode;
import train.common.api.stock.TenderStoragePolicy;
import train.common.library.EnumTrainType;

public class TenderRecord extends TrainRecord
{
    public static final int DUAL_CHAMBER_INVENTORY_SIZE = 5;
    public static final int COAL_BUNKER_INVENTORY_SIZE = 16;

    public TenderRecord(String internalName, Class entityClass, Item item, EnumTrainType enumTrainType, double mass, String[] colors, int guiRenderScale)
    {
        super(internalName, entityClass, item, enumTrainType, mass, colors, guiRenderScale);
    }

    public TenderRecord(String internalName, Class entityClass, Item item, String trainType, double mass, String[] colors, int guiRenderScale)
    {
        super(internalName, entityClass, item, trainType, mass, colors, guiRenderScale);
    }

    public TenderRecord(String internalName)
    {
        super(internalName);
    }

    public TenderRecord(String internalName, Class entityClass, Item item)
    {
        super(internalName, entityClass, item);
    }


    private TenderStoragePolicy tenderStoragePolicy = TenderStoragePolicy.COAL_BUNKER_ONLY;
    private TenderStorageMode defaultTenderStorageMode = TenderStorageMode.COAL_BUNKER;

    public TenderStoragePolicy getTenderStoragePolicy()
    {
        return tenderStoragePolicy;
    }

    public TenderRecord setTenderStoragePolicy(TenderStoragePolicy tenderStoragePolicy)
    {
        if (tenderStoragePolicy == null)
        {
            this.tenderStoragePolicy = TenderStoragePolicy.COAL_BUNKER_ONLY;
        }
        else
        {
            this.tenderStoragePolicy = tenderStoragePolicy;
        }

        return this;
    }

    public TenderStorageMode getDefaultTenderStorageMode()
    {
        switch (tenderStoragePolicy)
        {
            case DUAL_CHAMBER_ONLY:
                return TenderStorageMode.DUAL_CHAMBER;

            case SWITCHABLE:
                return defaultTenderStorageMode;

            case COAL_BUNKER_ONLY:
            default:
                return TenderStorageMode.COAL_BUNKER;
        }
    }

    public TenderRecord setDefaultTenderStorageMode(TenderStorageMode defaultTenderStorageMode)
    {
        if (defaultTenderStorageMode == null)
        {
            this.defaultTenderStorageMode = TenderStorageMode.COAL_BUNKER;
        }
        else
        {
            this.defaultTenderStorageMode = defaultTenderStorageMode;
        }

        return this;
    }

    public int getSecondaryTankCapacity()
    {
        return super.getSecondaryTankCapacity();
    }

    public TenderRecord setSecondaryTankCapacity(int secondaryTankCapacity)
    {
        super.setSecondaryCapacity(Math.max(0, secondaryTankCapacity));
        return this;
    }

    @Override
    public TenderRecord setSecondaryCapacity(int secondaryTankCapacity)
    {
        return setSecondaryTankCapacity(secondaryTankCapacity);
    }

    public int getDualChamberInventorySize()
    {
        return DUAL_CHAMBER_INVENTORY_SIZE;
    }

    public int getCoalBunkerInventorySize()
    {
        return COAL_BUNKER_INVENTORY_SIZE;
    }

    public boolean allowsTenderStorageMode(TenderStorageMode mode)
    {
        if (mode == null)
        {
            return false;
        }

        switch (tenderStoragePolicy)
        {
            case DUAL_CHAMBER_ONLY:
                return mode == TenderStorageMode.DUAL_CHAMBER;

            case COAL_BUNKER_ONLY:
                return mode == TenderStorageMode.COAL_BUNKER;

            case SWITCHABLE:
            default:
                return true;
        }
    }

    public boolean supportsDualChamberTender()
    {
        return allowsTenderStorageMode(TenderStorageMode.DUAL_CHAMBER)
                && getSecondaryTankCapacity() > 0;
    }

    public boolean supportsCoalBunkerTender()
    {
        return allowsTenderStorageMode(TenderStorageMode.COAL_BUNKER);
    }

    public boolean isTenderStorageSwitchable()
    {
        return tenderStoragePolicy == TenderStoragePolicy.SWITCHABLE;
    }

}
