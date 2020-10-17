package kenymylankca.harshenuniverse.items;

import java.util.List;

import javax.annotation.Nullable;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenPotions;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseHarshenStaff;
import kenymylankca.harshenuniverse.entity.EntityThrown;
import kenymylankca.harshenuniverse.objecthandlers.EntityThrowSpawnData;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SoulRipperBow extends BaseHarshenStaff
{
	public SoulRipperBow()
	{
		setRegistryName("soul_ripper_bow");
		setUnlocalizedName("soul_ripper_bow");
		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.getActiveItemStack().getItem() instanceof SoulRipperBow? (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 15F : 0.0F;
            }
        });
		this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("soulripperbow1").getFormattedText());
		tooltip.add("\u00A73" + new TextComponentTranslation("soulripperbow2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public int getMaxDamage() {
		return 1988;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == HarshenItems.HARSHEN_SOUL_FRAGMENT;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {return stack;}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if(getMaxItemUseDuration(stack) - timeLeft < 7)
			return;
		float f = ItemBow.getArrowVelocity(getMaxItemUseDuration(stack) - timeLeft);
		spawnThrownEntity(worldIn, entityLiving, 4 * f, new HarshenSoulRipperArrow(f), new EntityThrowSpawnData(2).setIgnoreBlocks(false));
        worldIn.playSound((EntityPlayer)null, entityLiving.posX, entityLiving.posY, entityLiving.posZ, HarshenSounds.RIPPER_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		stack.damageItem(1, entityLiving);
	}
	
	public static class HarshenSoulRipperArrow implements EntityThrown.HitResult
	{
		private final float power;
		
		public HarshenSoulRipperArrow(float power) {
			this.power = power;
		}

		@Override
		public void onHit(EntityThrown orb, RayTraceResult result, boolean isServer)
		{
			if(result.entityHit instanceof EntityLivingBase && result.entityHit != orb.getThrower())
			{
				EntityLiving entityLiving = (EntityLiving) result.entityHit;
				orb.setDead();
				if(HarshenUtils.hasJaguarArmorSet(entityLiving))
					entityLiving.playSound(HarshenSounds.JAGUAR_DEFENSE, 1f, 1f);

				else if(entityLiving.attackEntityFrom(new EntityDamageSourceIndirect("soul_ripper_bow", orb, orb.getThrower() == null ? orb : orb.getThrower()).setProjectile(), power * 13f))
				{
					orb.playSound(SoundEvents.ENTITY_ENDERMITE_HURT, 0F, 1.2F / (itemRand.nextFloat() * 0.2F + 0.9F));
					entityLiving.addPotionEffect(new PotionEffect(HarshenPotions.potionHarshed, 100));
				}
			}
		}
	}
}