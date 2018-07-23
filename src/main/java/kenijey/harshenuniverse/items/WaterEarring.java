package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class WaterEarring extends Item implements IHarshenProvider
{
	public WaterEarring() {
		setRegistryName("water_earring");
		setUnlocalizedName("water_earring");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}
}