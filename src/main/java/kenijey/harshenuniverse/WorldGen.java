package kenijey.harshenuniverse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import kenijey.harshenuniverse.base.HarshenStructure;
import kenijey.harshenuniverse.dimensions.DimensionPontus;
import kenijey.harshenuniverse.worldgenerators.overworld.JewelDirtGenOverworld;
import kenijey.harshenuniverse.worldgenerators.pontus.JewelDirtGenPontus;
import kenijey.harshenuniverse.worldgenerators.pontus.PontusWorldGeneratorItiumOre;
import kenijey.harshenuniverse.worldgenerators.pontus.PontusWorldGeneratorPontusEmeraldOre;
import net.minecraft.block.BlockFlower;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator
{
    private final WorldGenMinable soulore = new WorldGenMinable(HarshenBlocks.HARSHEN_SOUL_ORE.getDefaultState(), 3);
    private final WorldGenerator itiumOre = new PontusWorldGeneratorItiumOre();
    private final WorldGenerator pontusEmeraldOre = new PontusWorldGeneratorPontusEmeraldOre();
    private final WorldGenerator jewelDirtOverworld = new JewelDirtGenOverworld();
    private final WorldGenerator jewelDirtPontus = new JewelDirtGenPontus();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		int dim = world.provider.getDimension();
		if(dim == 0)
		{
			if(chunkX == 22 && chunkZ == 22)
				HarshenStructures.CASTLE.generateStucture(world, random, chunkX, chunkZ);
			if(chunkX == 11 && chunkZ == -5)
				HarshenStructures.GRAVEYARD.generateStucture(world, random, chunkX, chunkZ);
			runGenerator(this.soulore, world, random, chunkX, chunkZ, 10, 0, 20);
	    	flowerGenerator(HarshenBlocks.HARSHEN_SOUL_FLOWER, world, random, chunkX, chunkZ, 0.1f);
	    	flowerGenerator(HarshenBlocks.PLANT_OF_GLEAM, world, random, chunkX, chunkZ, 0.1f);
	    	runGenerator(jewelDirtOverworld, world, random, chunkX, chunkZ, 200, 0, 200);
		}
		else if(dim == DimensionPontus.DIMENSION_ID)
		{
	    	runGenerator(this.itiumOre, 		world, random, chunkX, chunkZ, 11, 0, 255);
	    	runGenerator(this.pontusEmeraldOre, world, random, chunkX, chunkZ, 12, 0, 255);
	    	flowerGenerator(HarshenBlocks.HARSHEN_SOUL_FLOWER, world, random, chunkX, chunkZ, 0.5f);
	    	runGenerator(jewelDirtPontus, world, random, chunkX, chunkZ, 22, 0, 200);
	    	
		}
		generateStructure(world, HarshenStructure.get(dim), random, chunkX, chunkZ);
	}
	
	private void generateStructure(World world, ArrayList<HarshenStructure> structures, Random random, int chunkX, int chunkZ)
	{
		Collections.shuffle(structures);
		for(HarshenStructure struc : structures)
			if(struc.canLoadAt(world.provider.getDimension(), chunkX, chunkZ) && struc.generateStucture(world, random, chunkX, chunkZ))
				break;
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chancesPerChunk, int minHeight, int maxHeight) 
	{
		int heightDiff = maxHeight - minHeight + 1;
	    for (int i = 0; i < chancesPerChunk; i ++) 
	    	{
	    		int x = chunkX * 16 + random.nextInt(16);
		        int y = minHeight + random.nextInt(heightDiff);
		        int z = chunkZ * 16 + random.nextInt(16);
		        generator.generate(world, random, new BlockPos(x, y, z));
	    	} 
	}
	
	private void flowerGenerator(BlockFlower flower, World worldIn, Random random, int chunkX, int chunkZ, float chancesToSpawn)
	{
		for(int i = 0; i < chancesToSpawn; i++)
			if(random.nextFloat() < chancesToSpawn)
			{
				int x = chunkX * 16 + random.nextInt(16);
				int z = chunkZ * 16 + random.nextInt(16);
				BlockPos position = worldIn.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));
				BlockPos blockpos = position.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
		
		        if (worldIn.isAirBlock(blockpos) && (worldIn.provider.isSurfaceWorld() || blockpos.getY() < 255) && 
		        		flower.canBlockStay(worldIn, blockpos, flower.getDefaultState()))
		        {
		            worldIn.setBlockState(blockpos,flower.getDefaultState(), 2);
		        }
			}
	}	
}