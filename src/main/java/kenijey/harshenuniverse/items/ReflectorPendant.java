package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
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
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
	
	@Override
	public int toolTipLines() {
		return 2;
	}
}