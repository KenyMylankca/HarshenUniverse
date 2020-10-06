package kenymylankca.harshenuniverse.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MysticFeather extends Item
{
	public MysticFeather()
	{
		setUnlocalizedName("mystic_feather");
		setRegistryName("mystic_feather");
	}
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

}
