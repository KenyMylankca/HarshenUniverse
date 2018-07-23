package kenijey.harshenuniverse.intergration.jei.hereticcauldron;

import kenijey.harshenuniverse.base.BaseJeiHandler;
import kenijey.harshenuniverse.recipies.CauldronRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIHereticCauldronHandler extends BaseJeiHandler<CauldronRecipes>
{
	@Override
	public IRecipeWrapper getRecipeWrapper(CauldronRecipes recipe) {
		return new JEIHereticCauldronWrapper(recipe);
	}
}