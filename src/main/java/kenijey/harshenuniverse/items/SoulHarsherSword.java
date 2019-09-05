package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.base.BaseHarshenSword;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class SoulHarsherSword extends BaseHarshenSword
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