package com.kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SoulBindingPendant extends Item implements IHarshenAccessoryProvider
{
	public SoulBindingPendant() {
		setRegistryName("soul_binding_pendant");
		setUnlocalizedName("soul_binding_pendant");
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.NECK;
	}
	
	@Override
	public void onAdd(EntityPlayer player, int slot) {
		player.playSound(HarshenSounds.ONE_RING, 1f, 1.8f);
		IHarshenAccessoryProvider.super.onAdd(player, slot);
	}
	
	@Override
	public void onRemove(EntityPlayer player, int slot) {
		player.playSound(HarshenSounds.SOUL_RIPPER_BOW_HIT, 1f, 0.6f);
		IHarshenAccessoryProvider.super.onRemove(player, slot);
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
}