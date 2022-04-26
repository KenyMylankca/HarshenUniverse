package com.kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;

public class AquaEarring extends Item implements IHarshenAccessoryProvider
{
	public AquaEarring() {
		setRegistryName("aqua_earring");
		setUnlocalizedName("aqua_earring");
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.LEFT_EAR;
	}
	
	@Override
	public void onTick(EntityPlayer player, int slot)
	{
		player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 115, HarshenUtils.hasAccessoryTimes(player, HarshenItems.AQUA_EARRING) - 1));
		IHarshenAccessoryProvider.super.onTick(player, slot);
	}
}