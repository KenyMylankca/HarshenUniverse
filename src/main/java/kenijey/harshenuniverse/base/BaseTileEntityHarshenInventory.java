package kenijey.harshenuniverse.base;

import kenijey.harshenuniverse.objecthandlers.HarshenItemStackHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;

public abstract class BaseTileEntityHarshenInventory extends BaseHarshenTileEntity implements net.minecraft.util.ITickable, ICapabilityProvider
{
	protected final HarshenItemStackHandler handler;
	protected boolean hasItem = false;
	protected int timer;
	private int dirtyTimer;
	
	public BaseTileEntityHarshenInventory(int slotsAmount){
		this.handler = new HarshenItemStackHandler(slotsAmount);
	}
	
	public BaseTileEntityHarshenInventory(int slotsAmount, int slotLimit){
		this(slotsAmount);
		this.handler.setSlotLimit(slotLimit);
	}
	
	@Override
	public void update()
	{
		timer ++;
		tick();
		if(dirtyTimer++ % 5 == 0)
			dirty();
	}
	
	public int getTimer()
	{
		return timer;
	}
	
	protected void tick()
	{
	}
	
	public boolean isSlotEmpty(int slot)
	{	 
		return this.handler.getStackInSlot(slot).getItem() == Items.AIR;
	}
	
	public boolean isEmpty()
	{
		for(int i = 0; i < handler.getSlots(); i++)
			if(!handler.getStackInSlot(i).isEmpty())
				return false;
		return true;
	}
	
	public boolean setItem(int slot, ItemStack item)
	{
		item.setCount(1);
		this.handler.setStackInSlot(slot, item);
		dirty();
		onItemAdded(slot);
		return true;
	}
	
	protected void onItemAdded(int slot)
	{
		
	}
		
	public void setItemAir(int slot)
	{
		this.handler.setStackInSlot(slot, new ItemStack(Blocks.AIR));
		dirty();
	}
	
	public ItemStack getItem(int slot)
	{
		return handler.getStackInSlot(slot);
	}
	
	protected void dirty()
	{
		markDirty();
		IBlockState state = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, state, state, 3);
	}
	
	public HarshenItemStackHandler getCapablityHandler(HarshenItemStackHandler handler)
	{
		return handler;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability  == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) getCapablityHandler(this.handler);
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability  == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}
	
	public HarshenItemStackHandler getHandler() {
		return handler;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.handler.deserializeNBT(compound.getCompoundTag("ItemStackHandler"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setTag("ItemStackHandler", this.handler.serializeNBT());
		return super.writeToNBT(nbt);
	}
}