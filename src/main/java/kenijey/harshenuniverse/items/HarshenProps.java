package kenijey.harshenuniverse.items;

import java.util.List;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.enums.items.EnumProp;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.EnumHelper;

public class HarshenProps extends ItemSword
{
	private static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("soul_sword", 3, 500, 6f, 3f, 30);
	
	public HarshenProps()
	{
		super(toolMaterial);
		setRegistryName("props");
		setUnlocalizedName("prop");
		setHasSubtypes(true);
	}

	protected String[] getNames() {
		return EnumProp.getNames();
	}

	protected List<Integer> getMetaForTab() {
		return null;
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) 
	{
		if(tab.equals(HarshenUniverse.harshenTab) || tab.equals(CreativeTabs.SEARCH))
			if(getMetaForTab() == null)
				for(int i = 0; i < getNames().length; i++)
					items.add(new ItemStack(this, 1, i));
			else
				for(int i : getMetaForTab())
					items.add(new ItemStack(this, 1, i));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		for(int i = 0; i < getNames().length; i ++)
			if(stack.getItemDamage() == i)
				return this.getUnlocalizedName() + "." + getNames()[i];
		return this.getUnlocalizedName() + "." + getNames()[0];
	}
}