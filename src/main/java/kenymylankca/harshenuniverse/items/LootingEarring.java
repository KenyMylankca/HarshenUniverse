package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class LootingEarring extends Item implements IHarshenProvider
{

	public LootingEarring() {
		setRegistryName("looting_earring");
		setUnlocalizedName("looting_earring");
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.LEFT_EAR;
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
}