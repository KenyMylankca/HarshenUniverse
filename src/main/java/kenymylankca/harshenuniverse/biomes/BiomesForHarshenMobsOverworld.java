package kenymylankca.harshenuniverse.biomes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class BiomesForHarshenMobsOverworld
{
	public static List<Biome> getBiomes(boolean pontus) {
		List<Biome> biomesList = new ArrayList<Biome>();
		biomesList.add(Biomes.BEACH);
		biomesList.add(Biomes.BIRCH_FOREST);
		biomesList.add(Biomes.BIRCH_FOREST_HILLS);
		biomesList.add(Biomes.COLD_BEACH);
		biomesList.add(Biomes.COLD_TAIGA);
		biomesList.add(Biomes.COLD_TAIGA_HILLS);
		biomesList.add(Biomes.EXTREME_HILLS);
		biomesList.add(Biomes.EXTREME_HILLS_WITH_TREES);
		biomesList.add(Biomes.FOREST);
		biomesList.add(Biomes.FOREST_HILLS);
		biomesList.add(Biomes.ICE_MOUNTAINS);
		biomesList.add(Biomes.ICE_PLAINS);
		biomesList.add(Biomes.JUNGLE);
		biomesList.add(Biomes.JUNGLE_EDGE);
		biomesList.add(Biomes.JUNGLE_HILLS);
		biomesList.add(Biomes.ROOFED_FOREST);
		biomesList.add(Biomes.TAIGA);
		biomesList.add(Biomes.TAIGA_HILLS);
		if(pontus)
			biomesList.add(HarshenBiomes.pontus_dimensional_biome);
		
		Biome[] biomes = new Biome[biomesList.size()];
		biomes = biomesList.toArray(biomes);
		return biomesList;
	}
}