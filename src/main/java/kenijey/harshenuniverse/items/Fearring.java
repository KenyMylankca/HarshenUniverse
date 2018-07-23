package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
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
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}
}