package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class FeedingEarring extends Item implements IHarshenAccessoryProvider
{
	int tick=0;
	public FeedingEarring() {
		setRegistryName("feeding_earring");
		setUnlocalizedName("feeding_earring");
		this.canRepair = false;
	}

	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.LEFT_EAR;
	}
	
	@Override
	public void onTick(EntityPlayer player, int slot)
	{
		tick++;
		if(tick == 500 && player.getFoodStats().getFoodLevel() < 20)
		{
			player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + HarshenUtils.hasAccessoryTimes(player, HarshenItems.FEEDING_EARRING));
			tick=0;
		}
		IHarshenAccessoryProvider.super.onTick(player, slot);
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
}