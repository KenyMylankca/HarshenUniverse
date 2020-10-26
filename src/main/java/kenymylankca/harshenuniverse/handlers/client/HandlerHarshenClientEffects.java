package kenymylankca.harshenuniverse.handlers.client;

import java.util.Random;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.config.IdConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HandlerHarshenClientEffects
{
	int tick = 0;
	int airBlockChecker = 1;
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if(event.getSource().getTrueSource() instanceof EntityPlayer)
		{
			EntityLivingBase entity = event.getEntityLiving();
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
			Item item = player.getHeldItemMainhand().getItem();
			
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.PUNCHY_RING) > 0)
				if(item instanceof ItemAir)
					player.playSound(HarshenSounds.IRON_HIT, 1f, 1.5f);
			
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.FIERY_RING) > 0)
				player.playSound(SoundEvents.BLOCK_FIRE_AMBIENT, 1f, 1.3f);
		}
	}

	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		if(event.player.world.provider.getDimension() == IdConfig.PontusDimension)
		{
			tick++;
			Random rand = new Random();
			if(tick == 120)
			{
				if(rand.nextInt(3) > 0)
				{
					for(int i=1; i<11; i++)
						if (event.player.world.getBlockState(event.player.getPosition().up(i)).getBlock() != Blocks.AIR)
						{
							airBlockChecker=2;
							break;
						}
					if(airBlockChecker == 1)
						event.player.playSound(HarshenSounds.PONTUS_WIND, rand.nextFloat(), rand.nextFloat()+rand.nextInt(3));
				}
				if(rand.nextInt(13*airBlockChecker) < 1)
					event.player.playSound(HarshenSounds.PONTUS_SCARY, rand.nextFloat(), rand.nextFloat()+rand.nextInt(2));
			}
			if (tick > 130)
				tick=0;
			airBlockChecker=1;
		}
	}
}