package kenijey.harshenuniverse.entity;

import java.util.List;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.biomes.BiomesForHarshenMobsOverworld;
import kenijey.harshenuniverse.config.IdConfig;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class HarshenEntities 
{
	private static final HarshenEntities instance = new HarshenEntities();
	
	public static void init()
	{
		registerEntity(EntitySoullessKnight.class, IdConfig.EntitySoullessKnight ,"soulless_knight", 0x19232C, 295051);
		registerEntity(EntitySoulPart.class, IdConfig.EntitySoulPart, "soul_part", 0xAAAAAA, 0xFFFFFF);
		registerEntity(EntityHarshenSoul.class, IdConfig.EntityHarshenSoul, "harshen_soul",  295051, 0xaed1515);
		registerEntity(EntitySoulShooter.class, IdConfig.EntitySoulShooter, "soul_shooter", 295051, 0xAA00AA);
		registerEntity(EntityKazzendre.class, IdConfig.Kazzendre, "kazzendre", 0xAA0000, 0xFFFFFF);
		registerEntity(EntityBloodySheep.class, IdConfig.BloodySheep, "bloody_sheep", 0xFFFFFF, 0xAA0000);
		
		registerEntity(EntityThrown.class, IdConfig.EntityThrown, "entity_thrown");
		
		registerSpawn(EntitySoulShooter.class, 40, 1, 1, EnumCreatureType.MONSTER, BiomesForHarshenMobsOverworld.getBiomes(true));
		registerSpawn(EntityHarshenSoul.class, 40, 1, 1, EnumCreatureType.MONSTER, BiomesForHarshenMobsOverworld.getBiomes(true));
	}
	
	public static void registerSpawn(Class <? extends EntityLiving > entityClass, int weightedProb, int min, int max, EnumCreatureType typeOfCreature, List<Biome> biomes)
	{
		EntityRegistry.addSpawn(entityClass, weightedProb, min, max, typeOfCreature, biomes.toArray(new Biome[biomes.size()]));
	}
	
	public static void registerEntity(Class entityClass, int id, String entityName, int solidColor, int spotColor)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(HarshenUniverse.MODID, entityName), entityClass, entityName,
				id, HarshenUniverse.instance, 64, 3, true, solidColor, spotColor);
	}
	
	public static void registerEntity(Class entityClass, int id, String entityName)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(HarshenUniverse.MODID, entityName), entityClass, entityName,
				id, HarshenUniverse.instance, 64, 3, true);
	}
	
	public static HarshenEntities instance()
	{
		return instance;
	}
}