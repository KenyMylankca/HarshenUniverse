package com.kenymylankca.harshenuniverse.integration.jei.pedestalslab;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import kenymylankca.harshenuniverse.base.BaseJeiWrapper;
import kenymylankca.harshenuniverse.recipes.PedestalSlabRecipes;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class JEIPedestalSlabWrapper extends BaseJeiWrapper
{
	private final List<List<ItemStack>> input;
	private final ItemStack output;

	@SuppressWarnings("unchecked")
	public JEIPedestalSlabWrapper(PedestalSlabRecipes recipe) {
		ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
		builder.add(recipe.getInput().getStackList());
		input = builder.build();
		output = recipe.getOutput();
	}
	
	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}
}