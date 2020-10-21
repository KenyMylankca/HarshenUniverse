package kenymylankca.harshenuniverse.worldgenerators.pontus;
import kenymylankca.harshenuniverse.fluids.HarshenFluids;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;

public class PontusCaveGenerator extends MapGenCaves 
{
    protected static final IBlockState BLK_LAVA = HarshenFluids.HARSHING_WATER_BLOCK.getDefaultState();
	
	@Override
	protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop,
			IBlockState state, IBlockState up) {
        data.setBlockState(x, y, z, y - 1 < 10 ? BLK_LAVA : BLK_AIR);
	}
}
