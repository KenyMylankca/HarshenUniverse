package com.kenymylankca.harshenuniverse.enchantment;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.HarshenUtils;
import net.minecraft.enchantment.Enchantment;

public class HarshenMixupEnchantment extends Enchantment
{
	public HarshenMixupEnchantment() {
		super(Enchantment.Rarity.VERY_RARE, HarshenUtils.HARSHEN_ITEMS_ONLY, HarshenUtils.listOf());
		setRegistryName(HarshenUniverse.MODID, "mixup"); 
		setName("mixup");
	}
}