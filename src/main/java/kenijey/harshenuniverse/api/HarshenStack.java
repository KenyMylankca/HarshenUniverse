package kenijey.harshenuniverse.api;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
/**
 * A custom class used for handling ItemStacks and {@link OreDictionary}. 
 * Each HarshenStack will represent the different items that can be used to represent one "slot" in the recipe.
 * <br>Its a bit like {@link OreDictionary}, one class of this can hold multiple ItemStacks, and are used to activate a recipe if any of 
 * the input stacks are in one of HarshenStacks slots. If a recipe wanted either a Carrot or a Potato as a one of the input items, HarshenStack 
 * <br> would be usfull as it would activate the recipe if all other inputs are right and there is either a carrot or a potato
 * <p>
 * For example a recipe that could take the inputs of:
 *<br>+A carrot
 *<br>+A stone sword
 *<br>+either a gold pickaxe or a diamond pickaxe.
 *<br>would need {@link HarshenStack}, the first {@link HarshenStack} being a carrot, the second a stone sword 
 * and the last {@link HarshenStack} being the Gold Pickaxe and Diamond Pickaxe</p>
 *<br> If you are still confused about how this class is used, see {@link HarshenStack#containsItem}
 * @author Wyn Price
 *
 */
public class HarshenStack
{
	/**The list containing all the stacks that this class represents*/
	private ArrayList<ItemStack> stackList = new ArrayList<>();
	
	/**Should ItemStacks be dependent on NBT*/
	private boolean dependOnNBT = false;
	
	/**
	 * Used to create an empty list
	 */
	public HarshenStack() {
		stackList.add(ItemStack.EMPTY);
	}
	
	/**
	 * Used to create lists of stacks
	 * @param stacks the list of stacks. Can have meta of {@link OreDictionary#WILDCARD_VALUE}
	 */
	public HarshenStack(ItemStack... stacks) 
	{
		for(ItemStack stack : stacks)
			stackList.add(stack);
	}
	
	/**
	 * Used to create a list of stacks, from oreDictionary
	 * @param oreDictName A list of OreDictionary value you want to use
	 */
	public HarshenStack(String... oreDictNames) {
		for(String oreDictName : oreDictNames)
		{
			NonNullList<ItemStack> stackList = OreDictionary.getOres(oreDictName);
			if(stackList.isEmpty())
				new IllegalArgumentException("Oredictionary vaule " + oreDictName + " doesnt exist").printStackTrace(System.out);
			else
				for(ItemStack stack : stackList)
					if(stack.getMetadata() == OreDictionary.WILDCARD_VALUE)
					{
				    		NonNullList<ItemStack> innerStacklist = NonNullList.create();
				    		stack.getItem().getSubItems(CreativeTabs.SEARCH, innerStacklist);
						for(ItemStack wildStack : innerStacklist)
							this.stackList.add(stack.copy());
					}
					else
						this.stackList.add(stack);
		}
	}
	
	private HarshenStack(ArrayList<ItemStack> stackList)
	{
		this.stackList = new ArrayList<>(stackList);
	}
	
	/**
	 * Clones the {@link HarshenStack}
	 */
	public HarshenStack clone()
	{
		return new HarshenStack(getStackList());
	}
	
	/**
	 * Sets if the {@link HarshenStack} should depend on NBT
	 * @param dependOnNBT what to to set {@link HarshenStack#dependOnNBT} to
	 * @return itself
	 */
	public HarshenStack setDependOnNBT(boolean dependOnNBT)
	{
		this.dependOnNBT = dependOnNBT;
		return this;
	}
	
	/**
	 * Does the {@link HarshenStack} depend on NBT
	 */
	public boolean isDependOnNBT() {
		return dependOnNBT;
	}
	
	
	/**
	 * Gets a new instance of a list of all the available stacks.
	 */
	public ArrayList<ItemStack> getStackList() {
		return new ArrayList<>(stackList);
	}
	
	/**
	 * Can the input stack be used as an input item. Returns true if the stackList ({@link HarshenStack#getStackList})
	 * contains the input item.
	 * @param stack the stack to test with
	 * @return true if the stack can be used, false if not
	 */
	public boolean containsItem(ItemStack stack)
	{
		for(ItemStack innerStack :  getStackList())
			if(((innerStack.getMetadata() == OreDictionary.WILDCARD_VALUE ? innerStack.getItem() == stack.getItem() : innerStack.isItemEqual(stack)) && 
					(dependOnNBT ? ItemStack.areItemStackShareTagsEqual(innerStack, stack) : true)) || (innerStack.isEmpty() && stack.isEmpty()))
				return true;
		return false;
	}
}
