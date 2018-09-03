package kenijey.harshenuniverse.base;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public abstract class BaseTileEntityHarshenSingleItemInventory extends BaseTileEntityHarshenInventory implements net.minecraft.util.ITickable, ICapabilityProvider
{
	public BaseTileEntityHarshenSingleItemInventory()
	{
		super(1, 1);
	}
	
	public boolean isSlotEmpty()
	{	 
		return super.isSlotEmpty(0);
	}
	
	public boolean setItem(ItemStack item)
	{
		return super.setItem(0, item);
	}
	
	@Override
	protected void onItemAdded(int slot)
	{
		onItemAdded();
	}
	
	protected void onItemAdded()
	{
		
	}
		
	public void setItemAir()
	{
		super.setItemAir(0);
	}
	
	public ItemStack getItem()
	{
		return super.getItem(0);
	}

	public boolean canAddItem() {
		return super.isSlotEmpty(0);
	}
	
	protected double randPos()
	{
		return MathHelper.clamp(new Random().nextDouble(), 0.15, 0.85);
	}
}