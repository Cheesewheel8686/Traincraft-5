package com.jcirmodelsquad.tcjcir.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import train.common.api.crafting.ITierCraftingManager;
import train.common.core.handlers.AbstractRecipeHandler;
import train.common.library.ItemIDs;

public class FreightRecipes extends AbstractRecipeHandler
{
    public FreightRecipes(ITierCraftingManager cm)
    {
        //round hopper
        cm.addRecipe(2, SteelIngot(4), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), new ItemStack(ItemIDs.hopperBay.item, 4), null, null, null, SteelIngot(2), new ItemStack(Blocks.chest, 4), RED_DYE, new ItemStack(ItemIDs.minecartRoundHopper.item), 1);

        //ribbed hopper
        cm.addRecipe(2, SteelIngot(4), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(2), null, null, null, null, null, new ItemStack(Blocks.chest, 4), new ItemStack(ItemIDs.minecartRibbedHopper.item), 1);

        //hopper 5201
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 3), null, null, null, SteelIngot(2), new ItemStack(Blocks.chest, 3), WHITE_DYE, new ItemStack(ItemIDs.minecartHopper5201.item, 1), 1);

        //hopper 6260
        cm.addRecipe(2, SteelIngot(4), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), new ItemStack(ItemIDs.hopperBay.item, 4), null, null, null, SteelIngot(2), new ItemStack(Blocks.chest, 4), WHITE_DYE, new ItemStack(ItemIDs.minecartHopper6260.item, 1), 1);

        //fncc 2375 hopper
        cm.addRecipe(2, SteelIngot(2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 2), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), null, SteelIngot(2), new ItemStack(Blocks.chest, 2), GREEN_DYE, new ItemStack(ItemIDs.minecartFNCC2375Hopper.item, 1), 1);

        //pdh 2800
        cm.addRecipe(2, SteelIngot(2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), new ItemStack(ItemIDs.hopperBay.item, 1), null, null, null, SteelIngot(2), new ItemStack(Blocks.chest, 1), CYAN_DYE, new ItemStack(ItemIDs.minecartPDH2800.item, 1), 1);

        //acf 2 bay
        cm.addRecipe(2, SteelIngot(2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 2), null, null, null, SteelIngot(2), new ItemStack(Blocks.chest, 2), WHITE_DYE, new ItemStack(ItemIDs.minecartACF2Bay.item, 1), 1);

        //acf 4650
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 3), null, null, null, SteelIngot(2), new ItemStack(Blocks.chest, 3), GREEN_DYE, new ItemStack(ItemIDs.minecartACF4650.item, 1), 1);

        //ps2 4750
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 3), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), null, SteelIngot(2), new ItemStack(Blocks.chest, 3), WHITE_DYE, new ItemStack(ItemIDs.minecartPS2_4750.item, 1), 1);

        //gatc 4180 airslide
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 2), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), null, SteelIngot(3), new ItemStack(Blocks.chest, 2), BLUE_DYE, new ItemStack(ItemIDs.minecartGATC4180Airslide.item, 1), 1);

        //acf 3500
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 3), null, null, null, SteelIngot(3), new ItemStack(Blocks.chest, 3), LIGHT_GRAY_DYE, new ItemStack(ItemIDs.minecartACF3500.item, 1), 1);

        //acf 3960
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 6), null, null, null, SteelIngot(3), new ItemStack(Blocks.chest, 3), WHITE_DYE, new ItemStack(ItemIDs.minecartACF3960.item, 1), 1);



        //60 centerbeam
        cm.addRecipe(2, new ItemStack(Blocks.iron_bars, 2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(2), null, new ItemStack(Blocks.iron_bars, 2), null, null, SteelIngot(3), BLACK_DYE, new ItemStack(ItemIDs.minecart60centerbeam.item, 1), 1);

        //66 centerbeam
        cm.addRecipe(2, new ItemStack(Blocks.iron_bars, 3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(2), null, new ItemStack(Blocks.iron_bars, 2), null, null, SteelIngot(2), ORANGE_DYE, new ItemStack(ItemIDs.minecart66centerbeam.item, 1), 1);

        //73 centerbeam
        cm.addRecipe(2, new ItemStack(Blocks.iron_bars, 3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(2), null, new ItemStack(Blocks.iron_bars, 3), null, null, SteelIngot(4), PINK_DYE, new ItemStack(ItemIDs.minecart73centerbeam.item, 1), 1);

        //gsi 60 foot bulkhead
        cm.addRecipe(2, new ItemStack(ItemIDs.freightCarRibbing.item, 4), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(3), getAnyPlankType(3), null, null, null, SteelIngot(3), BLUE_DYE, new ItemStack(ItemIDs.minecartGSI60FootBulkhead.item, 1), 1);

        //gsc 60 foot flatcar
        cm.addRecipe(2, getAnyPlankType(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(2), null, null, null, null, null, CYAN_DYE, new ItemStack(ItemIDs.minecartGSC60FootFlatcar.item, 1), 1);

        //thrall 63 centerbeam
        cm.addRecipe(2, new ItemStack(Blocks.iron_bars, 3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(2), null, new ItemStack(Blocks.iron_bars, 2), null, null, SteelIngot(2), GREEN_DYE, new ItemStack(ItemIDs.minecartthrall63centerbeam.item, 1), 1);

        //fncc 60 foot bulk
        cm.addRecipe(2, SteelIngot(5), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(2), getAnyPlankType(3), null, null, null, getAnyPlankType(3), GREEN_DYE, new ItemStack(ItemIDs.minecartFNCC60FootBulk.item, 1), 1);

        //gsc 53 foot flatcar
        cm.addRecipe(2, getAnyPlankType(2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(2), null, null, null, null, null, YELLOW_DYE, new ItemStack(ItemIDs.minecartGSC53FootFlatcar.item, 1), 1);

        //gsc 53 66 foot bulkhead
        cm.addRecipe(2, new ItemStack(ItemIDs.freightCarRibbing.item, 2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(2), getAnyPlankType(2), null, null, null, SteelIngot(2), ORANGE_DYE, new ItemStack(ItemIDs.minecartGSC5366FootBulkhead.item, 1), 1);

        //gsc 53 86 foot bulkhead
        cm.addRecipe(2, new ItemStack(ItemIDs.freightCarRibbing.item, 2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(2), getAnyPlankType(3), null, null, null, SteelIngot(3), BROWN_DYE, new ItemStack(ItemIDs.minecartGSC5386FootBulkhead.item, 1), 1);

        //mapa 35 foor flatcar
        cm.addRecipe(1, getAnyPlankType(2), new ItemStack(ItemIDs.archbarTruck.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 2), SteelIngot(2), null, null, null, getAnyPlankType(3), new ItemStack(Blocks.chest, 1), BROWN_DYE, new ItemStack(ItemIDs.minecartMaPa35FootGondola.item, 1), 1);



        //ps1 40
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(2), new ItemStack(Blocks.planks, 3), null, new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 2), BROWN_DYE, new ItemStack(ItemIDs.minecartPS140.item, 1), 1);

        //ps1 50
        cm.addRecipe(2, SteelIngot(4), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(2), null, null, new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 3), WHITE_DYE, new ItemStack(ItemIDs.minecartPS150.item, 1), 1);

        //ps1 60
        cm.addRecipe(2, SteelIngot(5), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(2), null, null, new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 4), BLUE_DYE, new ItemStack(ItemIDs.minecartPS160.item, 1), 1);

        //owo 60 verticube
        cm.addRecipe(2, SteelIngot(4), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 4), SteelIngot(2), null, null, new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 4), CYAN_DYE, new ItemStack(ItemIDs.minecartOWO60Verticube.item, 1), 1);

        //milw 40 boxcar
        cm.addRecipe(2, new ItemStack(Blocks.planks, 3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(3), null, new ItemStack(ItemIDs.passengerFluting.item, 4), new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 2), ORANGE_DYE, new ItemStack(ItemIDs.minecartMILW40boxcar.item, 1), 1);

        //40 highcube
        cm.addRecipe(2, SteelIngot(2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(4), new ItemStack(Blocks.planks, 3, OreDictionary.WILDCARD_VALUE), null, new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 2), BROWN_DYE, new ItemStack(ItemIDs.minecart40highcube.item, 1), 1);
        //cm.addRecipe(2, new ItemStack(Blocks.planks, 6), new ItemStack(ItemIDs.ironBogie.item, 2), new ItemStack(ItemIDs.ironFrame.item, 2), SteelIngot(2), null, null, null, null, new ItemStack(Blocks.chest, 2), BROWN_DYE, new ItemStack(ItemIDs.minecart40highcube.item, 1), 1);

        //highcube 60 foot
        cm.addRecipe(3, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 4), SteelIngot(3), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 4), YELLOW_DYE, new ItemStack(ItemIDs.minecarthicube60foot.item, 1), 1);

        //reefer 64
        cm.addRecipe(3, SteelIngot(4), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 4), SteelIngot(4), null, null, new ItemStack(ItemIDs.boxcarDoor.item, 2), new ItemStack(Blocks.ice, 16), new ItemStack(Blocks.chest, 4), null, new ItemStack(ItemIDs.minecartReefer64.item, 1), 1);

        //fncc 50 foot
        cm.addRecipe(2, new ItemStack(Items.iron_ingot, 3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(3), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 2), CYAN_DYE, new ItemStack(ItemIDs.minecartFNCC50Foot.item, 1), 1);

        //gn 40
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(2), new ItemStack(Blocks.planks, 3), null, new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 2), null, new ItemStack(ItemIDs.minecartGN40.item, 1), 1);

        //r70 reefer
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 4), SteelIngot(4), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), new ItemStack(ItemIDs.boxcarDoor.item, 2), new ItemStack(Blocks.ice, 16), new ItemStack(Blocks.chest, 4), null, new ItemStack(ItemIDs.minecartR70Reefer.item, 1), 1);

        //r70 reefer 2
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 4), SteelIngot(4), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), new ItemStack(ItemIDs.boxcarDoor.item, 2), new ItemStack(Blocks.ice, 16), new ItemStack(Blocks.chest, 4), null, new ItemStack(ItemIDs.minecartR70Reefer2.item, 1), 1);

        //nsc child HUHHHHH
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(3), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), new ItemStack(ItemIDs.boxcarDoor.item, 2), new ItemStack(Blocks.ice, 16), new ItemStack(Blocks.chest, 4), LIGHT_GRAY_DYE, new ItemStack(ItemIDs.minecartNSCchildHUH.item, 1), 1);

        //pcf 6033
        cm.addRecipe(2, SteelIngot(4), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 4), SteelIngot(2), null, null, new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 4), BROWN_DYE, new ItemStack(ItemIDs.minecartPCF6033.item, 1), 1);

        //pcf rbl smooth
        cm.addRecipe(2, new ItemStack(Items.iron_ingot, 3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(3), null, null, new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 2), RED_DYE, new ItemStack(ItemIDs.minecartPCF_RBL_Smooth.item, 1), 1);

        //pcf rbl ribbed
        cm.addRecipe(2, new ItemStack(Items.iron_ingot, 3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(3), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 2), CYAN_DYE, new ItemStack(ItemIDs.minecartPCF_RBL_Ribbed.item, 1), 1);

        //pcf b100
        cm.addRecipe(2, new ItemStack(Items.iron_ingot, 3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(4), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 2), BLUE_DYE, new ItemStack(ItemIDs.minecartPCF_B100.item, 1), 1);

        //acf railbox
        cm.addRecipe(2, new ItemStack(Items.iron_ingot, 3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(3), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 2), YELLOW_DYE, new ItemStack(ItemIDs.minecartACFRailbox.item, 1), 1);

        //acf railbox cushioned
        cm.addRecipe(2, new ItemStack(Items.iron_ingot, 3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(3), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 2), GREEN_DYE, new ItemStack(ItemIDs.minecartACFRailboxCushioned.item, 1), 1);

        //evans 5100
        cm.addRecipe(2, new ItemStack(Items.iron_ingot, 3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(3), null, null, new ItemStack(ItemIDs.boxcarDoor.item, 4), null, new ItemStack(Blocks.chest, 2), LIGHT_BLUE_DYE, new ItemStack(ItemIDs.minecartEvans5100.item, 1), 1);

        //1912 wood boxcar
        cm.addRecipe(1, getAnyPlankType(3), new ItemStack(ItemIDs.archbarTruck.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 2), SteelIngot(2), null, null, new ItemStack(Blocks.chest, 2), null, getAnyPlankType(3), BROWN_DYE, new ItemStack(ItemIDs.minecart1912WoodBoxcar.item, 1), 1);

        //ps 40t ss box
        cm.addRecipe(1, getAnyPlankType(2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(2), null, null, new ItemStack(Blocks.chest, 2), null, getAnyPlankType(3), BROWN_DYE, new ItemStack(ItemIDs.minecartPS_40t_ss_box.item, 1), 1);

        //sp b50
        cm.addRecipe(1, getAnyPlankType(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(2), null, null, new ItemStack(Blocks.chest, 2), null, getAnyPlankType(3), BROWN_DYE, new ItemStack(ItemIDs.minecartSP_B50.item, 1), 1);

        //sp b50 aar
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(2), new ItemStack(Blocks.planks, 3), null, new ItemStack(ItemIDs.boxcarDoor.item, 2), null, new ItemStack(Blocks.chest, 2), BROWN_DYE, new ItemStack(ItemIDs.minecartSP_B50_AAR.item, 1), 1);

        //pfe r40 23 reefer
        cm.addRecipe(2, SteelIngot(2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(3), getAnyPlankType(3), null, new ItemStack(ItemIDs.boxcarDoor.item, 2), new ItemStack(Blocks.ice, 16), new ItemStack(Blocks.chest, 3), ORANGE_DYE, new ItemStack(ItemIDs.minecartPFEr_40_23Reefer.item, 1), 1);



        //versa longi
        cm.addRecipe(2, SteelIngot(2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), new ItemStack(ItemIDs.hopperBay.item, 2), null, null, null, SteelIngot(3), new ItemStack(Blocks.chest, 2), null, new ItemStack(ItemIDs.minecartVersaLongi.item, 1), 1);

        //versa trans
        cm.addRecipe(2, SteelIngot(2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), new ItemStack(ItemIDs.hopperBay.item, 3), null, null, null, SteelIngot(3), new ItemStack(Blocks.chest, 2), BLUE_DYE, new ItemStack(ItemIDs.minecartVersaTrans.item, 1), 1);

        //mill gondola
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(2), null, new ItemStack(ItemIDs.freightCarRibbing.item, 2), null, SteelIngot(4), new ItemStack(Blocks.chest, 3), RED_DYE, new ItemStack(ItemIDs.minecartMillGondola.item, 1), 1);

        //woodchip hopper
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(2), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), null, SteelIngot(4), new ItemStack(Blocks.chest, 1), BROWN_DYE, new ItemStack(ItemIDs.minecartWoodchipHopper.item), 1);

        //fmc woodchip
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(2), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), null, SteelIngot(4), new ItemStack(Blocks.chest, 1), GREEN_DYE, new ItemStack(ItemIDs.minecartFMCWoodchip.item), 1);

        //ore jenny mod
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), new ItemStack(ItemIDs.hopperBay.item, 1), null, null, null, SteelIngot(3), new ItemStack(Blocks.chest, 1), null, new ItemStack(ItemIDs.minecartOreJenny.item), 1);

        //prr gla hopper
        cm.addRecipe(2, SteelIngot(1), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 2), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), null, SteelIngot(4), new ItemStack(Blocks.chest, 2), BROWN_DYE, new ItemStack(ItemIDs.minecartPRRGLaHopper.item), 1);

        //acf 41 gon
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(2), null, new ItemStack(ItemIDs.freightCarRibbing.item, 2), null, SteelIngot(3), new ItemStack(Blocks.chest, 2), BROWN_DYE, new ItemStack(ItemIDs.minecartACF41Gon.item, 1), 1);

        //BSC 3483
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 4), new ItemStack(ItemIDs.freightCarRibbing.item, 2), null, null, SteelIngot(2), new ItemStack(Blocks.hopper, 2), BLACK_DYE, new ItemStack(ItemIDs.minecartBSC3483.item, 1), 1);

        //bethgon ii
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 4), new ItemStack(ItemIDs.freightCarRibbing.item, 5), null, null, SteelIngot(2), new ItemStack(Blocks.chest, 3), BLACK_DYE, new ItemStack(ItemIDs.minecartBethgonII.item, 1), 1);

        //67 millgon
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 4), SteelIngot(2), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), null, SteelIngot(5), new ItemStack(Blocks.chest, 4), BROWN_DYE, new ItemStack(ItemIDs.minecart67millgon.item, 1), 1);

        //52 millgon
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), SteelIngot(2), null, new ItemStack(ItemIDs.freightCarRibbing.item, 2), null, SteelIngot(4), new ItemStack(Blocks.chest, 3), GREEN_DYE, new ItemStack(ItemIDs.minecart52millgon.item, 1), 1);

        //aar 50 ton hopper
        cm.addRecipe(2, SteelIngot(1), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 2), null, new ItemStack(ItemIDs.freightCarRibbing.item, 2), null, SteelIngot(4), new ItemStack(Blocks.chest, 2), BLACK_DYE, new ItemStack(ItemIDs.minecartAAR50TonHopper.item), 1);

        //aar 70 ton hopper
        cm.addRecipe(2, SteelIngot(1), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 3), null, new ItemStack(ItemIDs.freightCarRibbing.item, 2), null, SteelIngot(5), new ItemStack(Blocks.chest, 3), BLACK_DYE, new ItemStack(ItemIDs.minecartAAR70TonHopper.item), 1);

        //mapa 35 foot gondola
        cm.addRecipe(1, getAnyPlankType(2), new ItemStack(ItemIDs.archbarTruck.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 2), SteelIngot(2), null, null, null, null, null, BROWN_DYE, new ItemStack(ItemIDs.minecartMaPa35FootFlatcar.item, 1), 1);



        //11000 dot 111
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), SteelIngot(2), new ItemStack(ItemIDs.tankcarDome.item, 1), null, new ItemStack(Items.bucket, 1), null, SteelIngot(2), BLACK_DYE, new ItemStack(ItemIDs.minecart11000DOT111.item, 1), 1);

        //20600 dot 111
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), SteelIngot(2), new ItemStack(ItemIDs.tankcarDome.item, 1), null, new ItemStack(Items.bucket, 2), null, SteelIngot(4), GRAY_DYE, new ItemStack(ItemIDs.minecart20600DOT111.item, 1), 1);

        //29080 dot 111
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), SteelIngot(2), new ItemStack(ItemIDs.tankcarDome.item, 1), null, new ItemStack(Items.bucket, 3), null, SteelIngot(6), BLACK_DYE, new ItemStack(ItemIDs.minecart29080DOT111.item, 1), 1);

        //17600 dot 111
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), SteelIngot(2), new ItemStack(ItemIDs.tankcarDome.item, 1), null, new ItemStack(Items.bucket, 2), null, SteelIngot(4), WHITE_DYE, new ItemStack(ItemIDs.minecart17600DOT111.item, 1), 1);

        //natx 30600
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), SteelIngot(2), new ItemStack(ItemIDs.tankcarDome.item, 1), null, new ItemStack(Items.bucket, 3), SteelIngot(6), null, BLACK_DYE, new ItemStack(ItemIDs.minecartNATX30600.item, 1), 1);

        //gatc 10000
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), SteelIngot(2), new ItemStack(ItemIDs.tankcarDome.item, 1), null, new ItemStack(Items.bucket, 2), null, SteelIngot(4), BLACK_DYE, new ItemStack(ItemIDs.minecartgatc10000.item, 1), 1);

        //dupx 39200
        cm.addRecipe(2, null, new ItemStack(ItemIDs.sixWheelHeavyweightTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), SteelIngot(2), new ItemStack(ItemIDs.tankcarDome.item, 1), null, new ItemStack(Items.bucket, 4), null, SteelIngot(8), BLACK_DYE, new ItemStack(ItemIDs.minecartDUPX39200.item, 1), 1);



        //coffincar
        cm.addRecipe(1, getAnyPlankType(6), new ItemStack(ItemIDs.archbarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), new ItemStack(Items.stick, 4), new ItemStack(ItemIDs.tankcarDome.item, 1), null, new ItemStack(Items.bucket, 2), getAnyPlankType(2), getAnyLogType(2), null, new ItemStack(ItemIDs.minecartCoffinCar.item, 1), 1);

        //skeleton log car
        cm.addRecipe(1, getAnyPlankType(2), new ItemStack(ItemIDs.archbarTruck.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 1), new ItemStack(Items.stick, 4), null, null, null, null, getAnyLogType(2), null, new ItemStack(ItemIDs.minecartSkeletonLogCar.item, 1), 1);

        //early flat
        cm.addRecipe(1, getAnyPlankType(6), new ItemStack(ItemIDs.archbarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), new ItemStack(Items.stick, 4), null, null, null, getAnyPlankType(2), getAnyLogType(2), null, new ItemStack(ItemIDs.minecartEarlyFlat.item, 1), 1);



        //gunderson 89ft autorack
        cm.addRecipe(3, SteelIngot(4), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 4), SteelIngot(2), null, new ItemStack(ItemIDs.passengerFluting.item, 6), null, new ItemStack(ItemIDs.freightCarRibbing.item, 4), new ItemStack(ItemIDs.steelcab.item, 1), YELLOW_DYE, new ItemStack(ItemIDs.minecartGunderson89ftAutorack.item, 1), 1);

        //gunderston 89ft flat
        cm.addRecipe(3, SteelIngot(1), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 4), SteelIngot(2), null, null, null, null, null, YELLOW_DYE, new ItemStack(ItemIDs.minecartGunderson89ftFlat.item, 1), 1);

        //stampeed rack
        cm.addRecipe(3, SteelIngot(2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 4), SteelIngot(2), null, new ItemStack(ItemIDs.passengerFluting.item, 8), getAnyPlankType(3), new ItemStack(ItemIDs.freightCarRibbing.item, 4), new ItemStack(Items.leather, 2), null, new ItemStack(ItemIDs.minecartStampedeRack.item, 1), 1);

        //huskystack wellcar
        cm.addRecipe(3, SteelIngot(2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(3), null, null, null, null, null, YELLOW_DYE, new ItemStack(ItemIDs.minecartHuskyStackWellcar.item, 1), 1);



        //ingot pig
        cm.addRecipe(1, SteelIngot(2), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), SteelIngot(2), null, null, null, null, null, BROWN_DYE, new ItemStack(ItemIDs.minecartIngotPig.item, 1), 1);

        //steel slab flat
        cm.addRecipe(1, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(3), null, null, null, null, null, BROWN_DYE, new ItemStack(ItemIDs.minecartSteelSlabFlat.item, 1), 1);

        //evans coil car
        cm.addRecipe(2, null, new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), SteelIngot(4), null, null, SteelIngot(4), new ItemStack(Blocks.chest, 1), new ItemStack(Blocks.chest, 1), BLUE_DYE, new ItemStack(ItemIDs.minecartEvansCoilCar.item, 1), 1);



        //why are you here
        cm.addRecipe(1, null, new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.ironFrame.item, 1), new ItemStack(Items.iron_ingot, 2), null, null, new ItemStack(Blocks.mossy_cobblestone, 1), null, null, null, new ItemStack(ItemIDs.minecartBoulderWagon.item, 1), 1);

    }
}
