package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
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
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}
}