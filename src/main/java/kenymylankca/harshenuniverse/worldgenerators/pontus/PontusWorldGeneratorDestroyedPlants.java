package kenymylankca.harshenuniverse.worldgenerators.pontus;

import java.util.Random;

import kenymylankca.harshenuniverse.HarshenBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class PontusWorldGeneratorDestroyedPlants extends WorldGenerator
{
    private final IBlockState blockState;

    public PontusWorldGeneratorDestroyedPlants()
    {
        this.blockState = HarshenBlocks.HARSHEN_DESTROYED_PLANT.getDefaultState();
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position))
        {
            position = position.down();
        }

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.add(0, -1, 0)) == HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT.getDefaultState())
            {
                worldIn.setBlockState(blockpos, this.blockState, 2);
            }
        }
        return true;
    }
}