package com.kenymylankca.harshenuniverse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.kenymylankca.harshenuniverse.base.HarshenStructure;
import com.kenymylankca.harshenuniverse.config.GeneralConfig;
import com.kenymylankca.harshenuniverse.dimensions.DimensionPontus;
import com.kenymylankca.harshenuniverse.worldgenerators.overworld.JewelDirtGenOverworld;
import com.kenymylankca.harshenuniverse.worldgenerators.overworld.NocturneBloomGenerator;
import com.kenymylankca.harshenuniverse.worldgenerators.pontus.JewelDirtGenPontus;
import com.kenymylankca.harshenuniverse.worldgenerators.pontus.PontusWorldGeneratorItiumOre;
import com.kenymylankca.harshenuniverse.worldgenerators.pontus.PontusWorldGeneratorPontusEmeraldOre;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockStone;
import net.minecraft.core.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class HarshenWorldGen implements IWorldGenerator
{
    private final WorldGenMinable harshenSoulOreOverworld = new WorldGenMinable(HarshenBlocks.HARSHEN_SOUL_ORE.getDefaultState(), 3);
    private final WorldGenerator jewelDirtOverworld = new JewelDirtGenOverworld();
    private final WorldGenerator nocturneBloomOverworld = new NocturneBloomGenerator();
    
    private final WorldGenerator pontusItiumOre = new PontusWorldGeneratorItiumOre();
    private final WorldGenerator pontusEmeraldOre = new PontusWorldGeneratorPontusEmeraldOre();
    private final WorldGenerator pontusJewelDirt = new JewelDirtGenPontus();
    
    public static final int [] castleChunks = {33, 35};
    public static final int [] graveyardChunks = {27, -25};
    public static final int [] houseChunks = {40, 42};
    
    public static int castleDelay = 0;
    public static int graveyardDelay = 0;
    public static int houseDelay = 0;
    
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if(castleDelay > 0)
			castleDelay++;
		if(graveyardDelay > 0)
			graveyardDelay++;
		int dim = world.provider.getDimension();
		if(dim == 0)
		{
			if(chunkX == castleChunks[0] && chunkZ == castleChunks[1])
				castleDelay++;
			if(castleDelay >= GeneralConfig.structureProtectorDelay)
			{
				HarshenDataFileManager manager = new HarshenDataFileManager(world);
				manager.writeStructurePosToFile(world, HarshenStructures.CASTLE.generateStucture(world, random, castleChunks[0], castleChunks[1]), "castle");
				castleDelay = 0;
			}
			
			if(chunkX == graveyardChunks[0] && chunkZ == graveyardChunks[1])
				graveyardDelay++;
			if(graveyardDelay >= GeneralConfig.structureProtectorDelay)
			{
				HarshenDataFileManager manager = new HarshenDataFileManager(world);
				manager.writeStructurePosToFile(world, HarshenStructures.GRAVEYARD.generateStucture(world, random, graveyardChunks[0], graveyardChunks[1]), "graveyard");
				graveyardDelay = 0;
			}
			
			blockGenerator(this.harshenSoulOreOverworld, world, random, chunkX, chunkZ, 4, 0, 20);
			plantGenerator(HarshenBlocks.HARSHEN_SOUL_FLOWER, world, random, chunkX, chunkZ, 0.1f, 60, 130, false);
			plantGenerator(HarshenBlocks.PLANT_OF_GLEAM, world, random, chunkX, chunkZ, 0.1f, 111, 255, true);
			plantGenerator(HarshenBlocks.AKZENIA_MUSHROOM, world, random, chunkX, chunkZ, 0.1f, 0, 120, true);
	    	blockGenerator(jewelDirtOverworld, world, random, chunkX, chunkZ, 70, 0, 200);
	    	blockGenerator(nocturneBloomOverworld, world, random, chunkX, chunkZ, 50, 0, 60);
		}
		else if(dim == DimensionPontus.DIMENSION_ID)
		{
			if(houseDelay > 0)
				houseDelay++;
			
			if(chunkX == houseChunks[0] && chunkZ == houseChunks[1])
			{
				houseDelay++;
				HarshenDataFileManager manager = new HarshenDataFileManager(world);
				manager.writebooleanToFile("bloody_mary_alive", false);
			}
			
			if(houseDelay >= GeneralConfig.structureProtectorDelay)
			{
				HarshenDataFileManager manager = new HarshenDataFileManager(world);
				manager.writeStructurePosToFile(world, HarshenStructures.HOUSE.generateStucture(world, random, houseChunks[0], houseChunks[1]), "house");
				houseDelay = 0;
			}
			
	    	blockGenerator(this.pontusItiumOre, world, random, chunkX, chunkZ, 11, 0, 255);
	    	blockGenerator(this.pontusEmeraldOre, world, random, chunkX, chunkZ, 12, 0, 255);
	    	plantGenerator(HarshenBlocks.HARSHEN_SOUL_FLOWER, world, random, chunkX, chunkZ, 0.5f, 100, 200, false);
	    	blockGenerator(pontusJewelDirt, world, random, chunkX, chunkZ, 20, 0, 200);
		}
		generateStructure(world, HarshenStructure.get(dim), random, chunkX, chunkZ);
	}
	
	private void generateStructure(World world, ArrayList<HarshenStructure> structures, Random random, int chunkX, int chunkZ)
	{
		Collections.shuffle(structures);
		for(HarshenStructure struc : structures)
			if(struc.canLoadAt(world.provider.getDimension(), chunkX, chunkZ) && struc.generateStucture(world, random, chunkX, chunkZ) != null)
				break;
	}
	
	private void blockGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chancesPerChunk, int minHeight, int maxHeight) 
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
	
	private void plantGenerator(Block plant, World worldIn, Random random, int chunkX, int chunkZ, float chancesToSpawn, int minHeight, int maxHeight, boolean dirtOnly)
	{
		int heightDiff = maxHeight - minHeight + 1;
		if(random.nextFloat() < chancesToSpawn)
		{
			int x = chunkX * 16 + random.nextInt(16);
			int y = minHeight + random.nextInt(heightDiff);
			int z = chunkZ * 16 + random.nextInt(16);
			BlockPos position = worldIn.getTopSolidOrLiquidBlock(new BlockPos(x, y, z));
			BlockPos blockpos = position.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
	
	        if (worldIn.isAirBlock(blockpos) && (worldIn.provider.isSurfaceWorld() || blockpos.getY() < 255) && worldIn.isSideSolid(blockpos.down(), EnumFacing.UP) && !(worldIn.getBlockState(blockpos.down()).getBlock() instanceof BlockStone))
	        	if(!dirtOnly || (dirtOnly && worldIn.getBlockState(blockpos.down()).getBlock() instanceof BlockGrass))
	        		worldIn.setBlockState(blockpos,plant.getDefaultState(), 1);
		}
	}
}