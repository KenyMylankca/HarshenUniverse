package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
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
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}
}