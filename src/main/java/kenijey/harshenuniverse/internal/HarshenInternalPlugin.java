package kenijey.harshenuniverse.internal;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.api.BlockItem;
import kenijey.harshenuniverse.api.CauldronLiquid;
import kenijey.harshenuniverse.api.EnumInventorySlots;
import kenijey.harshenuniverse.api.HarshenPlugin;
import kenijey.harshenuniverse.api.HarshenStack;
import kenijey.harshenuniverse.api.IHarshenPlugin;
import kenijey.harshenuniverse.api.IHarshenProvider;
import kenijey.harshenuniverse.api.IHarshenRegistry;
import kenijey.harshenuniverse.enums.items.GlassContainerValue;
import kenijey.harshenuniverse.enums.items.GlassContainerValues;
import kenijey.harshenuniverse.fluids.HarshenFluids;
import kenijey.harshenuniverse.handlers.vanillaInventory.HandlerTotemOfUndying;
import kenijey.harshenuniverse.potions.HarshenPotions;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * The Inner plugin for HarshenCaste. 
 * This registers all the different recipes and all the inventory items.
 * @author Wyn Price
 *
 */
@HarshenPlugin
public class HarshenInternalPlugin implements IHarshenPlugin 
{		
	@Override
	public void register(IHarshenRegistry registry) 
	{
		//glass containers
		registry.registerGlassContainer("empty", -1, (PotionEffect)null);
		registry.registerGlassContainer("void", 0, new PotionEffect(HarshenPotions.potionSoulless, 600));
		registry.registerGlassContainer("regen", 0xF40D09, new PotionEffect(MobEffects.REGENERATION, 100, 200));
		registry.registerGlassContainer("cure", 0xEFEDA2, new PotionEffect(HarshenPotions.potionCure, 1));
		registry.registerGlassContainer("harshing_water", new CauldronLiquid("harshing_water", HarshenFluids.HARSHING_WATER_BLOCK.getDefaultState()), 0x613A63);
		registry.registerGlassContainer("harshen_dimensional_fluid", new CauldronLiquid("harshen_dimensional_fluid", HarshenFluids.HARSHEN_DIMENSIONAL_FLUID_BLOCK.getDefaultState()) , 0x324B64);
		registry.registerGlassContainer("blood", new CauldronLiquid("blood", new ResourceLocation(HarshenUniverse.MODID, "textures/blocks/blood_still.png")), 0x870705);
		registry.registerGlassContainer("lava", new CauldronLiquid("lava", Blocks.LAVA.getDefaultState()), 0xD96415);
		registry.registerGlassContainer("milk", new CauldronLiquid("milk", new ResourceLocation(HarshenUniverse.MODID, "textures/blocks/milk_still.png")), -1);
		registry.registerGlassContainer("water", new CauldronLiquid("water", Blocks.WATER.getDefaultState()), 0x598fe5);
		registry.registerGlassContainer("earth", new CauldronLiquid("earth", Blocks.DIRT.getDefaultState()), 0xc6854d);
		registry.registerGlassContainer("sand", new CauldronLiquid("sand", Blocks.SAND.getDefaultState()), 0xf4cf60);
		registry.registerGlassContainer("magic", new CauldronLiquid("magic", new ResourceLocation(HarshenUniverse.MODID, "textures/blocks/magic_still.png")), -1);
		
		GlassContainerValues.reloadAll();
		
		//magic table recipes
		registry.registerMagicTableRecipe(HarshenUtils.getMixupBook(), GlassContainerValues.VOID.getHarshenStack(), GlassContainerValues.HARSHEN_DIMENSIONAL_FLUID.getHarshenStack(), new HarshenStack(new ItemStack(Items.BOOK), new ItemStack(HarshenBlocks.ARCHIVE)), GlassContainerValues.MAGIC.getHarshenStack());
		registry.registerMagicTableRecipe(new ItemStack(HarshenBlocks.JEWEL_DIRT), GlassContainerValues.EARTH.getHarshenStack(), new HarshenStack(new ItemStack(HarshenItems.SOLIDIFYING_PASTE)), GlassContainerValues.BLOOD.getHarshenStack(), GlassContainerValues.MAGIC.getHarshenStack());
		registry.registerMagicTableRecipe(new ItemStack(HarshenItems.REACH_PENDANT), new HarshenStack(new ItemStack(Items.SLIME_BALL)), GlassContainerValues.MAGIC.getHarshenStack(), new HarshenStack(new ItemStack(HarshenItems.POWDER_OF_HERETISM)), new HarshenStack(new ItemStack(Items.SPECKLED_MELON)));
		registry.registerMagicTableRecipe(new ItemStack(Items.DIAMOND), new HarshenStack(new ItemStack(HarshenItems.DIAMOND_SHARD)), new HarshenStack(new ItemStack(HarshenItems.DIAMOND_SHARD)), new HarshenStack(new ItemStack(HarshenItems.DIAMOND_SHARD)), new HarshenStack(new ItemStack(HarshenItems.DIAMOND_SHARD)));
		registry.registerMagicTableRecipe(new ItemStack(Items.EMERALD), new HarshenStack(new ItemStack(HarshenItems.EMERALD_SHARD)), new HarshenStack(new ItemStack(HarshenItems.EMERALD_SHARD)), new HarshenStack(new ItemStack(HarshenItems.EMERALD_SHARD)), new HarshenStack(new ItemStack(HarshenItems.EMERALD_SHARD)));
		
		//pedestal slab recipes
		registry.registerPedestalSlabRecipe(new HarshenStack("cobblestone"), new ItemStack(Blocks.NETHERRACK));
		registry.registerPedestalSlabRecipe(new HarshenStack(new ItemStack(Items.ENDER_EYE)), new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE));
		registry.registerPedestalSlabRecipe(new HarshenStack(new ItemStack(Items.GOLDEN_APPLE)), new ItemStack(HarshenItems.BLOODY_APPLE));
		registry.registerPedestalSlabRecipe(new HarshenStack(new ItemStack(HarshenItems.PONTUS_CUBE)), new ItemStack(HarshenItems.BLOODY_PONTUS_CUBE));
		registry.registerPedestalSlabRecipe(new HarshenStack(new ItemStack(Items.BONE)), new ItemStack(HarshenItems.CURSED_BONE));
		registry.registerPedestalSlabRecipe(new HarshenStack(new ItemStack(Blocks.TORCH)), new ItemStack(HarshenBlocks.BLOODY_TORCH));
		registry.registerPedestalSlabRecipe(new HarshenStack(new ItemStack(Blocks.WOOL)), new ItemStack(HarshenBlocks.BLOODY_WOOL));
		
