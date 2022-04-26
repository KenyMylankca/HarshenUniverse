package com.kenymylankca.harshenuniverse.objecthandlers;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;

public class HarshenGlassContainerIngredient extends Ingredient 
{
	public HarshenGlassContainerIngredient(ItemStack... stacks) {
		super(stacks);
	}
	
	@Override
	public boolean apply(ItemStack p_apply_1_) {
		if (p_apply_1_ == null)
            return false;
        else
        	for (ItemStack itemstack : this.getMatchingStacks())
        		if (itemstack.getItem() == p_apply_1_.getItem())
        			if (itemstack.getMetadata() == 32767 || itemstack.getMetadata() == p_apply_1_.getMetadata())
        				return itemstack.getItem() instanceof UniversalBucket ? 
        						FluidStack.loadFluidStackFromNBT(itemstack.getTagCompound()).getFluid() ==  FluidStack.loadFluidStackFromNBT(p_apply_1_.getTagCompound()).getFluid(): true;
		return false;
	}
}