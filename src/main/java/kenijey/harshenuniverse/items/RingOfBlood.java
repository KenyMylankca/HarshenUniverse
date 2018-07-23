package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class RingOfBlood extends Item implements IHarshenProvider
{
	public RingOfBlood()
	{
		setUnlocalizedName("ring_of_blood");
		setRegistryName("ring_of_blood");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
}