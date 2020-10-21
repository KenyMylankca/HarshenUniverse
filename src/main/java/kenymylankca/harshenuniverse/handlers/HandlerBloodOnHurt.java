package kenymylankca.harshenuniverse.handlers;

import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.config.GeneralConfig;
import kenymylankca.harshenuniverse.damagesource.DamageSourceBleeding;
import kenymylankca.harshenuniverse.network.HarshenNetwork;
import kenymylankca.harshenuniverse.network.packets.MessagePacketPlaySound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerBloodOnHurt
{
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		String[] AllowedEntities = GeneralConfig.bleedableEntities;
		
		if(HarshenUtils.toArray(AllowedEntities).contains(event.getEntityLiving().getName().toLowerCase()) || event.getEntityLiving() instanceof EntityPlayer)
			if((event.getSource() instanceof EntityDamageSource && event.getAmount() > 3) || event.getSource() instanceof DamageSourceBleeding)
				if(event.getEntityLiving().world.rand.nextFloat() < GeneralConfig.bloodChance)
				{
					World world = event.getEntityLiving().world;
					HarshenUtils.splashBlood(event.getEntityLiving().getPosition(), event.getEntityLiving().world, 1, 1);
					if(!(event.getSource() instanceof DamageSourceBleeding))
						for(EntityPlayer player : HarshenUtils.getPlayersInDistance(world, 10, event.getEntityLiving().getPosition(), true))
							HarshenNetwork.sendToPlayer(player, new MessagePacketPlaySound(HarshenSounds.BLOOD_SPLASH, world.rand.nextFloat(), world.rand.nextFloat() + 0.5f, event.getEntityLiving().getPosition()));
				}
	}
}