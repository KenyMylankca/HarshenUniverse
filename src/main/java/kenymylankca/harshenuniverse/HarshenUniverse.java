package kenymylankca.harshenuniverse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kenymylankca.harshenuniverse.commands.HarshenCommandLoader;
import kenymylankca.harshenuniverse.creativetabs.HarshenTab;
import kenymylankca.harshenuniverse.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

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
    public static final String VERSION = "1.1.0";
    
    public static boolean hasLoaded;

    @SidedProxy(clientSide = "kenymylankca.harshenuniverse.proxy.ClientProxy", serverSide = "kenymylankca.harshenuniverse.proxy.ServerProxy")
    public static CommonProxy proxy;
    
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
    	proxy.preInit(event);
    	proxy.regRenders(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) 
    {
    	proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	proxy.postInit(event);
		hasLoaded = true;
    }
    
    @EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
    	event.registerServerCommand(new HarshenCommandLoader());
	}
}