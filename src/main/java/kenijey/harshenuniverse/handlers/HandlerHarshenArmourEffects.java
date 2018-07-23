package kenijey.harshenuniverse.handlers;

import kenijey.harshenuniverse.HarshenUtils;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.EntityEquipmentSlot.Type;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerHarshenArmourEffects 
{
	@SubscribeEvent
	public void onEntityTick(LivingUpdateEvent event)
	{
		int i = 0;
		for(EntityEquipmentSlot slot : EntityEquipmentSlot.values())
			if(slot.getSlotType() == Type.ARMOR && HarshenUtils.hasJaguarArmorSet(event.getEntityLiving())) i=4;
		if(i == 4)
		{
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 225, 0, false, false));
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.SPEED, 30, 0, false, false));
		}
	}
}