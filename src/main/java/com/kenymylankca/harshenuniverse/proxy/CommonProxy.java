package com.kenymylankca.harshenuniverse.proxy;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.HarshenCraftingRecipes;
import kenymylankca.harshenuniverse.HarshenEntities;
import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenPotions;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.HarshenStructures;
import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.HarshenVillagers;
import kenymylankca.harshenuniverse.HarshenWorldGen;
import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.armor.HarshenArmors;
import kenymylankca.harshenuniverse.base.HarshenStructure;
import kenymylankca.harshenuniverse.biomes.HarshenBiomes;
import kenymylankca.harshenuniverse.config.HarshenConfigs;
import kenymylankca.harshenuniverse.dimensions.HarshenDimensions;
import kenymylankca.harshenuniverse.dimensions.pontus.PontusWorldProvider;
import kenymylankca.harshenuniverse.enchantment.HarshenEnchantmetns;
import kenymylankca.harshenuniverse.enums.SetIds;
import kenymylankca.harshenuniverse.enums.items.EnumBloodCollector;
import kenymylankca.harshenuniverse.enums.items.EnumPontusGateSpawner;
import kenymylankca.harshenuniverse.enums.items.EnumPontusGateSpawnerParts;
import kenymylankca.harshenuniverse.enums.items.EnumProp;
import kenymylankca.harshenuniverse.enums.items.EnumRitualStick;
import kenymylankca.harshenuniverse.enums.particle.EnumHarshenParticle;
import kenymylankca.harshenuniverse.fluids.HarshenFluids;
import kenymylankca.harshenuniverse.handlers.CooldownHandler;
import kenymylankca.harshenuniverse.handlers.GuiHandler;
import kenymylankca.harshenuniverse.handlers.HandlerBlockBurn;
import kenymylankca.harshenuniverse.handlers.HandlerBloodOnHurt;
import kenymylankca.harshenuniverse.handlers.HandlerExtraRange;
import kenymylankca.harshenuniverse.handlers.HandlerGlassContainer;
import kenymylankca.harshenuniverse.handlers.HandlerHarshenAccessoryInventory;
import kenymylankca.harshenuniverse.handlers.HandlerHarshenArmourEffects;
import kenymylankca.harshenuniverse.handlers.HandlerHarshenDrops;
import kenymylankca.harshenuniverse.handlers.HandlerPontusAllowed;
import kenymylankca.harshenuniverse.handlers.HandlerPotionEffects;
import kenymylankca.harshenuniverse.handlers.HandlerServerNeedingHarshenEffects;
import kenymylankca.harshenuniverse.handlers.HandlerVillagerSpawn;
import kenymylankca.harshenuniverse.interfaces.ICommandStructure;
import kenymylankca.harshenuniverse.internal.HarshenAPIHandler;
import kenymylankca.harshenuniverse.network.HarshenNetwork;
import kenymylankca.harshenuniverse.objecthandlers.FaceRenderer;
import kenymylankca.harshenuniverse.structures.pontus.FauxCauldronStructure;
import kenymylankca.harshenuniverse.tileentity.TileEntityBloodFactory;
import kenymylankca.harshenuniverse.tileentity.TileEntityBloodVessel;
import kenymylankca.harshenuniverse.tileentity.TileEntityHarshenDimensionalGate;
import kenymylankca.harshenuniverse.tileentity.TileEntityHarshenDisplayBlock;
import kenymylankca.harshenuniverse.tileentity.TileEntityHarshenMagicTable;
import kenymylankca.harshenuniverse.tileentity.TileEntityHarshenRitualPedestal;
import kenymylankca.harshenuniverse.tileentity.TileEntityHarshenSpawner;
import kenymylankca.harshenuniverse.tileentity.TileEntityHereticCauldron;
import kenymylankca.harshenuniverse.tileentity.TileEntityHereticCauldronTop;
import kenymylankca.harshenuniverse.tileentity.TileEntityHiddenPlate;
import kenymylankca.harshenuniverse.tileentity.TileEntityPedestalSlab;
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
    public void preInit(FMLPreInitializationEvent event) //dont mess with order
    {
    	HarshenConfigs.IDS.preInit();
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
		
		CooldownHandler.register();
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
    			TileEntityHarshenRitualPedestal.class,
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
    		new HandlerHarshenAccessoryInventory(),
    		new HandlerBloodOnHurt(),
    		new HandlerPotionEffects(),
    		new HandlerHarshenArmourEffects(),
    		new HandlerGlassContainer(),
    		new HandlerPontusAllowed(),
    		new HandlerHarshenDrops(),
    		new HandlerBlockBurn(),
    		new HandlerVillagerSpawn(),
    		new HandlerServerNeedingHarshenEffects(),
    		new HandlerExtraRange());
    	
    	GameRegistry.registerWorldGenerator(new HarshenWorldGen(), 0);
    	
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