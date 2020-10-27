package kenymylankca.harshenuniverse.integration.jei.pedestalslab;

import kenymylankca.harshenuniverse.base.BaseJeiCategory;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class JEIPedestalSlabCategory extends BaseJeiCategory
{
	public JEIPedestalSlabCategory(String UID, IRecipeCategoryRegistration reg) {
		super(UID, reg);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if(!(recipeWrapper instanceof JEIPedestalSlabWrapper))
			return;
		JEIPedestalSlabWrapper wrapper = (JEIPedestalSlabWrapper) recipeWrapper;
		recipeLayout.getItemStacks().init(0, true, 53, 27);
		recipeLayout.getItemStacks().set(0, ingredients.getInputs(ItemStack.class).get(0));
		recipeLayout.getItemStacks().init(1, false, 132, 36);
		recipeLayout.getItemStacks().set(1, ingredients.getOutputs(ItemStack.class).get(0));
	}
}