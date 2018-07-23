package kenijey.harshenuniverse.recipies;

import java.util.ArrayList;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.api.HarshenStack;
import kenijey.harshenuniverse.internal.HarshenAPIHandler;
import net.minecraft.item.ItemStack;

public class MagicTableRecipe 
{	
	
	private final ArrayList<HarshenStack> inputStacks;
	private final ItemStack output;
	private boolean isFalse;
	
	private MagicTableRecipe(ArrayList<HarshenStack> inputStacks, ItemStack output) {
		for(HarshenStack stack : inputStacks)
			if(HarshenUtils.isItemFalse(stack) || HarshenUtils.isItemFalse(output))
				isFalse = true;
		this.inputStacks = inputStacks;
		this.output = output;
	}
	
	public static MagicTableRecipe getRecipeFromStacks(ArrayList<ItemStack> stacks)
	{
		for(MagicTableRecipe recipe : HarshenAPIHandler.allMagicTableRecipes)
			if(HarshenUtils.areHStacksEqual(recipe.getInputStacks(), stacks))
				return recipe;
		return null;
	}
	
	public ArrayList<HarshenStack> getInputStacks() {
		return inputStacks;
	}
	
	public ItemStack getOutput() {
		return output;
	}
	
	public static void addRecipe(ArrayList<HarshenStack> inputStacks, ItemStack output)
	{
		MagicTableRecipe recipe = new MagicTableRecipe(inputStacks, output);
		if(!recipe.isFalse)
			HarshenAPIHandler.allMagicTableRecipes.add(recipe);
	}
}
