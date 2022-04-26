package com.kenymylankca.harshenuniverse.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenEnchantmetns 
{
	public final static Enchantment MIXUP = new HarshenMixupEnchantment();
	
	public static void preInit()
	{
		reg(MIXUP);
	}
	
	private static void reg(Enchantment ench)
	{
		ForgeRegistries.ENCHANTMENTS.register(ench);
	}
}