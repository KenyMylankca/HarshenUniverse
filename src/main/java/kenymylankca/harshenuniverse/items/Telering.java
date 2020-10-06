package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenProvider;
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