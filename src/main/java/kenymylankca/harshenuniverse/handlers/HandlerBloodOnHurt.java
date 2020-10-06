package kenymylankca.harshenuniverse.handlers;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.damagesource.DamageSourceBleeding;
import kenymylankca.harshenuniverse.items.BloodCollector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerBloodOnHurt
{
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if(event.getSource() instanceof EntityDamageSource || event.getSource() instanceof DamageSourceBleeding)
			HarshenUtils.splashBlood(event.getEntityLiving());
			
		if(event.getSource().getTrueSource() instanceof EntityPlayer && ((EntityPlayer)event.getSource().getTrueSource()).getHeldItemOffhand().getItem() == HarshenItems.BLOOD_COLLECTOR)
			((BloodCollector)((EntityPlayer)event.getSource().getTrueSource()).getHeldItemOffhand().getItem()).fill(((EntityPlayer)event.getSource().getTrueSource()), EnumHand.OFF_HAND, 1);
	}
}