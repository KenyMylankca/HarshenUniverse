package com.kenymylankca.harshenuniverse.blocks;

import java.util.Random;

import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.interfaces.IMetaItemBlock;
import kenymylankca.harshenuniverse.items.SoulHarsherSpade;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JewelDirt extends Block implements IMetaItemBlock
{
	public static final PropertyInteger DIRT_TYPE = PropertyInteger.create("dirt_type", 0, 1);

	public JewelDirt()
	{
		super(Material.GROUND);
		setUnlocalizedName("jewel_dirt");
        setRegistryName("jewel_dirt");
        setHarvestLevel("shovel", 1);
        setHardness(3);
		setResistance(3);
        this.setDefaultState(this.blockState.getBaseState().withProperty(DIRT_TYPE, 0));
        blockSoundType = blockSoundType.GROUND;
    }
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
	{
		if(worldIn.getBlockState(pos) == this.blockState.getBaseState().withProperty(DIRT_TYPE, 0))
			this.blockState.getBaseState().withProperty(DIRT_TYPE, 0).getBlock().setHardness(3);
		
		if(worldIn.getBlockState(pos) == this.blockState.getBaseState().withProperty(DIRT_TYPE, 1))
			if(!((playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR)? playerIn.getHeldItemOffhand() : playerIn.getHeldItemMainhand()).getItem() instanceof SoulHarsherSpade))
				this.blockState.getBaseState().withProperty(DIRT_TYPE, 1).getBlock().setHardness(3000);
			else
				this.blockState.getBaseState().withProperty(DIRT_TYPE, 1).getBlock().setHardness(3);
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for(int i : DIRT_TYPE.getAllowedValues())
			items.add(new ItemStack(this, 1, i));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(DIRT_TYPE, meta);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(DIRT_TYPE);
	}

	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {DIRT_TYPE});
    }

	@Override
	public String[] getNames() {
		return HarshenUtils.listOf("overworld", "pontus");
	}
	
	@Override
	public int quantityDropped(Random random) {
		
		return 1 + random.nextInt(4);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		
		int x=(int) (Math.random()*6);
		if(x>2) return Items.DIAMOND;
		else return Items.EMERALD;
	}
}