package com.kenymylankca.harshenuniverse.blocks;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.items.DarkEwydoen;
import net.minecraft.block.BlockStairs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenDimensionalWoodStairs extends BlockStairs
{
	public HarshenDimensionalWoodStairs()
	{
		super(HarshenBlocks.HARSHEN_DIMENSIONAL_WOOD_CRATE.getDefaultState());
		setUnlocalizedName("harshen_dimensional_wood_stairs");
		setRegistryName("harshen_dimensional_wood_stairs");
		setHarvestLevel("axe", 3);
		useNeighborBrightness = true;
		setHardness(3000);
		setResistance(3000);
	}

	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if((playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR)? playerIn.getHeldItemOffhand() : playerIn.getHeldItemMainhand()).getItem() instanceof DarkEwydoen)
			setHardness(3);
		else
			setHardness(3000);
	}
}