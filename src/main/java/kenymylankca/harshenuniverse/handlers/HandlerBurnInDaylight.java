package kenymylankca.harshenuniverse.handlers;

import kenymylankca.harshenuniverse.interfaces.IBurnInDay;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerBurnInDaylight 
{
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		EntityLivingBase living = event.getEntityLiving();
		if(living instanceof IBurnInDay && living.world.isDaytime() && !living.world.isRemote && !living.isChild() && ((IBurnInDay)living).shouldBurn() && living.getBrightness() > 0.5F && 
				living.getRNG().nextFloat() * 30.0F < (living.getBrightness() - 0.4F) * 2.0F && living.world.canSeeSky(new BlockPos(living.posX, living.posY + (double)living.getEyeHeight(), living.posZ)))
		{
			boolean flag = true;
            ItemStack itemstack = living.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            if (!itemstack.isEmpty())
            {
                if (itemstack.isItemStackDamageable())
                {
                    itemstack.setItemDamage(itemstack.getItemDamage() + living.getRNG().nextInt(2));

                    if (itemstack.getItemDamage() >= itemstack.getMaxDamage())
                    {
                    	living.renderBrokenItemStack(itemstack);
                    	living.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
                    }
                }

                flag = false;
            }

            if (flag)
            {
            	living.setFire(8);
            }
		}
	}
}
