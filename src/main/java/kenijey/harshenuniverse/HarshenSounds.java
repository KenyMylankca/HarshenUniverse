package kenijey.harshenuniverse;

import java.util.ArrayList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenSounds {
	
	public final static SoundEvent BLOOD_COLLECTOR_USE = reg("blood_collector");
	public final static SoundEvent LIGHTNING_HIT = reg("lightning_hit");
	public final static SoundEvent RIPPER_SHOOT = reg("ripper_shoot");
	public final static SoundEvent LIGHTNING_RITUAL = reg("lightning_ritual");
	public final static SoundEvent HERETIC_RITUAL = reg("heretic_ritual");
	public final static SoundEvent LIGHTNING_SPELLING = reg("lightning_spelling");
	public final static SoundEvent MAGIC_TABLE = reg("magic_table");
	public final static SoundEvent HERETIC_CAULDRON_BLENDING = reg("heretic_cauldron_blending");
	public final static SoundEvent JAGUAR_DEFENSE = reg("jaguar_defense");
	public final static SoundEvent IRON_SCYTHE_HIT = reg("iron_scythe_hit");
	public final static SoundEvent RAPTOR_SCYTHE_HIT = reg("raptor_scythe_hit");
	public final static SoundEvent SOUL_HARSHER_SWORD_HIT = reg("soul_harsher_sword_hit");
	public final static SoundEvent SOUL_RIPPER_BOW_HIT = reg("soul_ripper_bow_hit");
	public final static SoundEvent IRON_HIT = reg("iron_hit");
	public final static SoundEvent ONE_RING = reg("one_ring");
	public final static SoundEvent SOUL_SHIELD_ADD = reg("soul_shield_add");
	public final static SoundEvent SOUL_SHIELD_REMOVE = reg("soul_shield_remove");
	public final static SoundEvent HARSHEN_MOB_HURT = reg("harshen_mob_hurt");
	public final static SoundEvent KAZZENDRE_AMBIENT = reg("kazzendre_ambient");
	public final static SoundEvent KAZZENDRE_HIT = reg("kazzendre_hit");
	public final static SoundEvent KAZZENDRE_HURT = reg("kazzendre_hurt");
	public final static SoundEvent SPAWNER_SUMMON = reg("spawner_summon");
	public final static SoundEvent BLOOD_RITUAL = reg("blood_ritual");
	public final static SoundEvent PONTUS_WIND = reg("pontus_wind");
	
	public static void register()
	{
		loadSound(BLOOD_COLLECTOR_USE);
		loadSound(LIGHTNING_HIT);
		loadSound(RIPPER_SHOOT);
		loadSound(LIGHTNING_RITUAL);
		loadSound(HERETIC_RITUAL);
		loadSound(LIGHTNING_SPELLING);
		loadSound(MAGIC_TABLE);
		loadSound(HERETIC_CAULDRON_BLENDING);
		loadSound(JAGUAR_DEFENSE);
		loadSound(SOUL_HARSHER_SWORD_HIT);
		loadSound(SOUL_RIPPER_BOW_HIT);
		loadSound(IRON_HIT);
		loadSound(ONE_RING);
		loadSound(SOUL_SHIELD_ADD);
		loadSound(SOUL_SHIELD_REMOVE);
		loadSound(HARSHEN_MOB_HURT);
		loadSound(KAZZENDRE_AMBIENT);
		loadSound(KAZZENDRE_HIT);
		loadSound(KAZZENDRE_HURT);
		loadSound(SPAWNER_SUMMON);
		loadSound(BLOOD_RITUAL);
		loadSound(PONTUS_WIND);
	}
	
	private static void loadSound(SoundEvent event)
	{
		event.toString();
	}
	
	private static ArrayList<SoundEvent> reg(String... name)
	{
		ArrayList<SoundEvent> fin = new ArrayList<SoundEvent>();
		for(String s : name)
		{
			ResourceLocation loc = new ResourceLocation(HarshenUniverse.MODID, s);
			ForgeRegistries.SOUND_EVENTS.register(new SoundEvent(loc).setRegistryName(loc));
			fin.add(ForgeRegistries.SOUND_EVENTS.getValue(loc));
		}
		return fin;
	}
	
	private static SoundEvent reg(String name)
	{
		ResourceLocation loc = new ResourceLocation(HarshenUniverse.MODID, name);
		ForgeRegistries.SOUND_EVENTS.register(new SoundEvent(loc).setRegistryName(loc));
		return ForgeRegistries.SOUND_EVENTS.getValue(loc);
	}
}