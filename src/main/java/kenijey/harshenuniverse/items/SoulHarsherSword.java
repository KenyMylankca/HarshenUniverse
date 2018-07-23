package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.base.BaseHarshenSword;
import kenijey.harshenuniverse.potions.HarshenPotions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class SoulHarsherSword extends BaseHarshenSword
{
	@Override
	protected String getSwordType() {
		return "sword";
	}

	@Override
	protected String getName() {
		return "soul_harsher_sword";
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
			if(!HarshenUtils.hasJaguarArmorSet((EntityLivingBase)entity))
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(HarshenPotions.potionBleeding, 150, 1));
		return super.onLeftClickEntity(stack, player, entity);
	}
}