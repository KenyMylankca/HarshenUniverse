package com.kenymylankca.harshenuniverse.items;

import java.util.List;

import kenymylankca.harshenuniverse.HarshenItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class SoulHarsherSpade extends ItemSpade
{
	public SoulHarsherSpade()
	{
		super(EnumHelper.addToolMaterial("soul_harsher_spade", 3, 2000, 13.5f, 2.5f, 30));
		setUnlocalizedName("soul_harsher_spade");
		setRegistryName("soul_harsher_spade");
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == HarshenItems.HARSHEN_SOUL_INGOT;
	}
}