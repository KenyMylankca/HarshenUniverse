package kenymylankca.harshenuniverse.fluids.blocks;

import java.util.ArrayList;
import java.util.List;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.HarshenPotions;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseFluidBlock;
import kenymylankca.harshenuniverse.fluids.HarshenFluids;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
	
	@Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
    	List<Entity> playersWithin = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new  AxisAlignedBB(pos, pos.add(1, 1, 1)));
		if(!playersWithin.isEmpty())
			for(Object entity: playersWithin.toArray())
			{
				if(!((EntityLivingBase)entity).isInWater())
					continue;
				
				if(entity instanceof EntityPlayer)
					if(((EntityPlayer)entity).capabilities.isCreativeMode || HarshenUtils.hasJaguarArmorSet((EntityPlayer) entity))
						continue;
				
				for(PotionEffect effect : getPotions())
					((EntityLivingBase)entity).addPotionEffect(effect);
			}
    }
}