package kenijey.harshenuniverse.objecthandlers;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class HarshenMachineItemStackHandler extends HarshenItemStackHandler
{

	private final HarshenItemStackHandler handler;
	
	public HarshenMachineItemStackHandler(HarshenItemStackHandler handler) {
		super(handler.getSlots());
		this.handler = handler;
	}
	
	private ArrayList<Integer> outPutSlots = new ArrayList<>();
	
	public HarshenMachineItemStackHandler addBlockedSlots(int... slots)
	{
		for(int i : slots)
			outPutSlots.add(i);
		return this;
	}
	
	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		return outPutSlots.contains(slot) ? stack : handler.insertItem(slot, stack, simulate);
	}
	
	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return outPutSlots.contains(slot) ? handler.extractItem(slot, amount, simulate) : ItemStack.EMPTY;
	}
	
	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		handler.setStackInSlot(slot, stack);
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		return handler.getStackInSlot(slot);
	}
	
	@Override
	public int getSlotLimit(int slot) {
		return handler.getSlotLimit(slot);
	}
	
	@Override
	public ArrayList<ItemStack> getStacks() {
		return handler.getStacks();
	}
	
	@Override
	public int getSlots() {
		return handler.getSlots();
	}
	
	

}
