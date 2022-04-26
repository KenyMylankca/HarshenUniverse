package com.kenymylankca.harshenuniverse.items;

import java.util.List;

import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.base.BaseHarshenSword;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class EmpoweredSoulHarsherSword extends BaseHarshenSword
{
	@Override
	protected String getName() {
		return "empowered_soul_harsher_sword";
	}
	
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		String type = this.getName();
		tooltip.add("\u00A73" + new TextComponentTranslation(type + "1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if(player.getCooledAttackStrength(1) == 1)
			player.playSound(HarshenSounds.IRON_SCYTHE_HIT, 1f, 1f);
		return super.onLeftClickEntity(stack, player, entity);
	}
}