package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.freight;

import com.jcirmodelsquad.tcjcir.models.loads.ModelWrappedWoodOptimized_sliceAAA;
import com.jcirmodelsquad.tcjcir.models.loads.Modelthrall63tiedownsA;
import com.jcirmodelsquad.tcjcir.models.loads.Modelthrall63tiedownsB;
import com.jcirmodelsquad.tcjcir.models.loads.Modelthrall63tiedownsLoaded;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractStandardFreightCar;
import train.common.entity.CargoManager;
import train.common.entity.CargoSpecification;
import train.common.enums.CargoItemFilter;

public class Thrall63centerbeam extends AbstractStandardFreightCar
{
    public Thrall63centerbeam(World world) {
        super(world);
        cargoFilterCategory = CargoItemFilter.WOOD_PRODUCTS;
    }

    

    @Override
    public CargoManager setupCargoManager()
    {
        return new CargoManager(new CargoSpecification[][] {

                //is 0.315 5 mb? is 0.063 1 mb? bigger positive number means lower, smaller positive number means higher
                { new CargoSpecification(Modelthrall63tiedownsA.class,//tiedowns empty 1
                        "trains/thrall63footcenterbeam_tiedowns", "Empty Tiedowns", 0, 3.00, 0)},

                { new CargoSpecification(Modelthrall63tiedownsB.class,//tiedowns empty 2
                        "trains/thrall63footcenterbeam_tiedowns", "Empty Tiedowns", 0, 3.00, 0)},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//washaska wood
                        "loads/WrappedWood3_Washaska_Straps", "Wrapped Lumber (Washaska)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Washaska_Straps", "Wrapped Lumber (Washaska)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Washaska_Straps", "Wrapped Lumber (Washaska)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Washaska_Straps", "Wrapped Lumber (Washaska)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Washaska_Straps", "Wrapped Lumber (Washaska)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Wrapped Lumber (Washaska)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//washaska wood 2
                        "loads/WrappedWood3_Washaska_Straps2", "Wrapped Lumber (Washaska)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Washaska_Straps2", "Wrapped Lumber (Washaska)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Washaska_Straps2", "Wrapped Lumber (Washaska)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Washaska_Straps2", "Wrapped Lumber (Washaska)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Washaska_Straps2", "Wrapped Lumber (Washaska)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Wrapped Lumber (Washaska)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//tolko wood
                        "loads/WrappedWood3_Tolko_Straps", "Wrapped Lumber (Tolko)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Tolko_Straps", "Wrapped Lumber (Tolko)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Tolko_Straps", "Wrapped Lumber (Tolko)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Tolko_Straps", "Wrapped Lumber (Tolko)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Tolko_Straps", "Wrapped Lumber (Tolko)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Wrapped Lumber (Tolko)", 0, 3, 0),},
//some other old values that might be useful should i revisit this: 2.437 2.122 1.807 1.492 1.177
                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//canfor wood
                        "loads/WrappedWood3_Canfor_Straps", "Wrapped Lumber (Canfor)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Canfor_Straps", "Wrapped Lumber (Canfor)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Canfor_Straps", "Wrapped Lumber (Canfor)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Canfor_Straps", "Wrapped Lumber (Canfor)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Canfor_Straps", "Wrapped Lumber (Canfor)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Wrapped Lumber (Canfor)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//blank wood
                        "loads/WrappedWood3_Blank_Straps", "Wrapped Lumber", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Blank_Straps", "Wrapped Lumber", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Blank_Straps", "Wrapped Lumber", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Blank_Straps", "Wrapped Lumber", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Blank_Straps", "Wrapped Lumber", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Wrapped Lumber", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//foggyriver wood
                        "loads/WrappedWood3_FRLC_Straps", "Wrapped Lumber (Foggy River)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_FRLC_Straps", "Wrapped Lumber (Foggy River)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_FRLC_Straps", "Wrapped Lumber (Foggy River)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_FRLC_Straps", "Wrapped Lumber (Foggy River)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_FRLC_Straps", "Wrapped Lumber (Foggy River)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Wrapped Lumber (Foggy River)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//thick & thin wood
                        "loads/WrappedWood3_Thick_n_Thin_Straps", "Wrapped Lumber (Thick & Thin)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Thick_n_Thin_Straps", "Wrapped Lumber (Thick & Thin)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Thick_n_Thin_Straps", "Wrapped Lumber (Thick & Thin)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Thick_n_Thin_Straps", "Wrapped Lumber (Thick & Thin)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/WrappedWood3_Thick_n_Thin_Straps", "Wrapped Lumber (Thick & Thin)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Wrapped Lumber (Thick & Thin)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//oak
                        "loads/UnwrappedWood_oak", "Unwrapped Lumber (Oak)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_oak", "Unwrapped Lumber (Oak)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_oak", "Unwrapped Lumber (Oak)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_oak", "Unwrapped Lumber (Oak)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_oak", "Unwrapped Lumber (Oak)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Unwrapped Lumber (Oak)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//Spruce
                        "loads/UnwrappedWood_spruce", "Unwrapped Lumber (Spruce)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_spruce", "Unwrapped Lumber (Spruce)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_spruce", "Unwrapped Lumber (Spruce)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_spruce", "Unwrapped Lumber (Spruce)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_spruce", "Unwrapped Lumber (Spruce)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Unwrapped Lumber (Spruce)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//Birch
                        "loads/UnwrappedWood_birch", "Unwrapped Lumber (Birch)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_birch", "Unwrapped Lumber (Birch)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_birch", "Unwrapped Lumber (Birch)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_birch", "Unwrapped Lumber (Birch)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_birch", "Unwrapped Lumber (Birch)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Unwrapped Lumber (Birch)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//Jungle
                        "loads/UnwrappedWood_jungle", "Unwrapped Lumber (Jungle)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_jungle", "Unwrapped Lumber (Jungle)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_jungle", "Unwrapped Lumber (Jungle)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_jungle", "Unwrapped Lumber (Jungle)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_jungle", "Unwrapped Lumber (Jungle)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Unwrapped Lumber (Jungle)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//Acacia
                        "loads/UnwrappedWood_acacia", "Unwrapped Lumber (Acacia)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_acacia", "Unwrapped Lumber (Acacia)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_acacia", "Unwrapped Lumber (Acacia)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_acacia", "Unwrapped Lumber (Acacia)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_acacia", "Unwrapped Lumber (Acacia)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Unwrapped Lumber (Acacia)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//Dark Oak
                        "loads/UnwrappedWood_darkoak", "Unwrapped Lumber (Dark Oak)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_darkoak", "Unwrapped Lumber (Dark Oak)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_darkoak", "Unwrapped Lumber (Dark Oak)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_darkoak", "Unwrapped Lumber (Dark Oak)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_darkoak", "Unwrapped Lumber (Dark Oak)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Unwrapped Lumber (Dark Oak)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//Pine
                        "loads/UnwrappedWood_pine", "Unwrapped Lumber (Pine)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_pine", "Unwrapped Lumber (Pine)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_pine", "Unwrapped Lumber (Pine)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_pine", "Unwrapped Lumber (Pine)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_pine", "Unwrapped Lumber (Pine)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Unwrapped Lumber (Pine)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//Redwood
                        "loads/UnwrappedWood_redwood", "Unwrapped Lumber (Redwood)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_redwood", "Unwrapped Lumber (Redwood)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_redwood", "Unwrapped Lumber (Redwood)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_redwood", "Unwrapped Lumber (Redwood)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_redwood", "Unwrapped Lumber (Redwood)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Unwrapped Lumber (Redwood)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//Fir
                        "loads/UnwrappedWood_fir", "Unwrapped Lumber (Fir)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_fir", "Unwrapped Lumber (Fir)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_fir", "Unwrapped Lumber (Fir)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_fir", "Unwrapped Lumber (Fir)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_fir", "Unwrapped Lumber (Fir)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Unwrapped Lumber (Fir)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//Douglas Fir
                        "loads/UnwrappedWood_douglas_fir", "Unwrapped Lumber (Douglas Fir)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_douglas_fir", "Unwrapped Lumber (Douglas Fir)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_douglas_fir", "Unwrapped Lumber (Douglas Fir)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_douglas_fir", "Unwrapped Lumber (Douglas Fir)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_douglas_fir", "Unwrapped Lumber (Douglas Fir)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Unwrapped Lumber (Douglas Fir)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//Sitka Spruce
                        "loads/UnwrappedWood_sitka_spruce", "Unwrapped Lumber (Sitka Spruce)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_sitka_spruce", "Unwrapped Lumber (Sitka Spruce)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_sitka_spruce", "Unwrapped Lumber (Sitka Spruce)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_sitka_spruce", "Unwrapped Lumber (Sitka Spruce)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_sitka_spruce", "Unwrapped Lumber (Sitka Spruce)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Unwrapped Lumber (Sitka Spruce)", 0, 3, 0),},

                { new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,//Western Red Cedar
                        "loads/UnwrappedWood_western_red_cedar", "Unwrapped Lumber (Western Red Cedar)", -0.0315, 2.54, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_western_red_cedar", "Unwrapped Lumber (Western Red Cedar)", -0.0315, 2.225, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_western_red_cedar", "Unwrapped Lumber (Western Red Cedar)", -0.0315, 1.91, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_western_red_cedar", "Unwrapped Lumber (Western Red Cedar)", -0.0315, 1.595, 0),
                        new CargoSpecification(ModelWrappedWoodOptimized_sliceAAA.class,
                                "loads/UnwrappedWood_western_red_cedar", "Unwrapped Lumber (Western Red Cedar)", -0.0315, 1.283, 0),
                        new CargoSpecification(Modelthrall63tiedownsLoaded.class,
                                "trains/thrall63footcenterbeam_tiedowns", "Unwrapped Lumber (Western Red Cedar)", 0, 3, 0),},
        });
    }

    @Override
    public void setupTextureDescription()
    {
        InsertTexture(0, "BN");
        InsertTexture(1, "TTZX");
        InsertTexture(2, "Generic Red");
        InsertTexture(3, "COER 'CENTERBEAM'");
        InsertTexture(4, "WRX");
        InsertTexture(5, "FNCC");
        InsertTexture(6, "DN");
        InsertTexture(7, "GLNT");
        InsertTexture(8, "AGW");
    }

    @Override
    public String getInventoryName() {
        return "Thrall 63' Centerbeam";
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 3.45F;
    }
}