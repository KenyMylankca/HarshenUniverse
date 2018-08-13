package kenijey.harshenuniverse.recipies;

import java.util.ArrayList;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.api.CauldronLiquid;
import kenijey.harshenuniverse.api.HarshenStack;
import kenijey.harshenuniverse.internal.HarshenAPIHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HereticRitualRecipes 
{
	private static ArrayList<HereticRitualRecipes> allRecipes = new ArrayList<HereticRitualRecipes>();
	
	private final HarshenStack cauldronItem;
	private final ItemStack output;
	private final ArrayList<HarshenStack> pedestalItems;
	private final CauldronLiquid catalyst;
	private boolean isFalse;
	private String tag;
	
	private HereticRitualRecipes(HarshenStack cauldronItem, ItemStack output, CauldronLiquid catalyst, HarshenStack... pedstalItems)
	{
		if(pedstalItems.length != 8)
			throw new IllegalArgumentException("input size for ritual recipe was not 8");
		ArrayList<HarshenStack> stackList = new ArrayList<>();
		for(int i = 0; i < 8; i++)
			if(HarshenUtils.isItemFalse(pedstalItems[i]))
				isFalse = true;
			else
				stackList.add(pedstalItems[i]);
		if(HarshenUtils.isItemFalse(cauldronItem) || !HarshenUtils.isItemAvalible(output))
			isFalse = true;
		
		this.cauldronItem = cauldronItem;
		this.output = output;
		this.catalyst = catalyst;
		this.pedestalItems = stackList;
		allRecipes.add(this);
	}
	
	public ArrayList<HarshenStack> getPedestalItems() {
		return pedestalItems;
	}
	
	public static HereticRitualRecipes getRecipe(ItemStack cauldronInput, CauldronLiquid fluid, ArrayList<ItemStack> pedestalItems) 
	{
		pedestalItems = new ArrayList<>(pedestalItems);
		for(HereticRitualRecipes recipe : allRecipes)
			if(recipe.getCauldronInput().containsItem(cauldronInput) && recipe.getCatalyst() == fluid && HarshenUtils.areHStacksEqual(recipe.getPedestalItems(), pedestalItems))
				return recipe;
		return null;
	}

	public HarshenStack getCauldronInput() 
	{
		return cauldronItem;
	}
	
	public ItemStack getOutput() 
	{
		return output;
	}
	
	public CauldronLiquid getCatalyst() 
	{
		return catalyst;
	}
	
	public static void addRecipe(HarshenStack cauldronItem, ItemStack output, CauldronLiquid catalyst, HarshenStack... pedstalItems)
	{
		HereticRitualRecipes recipe = new HereticRitualRecipes(cauldronItem, output, catalyst, pedstalItems);
		if(!recipe.isFalse)
			HarshenAPIHandler.allHereticCauldronRecipes.add(recipe);
	}
	
	public HereticRitualRecipes setTag(String tag)
	{
		this.tag = tag;
		return this;
	}
	
	public String getTag() {
		return tag;
	}
	
	public HereticRitualRecipes setUp(World world, BlockPos position)
	{
		return this.setTag(HarshenUtils.getTagLine(world, position, "heretic_cauldron"));
	}
}