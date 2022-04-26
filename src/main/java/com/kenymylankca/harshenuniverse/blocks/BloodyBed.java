package com.kenymylankca.harshenuniverse.blocks;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.base.BaseBloodyBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BloodyBed extends BaseBloodyBed
{
	public BloodyBed()
	{
		setRegistryName("bloody_bed");
		setUnlocalizedName("bloody_bed");
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		EnumFacing facing = placer.getHorizontalFacing();
		if(worldIn.getBlockState(pos.offset(facing)).getBlock().isReplaceable(worldIn, pos.offset(facing)))
		{
			worldIn.setBlockState(pos.offset(facing), HarshenBlocks.BLOODY_BED_HEAD.getDefaultState().withProperty(this.FACING, facing).withProperty(this.OCCUPIED, false), 3);
			worldIn.setBlockState(pos, HarshenBlocks.BLOODY_BED.getDefaultState().withProperty(this.FACING, facing).withProperty(this.OCCUPIED, false), 3);
		}
		else
			worldIn.destroyBlock(pos, true);
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		EnumFacing facing = state.getValue(FACING);
		if(worldIn.getBlockState(pos.offset(facing)).getBlock() instanceof BloodyBedHead)
			worldIn.setBlockToAir(pos.offset(facing));
		super.onBlockHarvested(worldIn, pos, state, player);
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