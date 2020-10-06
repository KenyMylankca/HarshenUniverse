package kenymylankca.harshenuniverse.items;

import java.util.List;

import kenymylankca.harshenuniverse.base.BaseHarshenSword;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class EmpoweredSoulHarsherSword extends BaseHarshenSword
{
	@Override
	protected String getName() {
		return "empowered_soul_harsher_sword";
	}

	@Override
	protected Item getRepairItem() {
		return null;
	}
	
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		String type = this.getName();
		tooltip.add("\u00A73" + new TextComponentTranslation(type + "1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}