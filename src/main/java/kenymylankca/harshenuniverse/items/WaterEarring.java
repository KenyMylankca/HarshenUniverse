package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenProvider;
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