package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.freight;

import com.jcirmodelsquad.tcjcir.models.loads.Modelversaflood_load;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFreightCar;
import train.common.entity.CargoManager;
import train.common.entity.CargoSpecification;
import train.common.enums.CargoItemFilter;

public class VersaLongi extends AbstractStandardFreightCar
{
    public VersaLongi(World world) {
        super(world);
        cargoFilterCategory = CargoItemFilter.AGGREGATE;
    }

    @Override
    public CargoManager setupCargoManager()
    {
        return new CargoManager(new CargoSpecification[][]
                {
                        {new CargoSpecification(Modelversaflood_load.class,
                                "loads/versaflood_load_gravel", "Gravel", new CargoSpecification.RenderParameters().setOffset(0.0, 3.0, 0)), },
                        {new CargoSpecification(Modelversaflood_load.class,
                                "loads/versaflood_load_peagravel", "Peagravel", new CargoSpecification.RenderParameters().setOffset(0.0, 3.0, 0)), },
                        {new CargoSpecification(Modelversaflood_load.class,
                                "loads/versaflood_load_crushed_marble", "Crushed Marble", new CargoSpecification.RenderParameters().setOffset(0.0, 3.0, 0)), },
                        {new CargoSpecification(Modelversaflood_load.class,
                                "loads/versaflood_load_dirt", "Dirt", new CargoSpecification.RenderParameters().setOffset(0.0, 3.0, 0)), },
                }
        );
    }

    @Override
    public void setupTextureDescription()
    {
        InsertTexture(0, "Generic Light Grey");
        InsertTexture(1, "Generic Black");
        InsertTexture(2, "OWO (Ballast Service)");
        InsertTexture(3, "WP");
        InsertTexture(4, "CD&P (Crushed Marble Service)");
        InsertTexture(5, "FNCC (MOW Ballast Service)");
        InsertTexture(6, "AA");
        InsertTexture(7, "CSXT");
        InsertTexture(8, "CSXT (MoW Service)");
    }

    @Override
    public String getInventoryName() {
        return "Versaflood Longitudinal Hopper";
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 2F;
    }
}