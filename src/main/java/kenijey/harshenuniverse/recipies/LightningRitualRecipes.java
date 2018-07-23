package kenijey.harshenuniverse.recipies;

import java.util.ArrayList;
import java.util.List;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.api.HarshenStack;
import kenijey.harshenuniverse.internal.HarshenAPIHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightningRitualRecipes {

	private static ArrayList<LightningRitualRecipes> allRecipies = new ArrayList<LightningRitualRecipes>();
	private final List<HarshenStack> inputs;
	private final ItemStack output;
	private final boolean useLightning;
	private BlockPos positionOfRitual;
	private boolean isFalse;
	private String tag;
	
	private LightningRitualRecipes(List<HarshenStack> inputs, ItemStack output, boolean useLightning) 
	{
		if(inputs.size() != 4)
			throw new IllegalArgumentException("input size for ritual recipe was not 4");
		for(int i = 0; i < 4; i++)
			if(HarshenUtils.isItemFalse(inputs.get(i)))
				isFalse = true;
		if(!HarshenUtils.isItemAvalible(output))
			isFalse = true;
		
		this.inputs = new ArrayList<HarshenStack>(inputs);
		this.output = output.copy();
		this.useLightning = useLightning;
		allRecipies.add(this);
	}
	
	private LightningRitualRecipes(List<HarshenStack> inputs, ItemStack output, boolean useLightning, BlockPos position) 
	{
		this(inputs, output, useLightning);
		this.positionOfRitual = position;
	}
	
	public static ArrayList<LightningRitualRecipes> getRecipes(ItemStack stack)
	{
		ArrayList<LightningRitualRecipes> working = new ArrayList<LightningRitualRecipes>();
		for(LightningRitualRecipes recipe : allRecipies)
			if(recipe.hasItem(stack))
				working.add(recipe);
		return working;
	}
	
	public List<HarshenStack> getInputs() {
		return inputs;
	}
	
	public LightningRitualRecipes setTag(String tag)
	{
		this.tag = tag;
		return this;
	}
	
	public String getTag() {
		return tag;
	}
	
	public LightningRitualRecipes setUpRitual(World world, BlockPos position)
	{
		return new LightningRitualRecipes(inputs, output, useLightning, position).setTag(HarshenUtils.getTagLine(world, position, "lightning_"));
	}
	
	public BlockPos getPositionOfRitual() {
		return positionOfRitual;
	}
	
	public ItemStack getOutput()
	{
		return output;
	}
	
	private boolean hasItem(ItemStack stack)
	{
		for(HarshenStack hStack : this.inputs)
			if(hStack.containsItem(stack))
				return true;
		return false;
	}
	
	public boolean lightning()
	{
		return this.useLightning;
	}
	
	public static void addRecipe(List<HarshenStack> inputs, ItemStack output, boolean useLightning)
	{
		LightningRitualRecipes recipe = new LightningRitualRecipes(inputs, output, useLightning);
		if(!recipe.isFalse)
			HarshenAPIHandler.allRitualRecipes.add(recipe);
	}
}
