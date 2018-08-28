package kenijey.harshenuniverse.blocks;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.base.BaseBloodyBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BloodyBed extends BaseBloodyBed
{
	public BloodyBed()
	{
		setRegistryName("bloody_bed");
		setUnlocalizedName("bloody_bed");
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        EnumFacing enumfacing = this.placer.getHorizontalFacing().getOpposite();
		if(!(worldIn.getBlockState(pos.offset(state.getValue(FACING).getOpposite())).getBlock() instanceof BloodyBedHead) && worldIn.getBlockState(pos.offset(state.getValue(FACING).getOpposite())).getBlock().isReplaceable(worldIn, pos.offset(state.getValue(FACING).getOpposite())))
		{
			worldIn.setBlockState(pos.offset(state.getValue(FACING).getOpposite()), HarshenBlocks.BLOODY_BED_HEAD.getDefaultState().withProperty(this.FACING, enumfacing).withProperty(this.OCCUPIED, false), 3);
			worldIn.setBlockState(pos, HarshenBlocks.BLOODY_BED.getDefaultState().withProperty(this.FACING, enumfacing).withProperty(this.OCCUPIED, false), 3);
		}
		else
			worldIn.destroyBlock(pos, true);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if(worldIn.getBlockState(pos.offset(state.getValue(FACING).getOpposite())).getBlock() instanceof BloodyBedHead)
			worldIn.setBlockToAir(pos.offset(state.getValue(FACING).getOpposite()));
		super.onBlockHarvested(worldIn, pos, state, player);
	}
}