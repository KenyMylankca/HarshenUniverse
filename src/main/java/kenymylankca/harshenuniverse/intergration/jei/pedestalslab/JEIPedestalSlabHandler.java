package kenymylankca.harshenuniverse.intergration.jei.pedestalslab;

import kenymylankca.harshenuniverse.base.BaseJeiHandler;
import kenymylankca.harshenuniverse.recipes.PedestalSlabRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIPedestalSlabHandler extends BaseJeiHandler<PedestalSlabRecipes>
{
	@Override
	public IRecipeWrapper getRecipeWrapper(PedestalSlabRecipes recipe) {
		return new JEIPedestalSlabWrapper(recipe);
	}
}