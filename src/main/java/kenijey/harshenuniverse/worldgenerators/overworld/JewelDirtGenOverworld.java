package kenijey.harshenuniverse.worldgenerators.overworld;

import java.util.Random;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.blocks.JewelDirt;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class JewelDirtGenOverworld extends WorldGenerator
{
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	BlockPos positionToGen = null;
			if(worldIn.getBlockState(position).getBlock() == Blocks.WATER && worldIn.getBlockState(position.down()).getBlock() == Blocks.DIRT)
				positionToGen = position.down();
    	if(positionToGen == null)
    		return false;
    	worldIn.setBlockState(positionToGen, HarshenBlocks.JEWEL_DIRT.getDefaultState().withProperty(JewelDirt.DIRT_TYPE, 0), 3);
    	return true;
    }
}