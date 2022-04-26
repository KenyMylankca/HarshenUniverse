package com.kenymylankca.harshenuniverse.items;

import java.util.List;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseItemMetaData;
import kenymylankca.harshenuniverse.blocks.BloodVessel;
import kenymylankca.harshenuniverse.enums.items.EnumBloodCollector;
import kenymylankca.harshenuniverse.tileentity.TileEntityBloodVessel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BloodCollector extends BaseItemMetaData
{
	private static final int capacity = 30;
	
	public BloodCollector()
	{
		setRegistryName("blood_collector");
		setUnlocalizedName("blood_collector");
	}
	
	public static int getCapacity()
	{
		return capacity;
	}
	
	public int getBloodLevel(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		NBTTagCompound nbt = getNBT(stack);

		return nbt.getInteger("Blood");
	}
	
	public void fill(EntityPlayer player, EnumHand hand, int amount)
	{
		player.world.playSound(player.posX, player.posY, player.posZ, HarshenSounds.BLOOD_COLLECTOR_USE, SoundCategory.BLOCKS, 1f, 0.6f, false);
		if(player.isCreative())
			return;
		ItemStack stack = player.getHeldItem(hand);
		NBTTagCompound nbt = getNBT(stack);
		
		if(nbt.getInteger("Blood") + amount > capacity)
			nbt.setInteger("Blood", capacity);
		else
			nbt.setInteger("Blood", nbt.getInteger("Blood") + amount);
		
		stack.setItemDamage(metaChange(nbt));
        stack.setTagCompound(nbt);
        player.setHeldItem(hand, stack);
	}
	
	public void drain(EntityPlayer player, EnumHand hand, int amount)
	{
		player.world.playSound(player.posX, player.posY, player.posZ, HarshenSounds.BLOOD_COLLECTOR_USE, SoundCategory.BLOCKS, 0.8f, 1f, false);
		if(player.isCreative())
			return;
		ItemStack stack = player.getHeldItem(hand);
		NBTTagCompound nbt = getNBT(stack);
		
		if(nbt.getInteger("Blood") - amount < 0)
			nbt.setInteger("Blood", 0);
		else
			nbt.setInteger("Blood", nbt.getInteger("Blood") - amount);
		
		stack.setItemDamage(metaChange(nbt));
		stack.setTagCompound(nbt);
		player.setHeldItem(hand, stack);
	}
	
	private NBTTagCompound getNBT(ItemStack stack)
	{
		NBTTagCompound nbt;
		if (stack.hasTagCompound())
	        nbt = stack.getTagCompound();
	    else
	    	nbt = new NBTTagCompound();
		
		if (!nbt.hasKey("Blood"))
			nbt.setInteger("Blood", 0);
		return nbt;
	}
	
	private int metaChange(NBTTagCompound nbt)
	{
		for(int i = 0; i < EnumBloodCollector.values().length; i ++)
		{
			if(EnumBloodCollector.values()[i].getAmount() <= nbt.getInteger("Blood") && (i + 1 == EnumBloodCollector.values().length || EnumBloodCollector.values()[i + 1].getAmount() > nbt.getInteger("Blood")))
				return i;
		}
		return 0;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation("tile.blood_block.name").getFormattedText() + "\u00A74" + ": " + "\u00A73" + Integer.toString(getNBT(stack).getInteger("Blood")) + " / " + capacity);
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(worldIn.getBlockState(pos).getBlock() instanceof BloodVessel)
		{
			TileEntityBloodVessel vessel = ((TileEntityBloodVessel)worldIn.getTileEntity(pos));
			switch (hand) {
			case MAIN_HAND:
				int tileEntityFill = player.isSneaking() ? Math.min(vessel.getCapacity() - vessel.getBloodLevel(), getNBT(player.getHeldItem(hand)).getInteger("Blood")) : 1;
				if(!vessel.isFull() && (getBloodLevel(player, hand) > 0 || player.isCreative()))
				{
					drain(player, hand, tileEntityFill);
					vessel.addBlood(tileEntityFill);
				}
				break;
			default:
				int tileEntityRemove = player.isSneaking() ? Math.min(vessel.getBloodLevel(), capacity - getNBT(player.getHeldItem(hand)).getInteger("Blood")) : 1;
				if(vessel.getBloodLevel() >= tileEntityRemove && (getBloodLevel(player, hand) < capacity || player.isCreative()))
				{
					vessel.drainBlood(tileEntityRemove);
					fill(player, hand, tileEntityRemove);
				}
				break;
			}
		}
		else if(player.isSneaking() && worldIn.getBlockState(pos.offset(facing).down()).isSideSolid(worldIn, pos, EnumFacing.UP) && (getBloodLevel(player, hand) > 0 || player.isCreative()))
		{	
			drain(player, hand, 1);
			worldIn.setBlockState(pos.offset(facing), HarshenBlocks.BLOOD_BLOCK.getDefaultState(), 3);
			worldIn.getBlockState(pos.offset(facing)).getBlock().onBlockAdded(worldIn, pos.offset(facing), worldIn.getBlockState(pos.offset(facing))); 
		}
		else if(worldIn.getBlockState(pos).getBlock() == HarshenBlocks.BLOOD_BLOCK && (getBloodLevel(player, hand) < capacity || player.isCreative()) && !player.isSneaking())
		{
			fill(player, hand, 1);
			worldIn.setBlockToAir(pos);
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	protected String[] getNames() {
		return EnumBloodCollector.getNames();
	}

	@Override
	protected List<Integer> getMetaForTab() {
		return HarshenUtils.toArray(0);
	}
}