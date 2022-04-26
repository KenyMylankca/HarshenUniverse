package com.kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.handlers.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class GuidanceOfHarshenUniverse extends Item
{
	public GuidanceOfHarshenUniverse() {
		setRegistryName("harshen_book");
		setUnlocalizedName("harshen_book");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.openGui(HarshenUniverse.instance, GuiHandler.BOOK, playerIn.getEntityWorld(), (int)playerIn.posX, (int)playerIn.posY, (int)playerIn.posZ);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
}