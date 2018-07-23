package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
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
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
}