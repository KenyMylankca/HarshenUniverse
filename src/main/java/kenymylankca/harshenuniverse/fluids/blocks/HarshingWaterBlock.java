package kenymylankca.harshenuniverse.fluids.blocks;

import java.util.ArrayList;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.HarshenPotions;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseFluidBlock;
import kenymylankca.harshenuniverse.fluids.HarshenFluids;
import net.minecraft.block.Block;
import net.minecraft.potion.PotionEffect;

public class HarshingWaterBlock extends BaseFluidBlock
{
	public HarshingWaterBlock() {
		super(HarshenFluids.HARSHING_WATER);
	}

	@Override
	protected ArrayList<PotionEffect> getPotions() {
		return new ArrayList<PotionEffect>(HarshenUtils.toArray(new PotionEffect(HarshenPotions.potionHarshed, 250, 1)));
	}

	@Override
	protected Block getBlockWhenSourceHit() {
		return HarshenBlocks.HARSHEN_DIMENSIONAL_ROCK;
	}

	@Override
	protected Block getBlockWhenOtherHit() {
		return HarshenBlocks.HARSHEN_DIMENSIONAL_ROCK;
	}
}