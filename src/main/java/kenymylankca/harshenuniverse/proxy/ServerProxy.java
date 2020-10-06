package kenymylankca.harshenuniverse.proxy;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.handlers.server.HandlerSyncConfig;
import kenymylankca.harshenuniverse.internal.HarshenAPIHandler;
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
