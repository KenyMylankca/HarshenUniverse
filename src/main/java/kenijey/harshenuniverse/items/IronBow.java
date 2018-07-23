package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.base.BaseHarshenBow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;

public class IronBow extends BaseHarshenBow
{
	public IronBow()
	{
		super(SoundEvents.ENTITY_ARROW_HIT);
		setUnlocalizedName("iron_bow");
		setRegistryName("iron_bow");
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.IRON_INGOT;
	}

	@Override
	public int getMaxDamage() {
		return 1388;
	}

	@Override
	public double additionDamage() {
		return 1.5D;
	}
	
	@Override
	protected boolean useDefaultArrow() {
		return true;
	}
}