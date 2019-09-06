package kenijey.harshenuniverse.items;

import java.util.List;

import kenijey.harshenuniverse.HarshenSounds;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
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
		this.setMaxDamage(3175);
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
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		RayTraceResult raytraceresult = this.rayTrace(worldIn, player, true);
		BlockPos blockpos = raytraceresult.getBlockPos();
		if(player.getCooledAttackStrength(1) == 1)
		{
			if(worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER)
			{
				worldIn.setBlockState(blockpos, Blocks.ICE.getDefaultState());
				player.getHeldItem(hand).damageItem(25, player);
				worldIn.playSound(player, blockpos, HarshenSounds.FREEZING, SoundCategory.BLOCKS, 1f, 1.2f);
				player.resetCooldown();
			}
			if(worldIn.getBlockState(blockpos).getBlock() == Blocks.LAVA)
			{
				worldIn.setBlockState(blockpos, Blocks.OBSIDIAN.getDefaultState());
				player.getHeldItem(hand).damageItem(25, player);
				worldIn.playSound(player, blockpos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1f, 1.2f);
			}
		}
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}