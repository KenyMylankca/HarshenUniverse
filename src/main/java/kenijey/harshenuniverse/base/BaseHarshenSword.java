package kenijey.harshenuniverse.base;

import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public abstract class BaseHarshenSword extends ItemSword
{
	private static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("soul_sword", 3, 4173, 13.5f, 7f, 30);
	public BaseHarshenSword()
	{
		super(toolMaterial);
		setUnlocalizedName(getName());
		setRegistryName(getName());
	}
	
	protected abstract String getName();
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == getRepairItem();
	}
	
	@Nullable
	protected abstract Item getRepairItem();
}