package kenymylankca.harshenuniverse.worldgenerators.pontus;

import kenymylankca.harshenuniverse.fluids.HarshenFluids;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenRavine;

public class PontusRavineGenerator extends MapGenRavine
{
	@Override
	protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
		data.setBlockState(x, y, z, y - 1 < 10 ? HarshenFluids.HARSHING_WATER_BLOCK.getDefaultState() : AIR);
	}
}
