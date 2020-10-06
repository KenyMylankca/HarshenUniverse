package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenProvider;
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