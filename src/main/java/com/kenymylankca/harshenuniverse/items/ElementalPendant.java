package com.kenymylankca.harshenuniverse.items;

import java.util.UUID;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class ElementalPendant extends Item implements IHarshenAccessoryProvider
{
	public ElementalPendant()
	{
		setUnlocalizedName("elemental_pendant");
		setRegistryName("elemental_pendant");
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.NECK;
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
	
	@Override
	public void onAdd(EntityPlayer player, int slot) {
		IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("04d5b650-d8a8-45a9-88f1-01992a4f5785"), "elementalPendantHealth", 6, 0).setSaved(true);
		if(!attributeHealth.hasModifier(modifierHealth))	
			attributeHealth.applyModifier(modifierHealth);
		
		IAttributeInstance attributeArmor = player.getEntityAttribute(SharedMonsterAttributes.ARMOR);
		AttributeModifier modifierArmor = new AttributeModifier(UUID.fromString("6c0d606c-b4ae-468d-a259-bcd5638055cb"), "elementalPendantArmor", 4, 0).setSaved(true);
		if(!attributeArmor.hasModifier(modifierArmor))
			attributeArmor.applyModifier(modifierArmor);
		IHarshenAccessoryProvider.super.onAdd(player, slot);
	}
	
	@Override
	public void onRemove(EntityPlayer player, int slot) {
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("04d5b650-d8a8-45a9-88f1-01992a4f5785"));
		player.getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(UUID.fromString("6c0d606c-b4ae-468d-a259-bcd5638055cb"));
		if(player.getMaxHealth() < player.getHealth()) player.setHealth(player.getMaxHealth());
		IHarshenAccessoryProvider.super.onRemove(player, slot);
	}
}