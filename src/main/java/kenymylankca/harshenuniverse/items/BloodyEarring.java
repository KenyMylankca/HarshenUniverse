package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class BloodyEarring extends Item implements IHarshenAccessoryProvider
{
	int tick = 0;
	public BloodyEarring()
	{
		setUnlocalizedName("bloody_earring");
		setRegistryName("bloody_earring");
	}

	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.LEFT_EAR;
	}
	
	@Override
	public void onTick(EntityPlayer player, int slot)
	{
		tick++;
		if(tick > 200)
		{
			player.heal(2);
			tick=0;
		}
		IHarshenAccessoryProvider.super.onTick(player, slot);
	}
}