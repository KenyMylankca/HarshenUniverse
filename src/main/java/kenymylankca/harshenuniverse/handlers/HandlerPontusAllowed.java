package kenymylankca.harshenuniverse.handlers;

import java.util.HashMap;

import kenymylankca.harshenuniverse.HarshenUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class HandlerPontusAllowed 
{
	private static HashMap<String, Integer> playersLevelMap = new HashMap<String, Integer>();
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		if(!playersLevelMap.containsKey(event.player.getUniqueID().toString()))
			setAllowed(event.player, event.player.getEntityData().getInteger("PontusBiomeLevel"));
		if(!HarshenUtils.isLevelAcceptable(event.player.world, event.player.getPosition(), event.player))
		{
			event.player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 5));
			event.player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 100, 4));
			event.player.addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 2));
		}
	}
		
	public static void setAllowed(EntityPlayer player, int level)
	{
		playersLevelMap.put(player.getUniqueID().toString(), level);
	}
	
	public static int getAllowed(EntityPlayer player)
	{
		return playersLevelMap.get(player.getUniqueID().toString());
	}
}