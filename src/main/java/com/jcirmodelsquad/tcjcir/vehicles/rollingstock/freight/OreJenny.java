package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.freight;

import com.jcirmodelsquad.tcjcir.models.loads.Modelorejenny_load;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFreightCar;
import train.common.entity.CargoManager;
import train.common.entity.CargoSpecification;
import train.common.enums.CargoItemFilter;

public class OreJenny extends AbstractStandardFreightCar
{
    public OreJenny(World world)
    {
        super(world);
        cargoFilterCategory = CargoItemFilter.ORE;
    }

    @Override
    public CargoManager setupCargoManager()
    {
        return new CargoManager(new CargoSpecification[][]
                {
                        {new CargoSpecification(Modelorejenny_load.class,
                                "loads/orejenny_load_iron1", "Iron Ore 1", new CargoSpecification.RenderParameters().setOffset(0.0, 3.0, 0)), },
                        {new CargoSpecification(Modelorejenny_load.class,
                                "loads/orejenny_load_iron2", "Iron Ore 2", new CargoSpecification.RenderParameters().setOffset(0.0, 3.0, 0)), },
                        {new CargoSpecification(Modelorejenny_load.class,
                                "loads/orejenny_load_peagravel", "Peagravel", new CargoSpecification.RenderParameters().setOffset(0.0, 3.0, 0)), },
                        {new CargoSpecification(Modelorejenny_load.class,
                                "loads/orejenny_load_crushed_limestone", "Crushed Limestone", new CargoSpecification.RenderParameters().setOffset(0.0, 3.0, 0)), },
                        {new CargoSpecification(Modelorejenny_load.class,
                                "loads/orejenny_load_redstone", "Redstone Dust", new CargoSpecification.RenderParameters().setOffset(0.0, 3.0, 0)), },
                }
        );
    }

    @Override
    public void setupTextureDescription()
    {
        InsertTexture(0, "ORJY");
        InsertTexture(1, "DMIR");
        InsertTexture(2, "MILW");
        InsertTexture(3, "WETX");
        InsertTexture(4, "JC's period mining incorperated");
    }

    @Override
    public String getInventoryName() {
        return "Ore Jenni lul bean sus";
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 1.1F;
    }
}
