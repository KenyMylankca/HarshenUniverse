package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class ZombiPendant extends Item implements IHarshenProvider
{
	public ZombiPendant()
	{
		setUnlocalizedName("zombi_pendant");
		setRegistryName("zombi_pendant");
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.NECK;
	}
}