package kenymylankca.harshenuniverse.integration.noodle;

import java.util.ArrayList;

import kenymylankca.harshenuniverse.HarshenUtils;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;

public class NoodleEvent extends ChunkGeneratorEvent 
{
	/**The position of the chunk*/
	private final int chunkX, chunkZ;
	
	/**The world*/
	private final World world;
	
	/**The Chunk Primer used for this chunk generation*/
	private ChunkPrimer primer;
	
	/**
	 * 
	 * @param gen The Chunk Generator that this stems from
	 * @param world The world
	 * @param x The Chunk X position
	 * @param z The Chunk Z position
	 * @param oldPrimer The old ChunkPrimer
	 */
	public NoodleEvent(IChunkGenerator gen, World world, int x, int z, ChunkPrimer oldPrimer) {
		super(gen);
		this.world = world;
		this.chunkX = x;
		this.chunkZ = z;
		this.primer = oldPrimer;
	}
	
	/**
	 * Should the noodle generator run
	 * @return
	 * if Noodle should override this world gen.
	 */
	public boolean isNoodle()
	{
		return true;
	}
	
	/**
	 * The Blocks used for the noodles. Preferably use the "stone" of your dimension
	 * @param chunkX the chunk X coordinate 
	 * @param chunkZ the chunk Z coordinate 
	 * @return A list of blocks to use for the noodles.
	 */
	public Block[] getNoodleBlocks(int chunkX, int chunkZ)
	{
		ArrayList<Block> blockList = HarshenUtils.getPontusBlocks(chunkX, 100, chunkZ);
		Block[] blocks = new Block[blockList.size()];
		for(int i = 0; i < blocks.length; i++)
			blocks[i] = blockList.get(i);
		return blocks;
	}
	
	public void setPrimer(ChunkPrimer primer) {
		this.primer = primer;
	}
	
	public ChunkPrimer getPrimer() {
		return primer;
	}
	
	public int getChunkX() {
		return chunkX;
	}
	
	public int getChunkZ() {
		return chunkZ;
	}
	
	public World getWorld() {
		return world;
	}
}
