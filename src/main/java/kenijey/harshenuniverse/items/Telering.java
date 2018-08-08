package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class Telering extends Item implements IHarshenProvider
{
	public Telering()
	{
		setUnlocalizedName("telering");
		setRegistryName("telering");
		setMaxDamage(700);
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.RING1;
	}
}