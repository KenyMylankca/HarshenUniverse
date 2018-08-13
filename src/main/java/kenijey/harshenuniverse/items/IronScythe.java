package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.base.BaseHarshenScythe;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class IronScythe extends BaseHarshenScythe
{
	private static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("iron_scythe", 2, 1010, 13.5f, 7f, 30);

	public IronScythe() 
	{
		super(toolMaterial);
		setUnlocalizedName("iron_scythe");
		setRegistryName("iron_scythe");
	}

	@Override
	protected float getSpeed() {
		return 1.4f;
	}

	@Override
	protected Item getRepairItem() {
		return Items.IRON_INGOT;
	}

	@Override
	public double getReach() {
		return 5;
	}
}