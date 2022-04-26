package com.kenymylankca.harshenuniverse.creativetabs;

import com.kenymylankca.harshenuniverse.HarshenItems;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HarshenTab extends CreativeModeTab
{
	public HarshenTab() {
		super("harshenTab");
		this.setBackgroundImage(new ResourceLocation("harshen_tab.png"));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack makeIcon() {
		return new ItemStack((ItemLike) HarshenItems.PONTUS_WORLD_GATE_SPAWNER);
	}
}