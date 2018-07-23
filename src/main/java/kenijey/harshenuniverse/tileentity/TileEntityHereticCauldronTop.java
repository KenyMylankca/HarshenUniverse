package kenijey.harshenuniverse.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityHereticCauldronTop extends TileEntity
{
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if(world.getTileEntity(pos.down()) != null)
			return (T) world.getTileEntity(pos.down()).getCapability(capability, facing);
		return null;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if(world.getTileEntity(pos.down()) != null)
				return world.getTileEntity(pos.down()).hasCapability(capability, facing);
		return false;
	}
}