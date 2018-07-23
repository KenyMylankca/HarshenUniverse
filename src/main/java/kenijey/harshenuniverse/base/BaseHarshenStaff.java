package kenijey.harshenuniverse.base;

import kenijey.harshenuniverse.entity.EntityThrown;
import kenijey.harshenuniverse.network.HarshenNetwork;
import kenijey.harshenuniverse.network.packets.MessagePacketUpdateComplexEntity;
import kenijey.harshenuniverse.objecthandlers.EntityThrowSpawnData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class BaseHarshenStaff extends Item
{
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		boolean flag = canItemBeUsed(worldIn, playerIn, handIn);
		if(flag && getMaxItemUseDuration(playerIn.getHeldItem(handIn)) > 0)
			playerIn.setActiveHand(handIn);
		if(getMaxItemUseDuration(playerIn.getHeldItem(handIn)) < 0)
			onItemUseFinish(playerIn.getHeldItem(handIn), worldIn, playerIn);
		return new ActionResult<ItemStack>(flag ? EnumActionResult.SUCCESS : EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}
	
	@Override
	public abstract int getMaxItemUseDuration(ItemStack stack);
	
	@Override
	public abstract ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving);
	
	protected EntityThrown spawnThrownEntity(World worldIn, EntityLivingBase entityLiving, float speed, EntityThrown.HitResult hitResult, EntityThrowSpawnData spawnData)
	{
		if(worldIn.isRemote)
			return null;
		EntityThrown thrown = new EntityThrown(worldIn, entityLiving, hitResult, spawnData.isLocation ? spawnData.location : spawnData.stack);
		thrown.shoot(entityLiving, entityLiving.rotationPitch, entityLiving.rotationYaw, 0.0F, speed, 1f);
		
		thrown.setIgnoreBlocks(spawnData.ignoreBlocks);
		
		worldIn.spawnEntity(thrown);
		
		HarshenNetwork.sendToPlayersInWorld(worldIn, new MessagePacketUpdateComplexEntity(thrown.getEntityId(), thrown.serializeNBT()));
		return thrown;
	}
	
	protected boolean canItemBeUsed(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		return true;
	}
}