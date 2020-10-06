package kenymylankca.harshenuniverse.base;

import java.util.ArrayList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class BaseTileEntityHarshenStackedInventory extends BaseHarshenTileEntity implements net.minecraft.util.ITickable, ICapabilityProvider
{
	protected final ItemStackHandler handler;
	protected int slotCount;
	protected boolean hasItem = false;
	protected int timer;
	private int dirtyTimer;
	
	public BaseTileEntityHarshenStackedInventory(){
		slotCount = getSlotCount();
		this.handler = new ItemStackHandler(slotCount);
	}
	
	public abstract int getSlotCount();
	
	@Override
	public void update()
	{
		timer ++;
		tick();
		if(dirtyTimer++ % 10 == 0)
			dirty();
		
	}
	
	public int getTimer()
	{
		return timer;
	}
	
	protected void tick()
	{
		
	}
		
	public void setStackInSlot(int slot, ItemStack stack)
	{
		this.handler.setStackInSlot(slot, stack);
		dirty();
	}
	
	public boolean hasItemInSlot(int slot)
	{
		return handler.getStackInSlot(slot).getItem() != Item.getItemFromBlock(Blocks.AIR);
	}
	
	public ArrayList<ItemStack> getItems()
	{
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
		for(int i = 0; i < slotCount; i++)
			stacks.add(handler.getStackInSlot(i));
		return stacks;
	}
	
	protected void dirty()
	{
		markDirty();
		IBlockState state = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, state, state, 3);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability  == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability  == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
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

