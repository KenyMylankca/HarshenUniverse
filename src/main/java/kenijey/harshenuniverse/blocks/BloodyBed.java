package kenijey.harshenuniverse.blocks;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.base.BaseBloodyBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BloodyBed extends BaseBloodyBed
{
	public EntityLivingBase placer;
	
	public BloodyBed()
	{
		setRegistryName("bloody_bed");
		setUnlocalizedName("bloody_bed");
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        EnumFacing placerfacing = this.placer.getHorizontalFacing();
		if(worldIn.getBlockState(pos.offset(placerfacing)).getBlock().isReplaceable(worldIn, pos.offset(placerfacing)))
		{
			worldIn.setBlockState(pos.offset(placerfacing), HarshenBlocks.BLOODY_BED_HEAD.getDefaultState().withProperty(this.FACING, placerfacing).withProperty(this.OCCUPIED, false), 3);
			worldIn.setBlockState(pos, HarshenBlocks.BLOODY_BED.getDefaultState().withProperty(this.FACING, placerfacing).withProperty(this.OCCUPIED, false), 3);
		}
		else
			worldIn.destroyBlock(pos, true);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		EnumFacing placerfacing = this.placer.getHorizontalFacing();
		if(worldIn.getBlockState(pos.offset(placerfacing)).getBlock() instanceof BloodyBedHead)
			worldIn.setBlockToAir(pos.offset(placerfacing));
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	@Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		this.placer=placer;
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if(state.getValue(FACING) == EnumFacing.NORTH)
			return new AxisAlignedBB(0f, 0f, 1f, 1f, 0.6f, -1f);
		if(state.getValue(FACING) == EnumFacing.SOUTH)
			return new AxisAlignedBB(0f, 0f, 0f, 1f, 0.6f, 2f);
		if(state.getValue(FACING) == EnumFacing.EAST)
			return new AxisAlignedBB(0f, 0f, 1f, 2f, 0.6f, 0f);
		else
			return new AxisAlignedBB(-1f, 0f, 0f, 1f, 0.6f, 1f);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		if(blockState.getValue(FACING) == EnumFacing.NORTH)
			return new AxisAlignedBB(0f, 0f, 1f, 1f, 0.5f, -1f);
		if(blockState.getValue(FACING) == EnumFacing.SOUTH)
			return new AxisAlignedBB(0f, 0f, 0f, 1f, 0.5f, 2f);
		if(blockState.getValue(FACING) == EnumFacing.EAST)
			return new AxisAlignedBB(0f, 0f, 1f, 2f, 0.5f, 0f);
		else
			return new AxisAlignedBB(-1f, 0f, 0f, 1f, 0.5f, 1f);
	}
}