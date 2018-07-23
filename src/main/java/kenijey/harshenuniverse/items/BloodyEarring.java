package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
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
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}
}