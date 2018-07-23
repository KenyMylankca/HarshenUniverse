package kenijey.harshenuniverse.structures.pontus;

import java.util.ArrayList;
import java.util.Random;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.interfaces.ICommandStructure;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FauxCauldronStructure implements ICommandStructure
{

	@Override
	public String structureName() {
		return "hereticritual";
	}

	@Override
	public void addToWorld(World world, BlockPos pos, Random random, boolean useRuin) {
		ArrayList<Integer> maxList = new ArrayList<>(HarshenUtils.toArray(-4, 5));
		maxList.add(Math.abs(maxList.get(0)));
		maxList.add(Math.abs(maxList.get(1)));
		boolean switchFlag = true;
		for(int x = maxList.get(0); x < maxList.get(1); x++)
			for(int z = maxList.get(0); z < maxList.get(1); z++)
			{
				if(!(maxList.contains(x) && maxList.contains(z)) && switchFlag)
					world.setBlockState(pos.add(x, -1, z), Blocks.OBSIDIAN.getDefaultState(), 3);
				else
					world.setBlockState(pos.add(x, -1, z), Blocks.GRASS.getDefaultState(), 3);
				switchFlag = !switchFlag;
			}
		for(int x = -1; x < 2; x++)
			for(int z = -1; z < 2; z++)
				if(!(x == 0 && z == 0))
					world.setBlockState(pos.add(x, 0, z), HarshenBlocks.BLOOD_BLOCK.getDefaultState(), 3);
		for(EnumFacing facing : EnumFacing.HORIZONTALS)
		{
			world.setBlockState(pos.offset(facing, 2), HarshenBlocks.BLOOD_BLOCK.getDefaultState(), 3);
			world.setBlockState(pos.offset(facing, 3), HarshenBlocks.HARSHEN_DIMENSIONAL_PEDESTAL.getDefaultState(), 3);
		}
		ArrayList<Integer> pedestalDistanceList = new ArrayList<>(HarshenUtils.toArray(-2, 2));
		for(int x : pedestalDistanceList)
			for(int z : pedestalDistanceList)
				world.setBlockState(pos.add(x, 0, z), HarshenBlocks.HARSHEN_DIMENSIONAL_PEDESTAL.getDefaultState(), 3);
		world.setBlockState(pos, HarshenBlocks.HERETIC_CAULDRON.getDefaultState(), 3);
	}
	
}
