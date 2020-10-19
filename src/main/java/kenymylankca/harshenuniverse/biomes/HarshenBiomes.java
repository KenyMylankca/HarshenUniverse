package kenymylankca.harshenuniverse.biomes;

import java.util.ArrayList;

import kenymylankca.harshenuniverse.base.BasePontusResourceBiome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenBiomes 
{
	public static void register()
	{
		initBiomes();
	}
	
	public static BasePontusResourceBiome pontus_biome;
	public static BasePontusResourceBiome pontus_outer_biome;
	public static BasePontusResourceBiome pontus_far_biome;
		
	public static ArrayList<BasePontusResourceBiome> pontusBiomes = new ArrayList<>();
	
	public static void initBiomes()
	{
		pontus_biome = initAndRegBiome(new PontusBiome());
		pontus_outer_biome = initAndRegBiome(new PontusOuterBiome());
		pontus_far_biome = initAndRegBiome(new PontusFarBiome());
	}
	
	private static BasePontusResourceBiome initAndRegBiome(BasePontusResourceBiome biome)
	{
		ForgeRegistries.BIOMES.register(biome);
		pontusBiomes.add(biome);
		BiomeDictionary.addTypes(biome, biome.getTypes());
		return biome;
	}
}