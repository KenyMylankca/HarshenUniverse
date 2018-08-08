package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class PontusRing extends Item implements IHarshenProvider
{
	public PontusRing()
	{
		setUnlocalizedName("pontus_ring");
		setRegistryName("pontus_ring");
	}

	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.RING1;
	}
}