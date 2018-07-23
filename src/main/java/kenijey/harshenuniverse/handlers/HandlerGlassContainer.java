package kenijey.harshenuniverse.handlers;

import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.enums.items.GlassContainerValues;
import kenijey.harshenuniverse.items.GlassContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerGlassContainer 
{
	@SubscribeEvent
	public void onEntityHit(LivingHurtEvent event)
	{
		if(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource) event.getSource()).getTrueSource() instanceof EntityLivingBase && 
				((EntityLivingBase)((EntityDamageSource) event.getSource()).getTrueSource()).getHeldItemMainhand().getItem() instanceof GlassContainer && 
				((EntityLivingBase)((EntityDamageSource) event.getSource()).getTrueSource()).getHeldItemMainhand().getItemDamage() == 0)
		{
			ItemStack stack = ((EntityLivingBase)((EntityDamageSource) event.getSource()).getTrueSource()).getHeldItemMainhand();
			stack.setItemDamage(2);
			((EntityLivingBase)((EntityDamageSource) event.getSource()).getTrueSource()).setHeldItem(EnumHand.MAIN_HAND, stack);
		}		
	}
	
	@SubscribeEvent
	public void onEntityRightClick(PlayerInteractEvent.EntityInteract event)
	{
		if(event.getEntityPlayer().getHeldItemMainhand().isItemEqual(new ItemStack(HarshenItems.GLASS_CONTAINER)) && event.getTarget().getClass() == EntityCow.class)
			event.getEntityPlayer().setHeldItem(EnumHand.MAIN_HAND, GlassContainerValues.MILK.getStack());

	}
}
