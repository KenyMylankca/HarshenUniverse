package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class WaterEarring extends Item implements IHarshenProvider
{
	public WaterEarring() {
		setRegistryName("water_earring");
		setUnlocalizedName("water_earring");
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.LEFT_EAR;
	}
}