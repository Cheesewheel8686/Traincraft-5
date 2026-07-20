package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.tanker;

import com.jcirmodelsquad.tcjcir.models.loads.Modelhazmat_plac_11000;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardTankerCar;
import train.common.entity.CargoManager;
import train.common.entity.CargoSpecification;

public class DOT11111000 extends AbstractStandardTankerCar {

    public DOT11111000(World world) {
        super(world);
        setCargoManager(new CargoManager(new CargoSpecification[][] {
                { new CargoSpecification(Modelhazmat_plac_11000.class, "loads/hazmat_plac_flammable_number",
                        "Hazmat Placard - Flammable (Bluk)", 0, 3.0, 0)},
                /*{ new CargoSpecification(Modelhazmat_plac_20600.class, "loads/hazmat_plac_flammable",
                        "Hazmat Placard - Flammable", 0, 3.0, 0)},*/
                { new CargoSpecification(Modelhazmat_plac_11000.class, "loads/hazmat_plac_corrosive_number",
                        "Hazmat Placard - Corrosive (Bulk)", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_11000.class, "loads/hazmat_plac_hot",
                        "Hazmat Placard - HOT", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_11000.class, "loads/hazmat_plac_oxidizer",
                        "Hazmat Placard - Oxidizer/Oxygen", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_11000.class, "loads/hazmat_plac_class9_numbers",
                        "Hazmat Placard - Class 9 (Bulk)", 0, 3, 0)},
                /*{ new CargoSpecification(Modelhazmat_plac_20600.class, "loads/hazmat_plac_class9",
                        "Hazmat Placard - Class 9", 0, 3, 0)},*/
                { new CargoSpecification(Modelhazmat_plac_11000.class, "loads/hazmat_plac_class6",
                        "Hazmat Placard - Class 6 (Poision, PG III, Toxic)", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_11000.class, "loads/hazmat_plac_wet",
                        "Hazmat Placard - Dangerous when Wet", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_11000.class, "loads/hazmat_plac_explosives",
                        "Hazmat Placard - Explosives", 0, 3, 0)},
                { new CargoSpecification(Modelhazmat_plac_11000.class, "loads/hazmat_plac_radioactive",
                        "Hazmat Placard - Radioactive", 0, 3, 0)},
        }));
    }

    @Override
    public void setupTextureDescription()
    {
        InsertTexture(0, "Generic");
        InsertTexture(1, "DOW");
        InsertTexture(2, "Generic Grey");
    }

    @Override
    public String getInventoryName() {
        return "11,000 Gallon Tank car";
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 1.9375F;
    }
}