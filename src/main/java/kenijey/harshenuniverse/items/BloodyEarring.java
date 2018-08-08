package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class BloodyEarring extends Item implements IHarshenProvider
{
	public BloodyEarring()
	{
		setUnlocalizedName("bloody_earring");
		setRegistryName("bloody_earring");
	}

	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.LEFT_EAR;
	}
}