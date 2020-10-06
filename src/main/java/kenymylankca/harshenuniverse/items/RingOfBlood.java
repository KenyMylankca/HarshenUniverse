package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class RingOfBlood extends Item implements IHarshenProvider
{
	public RingOfBlood()
	{
		setUnlocalizedName("ring_of_blood");
		setRegistryName("ring_of_blood");
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.RING1;
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
}