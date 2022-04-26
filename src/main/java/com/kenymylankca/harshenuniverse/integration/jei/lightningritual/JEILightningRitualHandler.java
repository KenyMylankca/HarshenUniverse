package com.kenymylankca.harshenuniverse.integration.jei.lightningritual;

import kenymylankca.harshenuniverse.base.BaseJeiHandler;
import kenymylankca.harshenuniverse.recipes.LightningRitualRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEILightningRitualHandler extends BaseJeiHandler<LightningRitualRecipes> 
{
	@Override
	public IRecipeWrapper getRecipeWrapper(LightningRitualRecipes recipe) {
		return new JEILightningRitualWrapper(recipe);
	}
}