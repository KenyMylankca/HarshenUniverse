package kenymylankca.harshenuniverse.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BloodBlock extends Block
{
	private static HashMap<BlockPos, Integer> tickMap = new HashMap<>();
	private static ArrayList<BlockPos> inUse = new ArrayList<>();
	
	public BloodBlock()
	{
		 super(Material.CLAY);
		 setUnlocalizedName("blood_block");
	     setRegistryName("blood_block");
	     blockSoundType = blockSoundType.SLIME;
	     setTickRandomly(true);
	}
	
	public void setRitualState(BlockPos pos, boolean setTo)
	{
		if(setTo)
			inUse.add(pos);
		else
			inUse.remove(pos);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 1, 0.0625f, 1);
	}
	
	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		if(!tickMap.containsKey(pos))
			tickMap.put(pos, 0);
		tickMap.put(pos, tickMap.get(pos) + 1);
		if(tickMap.get(pos) > 17 || worldIn.isRaining())
			worldIn.setBlockToAir(pos);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		tickMap.remove(pos);
		inUse.remove(pos);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, NULL_AABB);
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if(!worldIn.getBlockState(pos.down()).getBlock().isNormalCube(worldIn.getBlockState(pos.down()), worldIn, pos))
			worldIn.setBlockToAir(pos);
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(!worldIn.getBlockState(pos.down()).getBlock().isNormalCube(worldIn.getBlockState(pos.down()), worldIn, pos))
			worldIn.setBlockToAir(pos);
		else
			tickMap.put(pos, 0);
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) 
	{
		return false;
	}
	
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
	{
		return true;
	}
}