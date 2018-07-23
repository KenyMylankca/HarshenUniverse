package kenijey.harshenuniverse.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HarshenDestroyedPlant extends Block
{
	
	public HarshenDestroyedPlant() {
		super(Material.GRASS);
		setUnlocalizedName("harshen_destroyed_plant");
        setRegistryName("harshen_destroyed_plant");
        blockSoundType = blockSoundType.PLANT;
	}
	
	@Override
	protected boolean canSilkHarvest() {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.125f, 0f, 0.125f, 0.9375f, 0.8125f, 0.9375f);
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, NULL_AABB);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if(!(worldIn.getBlockState(pos.add(0, -1, 0)).getBlock() instanceof HarshenDimensionalDirt))
			worldIn.setBlockToAir(pos);
	}
	
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
	{
	        return true;
	}
}
