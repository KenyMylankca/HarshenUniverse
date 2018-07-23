package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class PunchyRing extends Item implements IHarshenProvider
{
	public PunchyRing()
	{
		setUnlocalizedName("punchy_ring");
		setRegistryName("punchy_ring");
		setMaxDamage(500);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}
}