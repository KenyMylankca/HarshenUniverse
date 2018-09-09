package kenijey.harshenuniverse;

import kenijey.harshenuniverse.armor.HarshenArmors;
import kenijey.harshenuniverse.enums.items.GlassContainerValue;
import kenijey.harshenuniverse.enums.items.GlassContainerValues;
import kenijey.harshenuniverse.objecthandlers.HarshenGlassContainerIngredient;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class HarshenCraftingRecipes 
{
	private static final String modid = HarshenUniverse.MODID;
	private static final String group = "harshen_items";
	
	public static void register()
	{		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "harshen_soul_ingot"), new ResourceLocation(group),
				new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT),
				" e ",
				"csc",
				" e ",
				
				'c', new ItemStack(HarshenItems.HARSHEN_CRYSTAL),
				's', new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT),
				'e', new ItemStack(HarshenItems.EMERALD_SHARD));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "pontus_ring"), new ResourceLocation(group),
				new ItemStack(HarshenItems.PONTUS_RING),
				" s ",
				"grg",
				" i ",
				
				'i', new ItemStack(HarshenItems.ITIUM),
				's', new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT),
				'g', new ItemStack(HarshenItems.POWDER_OF_HERETISM),
				'r', new ItemStack(HarshenItems.EMPTY_RING));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "bloody_earring"), new ResourceLocation(group),
				new ItemStack(HarshenItems.BLOODY_EARRING),
				" i ",
				" s ",
				" b ",
				
				'i', new ItemStack(HarshenItems.ITIUM),
				's', new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT),
				'b', new ItemStack(HarshenItems.BLOOD_ESSENCE));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "harshen_jaguar_armor_helmet"), new ResourceLocation(group),
				new ItemStack(HarshenArmors.HARSHEN_JAGUAR_ARMOR_HELMET),
				"iii",
				"i i",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "harshen_jaguar_armor_chestplate"), new ResourceLocation(group),
				new ItemStack(HarshenArmors.HARSHEN_JAGUAR_ARMOR_CHESTPLATE),
				"i i",
				"iii",
				"iii",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "harshen_jaguar_armor_leggings"), new ResourceLocation(group),
				new ItemStack(HarshenArmors.HARSHEN_JAGUAR_ARMOR_LEGGINGS),
				"iii",
				"i i",
				"i i",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "harshen_jaguar_armor_boots"), new ResourceLocation(group),
				new ItemStack(HarshenArmors.HARSHEN_JAGUAR_ARMOR_BOOTS),
				"i i",
				"i i",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "bloody_armor_helmet"), new ResourceLocation(group),
				new ItemStack(HarshenArmors.BLOODY_ARMOR_HELMET),
				"ili",
				"i i",
				
				'i', new ItemStack(Items.LEATHER),
				'l', new ItemStack(HarshenBlocks.BLOODY_WOOL));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "bloody_armor_chestplate"), new ResourceLocation(group),
				new ItemStack(HarshenArmors.BLOODY_ARMOR_CHESTPLATE),
				"i i",
				"ili",
				"ili",
				
				'i', new ItemStack(Items.LEATHER),
				'l', new ItemStack(HarshenBlocks.BLOODY_WOOL));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "bloody_armor_leggings"), new ResourceLocation(group),
				new ItemStack(HarshenArmors.BLOODY_ARMOR_LEGGINGS),
				"ili",
				"i i",
				"i i",
				
				'i', new ItemStack(Items.LEATHER),
				'l', new ItemStack(HarshenBlocks.BLOODY_WOOL));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "bloody_armor_boots"), new ResourceLocation(group),
				new ItemStack(HarshenArmors.BLOODY_ARMOR_BOOTS),
				"l l",
				"i i",
				
				'l', new ItemStack(Items.LEATHER),
				'i', new ItemStack(HarshenBlocks.BLOODY_WOOL));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "harshen_dimensional_pedestal"), new ResourceLocation(group),
				new ItemStack(HarshenBlocks.HARSHEN_DIMENSIONAL_PEDESTAL),
				" i ",
				" i ",
				" i ",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "harshen_dimensional_dirt"), new ResourceLocation(group),
				new ItemStack(HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT, 3),
				" d ",
				"dcd",
				" d ",
				
				'c', new ItemStack(HarshenItems.HARSHEN_CRYSTAL),
				'd', "dirt");
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "ritual_crystal.passive"), new ResourceLocation(group),
				new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 0),
				" l ",
				"cec",
				" l ",
				
				'e', new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT),
				'c', new ItemStack(HarshenItems.HARSHEN_CRYSTAL),
				'l', new ItemStack(HarshenItems.LIGHT_EMITTED_ESSENCE));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "pedestal_slab"), new ResourceLocation(group),
				new ItemStack(HarshenBlocks.PEDESTAL_SLAB),
				"i i",
				"iii",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "heretic_cauldron"), new ResourceLocation(group),
				new ItemStack(HarshenBlocks.HERETIC_CAULDRON),
				"i i",
				"i i",
				"iii",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "ritual_stick"), new ResourceLocation(group),
				new ItemStack(HarshenItems.RITUAL_STICK, 1, 0),
				" s ",
				"s  ",
				
				's', "stickWood");
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "blood_collector"), new ResourceLocation(group),
				new ItemStack(HarshenItems.BLOOD_COLLECTOR, 1, 0),
				"  i",
				"rbi",
				"nri",
				
				'b', "blockIron",
				'i', "ingotIron",
				'r', new ItemStack(Blocks.IRON_BARS),
				'n', "nuggetIron");
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "one_ring"), new ResourceLocation(group),
				new ItemStack(HarshenItems.ONE_RING),
				" g ",
				"pep",
				" g ",
				
				'e', new ItemStack(HarshenItems.EMPTY_RING),
				'p', new ItemStack(HarshenItems.POWDER_OF_HERETISM),
				'g', "ingotGold");
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "telering"), new ResourceLocation(group),
				new ItemStack(HarshenItems.TELERING),
				" e ",
				"grg",
				" p ",
				
				'p', new ItemStack(HarshenItems.POWDER_OF_HERETISM),
				'e', new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE),
				'g', new ItemStack(Items.GOLDEN_CARROT),
				'r', new ItemStack(HarshenItems.EMPTY_RING));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "glass_container"), new ResourceLocation(group),
				new ItemStack(HarshenItems.GLASS_CONTAINER, 1),
				" g ",
				"g g",
				" g ",
				
				'g', new ItemStack(Blocks.GLASS_PANE));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "zombi_pendant"), new ResourceLocation(group),
				new ItemStack(HarshenItems.ZOMBI_PENDANT),
				"g g",
				" z ",
				" e ",
				
				'z', new ItemStack(HarshenItems.ZOMBIE_EYE),
				'g', new ItemStack(Items.GOLD_INGOT),
				'e', new ItemStack(HarshenItems.LIGHT_EMITTED_ESSENCE));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "punchy_ring"), new ResourceLocation(group),
				new ItemStack(HarshenItems.PUNCHY_RING),
				"nbn",
				" i ",
				
				'n', "nuggetIron",
				'b', new ItemStack(Blocks.IRON_BARS),
				'i', new ItemStack(Items.IRON_INGOT));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "iron_bow"), new ResourceLocation(group),
				new ItemStack(HarshenItems.IRON_BOW),
				" il",
				"i l",
				" il",
				
				'i', new ItemStack(Items.IRON_INGOT),
				'l', new ItemStack(Items.LEAD));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "blood_placer"), new ResourceLocation(group),
				new ItemStack(HarshenBlocks.BLOOD_PLACER),
				"qrq",
				"qdq",
				"qqq",
				
				'q', new ItemStack(Items.QUARTZ),
				'd', new ItemStack(Blocks.DISPENSER),
				'r', new ItemStack(Items.COMPARATOR));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "blood_vessel"), new ResourceLocation(group),
				new ItemStack(HarshenBlocks.BLOOD_VESSEL),
				" t ",
				"gwg",
				" t ",
				
				't', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0),
				'g', new ItemStack(Blocks.STAINED_GLASS, 1, 14),
				'w', new ItemStack(HarshenBlocks.BLOODY_WOOL));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "iron_scythe"), new ResourceLocation(group),
				new ItemStack(HarshenItems.IRON_SCYTHE),
				"iii",
				" s ",
				"s  ",
				
				'i', new ItemStack(Items.IRON_INGOT),
				's', "stickWood");
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "blood_factory"), new ResourceLocation(group),
				new ItemStack(HarshenBlocks.BLOOD_FACTORY),
				"t t",
				" t ",
				" i ",
				
				'i', new ItemStack(HarshenItems.SOUL_INFUSED_INGOT),
				't', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 2));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "elytra"), new ResourceLocation(group),
				new ItemStack(Items.ELYTRA),
				"flf",
				"f f",
				"f f",
				
				'f', new ItemStack(HarshenItems.MYSTIC_FEATHER),
				'l', new ItemStack(Items.LEAD));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "solidifying_paste"), new ResourceLocation(group),
				new ItemStack(HarshenItems.SOLIDIFYING_PASTE, 16),
				" d ",
				"dcd",
				" d ",
				
				'd', "dirt",
				'c', Items.CLAY_BALL);
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "harshen_magic_table"), new ResourceLocation(group),
				new ItemStack(HarshenBlocks.HARSHEN_MAGIC_TABLE),
				"wew",
				" c ",
				"w w",
				
				'e', new ItemStack(HarshenItems.LIGHT_EMITTED_SEED),
				'w', new ItemStack(Blocks.OBSIDIAN),
				'c', new ItemStack(HarshenBlocks.HARSHEN_DIMENSIONAL_WOOD_CRATE));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "elytra_pendant"), new ResourceLocation(group),
				new ItemStack(HarshenItems.ELYTRA_PENDANT),
				"fif",
				
				'f', new ItemStack(HarshenItems.MYSTIC_FEATHER),
				'i', new ItemStack(HarshenItems.ITIUM));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "empty_ring"), new ResourceLocation(group),
				new ItemStack(HarshenItems.EMPTY_RING),
				" n ",
				"n n",
				" n ",
				
				'n', "nuggetIron");
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "soul_reminder"), new ResourceLocation(group),
				new ItemStack(HarshenBlocks.SOUL_REMINDER),
				"csc",
				"s s",
				"csc",
				
				'c', new ItemStack(Blocks.WEB),
				's', new ItemStack(Items.STRING));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "bloody_bed"), new ResourceLocation(group),
				new ItemStack(HarshenBlocks.BLOODY_BED),
				"lww",
				"ccc",
				
				'c', new ItemStack(HarshenBlocks.HARSHEN_DIMENSIONAL_WOOD_CRATE),
				'l', new ItemStack(Items.LEATHER),
				'w', new ItemStack(HarshenBlocks.BLOODY_WOOL));
		
		GameRegistry.addShapedRecipe(new ResourceLocation(modid, "wool"), new ResourceLocation(group),
				new ItemStack(Blocks.WOOL),
				"fff",
				"fsf",
				"fff",
				
				'f', "feather",
				's', "string");
		
		for(GlassContainerValue glass : GlassContainerValue.values())
			if(HarshenUtils.glassContainerHasState(glass))
			{
				Block block = ((IBlockState) glass.getType().getStateOrLoc()).getBlock();
				HarshenGlassContainerIngredient[] ingridientList = new HarshenGlassContainerIngredient[2];
				ingridientList[0] = new HarshenGlassContainerIngredient(GlassContainerValues.EMPTY.getStack()); 
				for(int i = 1; i < 2; i++)
					ingridientList[i] = new HarshenGlassContainerIngredient(HarshenUtils.toList(HarshenUtils.getAllRelatives(HarshenUtils.phaseBucket(block))));
				GameRegistry.addShapelessRecipe(new ResourceLocation(HarshenUniverse.MODID, glass.getType().getName().split(":")[1] + "_container"), new ResourceLocation(group), glass.getStack(), ingridientList);
			}
	}
}