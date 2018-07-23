package kenijey.harshenuniverse.biomes;

import java.util.ArrayList;

import kenijey.harshenuniverse.base.BasePontusResourceBiome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenBiomes 
{
	public static void register()
	{
		initBiomes();
	}
	
	public static BasePontusResourceBiome pontus_dimensional_biome;
	public static BasePontusResourceBiome pontus_outer_biome;
	public static BasePontusResourceBiome pontus_far_biome;
		
	public static ArrayList<BasePontusResourceBiome> allBiomes = new ArrayList<>();
	
	public static void initBiomes()
	{
		pontus_dimensional_biome = initAndRegBiome(new PontusBiome());
		pontus_outer_biome = initAndRegBiome(new PontusOuterBiome());
		pontus_far_biome = initAndRegBiome(new PontusFarBiome());
	}
	
	private static BasePontusResourceBiome initAndRegBiome(BasePontusResourceBiome biome)
	{
		ForgeRegistries.BIOMES.register(biome);
		allBiomes.add(biome);
		BiomeDictionary.addTypes(biome, biome.getTypes());
		return biome;
	}
	
	public static ArrayList<BasePontusResourceBiome> getAllBiomes() {
		return allBiomes;
	}
}
