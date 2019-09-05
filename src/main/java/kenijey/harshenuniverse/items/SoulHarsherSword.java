package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.base.BaseHarsherSword;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class SoulHarsherSword extends BaseHarsherSword
{
	@Override
	protected String getName() {
		return "soul_harsher_sword";
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		HarshenUtils.bleedTarget((EntityLivingBase) entity, 150, 1);
		return super.onLeftClickEntity(stack, player, entity);
	}
}