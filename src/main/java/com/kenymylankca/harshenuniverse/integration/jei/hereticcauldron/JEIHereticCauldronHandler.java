package com.kenymylankca.harshenuniverse.integration.jei.hereticcauldron;

import kenymylankca.harshenuniverse.base.BaseJeiHandler;
import kenymylankca.harshenuniverse.recipes.CauldronRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIHereticCauldronHandler extends BaseJeiHandler<CauldronRecipes>
{
	@Override
	public IRecipeWrapper getRecipeWrapper(CauldronRecipes recipe) {
		return new JEIHereticCauldronWrapper(recipe);
	}
}