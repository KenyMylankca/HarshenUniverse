package kenijey.harshenuniverse.items;

import java.util.List;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.base.BaseItemMetaData;
import kenijey.harshenuniverse.damagesource.DamageSourceBleeding;
import kenijey.harshenuniverse.potions.HarshenPotions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class Gillette extends BaseItemMetaData
{
	public Gillette()
	{
		setUnlocalizedName("gillette");
		setRegistryName("gillette");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		playerIn.addPotionEffect(new PotionEffect(HarshenPotions.potionBleeding, 80, 0));
		playerIn.attackEntityFrom(new DamageSourceBleeding(), 1f);
		playerIn.getHeldItemMainhand().setItemDamage(1);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("gillette1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	protected List<Integer> getMetaForTab() {
		return HarshenUtils.toArray(0);
	}

	@Override
	protected String[] getNames() {
		String[] names = {"clean", "bloody"};
		return names;
	}
}