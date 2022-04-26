package com.kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.base.BaseHarshenScythe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class IronScythe extends BaseHarshenScythe
{
	private static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("iron_scythe", 2, 1010, 13.5f, 7f, 30);

	public IronScythe() 
	{
		super(toolMaterial);
		setUnlocalizedName("iron_scythe");
		setRegistryName("iron_scythe");
	}

	@Override
	protected float getSpeed() {
		return 1.4f;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.IRON_INGOT;
	}

	@Override
	public double getReach() {
		return 5;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if(player.getCooledAttackStrength(1) == 1)
			player.playSound(HarshenSounds.IRON_SCYTHE_HIT, 1f, 1f);
		return super.onLeftClickEntity(stack, player, entity);
	}
}