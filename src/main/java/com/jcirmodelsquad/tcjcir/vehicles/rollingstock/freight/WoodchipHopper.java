package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.freight;

import com.jcirmodelsquad.tcjcir.models.loads.Modelwoodchip_hopper_chips;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFreightCar;
import train.common.entity.CargoManager;
import train.common.entity.CargoSpecification;
import train.common.enums.CargoItemFilter;

public class WoodchipHopper extends AbstractStandardFreightCar
{
    public WoodchipHopper(World world) {
        super(world);
        cargoFilterCategory = CargoItemFilter.WOOD_CHIPS;//woodchiphopper_chips
    }

    @Override
    public CargoManager setupCargoManager()
    {
        return new CargoManager(new CargoSpecification[][]
                {
                        {new CargoSpecification(Modelwoodchip_hopper_chips.class,
                                "loads/woodchiphopper_chips", "Woodchips load 1", new CargoSpecification.RenderParameters().setOffset(0.0, 3.0, 0)), },
                        {new CargoSpecification(Modelwoodchip_hopper_chips.class,
                                "loads/woodchiphopper_chips2", "Woodchips load 2", new CargoSpecification.RenderParameters().setOffset(0.0, 3.0, 0)), },
                }
        );
    }

    @Override
    public void setupTextureDescription()
    {
        InsertTexture(0, "CHIP");
        InsertTexture(1, "GN");
        InsertTexture(2, "BN");
        InsertTexture(3, "MILW");
        InsertTexture(4, "ATSF");
        InsertTexture(5, "OWO");
        InsertTexture(6, "FNCC");
        InsertTexture(7, "FNCC (Ex BTR)");
        InsertTexture(8, "FNCC (Ex BTR)");
        InsertTexture(9, "WP");
        InsertTexture(10, "DN");
    }

    @Override
    public String getInventoryName() {
        return "Thrall 59' Woodchip Hopper";
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 2.8F;
    }
}
