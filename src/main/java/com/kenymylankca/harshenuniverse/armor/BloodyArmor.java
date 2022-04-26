package com.kenymylankca.harshenuniverse.armor;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BloodyArmor extends ItemArmor
{
	static int tick=0;
	
	public BloodyArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name) 
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation("harshenuniverse", name));
	}
	
	@Override
	public boolean hasOverlay(ItemStack stack) {
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("bloodyarmor1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	public static void onTick(EntityLivingBase entity)
	{
		tick ++;
		if(tick > 222)
		{
			tick=0;
			entity.heal(2f);
		}
	}
}