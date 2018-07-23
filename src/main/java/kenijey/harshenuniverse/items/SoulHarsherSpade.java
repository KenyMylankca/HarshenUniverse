package kenijey.harshenuniverse.items;

import java.util.List;

import kenijey.harshenuniverse.HarshenItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class SoulHarsherSpade extends ItemSpade
{
	public SoulHarsherSpade()
	{
		super(EnumHelper.addToolMaterial("soul_harsher_spade", 3, 2000, 13.5f, 2.5f, 30));
		setUnlocalizedName("soul_harsher_spade");
		setRegistryName("soul_harsher_spade");
		setNoRepair();
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("spade1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == HarshenItems.SOUL_INFUSED_INGOT;
	}
}