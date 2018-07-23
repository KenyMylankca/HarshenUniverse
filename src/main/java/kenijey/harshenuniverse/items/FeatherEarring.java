package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class FeatherEarring extends Item implements IHarshenProvider
{
	public FeatherEarring()
	{
		setUnlocalizedName("feather_earring");
		setRegistryName("feather_earring");
	}

	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}
}