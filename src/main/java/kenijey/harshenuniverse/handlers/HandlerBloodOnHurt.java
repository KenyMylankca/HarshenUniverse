package kenijey.harshenuniverse.handlers;

import java.util.Random;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.config.GeneralConfig;
import kenijey.harshenuniverse.damagesource.DamageSourceBleeding;
import kenijey.harshenuniverse.items.BloodCollector;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerBloodOnHurt
{
	Class[] AllowedEntities = {EntityPlayerMP.class, EntityWitch.class, EntityVillager.class};
	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if(new Random().nextDouble() < GeneralConfig.bloodChance && (event.getSource() instanceof EntityDamageSource || event.getSource() instanceof DamageSourceBleeding) && HarshenUtils.toArray(AllowedEntities).contains(event.getEntity().getClass()))
			if(event.getEntity().world.isAirBlock(event.getEntity().getPosition()) && GeneralConfig.bloodDrops)
				event.getEntity().getEntityWorld().setBlockState(event.getEntity().getPosition(), HarshenBlocks.BLOOD_BLOCK.getDefaultState(), 3);
			if(event.getSource().getTrueSource() instanceof EntityPlayer && GeneralConfig.bloodOffHand &&
					((EntityPlayer)event.getSource().getTrueSource()).getHeldItemOffhand().getItem() == HarshenItems.BLOOD_COLLECTOR)
				((BloodCollector)((EntityPlayer)event.getSource().getTrueSource()).getHeldItemOffhand().getItem()).fill(((EntityPlayer)event.getSource().getTrueSource()), EnumHand.OFF_HAND, 1);
	}
}