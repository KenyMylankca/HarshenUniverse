package kenijey.harshenuniverse.handlers;

import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.enums.items.GlassContainerValues;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerGlassContainer 
{
	@SubscribeEvent
	public void onEntityRightClick(PlayerInteractEvent.EntityInteract event)
	{
		if(event.getEntityPlayer().getHeldItemMainhand().isItemEqual(new ItemStack(HarshenItems.GLASS_CONTAINER)) && event.getTarget().getClass() == EntityCow.class)
			event.getEntityPlayer().setHeldItem(EnumHand.MAIN_HAND, GlassContainerValues.MILK.getStack());
	}
}