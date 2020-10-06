package kenymylankca.harshenuniverse.blocks;

import kenymylankca.harshenuniverse.base.BaseBloodyBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BloodyBedHead extends BaseBloodyBed
{
	public BloodyBedHead()
	{
		setRegistryName("bloody_bed_head");
		setUnlocalizedName("bloody_bed");
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		if(worldIn.getBlockState(pos.offset(state.getValue(FACING).getOpposite())).getBlock() instanceof BloodyBed)
		{
			worldIn.destroyBlock(pos.offset(state.getValue(FACING).getOpposite()), !player.isCreative());
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if(state.getValue(FACING) == EnumFacing.NORTH)
			return new AxisAlignedBB(0f, 0f, 2f, 1f, 0.6f, 0f);
		if(state.getValue(FACING) == EnumFacing.SOUTH)
			return new AxisAlignedBB(0f, 0f, 1f, 1f, 0.6f, -1f);
		if(state.getValue(FACING) == EnumFacing.EAST)
			return new AxisAlignedBB(-1f, 0f, 1f, 1f, 0.6f, 0f);
		else
			return new AxisAlignedBB(0f, 0f, 0f, 2f, 0.6f, 1f);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		if(blockState.getValue(FACING) == EnumFacing.NORTH)
			return new AxisAlignedBB(0f, 0f, 2f, 1f, 0.6f, 0f);
		if(blockState.getValue(FACING) == EnumFacing.SOUTH)
			return new AxisAlignedBB(0f, 0f, 1f, 1f, 0.6f, -1f);
		if(blockState.getValue(FACING) == EnumFacing.EAST)
			return new AxisAlignedBB(-1f, 0f, 1f, 1f, 0.6f, 0f);
		else
			return new AxisAlignedBB(0f, 0f, 0f, 2f, 0.6f, 1f);
	}
}