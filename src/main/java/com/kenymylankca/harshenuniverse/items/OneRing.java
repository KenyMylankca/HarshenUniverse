package com.kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;

public class OneRing extends Item implements IHarshenAccessoryProvider
{
	public OneRing()
	{
		setUnlocalizedName("one_ring");
		setRegistryName("one_ring");
	}

	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.RING1;
	}

	@Override
	public void onAdd(EntityPlayer player, int slot) {
		player.playSound(HarshenSounds.ONE_RING, 1f, 1f);
		IHarshenAccessoryProvider.super.onAdd(player, slot);
	}
	
	@Override
	public void onRemove(EntityPlayer player, int slot) {
		player.playSound(SoundEvents.ENTITY_ENDEREYE_DEATH, 1f, 0.3f);
		IHarshenAccessoryProvider.super.onRemove(player, slot);
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
	
	@Override
	public void onTick(EntityPlayer player, int slot)
	{
		player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 7, 0, false, false));
		IHarshenAccessoryProvider.super.onTick(player, slot);
	}
}