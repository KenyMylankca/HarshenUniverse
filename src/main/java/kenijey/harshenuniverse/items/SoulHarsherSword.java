package kenijey.harshenuniverse.items;

import java.util.List;

import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.base.BaseHarshenSword;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class SoulHarsherSword extends BaseHarshenSword
{
	@Override
	protected String getName() {
		return "soul_harsher_sword";
	}
	
	@Override
	protected Item getRepairItem() {
		return HarshenItems.HARSHEN_SOUL_FRAGMENT;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		HarshenUtils.bleedTarget((EntityLivingBase) entity, 150, 1);
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		String type = this.getName();
		tooltip.add("\u00A73" + new TextComponentTranslation(type + "1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}