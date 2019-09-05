package kenijey.harshenuniverse.recipes;

import java.util.ArrayList;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.api.HarshenStack;
import kenijey.harshenuniverse.internal.HarshenAPIHandler;
import net.minecraft.item.ItemStack;

public class PedestalSlabRecipes 
{
	private static ArrayList<PedestalSlabRecipes> allRecipes = new ArrayList<PedestalSlabRecipes>();
	
	private final HarshenStack input;
	private final ItemStack output;
	private boolean isFalse;
	
	private PedestalSlabRecipes(HarshenStack input, ItemStack output)
	{
		isFalse =HarshenUtils.isItemFalse(input) || !HarshenUtils.isItemAvalible(output);
		this.input = input;
		this.output = output;
		allRecipes.add(this);
	}
	
	public static PedestalSlabRecipes getRecipe(ItemStack input) 
	{
		ArrayList<PedestalSlabRecipes> working = new ArrayList<PedestalSlabRecipes>();
		for(PedestalSlabRecipes recipe : allRecipes)
			if(recipe.getInput().containsItem(input))
				return recipe;
		return null;
	}
	
	public HarshenStack getInput() 
	{
		return input;
	}
	
	public ItemStack getOutput() 
	{
		return output;
	}
	
	public static void addRecipe(HarshenStack input, ItemStack output)
	{
		PedestalSlabRecipes recipe = new PedestalSlabRecipes(input, output);
		if(!recipe.isFalse)
			HarshenAPIHandler.allPedestalRecipes.add(recipe);
	}
}