package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumAccessoryInventorySlots;
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
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.NECK;
	}
}