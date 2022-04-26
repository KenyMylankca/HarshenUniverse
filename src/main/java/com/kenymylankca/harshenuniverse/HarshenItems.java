package com.kenymylankca.harshenuniverse;

import java.util.ArrayList;

import com.kenymylankca.harshenuniverse.base.BaseItemMetaData;
import com.kenymylankca.harshenuniverse.config.HarshenConfigs;
import com.kenymylankca.harshenuniverse.enums.items.EnumBloodCollector;
import com.kenymylankca.harshenuniverse.enums.items.EnumGillette;
import com.kenymylankca.harshenuniverse.enums.items.EnumPontusGateSpawner;
import com.kenymylankca.harshenuniverse.enums.items.EnumPontusGateSpawnerParts;
import com.kenymylankca.harshenuniverse.enums.items.EnumProp;
import com.kenymylankca.harshenuniverse.enums.items.EnumRitualCrystal;
import com.kenymylankca.harshenuniverse.enums.items.EnumRitualStick;
import com.kenymylankca.harshenuniverse.items.AkzeniaSoup;
import com.kenymylankca.harshenuniverse.items.AquaEarring;
import com.kenymylankca.harshenuniverse.items.BloodCollector;
import com.kenymylankca.harshenuniverse.items.BloodEssence;
import com.kenymylankca.harshenuniverse.items.BloodInfusedEnderEye;
import com.kenymylankca.harshenuniverse.items.BloodyApple;
import com.kenymylankca.harshenuniverse.items.BloodyEarring;
import com.kenymylankca.harshenuniverse.items.BloodyPontusCube;
import com.kenymylankca.harshenuniverse.items.BrokenArrow;
import com.kenymylankca.harshenuniverse.items.CriminalPendant;
import com.kenymylankca.harshenuniverse.items.CursedBone;
import com.kenymylankca.harshenuniverse.items.DarkEwydoen;
import com.kenymylankca.harshenuniverse.items.ElementalPendant;
import com.kenymylankca.harshenuniverse.items.ElytraPendant;
import com.kenymylankca.harshenuniverse.items.EmpoweredSoulHarsherSword;
import com.kenymylankca.harshenuniverse.items.EmptyRing;
import com.kenymylankca.harshenuniverse.items.EnderBow;
import com.kenymylankca.harshenuniverse.items.EnderNecklace;
import com.kenymylankca.harshenuniverse.items.EnionBow;
import com.kenymylankca.harshenuniverse.items.Fearring;
import com.kenymylankca.harshenuniverse.items.FeatherEarring;
import com.kenymylankca.harshenuniverse.items.FeedingEarring;
import com.kenymylankca.harshenuniverse.items.FieryRing;
import com.kenymylankca.harshenuniverse.items.Gillette;
import com.kenymylankca.harshenuniverse.items.GlassContainer;
import com.kenymylankca.harshenuniverse.items.GuidanceOfHarshenUniverse;
import com.kenymylankca.harshenuniverse.items.HarshenCrystal;
import com.kenymylankca.harshenuniverse.items.HarshenDimensionalDoor;
import com.kenymylankca.harshenuniverse.items.HarshenNightBlade;
import com.kenymylankca.harshenuniverse.items.HarshenProps;
import com.kenymylankca.harshenuniverse.items.HarshenSoulFragment;
import com.kenymylankca.harshenuniverse.items.HarshenSoulIngot;
import com.kenymylankca.harshenuniverse.items.IronBow;
import com.kenymylankca.harshenuniverse.items.IronHeart;
import com.kenymylankca.harshenuniverse.items.IronScythe;
import com.kenymylankca.harshenuniverse.items.Itium;
import com.kenymylankca.harshenuniverse.items.LightEmittedEssence;
import com.kenymylankca.harshenuniverse.items.LightEmittedSeed;
import com.kenymylankca.harshenuniverse.items.LightningStaff;
import com.kenymylankca.harshenuniverse.items.LootingEarring;
import com.kenymylankca.harshenuniverse.items.MineRing;
import com.kenymylankca.harshenuniverse.items.MysticFeather;
import com.kenymylankca.harshenuniverse.items.OneRing;
import com.kenymylankca.harshenuniverse.items.PontusCube;
import com.kenymylankca.harshenuniverse.items.PontusRing;
import com.kenymylankca.harshenuniverse.items.PontusWorldGateSpawner;
import com.kenymylankca.harshenuniverse.items.PontusWorldGateSpawnerPart;
import com.kenymylankca.harshenuniverse.items.PowderOfHeretism;
import com.kenymylankca.harshenuniverse.items.PunchyRing;
import com.kenymylankca.harshenuniverse.items.RaptorScythe;
import com.kenymylankca.harshenuniverse.items.ReachPendant;
import com.kenymylankca.harshenuniverse.items.ReflectorPendant;
import com.kenymylankca.harshenuniverse.items.RingOfBlood;
import com.kenymylankca.harshenuniverse.items.RitualCrystal;
import com.kenymylankca.harshenuniverse.items.RitualStick;
import com.kenymylankca.harshenuniverse.items.SolidifyingPaste;
import com.kenymylankca.harshenuniverse.items.SoulBindingPendant;
import com.kenymylankca.harshenuniverse.items.SoulHarsherPickaxe;
import com.kenymylankca.harshenuniverse.items.SoulHarsherSpade;
import com.kenymylankca.harshenuniverse.items.SoulHarsherSword;
import com.kenymylankca.harshenuniverse.items.SoulInfusedIngot;
import com.kenymylankca.harshenuniverse.items.SoulRipperBow;
import com.kenymylankca.harshenuniverse.items.SoulShield;
import com.kenymylankca.harshenuniverse.items.Telering;
import com.kenymylankca.harshenuniverse.items.ValorBadge;
import com.kenymylankca.harshenuniverse.items.XrayPendant;
import com.kenymylankca.harshenuniverse.items.ZombiPendant;
import com.kenymylankca.harshenuniverse.items.ZombieEye;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.registries.ForgeRegistries;

