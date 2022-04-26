package com.kenymylankca.harshenuniverse.handlers;

import java.util.UUID;

import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.armor.BloodyArmor;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerHarshenArmourEffects 
{
	@SubscribeEvent
	public void onEntityTick(LivingUpdateEvent event)
	{
		if(HarshenUtils.hasJaguarArmorSet(event.getEntityLiving()))
		{
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 225, 0, false, false));
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.SPEED, 30, 0, false, false));
			IAttributeInstance attribute = event.getEntityLiving().getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
			AttributeModifier modifier = new AttributeModifier(UUID.fromString("593db0cb-b5b0-497d-ad02-b708c41941ba"), "jaguarHealth", 4, 0).setSaved(true);
			if(!attribute.hasModifier(modifier))
				attribute.applyModifier(modifier);
		}
		else
			event.getEntityLiving().getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("593db0cb-b5b0-497d-ad02-b708c41941ba"));
		
		if(HarshenUtils.hasBloodyArmorSet(event.getEntityLiving()))
			BloodyArmor.onTick(event.getEntityLiving());
	}
}