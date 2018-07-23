package kenijey.harshenuniverse.intergration.jei.pedestalslab;

import kenijey.harshenuniverse.base.BaseJeiHandler;
import kenijey.harshenuniverse.recipies.PedestalSlabRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIPedestalSlabHandler extends BaseJeiHandler<PedestalSlabRecipes>
{
	@Override
	public IRecipeWrapper getRecipeWrapper(PedestalSlabRecipes recipe) {
		return new JEIPedestalSlabWrapper(recipe);
	}
}