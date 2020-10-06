package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class FeedingEarring extends Item implements IHarshenProvider
{
	public FeedingEarring() {
		setRegistryName("feeding_earring");
		setUnlocalizedName("feeding_earring");
		this.canRepair = false;
	}

	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.LEFT_EAR;
	}
}