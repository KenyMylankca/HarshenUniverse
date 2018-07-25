package kenijey.harshenuniverse.items;

import java.util.List;

import kenijey.harshenuniverse.HarshenSounds;
import kenijey.harshenuniverse.base.BaseHarshenStaff;
import kenijey.harshenuniverse.entity.EntityThrown;
import kenijey.harshenuniverse.objecthandlers.EntityThrowSpawnData;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class LightningStaff extends BaseHarshenStaff
{
	public LightningStaff()
	{
		setUnlocalizedName("lightning_staff");
		setRegistryName("lightning_staff");
		setMaxDamage(500);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.GOLD_INGOT;
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		if(count % 80 == 0)
			player.getEntityWorld().playSound((EntityPlayer) player, player.getPosition(), HarshenSounds.LIGHTNING_SPELLING, SoundCategory.PLAYERS, 3.7f, 1f);
		super.onUsingTick(player.getHeldItemMainhand(), player, 1);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 80;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation("lightning_staff0").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {return stack;}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(getMaxItemUseDuration(stack) - timeLeft < 25)
			return;
		spawnThrownEntity(worldIn, entityLiving, 7f, new LightningHit(), new EntityThrowSpawnData(0));
        stack.damageItem(1, entityLiving);
	}
	
	public static class LightningHit implements EntityThrown.HitResult
	{
		@Override
		public void onHit(EntityThrown entity, RayTraceResult result, boolean isServer) {
			if(isServer)
				entity.world.addWeatherEffect(new EntityLightningBolt(entity.world, result.hitVec.x, result.hitVec.y, result.hitVec.z, false));
		}
	}
}