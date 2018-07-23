package kenijey.harshenuniverse.items;

import java.util.List;
import java.util.Random;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.config.GeneralConfig;
import kenijey.harshenuniverse.damagesource.DamageSourceBleeding;
import kenijey.harshenuniverse.potions.HarshenPotions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class Gillette extends Item
{
	public Gillette()
	{
		setUnlocalizedName("gillette");
		setRegistryName("gillette");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		playerIn.addPotionEffect(new PotionEffect(HarshenPotions.potionBleeding, 100, 0));
		playerIn.attackEntityFrom(new DamageSourceBleeding(), 1f);
		if(worldIn.isAirBlock(playerIn.getPosition()) && GeneralConfig.bloodDrops && new Random().nextDouble() < GeneralConfig.bloodChance)
			worldIn.setBlockState(playerIn.getPosition(), HarshenBlocks.BLOOD_BLOCK.getDefaultState(), 3);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A74" + new TextComponentTranslation("gillette1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}