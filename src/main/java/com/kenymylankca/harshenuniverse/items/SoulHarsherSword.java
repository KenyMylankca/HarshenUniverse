package com.kenymylankca.harshenuniverse.items;

import java.util.List;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseHarshenSword;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == HarshenItems.HARSHEN_SOUL_FRAGMENT;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if(entity instanceof EntityLivingBase)
			if(HarshenUtils.hasJaguarArmorSet((EntityLivingBase) entity))
				player.playSound(HarshenSounds.JAGUAR_DEFENSE, 1f, 1f);
			else
			{
				player.playSound(HarshenSounds.SOUL_HARSHER_SWORD_HIT, 1f, 1f);
				HarshenUtils.bleedTarget((EntityLivingBase) entity, 150, 1);
			}
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		String type = this.getName();
		tooltip.add("\u00A73" + new TextComponentTranslation(type + "1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}