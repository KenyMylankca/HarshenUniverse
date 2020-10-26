package kenymylankca.harshenuniverse.base;

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
}