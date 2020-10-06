package kenymylankca.harshenuniverse.intergration.jei.hereticritual;

import kenymylankca.harshenuniverse.base.BaseJeiHandler;
import kenymylankca.harshenuniverse.recipes.HereticRitualRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIHereticRitualHandler extends BaseJeiHandler<HereticRitualRecipes>
{
	@Override
	public IRecipeWrapper getRecipeWrapper(HereticRitualRecipes recipe) {
		return new JEIHereticRitualWrapper(recipe);
	}
}