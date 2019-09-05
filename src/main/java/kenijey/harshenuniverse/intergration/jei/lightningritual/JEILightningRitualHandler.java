package kenijey.harshenuniverse.intergration.jei.lightningritual;

import kenijey.harshenuniverse.base.BaseJeiHandler;
import kenijey.harshenuniverse.recipes.LightningRitualRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEILightningRitualHandler extends BaseJeiHandler<LightningRitualRecipes> 
{
	@Override
	public IRecipeWrapper getRecipeWrapper(LightningRitualRecipes recipe) {
		return new JEILightningRitualWrapper(recipe);
	}
}