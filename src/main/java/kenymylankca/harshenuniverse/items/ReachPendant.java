package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class ReachPendant extends Item implements IHarshenProvider
{
	public ReachPendant() {
		setRegistryName("reach_pendant");
		setUnlocalizedName("reach_pendant");
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.NECK;
	}
}