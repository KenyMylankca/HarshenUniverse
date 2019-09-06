package kenijey.harshenuniverse.biomes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.base.BasePontusResourceBiome;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;

public class PontusBiomeProvider extends BiomeProvider
{
		
	@Override
	public Biome getBiome(BlockPos pos) {
		return biomeFromPosition(pos);
	}
	
	@Override
	public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height) {
		if (biomes == null || biomes.length < width * height)
        {
            biomes = new Biome[width * height];
        }

        Arrays.fill(biomes, 0, width * height, biomeFromPosition(new BlockPos(x, 0, z)));
        return biomes;
	}
	
	@Override
	public Biome[] getBiomes(Biome[] oldBiomeList, int x, int z, int width, int depth) {
		return getBiomesForGeneration(oldBiomeList, x, z, width, depth);
	}
	
	@Override
	public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
		return null;
	}
	
	@Override
	public Biome[] getBiomes(@Nullable Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag)
    {
        return this.getBiomes(listToReuse, x, z, width, length);
    }
	
	public static BasePontusResourceBiome biomeFromPosition(BlockPos pos)
	{
		ArrayList<BasePontusResourceBiome> biomeList = HarshenBiomes.pontusBiomes;
		double distance = new BlockPos(pos).getDistance(0, pos.getY(), 0);
		for(int i = 0; i < biomeList.size(); i ++)
		{
			BasePontusResourceBiome biomeToCheck = biomeList.get(i);
			if(i + 1 != biomeList.size())
			{
				if(biomeToCheck.distanceStartSpawn() <= distance && biomeList.get(i+1).distanceStartSpawn() > distance)
					return biomeToCheck;
				else;
			}
			else
				return biomeList.get(biomeList.size() - 1);
		}
	
		return HarshenBiomes.pontus_dimensional_biome;
	}
	
	public static double getDistance(BlockPos pos)
	{
		return new BlockPos(pos).getDistance(0, pos.getY(), 0);
	}
	
	public static double getDistance(int x, int z)
	{
		return getDistance(HarshenUtils.chunkToPos(x, z));
	}
	
	public static BasePontusResourceBiome biomeFromPosition(int chunk_X, int chunk_Z)
	{
		return biomeFromPosition(new BlockPos(chunk_X * 16, 0, chunk_Z * 16));
	}
	
	public static BasePontusResourceBiome biomeFromPosition(int x, int z, Object n)
	{
		return biomeFromPosition(new BlockPos(x, 0, z));
	}
	
}
