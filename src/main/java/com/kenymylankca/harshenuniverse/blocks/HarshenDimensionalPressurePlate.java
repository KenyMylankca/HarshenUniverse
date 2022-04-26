package com.kenymylankca.harshenuniverse.blocks;

import java.util.Random;

import kenymylankca.harshenuniverse.base.BaseHarshenBlockBreakableWithSHPickaxe;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HarshenDimensionalPressurePlate extends BaseHarshenBlockBreakableWithSHPickaxe
{
    public static final PropertyBool POWERED = PropertyBool.create("powered");

	public HarshenDimensionalPressurePlate() {
		setUnlocalizedName("harshen_dimensional_pressure_plate");
		setRegistryName("harshen_dimensional_pressure_plate");
		this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.valueOf(false)));
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (worldIn.getBlockState(pos) == this.getStateFromMeta(0)) {
			notifyChange(worldIn, pos, worldIn.getBlockState(pos), 1, true);
			worldIn.scheduleBlockUpdate(pos, this, 20, 2);
		}
		super.onEntityWalk(worldIn, pos, entityIn);
	}

	private void notifyChange(World worldIn, BlockPos pos, IBlockState blockState, int newState, Boolean updateOn) {
		worldIn.setBlockState(pos, getStateFromMeta(newState), 3);
		worldIn.notifyNeighborsOfStateChange(pos, this, false);
        worldIn.notifyNeighborsOfStateChange(pos.down(), this, false);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (state == this.getStateFromMeta(1))
			if (worldIn.getEntitiesWithinAABB(EntityLivingBase.class,
					new AxisAlignedBB(pos.add(0, 1, 0), pos.add(1, 2, 1))).isEmpty())
				notifyChange(worldIn, pos, state, 0, false);
			else
				worldIn.scheduleBlockUpdate(pos, this, 20, 2);
		super.updateTick(worldIn, pos, state, rand);
	}
	
	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return ((Boolean)blockState.getValue(POWERED)).booleanValue() ? 15 : 0;
	}
	
	@Override
	public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.UP? ((Boolean)blockState.getValue(POWERED)).booleanValue() ? 15 : 0 : 0;
	}
	
	@Override
	public boolean canProvidePower(IBlockState state) {
		return true;
	}
	
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(POWERED, Boolean.valueOf(meta == 1));
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((Boolean)state.getValue(POWERED)).booleanValue() ? 1 : 0;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {POWERED});
    }
}