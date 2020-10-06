package kenymylankca.harshenuniverse.objecthandlers;

import java.util.Random;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public abstract class HarshenTrade implements ITradeList
{
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) 
	{
		if(getInputTwo(random).isEmpty()) recipeList.add(new MerchantRecipe(getInputOne(random), getOutput(random)));
		else recipeList.add(new MerchantRecipe(getInputOne(random), getInputTwo(random), getOutput(random)));
	}
	
	public abstract ItemStack getOutput(Random random);
	public abstract ItemStack getInputOne(Random random);
	
	public ItemStack getInputTwo(Random random)
	{
		return ItemStack.EMPTY;
	}
	


}
