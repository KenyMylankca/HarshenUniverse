package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenProvider;
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
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.RING1;
	}
}