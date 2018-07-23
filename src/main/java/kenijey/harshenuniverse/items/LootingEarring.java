package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class LootingEarring extends Item implements IHarshenProvider
{

	public LootingEarring() {
		setRegistryName("looting_earring");
		setUnlocalizedName("looting_earring");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
}