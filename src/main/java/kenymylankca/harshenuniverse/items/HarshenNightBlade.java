package kenymylankca.harshenuniverse.items;

import java.util.List;

import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.handlers.CooldownHandler;
import kenymylankca.harshenuniverse.handlers.CooldownHandler.ICooldownHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HarshenNightBlade extends ItemSword
{
	private static ToolMaterial nightBlade = EnumHelper.addToolMaterial("nightBlade", 3, 1283, 10f, 0.1f, 3);
	
	public HarshenNightBlade()
	{
		super(nightBlade);
		setUnlocalizedName("harshen_night_blade");
		setRegistryName("harshen_night_blade");
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("nightblade1").getFormattedText());
		tooltip.add("\u00A73" + new TextComponentTranslation("nightblade2").getFormattedText());
		tooltip.add("\u00A73" + TextFormatting.ITALIC + new TextComponentTranslation("nightblade3").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		ICooldownHandler cap = stack.getCapability(CooldownHandler.COOLDOWN, EnumFacing.DOWN);
		return 1 - Math.min((double)cap.getCooldown() / cap.getMaxCooldown(), 1);
	}
	
	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return 0x001f5dc1;
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		ICooldownHandler cap = stack.getCapability(CooldownHandler.COOLDOWN, EnumFacing.DOWN);
		return !(cap.getCooldown() >= cap.getMaxCooldown() || cap.getCooldown() == 0);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		ICooldownHandler cap = stack.getCapability(CooldownHandler.COOLDOWN, EnumFacing.DOWN);
		if(entity instanceof EntityLivingBase && cap.isReady())
		{
			cap.setCooldown(1);
			entity.playSound(HarshenSounds.NIGHT_BLADE_STAB, 1f, 1f);
			if(player.isPotionActive(MobEffects.INVISIBILITY))
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 27f);
			else
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 19.4f);
			
			player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 90));
		}
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		ICooldownHandler cap = stack.getCapability(CooldownHandler.COOLDOWN, EnumFacing.DOWN);
		return cap.isReady();
	}
}