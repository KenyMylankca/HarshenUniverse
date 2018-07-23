package kenijey.harshenuniverse.intergration.jei.magictable;

import kenijey.harshenuniverse.base.BaseJeiHandler;
import kenijey.harshenuniverse.recipies.MagicTableRecipe;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIMagicTableHandler extends BaseJeiHandler<MagicTableRecipe>
{
	@Override
	public IRecipeWrapper getRecipeWrapper(MagicTableRecipe recipe) {
		return new JEIMagicTableWrapper(recipe);
	}
}