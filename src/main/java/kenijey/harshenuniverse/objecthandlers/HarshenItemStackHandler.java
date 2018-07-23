package kenijey.harshenuniverse.objecthandlers;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class HarshenItemStackHandler extends ItemStackHandler 
{
	public HarshenItemStackHandler(int amount) {
		super(amount);
	}
	
	private int slotLimit = 64;
	
	public HarshenItemStackHandler setSlotLimit(int slotLimit) {
		this.slotLimit = slotLimit;
		return this;
	}
	
	public ArrayList<ItemStack> getStacks()
	{
		return new ArrayList<ItemStack>(stacks);
	}
	
	@Override
	public int getSlotLimit(int slot) {
		return slotLimit;
	}
	
	public boolean containsItem(Item item)
	{
		for(ItemStack stack : stacks)
			if(stack.getItem() == item)
				return true;
		return false;
	}
}