		//cauldron recipes
		registry.registerCauldronRecipe(new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL)), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), GlassContainerValues.BLOOD.getType());
		registry.registerCauldronRecipe(new HarshenStack(new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT)), new ItemStack(HarshenItems.SOUL_INFUSED_INGOT), GlassContainerValues.HARSHING_WATER.getType());
		registry.registerCauldronRecipe(new HarshenStack("sand"), new ItemStack(Blocks.SOUL_SAND), GlassContainerValues.HARSHEN_DIMENSIONAL_FLUID.getType());
		registry.registerCauldronRecipe(new HarshenStack("cobblestone"), new ItemStack(Blocks.NETHERRACK), GlassContainerValues.BLOOD.getType());
		registry.registerCauldronRecipe(new HarshenStack("cobblestone"), new ItemStack(Blocks.OBSIDIAN, 2), GlassContainerValues.LAVA.getType());
		registry.registerCauldronRecipe(GlassContainerValues.EMPTY.getHarshenStack(), GlassContainerValues.CURE.getStack(), GlassContainerValues.MILK.getType());
		registry.registerCauldronRecipe(GlassContainerValues.EMPTY.getHarshenStack(), GlassContainerValues.REGEN.getStack(), GlassContainerValues.BLOOD.getType());
		registry.registerCauldronRecipe(new HarshenStack(new ItemStack(HarshenItems.EMPTY_RING)), new ItemStack(HarshenItems.RING_OF_BLOOD), GlassContainerValues.BLOOD.getType());
		registry.registerCauldronRecipe(new HarshenStack(new ItemStack(HarshenItems.IRON_HEART)), new ItemStack(HarshenItems.SOUL_BINDING_PENDANT), GlassContainerValues.MAGIC.getType());
		
		//heretic ritual recipes
		HarshenStack blockGemStack = new HarshenStack("blockGold", "blockDiamond", "blockEmerald");
		registry.registerHereticRecipe(new HarshenStack(new ItemStack(Items.APPLE)), new ItemStack(Items.GOLDEN_APPLE, 1, 1), GlassContainerValues.WATER.getType(),
				blockGemStack.clone(), blockGemStack.clone(), blockGemStack.clone(), blockGemStack.clone(), blockGemStack.clone(), blockGemStack.clone(), blockGemStack.clone(), blockGemStack.clone());
			
		registry.registerHereticRecipe(new HarshenStack(new ItemStack(HarshenItems.EMPTY_RING)), new ItemStack(HarshenItems.XRAY_PENDANT), GlassContainerValues.MAGIC.getType(), 
				new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack(new ItemStack(HarshenItems.POWDER_OF_HERETISM)), new HarshenStack(new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)),
				new HarshenStack("gemEmerald"), new HarshenStack("gemQuartz"), new HarshenStack(new ItemStack(Items.ENDER_EYE)),
				new HarshenStack(new ItemStack(Blocks.END_ROD)), new HarshenStack("oreLapis"));
		
		registry.registerHereticRecipe(GlassContainerValues.EMPTY.getHarshenStack(3), GlassContainerValues.MAGIC.getStack(3), GlassContainerValues.HARSHING_WATER.getType(), 
				new HarshenStack(new ItemStack(HarshenItems.LIGHT_EMITTED_ESSENCE)), new HarshenStack("dyePurple"), new HarshenStack("dyePink"),
				new HarshenStack(new ItemStack(Items.SUGAR)), new HarshenStack("enderpearl"), new HarshenStack(new ItemStack(Items.CHORUS_FRUIT_POPPED)),
				new HarshenStack(new ItemStack(Items.BLAZE_POWDER)), new HarshenStack(new ItemStack(Items.SPIDER_EYE)));
		
		//lightning ritual recipes
		registry.registerLightningRecipe(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_SPAWNER), true, new HarshenStack(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_PARTS)), new HarshenStack(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_PARTS, 1, 1)),
				new HarshenStack(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_PARTS, 1 ,2)), new HarshenStack(new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.EMPOWERED_SOUL_HARSHER_SWORD), true, new HarshenStack(new ItemStack(HarshenItems.SOUL_HARSHER_SWORD)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE, 1 ,0)), new HarshenStack(new ItemStack(HarshenItems.BLOOD_ESSENCE)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_SPAWNER, 1, 1), true, new HarshenStack(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_SPAWNER)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(HarshenItems.EMERALD_SHARD)), new HarshenStack(new ItemStack(HarshenItems.IRON_HEART)));
		registry.registerLightningRecipe(new ItemStack(Items.NETHER_STAR), true, new HarshenStack(new ItemStack(HarshenItems.ITIUM)), new HarshenStack(new ItemStack(HarshenItems.LIGHT_EMITTED_ESSENCE)),
				new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1 , 1)), new HarshenStack(new ItemStack(HarshenItems.MYSTIC_FEATHER)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.SOUL_HARSHER_SWORD, 1, 0), true, new HarshenStack(new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT, 1, 0)), new HarshenStack(new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT, 1, 0)),
				new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1 ,1)), new HarshenStack(new ItemStack(Items.IRON_SWORD)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.ELEMENTAL_PENDANT, 1, 0), true, new HarshenStack(new ItemStack(HarshenItems.ITIUM, 1, 0)), new HarshenStack(new ItemStack(Items.MAGMA_CREAM)),
				new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack(new ItemStack(Items.GOLDEN_APPLE)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.MINERING), true, new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack(new ItemStack(HarshenItems.EMPTY_RING)),
				new HarshenStack(HarshenUtils.getLapis()), new HarshenStack(new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.ENION_BOW), true, new HarshenStack(new ItemStack(HarshenItems.IRON_BOW)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack("blockRedstone"), new HarshenStack("blockPrismarineDark"));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.LOOTING_EARRING), true, new HarshenStack(new ItemStack(HarshenBlocks.BLOCK_OF_HEADS)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(HarshenUtils.getLapis()), new HarshenStack("gemEmerald"));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.ENDER_PENDANT), true, new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack(new ItemStack(Items.ENDER_EYE)),
				new HarshenStack(new ItemStack(HarshenItems.POWDER_OF_HERETISM)), new HarshenStack(new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.WATER_EARRING), true, new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack(new ItemStack(Items.PRISMARINE_CRYSTALS)),
				new HarshenStack(new ItemStack(HarshenItems.ITIUM)), new HarshenStack(new ItemStack(HarshenItems.BLOOD_ESSENCE)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.SOUL_RIPPER_BOW), true, new HarshenStack(new ItemStack(HarshenItems.IRON_BOW)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)), new HarshenStack("blockPrismarineDark"));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.FIERY_RING), true, new HarshenStack(new ItemStack(HarshenItems.POWDER_OF_HERETISM)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(Items.FIRE_CHARGE)), new HarshenStack(new ItemStack(HarshenItems.EMPTY_RING)));
		registry.registerLightningRecipe(new ItemStack(Items.BLAZE_POWDER, 12), true, new HarshenStack(new ItemStack(Items.BLAZE_ROD)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 0)),
				new HarshenStack(new ItemStack(Items.BLAZE_ROD)), new HarshenStack(new ItemStack(Items.BLAZE_ROD)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.RAPTOR_SCYTHE), true, new HarshenStack(new ItemStack(HarshenItems.IRON_SCYTHE)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)), new HarshenStack(new ItemStack(HarshenItems.VALOR_BADGE)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.SOUL_SHIELD), true, new HarshenStack(new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack("slimeball"), new HarshenStack("blockIron"));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.MYSTIC_FEATHER), true, new HarshenStack(), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack("feather"), new HarshenStack(new ItemStack(Items.CHORUS_FRUIT_POPPED)));
		registry.registerLightningRecipe(new ItemStack(HarshenBlocks.GILDED_OBSIDIAN), true, new HarshenStack(), new HarshenStack(),
				new HarshenStack("obsidian"), new HarshenStack("blockGold"));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.ENDER_BOW), true, new HarshenStack(new ItemStack(HarshenBlocks.GILDED_OBSIDIAN)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(HarshenItems.IRON_BOW)), new HarshenStack(new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE)));
		registry.registerLightningRecipe(new ItemStack(HarshenBlocks.HARSHEN_DIMENSIONAL_WOOD_CRATE), true, new HarshenStack("plankWood"), new HarshenStack("plankWood"),
				new HarshenStack(new ItemStack(HarshenItems.HARSHEN_CRYSTAL)), new HarshenStack("plankWood"));
		registry.registerLightningRecipe(new ItemStack(Items.CHORUS_FRUIT), true, new HarshenStack(new ItemStack(Items.CHORUS_FRUIT_POPPED)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 0)),
				new HarshenStack(new ItemStack(Blocks.AIR)), new HarshenStack(new ItemStack(Blocks.AIR)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.LIGHTNING_STAFF), true, new HarshenStack("blockPrismarineDark"), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(HarshenItems.RITUAL_STICK, 1, 1)), new HarshenStack(new ItemStack(HarshenItems.VALOR_BADGE)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.SOUL_HARSHER_PICKAXE), true, new HarshenStack("blockPrismarineDark"), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(Items.IRON_PICKAXE)), new HarshenStack(new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.SOUL_HARSHER_SPADE), true, new HarshenStack("blockPrismarineDark"), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(Items.IRON_SHOVEL)), new HarshenStack(new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.REFLECTOR_PENDANT), true, new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack(new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE)),
				new HarshenStack(new ItemStack(HarshenItems.ITIUM)), new HarshenStack(new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)));
		registry.registerLightningRecipe(new ItemStack(HarshenItems.FEEDING_EARRING), true, new HarshenStack(new ItemStack(HarshenItems.BLOODY_APPLE)),new HarshenStack(new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE)),
				new HarshenStack(new ItemStack(HarshenItems.IRON_HEART)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)));
		
		//cauldron recipes
		registry.registerCauldronLiquid(GlassContainerValues.LAVA.getStack(), GlassContainerValues.EMPTY.getStack(), GlassContainerValues.LAVA.getType(), 1);
		registry.registerCauldronLiquid(GlassContainerValues.WATER.getStack(), GlassContainerValues.EMPTY.getStack(), GlassContainerValues.WATER.getType(), 1);
		registry.registerCauldronLiquid(GlassContainerValues.MILK.getStack(), GlassContainerValues.EMPTY.getStack(), GlassContainerValues.MILK.getType(), 1);
		
		//cauldron fluids
		for(GlassContainerValue container : GlassContainerValue.values())
			if(container.isSubContainer())
			{
				registry.registerCauldronLiquid(container.getStack(), GlassContainerValues.EMPTY.getStack().copy(), container.getType(), 1);
			}
		registry.registerCauldronLiquid(new FluidStack(HarshenFluids.HARSHEN_DIMENSIONAL_FLUID, 1000), GlassContainerValues.HARSHEN_DIMENSIONAL_FLUID.getType(), 3);
		registry.registerCauldronLiquid(new FluidStack(HarshenFluids.HARSHING_WATER, 1000), GlassContainerValues.HARSHING_WATER.getType(), 3);
				
		//inventory items
    	registry.registerInventoryItem(new BlockItem(Items.TOTEM_OF_UNDYING), EnumInventorySlots.NECK, new HandlerTotemOfUndying(), false, 0);
    	for(Item item : ForgeRegistries.ITEMS.getValues())
    		if(item instanceof IHarshenProvider)
    			registry.registerInventoryItem(new BlockItem(item), (IHarshenProvider)item);
    	for(Block block : ForgeRegistries.BLOCKS.getValues())
    		if(block instanceof IHarshenProvider)
    			registry.registerInventoryItem(new BlockItem(block), (IHarshenProvider)block);
	}

	@Override
	public String getModID() {
		return HarshenUniverse.MODID;
	}
}