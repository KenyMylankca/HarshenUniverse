package kenijey.harshenuniverse.proxy;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.handlers.server.HandlerSyncConfig;
import kenijey.harshenuniverse.internal.HarshenAPIHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public class ServerProxy extends CommonProxy
{	
	@Override
	public void init(FMLInitializationEvent event) {
		HarshenUtils.registerHandlers(new HandlerSyncConfig());
		super.init(event);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		HarshenAPIHandler.register();
		HarshenUniverse.LOGGER.info("All recipes loaded");
	}
}
