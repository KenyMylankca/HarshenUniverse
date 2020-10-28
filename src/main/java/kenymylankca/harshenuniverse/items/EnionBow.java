package kenymylankca.harshenuniverse.items;

import java.util.List;

import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.base.BaseHarshenBow;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class EnionBow extends BaseHarshenBow
{
	public EnionBow()
	{
		super(HarshenSounds.LIGHTNING_HIT);
		setUnlocalizedName("enion_bow");
		setRegistryName("enion_bow");
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.REDSTONE;
	}

	@Override
	public int getMaxDamage() {
		return 1837;
	}

	@Override
	public double additionDamage() {
		return 2.0D;
	}
	
	@Override
	public float getArrowVelocity() {
		return 4.4f;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if(entity instanceof EntityLivingBase && player.getCooledAttackStrength(1) == 1)
		{
			player.playSound(HarshenSounds.LIGHTNING_HIT, 0.8f, 1.3f);
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 30));
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 45, 7));
		}
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00a73" + new TextComponentTranslation("enionbow1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}