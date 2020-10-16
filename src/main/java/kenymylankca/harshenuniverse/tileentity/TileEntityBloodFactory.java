package kenymylankca.harshenuniverse.tileentity;

import kenymylankca.harshenuniverse.base.BaseTileEntityHarshenSingleItemInventoryActive;
import kenymylankca.harshenuniverse.interfaces.IBloodSupply;
import net.minecraft.item.ItemStack;

public class TileEntityBloodFactory extends BaseTileEntityHarshenSingleItemInventoryActive
{	
	private IBloodSupply itemSupply;
	
	@Override
	protected int getTicksUntillDone() {
		return itemSupply == null ? 0 : itemSupply.ticksUntillUsed();
	}

	private int tickRate = 0;
	
	@Override
	protected void finishedTicking()
	{
		setItem(ItemStack.EMPTY);
	}
	
	@Override
	protected boolean checkForCompletion(boolean checkingUp) {
		if(getItem().getItem() instanceof IBloodSupply && !checkingUp && world.getTileEntity(pos.down()) instanceof TileEntityBloodVessel)
			if(!((TileEntityBloodVessel)world.getTileEntity(pos.down())).isFull())
			{
				itemSupply = (IBloodSupply) getItem().getItem();
				activate();
			}
		return getItem().getItem() instanceof IBloodSupply;
	}
	
	@Override
	protected void tick() {	
		if(!isActive()) checkForCompletion(false);
		if(isActive() && !(getItem().getItem() instanceof IBloodSupply))
		{
			itemSupply = null;
			deactivate();
		}
		if(isActive() && itemSupply != null && world.getTileEntity(pos.down()) instanceof TileEntityBloodVessel && tickRate++ % 50 == 0 )
		{
			if(((TileEntityBloodVessel)world.getTileEntity(pos.down())).isFull())
				deactivate();
			else
				((TileEntityBloodVessel)world.getTileEntity(pos.down())).addBlood(itemSupply.getAmountPerSecond());
		}
	}
}