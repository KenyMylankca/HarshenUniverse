package kenijey.harshenuniverse.blocks;

import java.util.Random;

import kenijey.harshenuniverse.HarshenItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CropOfGleam extends BlockCrops
{
    private static final AxisAlignedBB[] CROPS_AABB = new AxisAlignedBB[] {
    		new AxisAlignedBB(0.3125d, 0.0D, 0.3125d, 0.6875d, 0.25D, 0.6875d), 
    		new AxisAlignedBB(0.3125d, 0.0D, 0.3125d, 0.6875d, 0.3125D, 0.6875d),
    		new AxisAlignedBB(0.3125d, 0.0D, 0.3125d, 0.6875d, 0.3125D, 0.6875d),
    		new AxisAlignedBB(0.3125d, 0.0D, 0.3125d, 0.6875d, 0.375D, 0.6875d),
    		new AxisAlignedBB(0.3125d, 0.0D, 0.3125d, 0.6875d, 0.4375D, 0.6875d),
    		new AxisAlignedBB(0.3125d, 0.0D, 0.3125d, 0.6875d, 0.5D, 0.6875d),
    		new AxisAlignedBB(0.3125d, 0.0D, 0.3125d, 0.6875d, 0.5625D, 0.6875d),
    		new AxisAlignedBB(0.3125d, 0.0D, 0.3125d, 0.6875d, 0.6875d, 0.6875d)
    		};
    
	public CropOfGleam() 
	{
		setRegistryName("crop_of_gleam");
		setUnlocalizedName("crop_of_gleam");
	}
	
	@Override
	protected Item getSeed() {
		return HarshenItems.LIGHT_EMITTED_SEED;
	}
	
	@Override
	protected Item getCrop() {
		return HarshenItems.LIGHT_EMITTED_ESSENCE;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return CROPS_AABB[((Integer)state.getValue(this.getAgeProperty())).intValue()];
    }
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return super.canPlaceBlockAt(worldIn, pos) && worldIn.getBlockState(pos.down()).getBlock() instanceof HarshenDimensionalDirt;
	}
	
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
	{
	        return true;
	}
	
	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return false;
	}
}