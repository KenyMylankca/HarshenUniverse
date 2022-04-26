package com.kenymylankca.harshenuniverse.items;

import java.util.List;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.api.CauldronLiquid;
import kenymylankca.harshenuniverse.base.BaseItemMetaData;
import kenymylankca.harshenuniverse.enums.items.GlassContainerValue;
import kenymylankca.harshenuniverse.fluids.HarshenFluids;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class GlassContainer extends BaseItemMetaData
{
	public GlassContainer() {
		setRegistryName("glass_container");
		setUnlocalizedName("glass_container");
		setHasSubtypes(true);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		RayTraceResult raytraceresult = this.rayTrace(worldIn, player, true);
		BlockPos blockpos = raytraceresult.getBlockPos();
		IBlockState iblockstate = worldIn.getBlockState(blockpos);
		int slot = player.inventory.getSlotFor(player.getHeldItem(hand));
		
		if	(player.getHeldItem(hand).getMetadata() != 0)
			return EnumActionResult.PASS;
		if	(pos.getY() == 0) {
			player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount()-1);
			if(player.getHeldItem(hand).getCount() < 1)
				player.inventory.setInventorySlotContents(slot, new ItemStack(this, 1, 1));
			else
				player.addItemStackToInventory(new ItemStack(this, 1, 1));
		}
		if	(iblockstate.getBlock() instanceof BlockDirt) {
			player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount()-1);
			if(player.getHeldItem(hand).getCount() < 1)
				player.inventory.setInventorySlotContents(slot, new ItemStack(this, 1, 10));
			else
				player.addItemStackToInventory(new ItemStack(this, 1, 10));
		}
		if	(iblockstate.getBlock() instanceof BlockSand) {
			player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount()-1);
			if(player.getHeldItem(hand).getCount() < 1)
				player.inventory.setInventorySlotContents(slot, new ItemStack(this, 1, 11));
			else
				player.addItemStackToInventory(new ItemStack(this, 1, 11));
		}
		if	(iblockstate.getBlock() == HarshenFluids.HARSHING_WATER_BLOCK) {
			player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount()-1);
			if(player.getHeldItem(hand).getCount() < 1)
				player.inventory.setInventorySlotContents(slot, new ItemStack(this, 1, 4));
			else
				player.addItemStackToInventory(new ItemStack(this, 1, 4));
		}
		if	(iblockstate.getBlock() == HarshenFluids.HARSHEN_DIMENSIONAL_FLUID_BLOCK) {
			player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount()-1);
			if(player.getHeldItem(hand).getCount() < 1)
				player.inventory.setInventorySlotContents(slot, new ItemStack(this, 1, 5));
			else
				player.addItemStackToInventory(new ItemStack(this, 1, 5));
		}
		if	(iblockstate.getBlock() == HarshenBlocks.BLOOD_BLOCK) {
			player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount()-1);
			if(player.getHeldItem(hand).getCount() < 1)
				player.inventory.setInventorySlotContents(slot, new ItemStack(this, 1, 6));
			else
				player.addItemStackToInventory(new ItemStack(this, 1, 6));
			worldIn.setBlockToAir(pos);
		}
		if	(iblockstate.getBlock() == Blocks.LAVA) {
			player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount()-1);
			if(player.getHeldItem(hand).getCount() < 1)
				player.inventory.setInventorySlotContents(slot, new ItemStack(this, 1, 7));
			else
				player.addItemStackToInventory(new ItemStack(this, 1, 7));
		}
		if	(iblockstate.getBlock() == Blocks.WATER) {
			player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount()-1);
			if(player.getHeldItem(hand).getCount() < 1)
				player.inventory.setInventorySlotContents(slot, new ItemStack(this, 1, 9));
			else
				player.addItemStackToInventory(new ItemStack(this, 1, 9));
		}
		return EnumActionResult.FAIL;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		if(!HarshenUniverse.hasLoaded) return "item.glasscontainer"; //Used to prevent the game from crashing during startup
			String args = getGlassContaining(GlassContainerValue.getContainerFromMeta(stack.getMetadata()).getType());
		if(args != null)
			stack.setStackDisplayName(TextFormatting.RESET + new TextComponentTranslation(stack.getItem().getUnlocalizedName() + 
					".container", args).getFormattedText());
		for(int i = 0; i < getNames().length; i ++)
			if(stack.getItemDamage() == i)
				return this.getUnlocalizedName() + "." + getNames()[i];
		return this.getUnlocalizedName() + "." + "illegal";
	}
	
	public static String getGlassContaining(CauldronLiquid liquid)
	{
		if(liquid == null)
			return null;
		String s = liquid.hasState() && !((IBlockState)liquid.getStateOrLoc()).getBlock().getLocalizedName().equals("tile.null.name") ? 
				((IBlockState)liquid.getStateOrLoc()).getBlock().getLocalizedName() :  new TextComponentTranslation("fluid." + liquid.getName().split(":")[1]).getUnformattedText();
		return s;
	}
	
	@Override
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		if(!hasDrinkEffect(playerIn, handIn))
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
		playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return stack.getMetadata() == 0 ? 64 : this.maxStackSize;
	}
	
	private boolean hasDrinkEffect(EntityPlayer playerIn, EnumHand handIn)
	{
		return hasDrinkEffect(playerIn.getHeldItem(handIn).getMetadata());
	}
	
	private boolean hasDrinkEffect(int meta)
	{
		return GlassContainerValue.getContainerFromMeta(meta).getEffects() != null;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
		GlassContainerValue enu = GlassContainerValue.getContainerFromMeta(stack.getMetadata());
		if(enu.getEffects() != null)
			for(PotionEffect effect : enu.getEffects())
				entityLiving.addPotionEffect(effect);
		if(entityLiving instanceof EntityPlayer)
			if(((EntityPlayer)entityLiving).isCreative())
				return stack;
		return new ItemStack(this);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return hasDrinkEffect(stack.getMetadata()) ? EnumAction.DRINK : EnumAction.NONE;
	}

	@Override
	protected List<Integer> getMetaForTab() {
		return null;
	}

	@Override
	protected String[] getNames() {
		return GlassContainerValue.getNames();
	}
}