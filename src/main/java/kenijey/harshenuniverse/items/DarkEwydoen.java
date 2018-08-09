package kenijey.harshenuniverse.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class DarkEwydoen extends ItemAxe
{
	public DarkEwydoen()
	{
		super(ToolMaterial.DIAMOND);
		setUnlocalizedName("dark_ewydoen");
		setRegistryName("dark_ewydoen");
		this.attackDamage=12f;
		this.setMaxDamage(4175);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if(entity instanceof EntityLivingBase)
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 50, 1));
		if(player.isPotionActive(MobEffects.SPEED))
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 1));
		else
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 120));
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("darkewydoen1").getFormattedText());
		tooltip.add("\u00A73" + new TextComponentTranslation("darkewydoen2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}