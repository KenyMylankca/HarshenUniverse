package kenymylankca.harshenuniverse.handlers.client;

import java.util.Random;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.config.IdConfig;
import kenymylankca.harshenuniverse.entity.EntityKazzendre;
import kenymylankca.harshenuniverse.items.DarkEwydoen;
import kenymylankca.harshenuniverse.items.EmpoweredSoulHarsherSword;
import kenymylankca.harshenuniverse.items.EnionBow;
import kenymylankca.harshenuniverse.items.IronBow;
import kenymylankca.harshenuniverse.items.IronScythe;
import kenymylankca.harshenuniverse.items.RaptorScythe;
import kenymylankca.harshenuniverse.items.SoulHarsherSword;
import kenymylankca.harshenuniverse.items.SoulRipperBow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class HandlerHarshenClientEffects
{
	int tick = 0;
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if(event.getSource().getDamageType() == "player")
		{
			EntityLivingBase entity = event.getEntityLiving();
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
			Item item = player.getHeldItemMainhand().getItem();
			
			if (item instanceof IronScythe) entity.playSound(HarshenSounds.IRON_SCYTHE_HIT, 1f, 1f);
			if (item instanceof IronBow) entity.playSound(HarshenSounds.IRON_HIT, 1f, 1f);
			if (item instanceof EnionBow) entity.playSound(HarshenSounds.LIGHTNING_HIT, 1f, 1f);
			if (item instanceof DarkEwydoen) entity.playSound(HarshenSounds.EWYDOEN_HIT, 1f, 1f);
			
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
			
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.PUNCHY_RING) > 0)
				if(item instanceof ItemAir)
					entity.playSound(HarshenSounds.IRON_HIT, 1f, 1.5f);
			
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.FIERY_RING) > 0)
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

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		tick++;
		
		if(event.player.world.provider.getDimension() == IdConfig.PontusDimension)
		{
			Random rand = new Random();
			if(rand.nextInt(100) == tick)
			{
				event.player.playSound(HarshenSounds.PONTUS_WIND, rand.nextFloat(), 1f);
				tick=0;
			}
			if(tick>100) tick=0;
		}
	}
}