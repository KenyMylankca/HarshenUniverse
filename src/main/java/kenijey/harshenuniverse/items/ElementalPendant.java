package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class ElementalPendant extends Item implements IHarshenProvider
{
	public ElementalPendant()
	{
		setUnlocalizedName("elemental_pendant");
		setRegistryName("elemental_pendant");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
}