package kenijey.harshenuniverse;

import java.util.ArrayList;

import kenijey.harshenuniverse.base.BaseItemMetaData;
import kenijey.harshenuniverse.config.HarshenConfigs;
import kenijey.harshenuniverse.enums.items.EnumBloodCollector;
import kenijey.harshenuniverse.enums.items.EnumGillette;
import kenijey.harshenuniverse.enums.items.EnumPontusGateSpawner;
import kenijey.harshenuniverse.enums.items.EnumPontusGateSpawnerParts;
import kenijey.harshenuniverse.enums.items.EnumProp;
import kenijey.harshenuniverse.enums.items.EnumRitualCrystal;
import kenijey.harshenuniverse.enums.items.EnumRitualStick;
import kenijey.harshenuniverse.items.AkzeniaSoup;
import kenijey.harshenuniverse.items.BloodCollector;
import kenijey.harshenuniverse.items.BloodEssence;
import kenijey.harshenuniverse.items.BloodInfusedEnderEye;
import kenijey.harshenuniverse.items.BloodyApple;
import kenijey.harshenuniverse.items.BloodyEarring;
import kenijey.harshenuniverse.items.BloodyPontusCube;
import kenijey.harshenuniverse.items.BrokenArrow;
import kenijey.harshenuniverse.items.CriminalPendant;
import kenijey.harshenuniverse.items.CursedBone;
import kenijey.harshenuniverse.items.DarkEwydoen;
import kenijey.harshenuniverse.items.ElementalPendant;
import kenijey.harshenuniverse.items.ElytraPendant;
import kenijey.harshenuniverse.items.EmpoweredSoulHarsherSword;
import kenijey.harshenuniverse.items.EmptyRing;
import kenijey.harshenuniverse.items.EnderBow;
import kenijey.harshenuniverse.items.EnderNecklace;
import kenijey.harshenuniverse.items.EnionBow;
import kenijey.harshenuniverse.items.Fearring;
import kenijey.harshenuniverse.items.FeatherEarring;
import kenijey.harshenuniverse.items.FeedingEarring;
import kenijey.harshenuniverse.items.FieryRing;
import kenijey.harshenuniverse.items.Gillette;
import kenijey.harshenuniverse.items.GlassContainer;
import kenijey.harshenuniverse.items.GuidanceOfHarshenUniverse;
import kenijey.harshenuniverse.items.HarshenCrystal;
import kenijey.harshenuniverse.items.HarshenDimensionalDoor;
import kenijey.harshenuniverse.items.HarshenNightBlade;
import kenijey.harshenuniverse.items.HarshenProps;
import kenijey.harshenuniverse.items.HarshenSoulFragment;
import kenijey.harshenuniverse.items.HarshenSoulIngot;
import kenijey.harshenuniverse.items.IronBow;
import kenijey.harshenuniverse.items.IronHeart;
import kenijey.harshenuniverse.items.IronScythe;
import kenijey.harshenuniverse.items.Itium;
import kenijey.harshenuniverse.items.LightEmittedEssence;
import kenijey.harshenuniverse.items.LightEmittedSeed;
import kenijey.harshenuniverse.items.LightningStaff;
import kenijey.harshenuniverse.items.LootingEarring;
import kenijey.harshenuniverse.items.MineRing;
import kenijey.harshenuniverse.items.MysticFeather;
import kenijey.harshenuniverse.items.OneRing;
import kenijey.harshenuniverse.items.PontusCube;
import kenijey.harshenuniverse.items.PontusRing;
import kenijey.harshenuniverse.items.PontusWorldGatePart;
import kenijey.harshenuniverse.items.PontusWorldGateSpawner;
import kenijey.harshenuniverse.items.PowderOfHeretism;
import kenijey.harshenuniverse.items.PunchyRing;
import kenijey.harshenuniverse.items.RaptorScythe;
import kenijey.harshenuniverse.items.ReachPendant;
import kenijey.harshenuniverse.items.ReflectorPendant;
import kenijey.harshenuniverse.items.RingOfBlood;
import kenijey.harshenuniverse.items.RitualCrystal;
import kenijey.harshenuniverse.items.RitualStick;
import kenijey.harshenuniverse.items.SolidifyingPaste;
import kenijey.harshenuniverse.items.SoulBindingPendant;
import kenijey.harshenuniverse.items.SoulHarsherPickaxe;
import kenijey.harshenuniverse.items.SoulHarsherSpade;
import kenijey.harshenuniverse.items.SoulHarsherSword;
import kenijey.harshenuniverse.items.SoulInfusedIngot;
import kenijey.harshenuniverse.items.SoulRipperBow;
import kenijey.harshenuniverse.items.SoulShield;
import kenijey.harshenuniverse.items.Telering;
import kenijey.harshenuniverse.items.ValorBadge;
import kenijey.harshenuniverse.items.WaterEarring;
import kenijey.harshenuniverse.items.XrayPendant;
import kenijey.harshenuniverse.items.ZombiPendant;
import kenijey.harshenuniverse.items.ZombieEye;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

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
	public static final BaseItemMetaData PONTUS_WORLD_GATE_PARTS = new PontusWorldGatePart();
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
	public static final Item WATER_EARRING = new WaterEarring();
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
		regItem(WATER_EARRING);
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
		regMetaItem(PONTUS_WORLD_GATE_PARTS, EnumPontusGateSpawnerParts.getNames(), "pontus_world_gate_part_");
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
