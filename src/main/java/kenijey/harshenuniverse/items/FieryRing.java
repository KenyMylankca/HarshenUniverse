package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class FieryRing extends Item implements IHarshenProvider
{
	public FieryRing() {
		setUnlocalizedName("fiery_ring");
		setRegistryName("fiery_ring");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}
}