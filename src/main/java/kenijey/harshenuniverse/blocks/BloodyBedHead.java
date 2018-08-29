package kenijey.harshenuniverse.blocks;

import kenijey.harshenuniverse.base.BaseBloodyBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BloodyBedHead extends BaseBloodyBed
{
	public BloodyBedHead()
	{
		setRegistryName("bloody_bed_head");
		setUnlocalizedName("bloody_bed");
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if(worldIn.getBlockState(pos.offset(state.getValue(FACING).getOpposite())).getBlock() instanceof BloodyBed)
		{
			worldIn.getBlockState(pos.offset(state.getValue(FACING).getOpposite())).getBlock().onBlockHarvested(worldIn, pos.offset(state.getValue(FACING)), worldIn.getBlockState(pos.offset(state.getValue(FACING))), player);
			worldIn.destroyBlock(pos.offset(state.getValue(FACING).getOpposite()), !player.isCreative());
		}
	}
}