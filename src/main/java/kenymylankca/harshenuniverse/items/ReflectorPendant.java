package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import net.minecraft.item.Item;

public class ReflectorPendant extends Item implements IHarshenAccessoryProvider
{
	public ReflectorPendant()
	{
		setUnlocalizedName("reflector_pendant");
		setRegistryName("reflector_pendant");
		setMaxDamage(840);
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.NECK;
	}
	
	@Override
	public int toolTipLines() {
		return 2;
	}
}