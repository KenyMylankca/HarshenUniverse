package kenijey.harshenuniverse.proxy;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.HarshenCraftingRecipes;
import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.HarshenSounds;
import kenijey.harshenuniverse.HarshenStructures;
import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.HarshenVillagers;
import kenijey.harshenuniverse.WorldGen;
import kenijey.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenijey.harshenuniverse.armor.HarshenArmors;
import kenijey.harshenuniverse.base.HarshenStructure;
import kenijey.harshenuniverse.biomes.HarshenBiomes;
import kenijey.harshenuniverse.config.HarshenConfigs;
import kenijey.harshenuniverse.dimensions.HarshenDimensions;
import kenijey.harshenuniverse.dimensions.pontus.PontusWorldProvider;
import kenijey.harshenuniverse.enchantment.HarshenEnchantmetns;
import kenijey.harshenuniverse.entity.HarshenEntities;
import kenijey.harshenuniverse.enums.SetIds;
import kenijey.harshenuniverse.enums.items.EnumBloodCollector;
import kenijey.harshenuniverse.enums.items.EnumPontusGateSpawner;
import kenijey.harshenuniverse.enums.items.EnumPontusGateSpawnerParts;
import kenijey.harshenuniverse.enums.items.EnumProp;
import kenijey.harshenuniverse.enums.items.EnumRitualStick;
import kenijey.harshenuniverse.enums.particle.EnumHarshenParticle;
import kenijey.harshenuniverse.fluids.HarshenFluids;
import kenijey.harshenuniverse.handlers.GuiHandler;
import kenijey.harshenuniverse.handlers.HandlerBlockBurn;
import kenijey.harshenuniverse.handlers.HandlerBloodOnHurt;
import kenijey.harshenuniverse.handlers.HandlerBurnInDaylight;
import kenijey.harshenuniverse.handlers.HandlerExtraRange;
import kenijey.harshenuniverse.handlers.HandlerGlassContainer;
import kenijey.harshenuniverse.handlers.HandlerHarshenArmourEffects;
import kenijey.harshenuniverse.handlers.HandlerHarshenDrops;
import kenijey.harshenuniverse.handlers.HandlerHarshenInventory;
import kenijey.harshenuniverse.handlers.HandlerPontusAllowed;
import kenijey.harshenuniverse.handlers.HandlerPotionEffects;
import kenijey.harshenuniverse.handlers.HandlerServerNeedingHarshenEffects;
import kenijey.harshenuniverse.handlers.HandlerVillagerSpawn;
import kenijey.harshenuniverse.interfaces.ICommandStructure;
import kenijey.harshenuniverse.internal.HarshenAPIHandler;
import kenijey.harshenuniverse.network.HarshenNetwork;
import kenijey.harshenuniverse.objecthandlers.FaceRenderer;
import kenijey.harshenuniverse.potions.HarshenPotions;
import kenijey.harshenuniverse.structures.pontus.FauxCauldronStructure;
import kenijey.harshenuniverse.tileentity.TileEntityBloodFactory;
import kenijey.harshenuniverse.tileentity.TileEntityBloodVessel;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenDimensionalGate;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenDimensionalPedestal;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenDisplayBlock;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenMagicTable;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenSpawner;
import kenijey.harshenuniverse.tileentity.TileEntityHereticCauldron;
import kenijey.harshenuniverse.tileentity.TileEntityHereticCauldronTop;
import kenijey.harshenuniverse.tileentity.TileEntityHiddenPlate;
import kenijey.harshenuniverse.tileentity.TileEntityPedestalSlab;
import net.minecraft.block.Block;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
    public void preInit(FMLPreInitializationEvent event) 
    {    	    	
    	HarshenConfigs.IDS.preInit(); //dont mess with order
    	HarshenConfigs.GENERAL.preInit();
    	
    	HarshenPotions.preInit();
		HarshenPotions.register();
				
    	setUpEnumValues();
    	
    	HarshenEnchantmetns.preInit();
				
		HarshenBlocks.preInit();
		HarshenItems.preInit();
		HarshenStructures.preInit();
		
		HarshenConfigs.BLOCKS.preInit();
		HarshenConfigs.ITEMS.preInit();
		HarshenConfigs.STRUCTURES.preInit();
		
		HarshenBlocks.register();
		HarshenItems.register();
		HarshenStructures.register();
		
		HarshenFluids.preInit();
		
    	HarshenConfigs.ACCESSORIES.preInit();
    	
    	HarshenStructure.load();
		
		HarshenArmors.preInit();
		HarshenArmors.register();
		
		HarshenDimensions.register();
		
		HarshenBiomes.register();
				
		HarshenNetwork.preInit();
		
		HarshenSounds.register();
		
		HarshenVillagers.preInit();
				
		HarshenAPIHandler.loadPlugins(event.getAsmData());
							
		HarshenUniverse.LOGGER.info("HarshenUniverse loaded correctly");
    }	
    
    private void setUpEnumValues()
    {
		SetIds.setup(EnumBloodCollector.values());
		SetIds.setup(EnumPontusGateSpawner.values());
		SetIds.setup(EnumPontusGateSpawnerParts.values());
		SetIds.setup(EnumProp.values());
		SetIds.setup(EnumRitualStick.values());
		SetIds.setup(EnumAccessoryInventorySlots.values());
    }

    public void init(FMLInitializationEvent event) 
    {
    	
		HarshenCraftingRecipes.register();

    	HarshenEntities.init();
    	
		NetworkRegistry.INSTANCE.registerGuiHandler(HarshenUniverse.instance, new GuiHandler());

    	Class[] tileEntityClasses = {
    			TileEntityHarshenDimensionalPedestal.class,
    			TileEntityHereticCauldron.class,
    			TileEntityHereticCauldronTop.class,
    			TileEntityHarshenDisplayBlock.class,
    			TileEntityHarshenSpawner.class,
    			TileEntityHarshenDimensionalGate.class,
    			TileEntityPedestalSlab.class,
    			TileEntityBloodVessel.class,
    			TileEntityBloodFactory.class,
    			TileEntityHiddenPlate.class,
    			TileEntityHarshenMagicTable.class,
    	};
    	for(Class clas : tileEntityClasses)
    		GameRegistry.registerTileEntity(clas, HarshenUniverse.MODID + clas.getSimpleName());	
    	
    	HarshenUtils.registerHandlers(
    		new HandlerHarshenInventory(),
    		new HandlerBloodOnHurt(),
    		new HandlerPotionEffects(),
    		new HandlerHarshenArmourEffects(),
    		new HandlerGlassContainer(),
    		new HandlerPontusAllowed(),
    		new HandlerHarshenDrops(),
    		new HandlerBlockBurn(),
    		new HandlerVillagerSpawn(),
    		new HandlerServerNeedingHarshenEffects(),
    		new HandlerExtraRange(),
    		new HandlerBurnInDaylight());
    	
    	GameRegistry.registerWorldGenerator(new WorldGen(), 0);
    	
    	ICommandStructure[] customStructures = {
    			new FauxCauldronStructure()
    	};
    	
    	ICommandStructure.ALL_STRUCTURES.addAll(HarshenUtils.toArray(customStructures));
    }

    public void postInit(FMLPostInitializationEvent event) 
    {
    	
    }
    
    public int getrenderDistance()
    {
    	return 0;
    }

	public void registerFluidBlockRendering(Block block, String name) {}

	public void regRenders(FMLPreInitializationEvent event)
	{
		
	}

	public EntityPlayer getPlayer() 
	{
		return null;
	}

	public void setWorldRenderer(PontusWorldProvider prov) {		
	}
	
    public Particle spawnParticle(EnumHarshenParticle type, Vec3d position, Vec3d directionSpeed, float scale, boolean disableMoving, Object...info){return null;};
	
	public void addErroredPosition(FaceRenderer renderer){}
	
	public void resetErroredPositions(){}
}