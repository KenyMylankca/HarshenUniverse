package com.kenymylankca.harshenuniverse.blocks;

import kenymylankca.harshenuniverse.base.BaseBlockHarshenSingleInventory;
import kenymylankca.harshenuniverse.base.BaseTileEntityHarshenSingleItemInventory;
import kenymylankca.harshenuniverse.tileentity.TileEntityPedestalSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class PedestalSlab extends BaseBlockHarshenSingleInventory
{
	public PedestalSlab() {
		super(Material.ROCK);
		setRegistryName("pedestal_slab");
		setUnlocalizedName("pedestal_slab");
		setHardness(5.0F);
		setResistance(5.0F);
	}

	@Override
	public BaseTileEntityHarshenSingleItemInventory getTile() {
		return new TileEntityPedestalSlab();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return new AxisAlignedBB(0.12f, 0f, 0.12f, 0.88f, 0.45f, 0.88f);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.12f, 0f, 0.12f, 0.88f, 0.45f, 0.88f);
	}
}