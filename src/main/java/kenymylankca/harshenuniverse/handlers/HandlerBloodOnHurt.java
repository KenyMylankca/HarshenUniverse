package kenymylankca.harshenuniverse.handlers;

import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.damagesource.DamageSourceBleeding;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerBloodOnHurt
{
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if(event.getSource() instanceof EntityDamageSource || event.getSource() instanceof DamageSourceBleeding)
			HarshenUtils.splashBlood(event.getEntityLiving());
	}
}