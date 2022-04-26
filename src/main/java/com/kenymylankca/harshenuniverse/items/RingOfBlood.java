package com.kenymylankca.harshenuniverse.items;

import java.util.UUID;

import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class RingOfBlood extends Item implements IHarshenAccessoryProvider
{
	public RingOfBlood()
	{
		setUnlocalizedName("ring_of_blood");
		setRegistryName("ring_of_blood");
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.RING1;
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
	
	@Override
	public void onAdd(EntityPlayer player, int slot)
	{
		IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("83cd6b7e-a838-11e7-abc4-cec278b6b50"), "ringOfBloodHealth", HarshenUtils.hasAccessoryTimes(player, this) * 6, 0).setSaved(true);
		if(!attributeHealth.hasModifier(modifierHealth))	
			attributeHealth.applyModifier(modifierHealth);
		IHarshenAccessoryProvider.super.onAdd(player, slot);
	}
	
	@Override
	public void onRemove(EntityPlayer player, int slot)
	{
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("83cd6b7e-a838-11e7-abc4-cec278b6b50"));
		if(player.getMaxHealth() < player.getHealth())
			player.setHealth(player.getMaxHealth());
		IHarshenAccessoryProvider.super.onRemove(player, slot);
	}
}