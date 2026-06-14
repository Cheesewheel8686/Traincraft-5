package train.common.api.stock;

public enum TenderStorageMode
{
    COAL_BUNKER(0),
    DUAL_CHAMBER(1);

    private final int id;

    TenderStorageMode(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public static TenderStorageMode fromId(int id)
    {
        for (TenderStorageMode mode : values())
        {
            if (mode.id == id)
            {
                return mode;
            }
        }

        return COAL_BUNKER;
    }
}
