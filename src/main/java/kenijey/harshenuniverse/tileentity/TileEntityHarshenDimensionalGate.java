package kenijey.harshenuniverse.tileentity;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.base.BaseHarshenTileEntity;
import kenijey.harshenuniverse.blocks.HarshenDimensionalGate;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityHarshenDimensionalGate extends BaseHarshenTileEntity implements ITickable
{
	public final static int TOTAL_TICKS = 12000; // make sure divisible by 20
	private int ticksLeft = -1;

	@Override
	public void update() {
		if(ticksLeft < 0)
			ticksLeft = getBlockMetadata() == 0 ? TOTAL_TICKS : 0;
		
		if(getBlockType() == HarshenBlocks.HARSHEN_DIMENSIONAL_GATE && !world.getBlockState(pos).getValue(HarshenDimensionalGate.ACTIVE) && !world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos, pos.add(1, 1, 1))).isEmpty())
			for(EntityItem entityitem : world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos, pos.add(1, 1, 1))))
				if(entityitem.getItem().getItem() == HarshenItems.RITUAL_CRYSTAL && entityitem.getItem().getMetadata() == 1)
				{
					world.setBlockState(pos, world.getBlockState(pos).getBlock().getDefaultState().withProperty(HarshenDimensionalGate.ACTIVE, true)
							.withProperty(HarshenDimensionalGate.FOREVER, world.getBlockState(pos).getValue(HarshenDimensionalGate.FOREVER)), 3);
					entityitem.getItem().setCount(entityitem.getItem().getCount() - 1);
				}
		if(getBlockMetadata() == 0)
			if(ticksLeft-- <= 0)
				((HarshenDimensionalGate)world.getBlockState(pos).getBlock()).deactivate(world, pos);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("TicksLeft", ticksLeft);
		return super.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		ticksLeft = compound.getInteger("TicksLeft");
		super.readFromNBT(compound);
	}
	
	public int getTick()
	{
		return ticksLeft;
	}
}