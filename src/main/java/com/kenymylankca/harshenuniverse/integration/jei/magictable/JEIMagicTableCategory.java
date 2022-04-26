package com.kenymylankca.harshenuniverse.integration.jei.magictable;

import java.awt.Point;

import kenymylankca.harshenuniverse.base.BaseJeiCategory;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class JEIMagicTableCategory extends BaseJeiCategory
{
	public JEIMagicTableCategory(String UID, IRecipeCategoryRegistration reg) {
		super(UID, reg);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if(!(recipeWrapper instanceof JEIMagicTableWrapper))
			return;
		Point[] points = {new Point(67, 12), new Point(32, 47), new Point(102, 47), new Point(67, 82)};
		int listSize = ingredients.getInputs(ItemStack.class).size();
		for(int i = 0; i < listSize; i++)
		{
			recipeLayout.getItemStacks().init(i, true, points[i].x, points[i].y);
			recipeLayout.getItemStacks().set(i, ingredients.getInputs(ItemStack.class).get(i));
		}
		recipeLayout.getItemStacks().init(listSize, false, 67, 47);
		recipeLayout.getItemStacks().set(listSize, ingredients.getOutputs(ItemStack.class).get(0));
	}
}