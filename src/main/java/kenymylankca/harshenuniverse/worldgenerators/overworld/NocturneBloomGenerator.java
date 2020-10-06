package kenymylankca.harshenuniverse.worldgenerators.overworld;

import java.util.Random;

import kenymylankca.harshenuniverse.HarshenBlocks;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockStone;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class NocturneBloomGenerator extends WorldGenerator
{
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	BlockPos positionToGen = null;
			if((worldIn.getBlockState(position.down()).getBlock() instanceof BlockStone) && worldIn.getBlockState(position).getBlock() instanceof BlockAir &&
					worldIn.getBlockState(position.up()).getBlock() instanceof BlockAir)
				positionToGen = position;
    	if(positionToGen == null)
    		return false;
    	worldIn.setBlockState(positionToGen, HarshenBlocks.NOCTURNE_BLOOM.getDefaultState(), 3);
    	return true;
    }
}