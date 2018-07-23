package kenijey.harshenuniverse.intergration.jei.hereticritual;

import kenijey.harshenuniverse.base.BaseJeiHandler;
import kenijey.harshenuniverse.recipies.HereticRitualRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIHereticRitualHandler extends BaseJeiHandler<HereticRitualRecipes>
{
	@Override
	public IRecipeWrapper getRecipeWrapper(HereticRitualRecipes recipe) {
		return new JEIHereticRitualWrapper(recipe);
	}
}