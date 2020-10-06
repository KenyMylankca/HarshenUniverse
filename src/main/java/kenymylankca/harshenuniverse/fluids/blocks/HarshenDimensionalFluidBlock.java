package kenymylankca.harshenuniverse.fluids.blocks;

import java.util.ArrayList;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.base.BaseFluidBlock;
import kenymylankca.harshenuniverse.fluids.HarshenFluids;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenDimensionalFluidBlock extends BaseFluidBlock
{
    public HarshenDimensionalFluidBlock()
    {
        super(HarshenFluids.HARSHEN_DIMENSIONAL_FLUID);
    }
    
    @Override
    public boolean checkForMixing(World worldIn, BlockPos pos, IBlockState state) {
    	 for (EnumFacing enumfacing : EnumFacing.values())
    		if(shouldBlockBeChanged(worldIn.getBlockState(pos.offset(enumfacing))) && enumfacing != EnumFacing.UP)
  	    		worldIn.setBlockState(pos.offset(enumfacing), HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT.getDefaultState(), 3);
    	 
    	 for (EnumFacing enumfacing : EnumFacing.HORIZONTALS)
     		if(shouldBlockBeChanged(worldIn.getBlockState(pos.offset(enumfacing).down())))
   	    		worldIn.setBlockState(pos.offset(enumfacing).down(), HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT.getDefaultState(), 3);
    		 
    	return super.checkForMixing(worldIn, pos, state);
    }
    
    private boolean shouldBlockBeChanged(IBlockState state)
    {
    	return 	state.getBlock() instanceof BlockDirt ||
    			state.getBlock() instanceof BlockGrass ||
    			state.getBlock() instanceof BlockLeaves;
    }

	@Override
	protected ArrayList<PotionEffect> getPotions() {
		ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
		effects.add(new PotionEffect(Potion.getPotionById(9), 250));
		effects.add(new PotionEffect(Potion.getPotionById(2), 250));
		return effects;
	}

	@Override
	protected Block getBlockWhenSourceHit() {
		return HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT;
	}

	@Override
	protected Block getBlockWhenOtherHit() {
		return HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT;
	}
}