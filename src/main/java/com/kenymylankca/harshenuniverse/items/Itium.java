package com.kenymylankca.harshenuniverse.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class Itium extends Item
{
	public Itium()
	{
		setUnlocalizedName("itium");
		setRegistryName("itium");
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("itium1").getFormattedText());
		tooltip.add(new TextComponentTranslation(" ").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}