package com.kenymylankca.harshenuniverse.damagesource;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DamageSourceHarshed extends DamageSource
{
	public DamageSourceHarshed() {
		super("harshed");
	}
	
	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
		return new TextComponentTranslation("death.harshed", entityLivingBaseIn.getDisplayName());
	}
}