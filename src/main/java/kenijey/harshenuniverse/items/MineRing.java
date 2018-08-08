package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.Item;

public class MineRing extends Item implements IHarshenProvider
{
	public MineRing()
	{
		setUnlocalizedName("minering");
		setRegistryName("minering");
		setMaxDamage(700);
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.RING1;
	}
}