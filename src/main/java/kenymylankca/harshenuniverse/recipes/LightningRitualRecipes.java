package kenymylankca.harshenuniverse.recipes;

import java.util.ArrayList;
import java.util.List;

import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.api.HarshenStack;
import kenymylankca.harshenuniverse.internal.HarshenAPIHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightningRitualRecipes {

	private static ArrayList<LightningRitualRecipes> allRecipes = new ArrayList<LightningRitualRecipes>();
	private final List<HarshenStack> inputs;
	private final ItemStack output;
	private BlockPos positionOfRitual;
	private boolean isFalse;
	private String tag;
	
	private LightningRitualRecipes(List<HarshenStack> inputs, ItemStack output) 
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
		allRecipes.add(this);
	}
	
	private LightningRitualRecipes(List<HarshenStack> inputs, ItemStack output, BlockPos position) 
	{
		this(inputs, output);
		this.positionOfRitual = position;
	}
	
	public static ArrayList<LightningRitualRecipes> getRecipes(ItemStack stack)
	{
		ArrayList<LightningRitualRecipes> working = new ArrayList<LightningRitualRecipes>();
		for(LightningRitualRecipes recipe : allRecipes)
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
		return new LightningRitualRecipes(inputs, output, position).setTag(HarshenUtils.getTagLine(world, position, "lightning_"));
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
	
	public static void addRecipe(List<HarshenStack> inputs, ItemStack output)
	{
		LightningRitualRecipes recipe = new LightningRitualRecipes(inputs, output);
		if(!recipe.isFalse)
			HarshenAPIHandler.allRitualRecipes.add(recipe);
	}
}
