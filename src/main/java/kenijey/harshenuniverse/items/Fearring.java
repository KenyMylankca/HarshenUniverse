package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class Fearring extends Item implements IHarshenProvider
{
	public Fearring()
	{
		setUnlocalizedName("fearring");
		setRegistryName("fearring");
	}

	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.LEFT_EAR;
	}
}