package com.kenymylankca.harshenuniverse;

import com.kenymylankca.harshenuniverse.creativetabs.HarshenTab;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("harshenuniverse")
public class HarshenUniverse
{
	public static final String MOD_ID = "harshenuniverse";
	public static final CreativeModeTab HarshenTab = new HarshenTab();
	public HarshenUniverse()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
}











/*



@Mod(
		modid = HarshenUniverse.MODID,
		name = HarshenUniverse.MODNAME,
		version = HarshenUniverse.VERSION,
		useMetadata = true,
		acceptedMinecraftVersions = "[1.12.2]",
		dependencies = "required-after:forge@[14.23.5.2847,)")

public class HarshenUniverse
{
    public static final String MODID = "harshenuniverse";
    public static final String MODNAME = "Harshen Universe";
    public static final String VERSION = "1.2.6";
    
    public static boolean hasLoaded;

    @SidedProxy(clientSide = "kenymylankca.harshenuniverse.proxy.ClientProxy", serverSide = "kenymylankca.harshenuniverse.proxy.ServerProxy")
    public static CommonProxy commonProxy;
    
    public static final CreativeTabs harshenTab = new HarshenTab();
    
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    
    @Instance(MODID)
    public static HarshenUniverse instance;
    
	static
	{
		FluidRegistry.enableUniversalBucket();
	}

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	commonProxy.preInit(event);
    	commonProxy.regRenders(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    	commonProxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	commonProxy.postInit(event);
		hasLoaded = true;
    }
    
    @EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
    	event.registerServerCommand(new HarshenCommandLoader());
	}
}*/