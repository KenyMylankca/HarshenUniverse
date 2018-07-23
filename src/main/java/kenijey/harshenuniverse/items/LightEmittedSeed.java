package kenijey.harshenuniverse.items;

import java.util.List;

import kenijey.harshenuniverse.HarshenBlocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class LightEmittedSeed extends ItemSeeds
{

	public LightEmittedSeed() {
		super(HarshenBlocks.CROP_OF_GLEAM, HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT);
		setUnlocalizedName("light_emitted_seed");
		setRegistryName("light_emitted_seed");	
		}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("seed1").getFormattedText());
		tooltip.add("\u00a73" + new TextComponentTranslation("seed2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		return worldIn.getBlockState(pos).getBlock() == HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT ?  super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ) : EnumActionResult.PASS;
	}
}
