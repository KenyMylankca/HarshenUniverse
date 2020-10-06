package kenymylankca.harshenuniverse.handlers.vanillaInventory;

import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.api.HarshenEvent;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class HandlerTotemOfUndying 
{
	@HarshenEvent
	public void PlayerDeath(LivingDeathEvent event)
	{
		EntityPlayer player = (EntityPlayer) event.getEntity();
		event.setCanceled(true);
		if (player instanceof EntityPlayerMP)
		{
            EntityPlayerMP entityplayermp = (EntityPlayerMP)player;
            entityplayermp.addStat(StatList.getObjectUseStats(Items.TOTEM_OF_UNDYING));
            CriteriaTriggers.USED_TOTEM.trigger(entityplayermp, HarshenUtils.getFirstOccuringItem(player, Items.TOTEM_OF_UNDYING));
        }
		
		HarshenUtils.setStackInSlot(player, Items.TOTEM_OF_UNDYING, ItemStack.EMPTY);
		player.setHealth(1.0F);
		player.clearActivePotions();
		player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 900, 1));
		player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 100, 1));
		player.world.setEntityState(player, (byte)35);
	}
}
