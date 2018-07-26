package kenijey.harshenuniverse.handlers;

import kenijey.harshenuniverse.HarshenUtils;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerHarshenArmourEffects 
{
	int tick=0;
	
	@SubscribeEvent
	public void onEntityTick(LivingUpdateEvent event)
	{
		if(HarshenUtils.hasJaguarArmorSet(event.getEntityLiving()))
		{
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 225, 0, false, false));
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.SPEED, 30, 0, false, false));
		}
			
		if(HarshenUtils.hasBloodyArmorSet(event.getEntityLiving()))
		{
			if(tick < 222) tick ++;
			else
			{
				tick=0;
				event.getEntityLiving().heal(2f);
			}
		}
	}
}