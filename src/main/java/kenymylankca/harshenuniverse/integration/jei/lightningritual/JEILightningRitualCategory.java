package kenymylankca.harshenuniverse.integration.jei.lightningritual;

import java.awt.Dimension;
import java.util.List;

import kenymylankca.harshenuniverse.base.BaseJeiCategory;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class JEILightningRitualCategory extends BaseJeiCategory
{	
	public JEILightningRitualCategory(String UID, IRecipeCategoryRegistration reg) {
		super(UID, reg);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if(!(recipeWrapper instanceof JEILightningRitualWrapper))
			return;
		JEILightningRitualWrapper wrapper = (JEILightningRitualWrapper) recipeWrapper;
		List<List<ItemStack>> stackList = ingredients.getInputs(ItemStack.class);
		for(int i = 0; i < 4; i++)
			addSlot(recipeLayout, stackList, i);
		recipeLayout.getItemStacks().init(4, false, 122, 45);
		recipeLayout.getItemStacks().set(4, ingredients.getOutputs(ItemStack.class).get(0));
	}
	
	Dimension[] positionsOfSlots = {new Dimension(26, 2), new Dimension(65, 2), new Dimension(19, 24), new Dimension(71, 23)};
	
	private void addSlot(IRecipeLayout recipeLayout, List<List<ItemStack>> list, int id)
	{
		recipeLayout.getItemStacks().init(id, true, positionsOfSlots[id].width, positionsOfSlots[id].height);
		recipeLayout.getItemStacks().set(id, list.get(id));
	}
}