package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class FeatherEarring extends Item implements IHarshenProvider
{
	public FeatherEarring()
	{
		setUnlocalizedName("feather_earring");
		setRegistryName("feather_earring");
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