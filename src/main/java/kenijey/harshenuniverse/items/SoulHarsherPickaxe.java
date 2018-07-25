package kenijey.harshenuniverse.items;

import java.util.List;

import kenijey.harshenuniverse.HarshenItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class SoulHarsherPickaxe extends ItemPickaxe
{
	public SoulHarsherPickaxe()
	{
		super(EnumHelper.addToolMaterial("soul_harsher_pickaxe", 3, 4305, 13.5f, 2.5f, 30));
		setUnlocalizedName("soul_harsher_pickaxe");
		setRegistryName("soul_harsher_pickaxe");
		setNoRepair();
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("pickaxe1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == HarshenItems.SOUL_INFUSED_INGOT;
	}
}