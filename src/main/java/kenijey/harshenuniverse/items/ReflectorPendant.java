package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class ReflectorPendant extends Item implements IHarshenProvider
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