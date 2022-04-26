package com.kenymylankca.harshenuniverse.base;

import kenymylankca.harshenuniverse.items.DarkEwydoen;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BaseHarshenBlockBreakableWithEwydoen extends Block
{
	public BaseHarshenBlockBreakableWithEwydoen()
	{
		super(Material.WOOD);
		setHarvestLevel("axe", 3);
		blockSoundType = blockSoundType.WOOD;
		setHardness(3000);
		setResistance(3000);
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if((playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR)? playerIn.getHeldItemOffhand() : playerIn.getHeldItemMainhand()).getItem() instanceof DarkEwydoen)
			setHardness(3);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
	}
}