public class HarshenItems 
{
	public static final Item ITEM_HARSHEN_DIMENSIONAL_DOOR = new HarshenDimensionalDoor();
	public static final Item HARSHEN_BOOK = new GuidanceOfHarshenUniverse();
	public static final Item HARSHEN_SOUL_FRAGMENT = new HarshenSoulFragment();
	public static final Item SOUL_HARSHER_SWORD = new SoulHarsherSword();
	public static final Item EMPOWERED_SOUL_HARSHER_SWORD = new EmpoweredSoulHarsherSword();
	public static final Item SOUL_HARSHER_PICKAXE = new SoulHarsherPickaxe();
	public static final Item ITIUM = new Itium();
	public static final Item HARSHEN_CRYSTAL = new HarshenCrystal();
	public static final Item HARSHEN_SOUL_INGOT = new HarshenSoulIngot();
	public static final Item PONTUS_RING = new PontusRing();
	public static final Item BLOODY_EARRING = new BloodyEarring();
	public static final Item BLOOD_ESSENCE = new BloodEssence();
	public static final BaseItemMetaData PONTUS_WORLD_GATE_SPAWNER_PARTS = new PontusWorldGateSpawnerPart();
	public static final BaseItemMetaData PONTUS_WORLD_GATE_SPAWNER = new PontusWorldGateSpawner();
	public static final Item LIGHT_EMITTED_SEED = new LightEmittedSeed();
	public static final Item LIGHT_EMITTED_ESSENCE = new LightEmittedEssence();
	public static final BaseItemMetaData BLOOD_COLLECTOR = new BloodCollector();
	public static final BaseItemMetaData RITUAL_CRYSTAL = new RitualCrystal();
	public static final BaseItemMetaData RITUAL_STICK = new RitualStick();
	public static final Item PROPS = new HarshenProps();
	public static final BaseItemMetaData GLASS_CONTAINER = new GlassContainer();
	public static final Item SOUL_INFUSED_INGOT = new SoulInfusedIngot();
	public static final Item FEATHER_EARRING = new FeatherEarring();
	public static final Item FEARRING = new Fearring();
	public static final Item ONE_RING = new OneRing();
	public static final Item CRIMINAL_PENDANT = new CriminalPendant();
	public static final Item TELERING = new Telering();
	public static final Item BLOOD_INFUSED_ENDER_EYE = new BloodInfusedEnderEye();
	public static final Item ELEMENTAL_PENDANT = new ElementalPendant();
	public static final Item POWDER_OF_HERETISM = new PowderOfHeretism();
	public static final Item ZOMBIE_EYE = new ZombieEye();
	public static final Item ZOMBI_PENDANT = new ZombiPendant();
	public static final Item BLOODY_APPLE = new BloodyApple();
	public static final Item PONTUS_CUBE = new PontusCube();
	public static final Item MINERING = new MineRing();
	public static final Item BLOODY_PONTUS_CUBE = new BloodyPontusCube();
	public static final Item PUNCHY_RING = new PunchyRing();
	public static final Item LOOTING_EARRING = new LootingEarring();
	public static final Item ENDER_NECKLACE = new EnderNecklace();
	public static final Item SOUL_SHIELD = new SoulShield();
	public static final Item AQUA_EARRING = new AquaEarring();
	public static final Item XRAY_PENDANT = new XrayPendant();
	public static final Item IRON_BOW = new IronBow();
	public static final Item ENION_BOW = new EnionBow();
	public static final Item SOUL_RIPPER_BOW = new SoulRipperBow();
	public static final Item FIERY_RING = new FieryRing();
	public static final Item BROKEN_ARROW = new BrokenArrow();
	public static final Item VALOR_BADGE = new ValorBadge();
	public static final Item IRON_SCYTHE = new IronScythe();
	public static final Item RAPTOR_SCYTHE = new RaptorScythe();
	public static final Item ELYTRA_PENDANT = new ElytraPendant();
	public static final Item MYSTIC_FEATHER = new MysticFeather();
	public static final Item LIGHTNING_STAFF = new LightningStaff();
	public static final Item SOLIDIFYING_PASTE = new SolidifyingPaste();
	public static final Item ENDER_BOW = new EnderBow();
	public static final Item FEEDING_EARRING = new FeedingEarring();
	public static final Item IRON_HEART = new IronHeart();
	public static final Item RING_OF_BLOOD = new RingOfBlood();
	public static final Item SOUL_BINDING_PENDANT = new SoulBindingPendant();
	public static final Item REACH_PENDANT = new ReachPendant();
	public static final Item EMPTY_RING = new EmptyRing();
	public static final BaseItemMetaData GILLETTE = new Gillette();
	public static final Item CURSED_BONE = new CursedBone();
	public static final Item SOUL_HARSHER_SPADE = new SoulHarsherSpade();
	public static final Item REFLECTOR_PENDANT = new ReflectorPendant();
	public static final Item AKZENIA_SOUP = new AkzeniaSoup();
	public static final Item DARK_EWYDOEN = new DarkEwydoen();
	public static final Item HARSHEN_NIGHT_BLADE = new HarshenNightBlade();
	
