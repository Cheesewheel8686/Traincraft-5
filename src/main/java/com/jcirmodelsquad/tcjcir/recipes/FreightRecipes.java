package com.jcirmodelsquad.tcjcir.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import train.common.api.crafting.ITierCraftingManager;
import train.common.core.handlers.AbstractRecipeHandler;
import train.common.library.ItemIDs;

public class FreightRecipes extends AbstractRecipeHandler
{
    public FreightRecipes(ITierCraftingManager cm)
    {

        //BSC 3483
        cm.addRecipe(2, SteelIngot(3), new ItemStack(ItemIDs.freightCarTruck.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ItemIDs.hopperBay.item, 3), new ItemStack(ItemIDs.freightCarRibbing.item, 3), null, null, SteelIngot(2), new ItemStack(Blocks.hopper, 2), BLACK_DYE, new ItemStack(ItemIDs.minecartBSC3483.item, 1), 1);

    }
}
