package kenymylankca.harshenuniverse.worldgenerators.pontus;

import java.util.Random;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.blocks.JewelDirt;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class JewelDirtGenPontus extends WorldGenerator
{
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	BlockPos positionToGen = null;
			if(worldIn.getBlockState(position).getBlock() == HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT || worldIn.getBlockState(position).getBlock() == HarshenBlocks.HARSHEN_DIMENSIONAL_ROCK)
				positionToGen = position;
    	if(positionToGen == null)
    		return false;
    	worldIn.setBlockState(positionToGen, HarshenBlocks.JEWEL_DIRT.getDefaultState().withProperty(JewelDirt.DIRT_TYPE, 1), 3);
    	return true;
    }
}