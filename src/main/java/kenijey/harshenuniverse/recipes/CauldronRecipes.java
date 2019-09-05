package kenijey.harshenuniverse.recipes;

import java.util.ArrayList;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.api.CauldronLiquid;
import kenijey.harshenuniverse.api.HarshenStack;
import kenijey.harshenuniverse.internal.HarshenAPIHandler;
import net.minecraft.item.ItemStack;

public class CauldronRecipes 
{
	private static ArrayList<CauldronRecipes> allRecipes = new ArrayList<CauldronRecipes>();
	
	private final HarshenStack input;
	private final ItemStack output;
	private final CauldronLiquid catalyst;
	private boolean isFalse = false;
	
	private CauldronRecipes(HarshenStack input, ItemStack output, CauldronLiquid catalyst)
	{
		isFalse = HarshenUtils.isItemFalse(input) || !HarshenUtils.isItemAvalible(output);
		this.input = input;
		this.output = output;
		this.catalyst = catalyst;
		allRecipes.add(this);
	}
	
	public static CauldronRecipes getRecipe(ItemStack input, CauldronLiquid fluid) 
	{
		ArrayList<CauldronRecipes> working = new ArrayList<CauldronRecipes>();
		for(CauldronRecipes recipe : allRecipes)
			if(recipe.getInput().containsItem(input) && recipe.getCatalyst() == fluid)
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
	
	public CauldronLiquid getCatalyst() 
	{
		return catalyst;
	}
	
	public static void addRecipe(HarshenStack input, ItemStack output, CauldronLiquid type)
	{
		CauldronRecipes recipe = new CauldronRecipes(input, output, type);
		if(!recipe.isFalse)
			HarshenAPIHandler.allCauldronRecipes.add(recipe);
	}
}