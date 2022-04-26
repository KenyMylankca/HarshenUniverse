package com.kenymylankca.harshenuniverse.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NocturneBloom extends Block
{
	public static PropertyBool GROWN = PropertyBool.create("grown");
	
	public NocturneBloom()
	{
		super(Material.PLANTS);
		setRegistryName("nocturne_bloom");
		setUnlocalizedName("nocturne_bloom");
		this.setSoundType(SoundType.PLANT);
		setHardness(0.2F);
		setResistance(0.2F);
		this.setLightLevel(0.3f);
		setTickRandomly(true);
		setDefaultState(this.blockState.getBaseState().withProperty(GROWN, false));
	}
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        this.checkForDrop(worldIn, pos, state);
    }
	
	private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
        if (worldIn.getBlockState(pos.up()).getBlock() != Blocks.AIR && state.getValue(GROWN))
        {
            worldIn.destroyBlock(pos, false);
            return false;
        }
        else
        {
            return true;
        }
    }
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return null;
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) 
	{
		Random rand = new Random();
		if(player.capabilities.isCreativeMode)
		{
			super.onBlockHarvested(worldIn, pos, state, player);
			return;
		}
		if(!worldIn.isRemote && state.getValue(GROWN) && rand.nextFloat() < 0.2)
		{
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.ENDER_PEARL));
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.QUARTZ));
			super.onBlockHarvested(worldIn, pos, state, player);
		}
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, NULL_AABB);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if(state.getValue(GROWN))
		{
			return new AxisAlignedBB(0.33f, 0f, 0.33f, 0.7f, 1.5f, 0.77f);
		}
		else
			return new AxisAlignedBB(0.33f, 0f, 0.33f, 0.7f, 0.77f, 0.77f);
	}
	
	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random)
	{
		if(worldIn.getBlockState(pos.up()).getBlock() instanceof BlockAir && random.nextInt(7) == 0)
			worldIn.setBlockState(pos, state.withProperty(GROWN, true));
		super.randomTick(worldIn, pos, state, random);
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(!worldIn.isSideSolid(pos.down(), EnumFacing.UP))
			worldIn.destroyBlock(pos, true);
		super.onBlockAdded(worldIn, pos, state);
	}
	
	public BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {GROWN});
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		if(state.getValue(GROWN))
			return 1;
		else
			return 0;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta == 0)
			return this.getDefaultState().withProperty(GROWN, false);
		else
			return this.getDefaultState().withProperty(GROWN, true);
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) 
	{
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}