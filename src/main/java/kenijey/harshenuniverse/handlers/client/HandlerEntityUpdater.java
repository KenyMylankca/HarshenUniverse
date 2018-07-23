package kenijey.harshenuniverse.handlers.client;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class HandlerEntityUpdater 
{
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		if(event.player.world.isRemote)
		for(Entity e : event.player.world.getLoadedEntityList())
			if(ENTITY_MAP.containsKey(e.getEntityId()))
			{
				e.readFromNBT(ENTITY_MAP.get(e.getEntityId()));
				ENTITY_MAP.remove(e.getEntityId());
			}
	}
	
	private static final HashMap<Integer, NBTTagCompound> ENTITY_MAP = new HashMap<>();
	
	public static void addToMap(int id, NBTTagCompound tag)
	{
		ENTITY_MAP.put(id, tag);
	}
}
