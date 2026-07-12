package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.freight;

import com.jcirmodelsquad.tcjcir.models.loads.ModelBSC3483_coal_load;
import com.jcirmodelsquad.tcjcir.models.loads.ModelBethgon_load;
import com.jcirmodelsquad.tcjcir.models.loads.Modelboulder;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFreightCar;
import train.common.entity.CargoManager;
import train.common.entity.CargoSpecification;

public class BSC3483 extends AbstractStandardFreightCar
{
    public BSC3483(World world) {
        super(world);
    }
    @Override
    public CargoManager setupCargoManager()
    {
        return new CargoManager(new CargoSpecification[][]
        {
                {new CargoSpecification(ModelBSC3483_coal_load.class,
                        "loads/BSC_3483_load_coal", "Coal load 1", new CargoSpecification.RenderParameters().setOffset(0.0, 2.9, 0)), },
                {new CargoSpecification(ModelBethgon_load.class,
                        "loads/bethgon_load_coal", "Coal load 2", new CargoSpecification.RenderParameters().setOffset(0.0, 3.1, 0)), },
                {new CargoSpecification(ModelBSC3483_coal_load.class,
                        "loads/BSC_3483_load_rock", "Rock load 1", new CargoSpecification.RenderParameters().setOffset(0.0, 2.9, 0)), },
                {new CargoSpecification(ModelBethgon_load.class,
                        "loads/bethgon_load_rock", "Rock load 2", new CargoSpecification.RenderParameters().setOffset(0.0, 3.1, 0)), },
                {new CargoSpecification(ModelBSC3483_coal_load.class,
                        "loads/BSC_3483_load_cullet", "Cullet Glass", new CargoSpecification.RenderParameters().setOffset(0.0, 2.9, 0)), },
                {new CargoSpecification(ModelBethgon_load.class,
                        "loads/bethgon_load_peterite", "Peterite", new CargoSpecification.RenderParameters().setOffset(0.0, 3.1, 0)), },

        });
    }

    @Override
    public void setupTextureDescription()
    {
        InsertTexture(0, "Blandsville & Blankerston");//ModelB(ethgon)S(ethgon)C(ethgon)3483
        InsertTexture(1, "");
    }

    @Override
    public String getInventoryName() {
        return "BSC 3483 Cu Ft Open Top Hopper";
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 2.57F;
    }
}
