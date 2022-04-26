package com.kenymylankca.harshenuniverse.integration.jei.magictable;

import kenymylankca.harshenuniverse.base.BaseJeiHandler;
import kenymylankca.harshenuniverse.recipes.MagicTableRecipe;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIMagicTableHandler extends BaseJeiHandler<MagicTableRecipe>
{
	@Override
	public IRecipeWrapper getRecipeWrapper(MagicTableRecipe recipe) {
		return new JEIMagicTableWrapper(recipe);
	}
}