package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import net.minecraft.item.Item;

public class PunchyRing extends Item implements IHarshenAccessoryProvider
{
	public PunchyRing()
	{
		setUnlocalizedName("punchy_ring");
		setRegistryName("punchy_ring");
		setMaxDamage(500);
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.RING1;
	}
}