	public static void preInit()
	{
		regItem(ITEM_HARSHEN_DIMENSIONAL_DOOR, 8);
		regItem(HARSHEN_SOUL_FRAGMENT, 8);
		regItem(ITIUM, 8);
		regItem(HARSHEN_CRYSTAL, 18);
		regItem(HARSHEN_SOUL_INGOT, 8);
		regItem(BLOOD_ESSENCE, 8);
		regItem(LIGHT_EMITTED_ESSENCE, 8);
		regItem(LIGHT_EMITTED_SEED, 16);
		regItem(SOUL_INFUSED_INGOT, 2);
		regItem(POWDER_OF_HERETISM, 8);
		regItem(ZOMBIE_EYE, 64);
		regItem(BLOODY_APPLE, 13);
		regItem(PONTUS_CUBE, 7);
		regItem(BLOODY_PONTUS_CUBE, 6);
		regItem(BROKEN_ARROW, 64);
		regItem(VALOR_BADGE, 32);
		regItem(MYSTIC_FEATHER, 12);
		regItem(SOLIDIFYING_PASTE, 64);
		regItem(IRON_HEART, 64);
		regItem(EMPTY_RING, 16);
		regItem(CURSED_BONE, 16);
		regItem(BLOOD_INFUSED_ENDER_EYE, 4);
		regItem(AKZENIA_SOUP, 1);
		regItem(HARSHEN_BOOK);
		
		//WEAPONS
		regItem(EMPOWERED_SOUL_HARSHER_SWORD);
		regItem(DARK_EWYDOEN);
		regItem(SOUL_HARSHER_SWORD);
		regItem(IRON_SCYTHE);
		regItem(RAPTOR_SCYTHE);
		regItem(HARSHEN_NIGHT_BLADE);
		regItem(IRON_BOW);
		regItem(ENION_BOW);
		regItem(SOUL_RIPPER_BOW);
		regItem(ENDER_BOW);
		regItem(LIGHTNING_STAFF);
		
		//TOOLS
		regItem(SOUL_HARSHER_PICKAXE);
		regItem(SOUL_HARSHER_SPADE);
		
		//ACCESSORIES
		regItem(ZOMBI_PENDANT);
		regItem(PUNCHY_RING);
		regItem(LOOTING_EARRING);
		regItem(ENDER_NECKLACE);
		regItem(FEATHER_EARRING);
		regItem(FEARRING);
		regItem(ONE_RING);
		regItem(CRIMINAL_PENDANT);
		regItem(TELERING);
		regItem(MINERING);
		regItem(PONTUS_RING);
		regItem(BLOODY_EARRING);
		regItem(SOUL_SHIELD);
		regItem(AQUA_EARRING);
		regItem(XRAY_PENDANT);
		regItem(FIERY_RING);
		regItem(ELYTRA_PENDANT);
		regItem(FEEDING_EARRING);
		regItem(RING_OF_BLOOD);
		regItem(SOUL_BINDING_PENDANT);
		regItem(REACH_PENDANT);
		regItem(REFLECTOR_PENDANT);
		regItem(ELEMENTAL_PENDANT);
		
		regMetaItem(GILLETTE, EnumGillette.getNames(), "gillette_");
		regMetaItem(RITUAL_STICK, emptyList(EnumRitualStick.values().length), "ritual_stick");
		regMetaItem(PONTUS_WORLD_GATE_SPAWNER, EnumPontusGateSpawner.getNames(), "pontus_world_gate_spawner_");
		regMetaItem(PONTUS_WORLD_GATE_SPAWNER_PARTS, EnumPontusGateSpawnerParts.getNames(), "pontus_world_gate_spawner_part_");
		regMetaItem(PROPS, EnumProp.getNames(), "prop_");
		regMetaItem(BLOOD_COLLECTOR, EnumBloodCollector.getNames(), "blood_collector_");
		regMetaItem(RITUAL_CRYSTAL, 12, EnumRitualCrystal.getNames(), "ritual_crystal_");
		regMetaItem(GLASS_CONTAINER, emptyList(65536), "glass_container", new ExceptionName(0, "_empty"), new ExceptionName(12, "_magic"));
	}
	
