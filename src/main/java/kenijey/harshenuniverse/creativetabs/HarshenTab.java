package kenijey.harshenuniverse.creativetabs;

import kenijey.harshenuniverse.HarshenItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class HarshenTab extends CreativeTabs
{
	public HarshenTab()
	{
		super("harshenTab");
		this.setBackgroundImageName("harshen_tab.png");
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(HarshenItems.PONTUS_WORLD_GATE_SPAWNER);
	}
}