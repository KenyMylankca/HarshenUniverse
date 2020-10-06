package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class CriminalPendant extends Item implements IHarshenProvider
{
	public CriminalPendant()
	{
		setUnlocalizedName("criminal_pendant");
		setRegistryName("criminal_pendant");
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.NECK;
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
}