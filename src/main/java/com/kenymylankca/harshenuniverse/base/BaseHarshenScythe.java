package com.kenymylankca.harshenuniverse.base;

import com.google.common.collect.Multimap;

import kenymylankca.harshenuniverse.HarshenUtils;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public abstract class BaseHarshenScythe extends ItemSword
{
	public BaseHarshenScythe(ToolMaterial material) {
		super(material);
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack)
	{
		final Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(slot, stack);

		if (slot == EntityEquipmentSlot.MAINHAND)
			HarshenUtils.replaceModifier(modifiers, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, getSpeed());

		return modifiers;
	}
	
	protected abstract float getSpeed();
	
	public abstract double getReach();
}