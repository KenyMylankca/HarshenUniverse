package kenijey.harshenuniverse;

import java.util.ArrayList;
import java.util.HashMap;

import kenijey.harshenuniverse.base.BaseBlockMeta;
import kenijey.harshenuniverse.blocks.Archive;
import kenijey.harshenuniverse.blocks.BlockOfHeads;
import kenijey.harshenuniverse.blocks.BloodBlock;
import kenijey.harshenuniverse.blocks.BloodFactory;
import kenijey.harshenuniverse.blocks.BloodPlacer;
import kenijey.harshenuniverse.blocks.BloodVessel;
import kenijey.harshenuniverse.blocks.BloodyTorch;
import kenijey.harshenuniverse.blocks.BloodyWool;
import kenijey.harshenuniverse.blocks.CropOfGleam;
import kenijey.harshenuniverse.blocks.GildedObsidian;
import kenijey.harshenuniverse.blocks.HarshenChaoticRock;
import kenijey.harshenuniverse.blocks.HarshenDestroyedPlant;
import kenijey.harshenuniverse.blocks.HarshenDimensionalDirt;
import kenijey.harshenuniverse.blocks.HarshenDimensionalDoor;
import kenijey.harshenuniverse.blocks.HarshenDimensionalFlatPlate;
import kenijey.harshenuniverse.blocks.HarshenDimensionalGate;
import kenijey.harshenuniverse.blocks.HarshenDimensionalGlass;
import kenijey.harshenuniverse.blocks.HarshenDimensionalLadder;
import kenijey.harshenuniverse.blocks.HarshenDimensionalPedestal;
import kenijey.harshenuniverse.blocks.HarshenDimensionalPlate;
import kenijey.harshenuniverse.blocks.HarshenDimensionalRock;
import kenijey.harshenuniverse.blocks.HarshenDimensionalStairs;
import kenijey.harshenuniverse.blocks.HarshenDimensionalStone;
import kenijey.harshenuniverse.blocks.HarshenDimensionalWoodCrate;
import kenijey.harshenuniverse.blocks.HarshenDisplayBlock;
import kenijey.harshenuniverse.blocks.HarshenFarRock;
import kenijey.harshenuniverse.blocks.HarshenHiddenPlate;
import kenijey.harshenuniverse.blocks.HarshenHiddenPlateActive;
import kenijey.harshenuniverse.blocks.HarshenMagicTable;
import kenijey.harshenuniverse.blocks.HarshenSoulFlower;
import kenijey.harshenuniverse.blocks.HarshenSoulOre;
import kenijey.harshenuniverse.blocks.HarshenSpawner;
import kenijey.harshenuniverse.blocks.HereticCauldron;
import kenijey.harshenuniverse.blocks.HereticCauldronTop;
import kenijey.harshenuniverse.blocks.ItiumOre;
import kenijey.harshenuniverse.blocks.JewelDirt;
import kenijey.harshenuniverse.blocks.PedestalSlab;
import kenijey.harshenuniverse.blocks.PlantOfGleam;
import kenijey.harshenuniverse.blocks.PontusChaoticLeaves;
import kenijey.harshenuniverse.blocks.PontusChaoticWood;
import kenijey.harshenuniverse.blocks.PontusDeadLeaves;
import kenijey.harshenuniverse.blocks.PontusDeadWood;
import kenijey.harshenuniverse.blocks.PontusEmeraldOre;
import kenijey.harshenuniverse.blocks.PontusFarLeaves;
import kenijey.harshenuniverse.blocks.PontusFarWood;
import kenijey.harshenuniverse.blocks.SoulReminder;
import kenijey.harshenuniverse.config.HarshenConfigs;
import kenijey.harshenuniverse.interfaces.IMetaItemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HarshenBlocks
{
	private static ArrayList<Block> blocksWithItems = new ArrayList<Block>();
	private static HashMap<Block, Integer> blockStackSize = new HashMap<>();
	private static ArrayList<Block> blocksWithCustomStateMap = new ArrayList<Block>();
	private static ArrayList<IProperty<?>[]> propertiesToIgnoreCustomStateMap = new ArrayList<IProperty<?>[]>();
	
	public static final Block HARSHEN_SOUL_ORE = new HarshenSoulOre();
	public static final Block HARSHEN_DIMENSIONAL_STONE = new HarshenDimensionalStone();
	public static final Block HARSHEN_DISPLAY_BLOCK = new HarshenDisplayBlock();
	public static final Block HARSHEN_DIMENSIONAL_DOOR = new HarshenDimensionalDoor();
	public static final Block HARSHEN_HIDDEN_PLATE = new HarshenHiddenPlate();
	public static final Block HARSHEN_HIDDEN_PLATE_ACTIVE = new HarshenHiddenPlateActive();
	public static final Block HARSHEN_DIMENSIONAL_STAIRS = new HarshenDimensionalStairs();
	public static final Block HARSHEN_DIMENSIONAL_GLASS = new HarshenDimensionalGlass();
	public static final Block HARSHEN_DIMENSIONAL_LADDER = new HarshenDimensionalLadder();
	public static final Block HARSHEN_DIMENSIONAL_PRESSURE_PLATE = new HarshenDimensionalPlate();
	public static final Block HARSHEN_DIMENSIONAL_FLAT_PLATE = new HarshenDimensionalFlatPlate();
	public static final Block SOUL_REMINDER = new SoulReminder();
	public static final Block HARSHEN_DIMENSIONAL_WOOD_CRATE = new HarshenDimensionalWoodCrate();
	public static final Block HARSHEN_DIMENSIONAL_DIRT = new HarshenDimensionalDirt();
	public static final Block ITIUM_ORE = new ItiumOre();
	public static final Block HARSHEN_DESTROYED_PLANT = new HarshenDestroyedPlant();
	public static final Block HARSHEN_DIMENSIONAL_ROCK = new HarshenDimensionalRock();
	public static final Block HARSHEN_CHAOTIC_ROCK = new HarshenChaoticRock();
	public static final Block HARSHEN_FAR_ROCK = new HarshenFarRock();
	public static final Block HARSHEN_DIMENSIONAL_PEDESTAL = new HarshenDimensionalPedestal();
	public static final Block HARSHEN_DIMENSIONAL_GATE = new HarshenDimensionalGate();
	public static final Block HERETIC_CAULDRON = new HereticCauldron();
	public static final Block HERETIC_CAULDRON_TOP = new HereticCauldronTop();
	public static final Block BLOOD_BLOCK = new BloodBlock();
	public static final Block PONTUS_DEAD_LEAVES = new PontusDeadLeaves();
	public static final Block HARSHEN_SPAWNER = new HarshenSpawner();
	public static final Block PEDESTAL_SLAB = new PedestalSlab();
	public static final Block PONTUS_CHAOTIC_LEAVES = new PontusChaoticLeaves();
	public static final Block BLOOD_PLACER = new BloodPlacer();
	public static final Block BLOOD_VESSEL = new BloodVessel();
	public static final Block BLOOD_FACTORY = new BloodFactory();
	public static final Block PONTUS_EMERALD_ORE = new PontusEmeraldOre();
	public static final Block ARCHIVE = new Archive();
	public static final Block BLOCK_OF_HEADS = new BlockOfHeads();
	public static final Block HARSHEN_MAGIC_TABLE = new HarshenMagicTable();
	public static final Block PONTUS_FAR_LEAVES = new PontusFarLeaves();
	public static final Block GILDED_OBSIDIAN = new GildedObsidian();
	public static final Block JEWEL_DIRT = new JewelDirt();
	public static final Block BLOODY_TORCH = new BloodyTorch();
	public static final Block BLOODY_WOOL = new BloodyWool();
	
	public static final BlockCrops CROP_OF_GLEAM = new CropOfGleam();
	
	public static final BlockFlower HARSHEN_SOUL_FLOWER = new HarshenSoulFlower();
	public static final BlockFlower PLANT_OF_GLEAM = new PlantOfGleam();
	
	public static final BlockLog PONTUS_DEAD_WOOD = new PontusDeadWood();
	public static final BlockLog PONTUS_CHAOTIC_WOOD = new PontusChaoticWood();
	public static final BlockLog PONTUS_FAR_WOOD = new PontusFarWood();

	public static void preInit() 
	{
		regBlock(HARSHEN_DIMENSIONAL_STONE, 64);
		regBlock(HARSHEN_HIDDEN_PLATE, 64);
		regBlock(HARSHEN_DIMENSIONAL_PRESSURE_PLATE, 64);
		regBlock(HARSHEN_DIMENSIONAL_FLAT_PLATE, 64);
		regBlock(HARSHEN_DIMENSIONAL_STAIRS, 64);
		regBlock(HARSHEN_DIMENSIONAL_GLASS, 64);
		regBlock(HARSHEN_DIMENSIONAL_LADDER, 64);
		regBlock(HARSHEN_DIMENSIONAL_WOOD_CRATE, 64);
		regBlock(HARSHEN_DISPLAY_BLOCK, 64);
		regBlock(HARSHEN_DIMENSIONAL_DIRT, 64);
		regBlock(HARSHEN_DIMENSIONAL_ROCK, 64);
		regBlock(HARSHEN_CHAOTIC_ROCK, 64);
		regBlock(HARSHEN_FAR_ROCK, 64);
		regBlock(HARSHEN_DIMENSIONAL_GATE, 1, HarshenDimensionalGate.FOREVER, HarshenDimensionalGate.TIMER);
		regBlock(HARSHEN_SPAWNER, 64);
		regBlock(PONTUS_DEAD_WOOD, 64);
		regBlock(PONTUS_CHAOTIC_WOOD, 64);
		regBlock(PONTUS_DEAD_LEAVES, 64);
		regBlock(PONTUS_CHAOTIC_LEAVES, 64);
		regBlock(PONTUS_FAR_WOOD, 64);
		regBlock(PONTUS_FAR_LEAVES, 64);
		regBlock(HERETIC_CAULDRON, 64);
		regBlock(HARSHEN_SOUL_ORE, 64);
		regBlock(ITIUM_ORE, 64);
		regBlock(HARSHEN_DESTROYED_PLANT, 64);
		regBlock(HARSHEN_SOUL_FLOWER, 64);
		regBlock(SOUL_REMINDER, 1);
		regBlock(HARSHEN_DIMENSIONAL_PEDESTAL, 8);
		regBlock(PLANT_OF_GLEAM, 64);
		regBlock(BLOOD_BLOCK, 1);
		regBlock(BLOOD_PLACER, 64);
		regBlock(BLOOD_VESSEL, 64);
		regBlock(BLOOD_FACTORY, 64);
		regBlock(PEDESTAL_SLAB, 64);
		regBlock(PONTUS_EMERALD_ORE, 64);
		regBlock(ARCHIVE, 64);
		regBlock(BLOCK_OF_HEADS, 64);
		regBlock(HARSHEN_MAGIC_TABLE, 4);
		regBlock(GILDED_OBSIDIAN, 64);
		regBlock(JEWEL_DIRT, 64);
		regBlock(BLOODY_TORCH, 64);
		regBlock(BLOODY_WOOL, 64);
		
		regSingleBlock(HERETIC_CAULDRON_TOP);
		regSingleBlock(CROP_OF_GLEAM);
		regSingleBlock(HARSHEN_DIMENSIONAL_DOOR, HarshenDimensionalDoor.POWERED);
		regSingleBlock(HARSHEN_HIDDEN_PLATE_ACTIVE);
	}

	public static void regRenders() {
		for(int i = 0; i < blocksWithCustomStateMap.size(); i++)
			createStateMappers(blocksWithCustomStateMap.get(i), propertiesToIgnoreCustomStateMap.get(i));
		for(Block b : blocksWithItems)
			regRender(b);
	}

	public static void regBlock(Block block, int stackSize) {
		blocksWithItems.add(block);
		blockStackSize.put(block, stackSize);	
		HarshenConfigs.BLOCKS.allComponants.add(block);
	}
	
	public static void regBlock(Block block, int stackSize, IProperty<?>... toIgnore)
	{
		blocksWithCustomStateMap.add(block);
		propertiesToIgnoreCustomStateMap.add(toIgnore);
		regBlock(block, stackSize);
	}
	
	@SideOnly(Side.CLIENT)
	public static void createStateMappers(Block block, IProperty<?>[] toIgnore)
	{
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder().ignore(toIgnore)).build());
	}
	
	public static void regSingleBlock(Block block)
	{
		HarshenConfigs.BLOCKS.allComponants.add(block);
	}
	
	public static void regSingleBlock(Block block,  IProperty<?>... toIgnore)
	{
		blocksWithCustomStateMap.add(block);
		propertiesToIgnoreCustomStateMap.add(toIgnore);
		HarshenConfigs.BLOCKS.allComponants.add(block);
	}

	public static void regRender(Block block) {
		block.setCreativeTab(HarshenUniverse.harshenTab);
		int timesToLoad = 1;
		boolean flag = blockDataMap.keySet().contains(block);
		if(flag)
			timesToLoad = blockDataMap.get(block);
		for(int i = 0; i < timesToLoad; i++)
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i, new ModelResourceLocation(block.getRegistryName() + 
					(flag ? "." + ((IMetaItemBlock)block).getNames()[i]: ""), "inventory"));
	}

	private static HashMap<Block, Integer> blockDataMap = new HashMap<>();
	
	public static void register()
	{
		for(Block block : HarshenConfigs.BLOCKS.allComponants)
			if(HarshenConfigs.BLOCKS.isEnabled(block))
			{
				ForgeRegistries.BLOCKS.register(block);
				if(blocksWithItems.contains(block))
				{
					ItemBlock item = block instanceof IMetaItemBlock ? add(block) : new ItemBlock(block);
					item.setRegistryName(block.getRegistryName());
					item.setMaxStackSize(blockStackSize.get(block));
					ForgeRegistries.ITEMS.register(item);
				}
			}
	}
	
	private static BaseBlockMeta add(Block block)
	{
		blockDataMap.put(block, ((IMetaItemBlock)block).getNames().length);
		return new BaseBlockMeta(block);
	}
}