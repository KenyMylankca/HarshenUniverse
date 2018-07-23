package kenijey.harshenuniverse.handlers.client;

import kenijey.harshenuniverse.HarshenSounds;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.entity.EntityKazzendre;
import kenijey.harshenuniverse.items.EmpoweredSoulHarsherSword;
import kenijey.harshenuniverse.items.EnionBow;
import kenijey.harshenuniverse.items.FieryRing;
import kenijey.harshenuniverse.items.IronBow;
import kenijey.harshenuniverse.items.IronScythe;
import kenijey.harshenuniverse.items.PunchyRing;
import kenijey.harshenuniverse.items.RaptorScythe;
import kenijey.harshenuniverse.items.SoulHarsherSword;
import kenijey.harshenuniverse.items.SoulRipperBow;
import kenijey.harshenuniverse.objecthandlers.HarshenItemStackHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerHarshenClientEffects
{
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if(event.getSource().getDamageType() == "player")
		{
			EntityLivingBase entity = event.getEntityLiving();
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
			Item item = player.getHeldItemMainhand().getItem();
			HarshenItemStackHandler handler = HarshenUtils.getHandler(player);
			
			if (item instanceof IronScythe) entity.playSound(HarshenSounds.IRON_SCYTHE_HIT, 1f, 1f);
			if (item instanceof IronBow) entity.playSound(HarshenSounds.IRON_HIT, 1f, 1f);
			if (item instanceof EnionBow) entity.playSound(HarshenSounds.LIGHTNING_HIT, 1f, 1f);
			
			if (item instanceof RaptorScythe)
				if(HarshenUtils.hasJaguarArmorSet(entity))
					entity.playSound(HarshenSounds.JAGUAR_DEFENSE, 1f, 1f);
				else
					entity.playSound(HarshenSounds.RAPTOR_SCYTHE_HIT, 1f, 1f);
			
			if (item instanceof SoulHarsherSword || item instanceof EmpoweredSoulHarsherSword)
				if(HarshenUtils.hasJaguarArmorSet(entity))
					entity.playSound(HarshenSounds.JAGUAR_DEFENSE, 1f, 1f);
				else
					entity.playSound(HarshenSounds.SOUL_HARSHER_SWORD_HIT, 1f, 1f);
			
			if (item instanceof SoulRipperBow)
				if(HarshenUtils.hasJaguarArmorSet(entity))
					entity.playSound(HarshenSounds.JAGUAR_DEFENSE, 1f, 1f);
				else
					entity.playSound(HarshenSounds.SOUL_RIPPER_BOW_HIT, 1f, 1f);
			
			if(handler.getStackInSlot(3).getItem() instanceof PunchyRing ||	handler.getStackInSlot(4).getItem() instanceof PunchyRing)
				if(item instanceof ItemAir)
					entity.playSound(HarshenSounds.IRON_HIT, 1f, 1.5f);
			
			if(handler.getStackInSlot(3).getItem() instanceof FieryRing ||	handler.getStackInSlot(4).getItem() instanceof FieryRing)
				entity.playSound(SoundEvents.BLOCK_FIRE_AMBIENT, 1f, 1.3f);
		}
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			if(event.getSource().getTrueSource() instanceof EntityKazzendre)
			{
				event.getSource().getTrueSource().playSound(HarshenSounds.KAZZENDRE_HIT, 1f, 1f);
			}
		}
	}
}