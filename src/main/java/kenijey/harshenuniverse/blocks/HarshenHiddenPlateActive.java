package kenijey.harshenuniverse.blocks;

import java.util.Random;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.base.BaseHarshenBlockBreakableWithSHPickaxe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HarshenHiddenPlateActive extends BaseHarshenBlockBreakableWithSHPickaxe
{
	public HarshenHiddenPlateActive() {
		setTickRandomly(true);
        setUnlocalizedName("harshen_dimensional_plate_active");
        setRegistryName("harshen_dimensional_plate_active");		
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		for(EnumFacing face : EnumFacing.VALUES)
			worldIn.notifyNeighborsOfStateChange(pos.offset(facing), this, false);
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		worldIn.setBlockState(pos, HarshenBlocks.HARSHEN_HIDDEN_PLATE.getDefaultState(), 3);
		super.updateTick(worldIn, pos, state, rand);
	}
	
	 public boolean canProvidePower(IBlockState state)
	 {
		 return true;
	 }

	 public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	 {
		 return 15;
	 }
	 
	 @Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(HarshenBlocks.HARSHEN_HIDDEN_PLATE);
	}
}