	public final static ArrayList<Item> ALL_ITEMS= new ArrayList<Item>();
	
	public static void regRenders()
	{
		for(Item item : ALL_ITEMS)
			regRender(item);
		regRenderMeta();
	}
	
	private static String[] emptyList(int size)
	{
		String[] s = new String[size];
		for(int i = 0; i < size; i++)
			s[i] = "";
		return s;
	}
	
	private static Item getItem(Item item)
	{
		return item;
	}
	
	public static void regItem(Item item)
	{
		regItem(item, 1);
	}
	
	public static void regItem(Item item, int stackSize)
	{
		ALL_ITEMS.add(item);
		item.setMaxStackSize(stackSize);
		HarshenConfigs.ITEMS.allComponants.add(item);
	}
	
	public static void regMetaItem(BaseItemMetaData item, int stackSize, String[] names, String prefix, ExceptionName...exceptionNames)
	{
		item.setMaxStackSize(stackSize);
		for(ExceptionName exc : exceptionNames)
			names[exc.position] = exc.name;
		HarshenConfigs.ITEMS.allComponants.add(item);
		allMetaItems.add(item);
		allMetaNames.add(names);
		allMetaPrefix.add(prefix);
	}
	
	public static void regMetaItem(Item item, int stackSize, String[] names, String prefix, ExceptionName...exceptionNames)
	{
		item.setMaxStackSize(stackSize);
		for(ExceptionName exc : exceptionNames)
			names[exc.position] = exc.name;
		HarshenConfigs.ITEMS.allComponants.add(item);
		allMetaItems.add(item);
		allMetaNames.add(names);
		allMetaPrefix.add(prefix);
	}
	
	private static ArrayList<Item> allMetaItems = new ArrayList<Item>();
	private static ArrayList<String[]> allMetaNames = new ArrayList<String[]>();
	private static ArrayList<String> allMetaPrefix = new ArrayList<String>();
	
	public static void regMetaItem(BaseItemMetaData item, String[] names, String prefix, ExceptionName...exceptionNames)
	{
		regMetaItem(item, 1, names, prefix, exceptionNames);
	}
	
	public static void regMetaItem(Item item, String[] names, String prefix, ExceptionName...exceptionNames)
	{
		regMetaItem(item, 1, names, prefix, exceptionNames);
	}
	
	public static void regRender(Item item)
	{
		item.setCreativeTab(HarshenUniverse.harshenTab);
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static void regRenderMeta()
	{
		for(int i = 0; i < allMetaItems.size(); i++)
			for(int j = 0; j < allMetaNames.get(i).length; j++)
				regRender(allMetaItems.get(i), j, allMetaPrefix.get(i) + allMetaNames.get(i)[j]);
	}
	
	public static void regRender(Item item, int meta, String fileName)
	{
		new ItemStack(item, 1, meta).getItem().setCreativeTab(HarshenUniverse.harshenTab);
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(HarshenUniverse.MODID, fileName), "inventory"));
	}

	public static void register() 
	{
		for(Item item : HarshenConfigs.ITEMS.allComponants)
			if(HarshenConfigs.ITEMS.isEnabled(item))
				ForgeRegistries.ITEMS.register(item);
	}
}

class ExceptionName
{
	public final int position;
	public final String name;
	public ExceptionName(int position, String name) {
		this.position = position;
		this.name = name;
	}
}