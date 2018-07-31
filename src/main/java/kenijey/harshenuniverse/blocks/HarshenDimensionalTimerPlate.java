package kenijey.harshenuniverse.blocks;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.base.BaseHarshenBlockBreakableWithSHPickaxe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenDimensionalTimerPlate extends BaseHarshenBlockBreakableWithSHPickaxe
{
	public HarshenDimensionalTimerPlate() {
        setUnlocalizedName("harshen_dimensional_timer_plate");
        setRegistryName("harshen_dimensional_timer_plate");		
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if(entityIn instanceof EntityPlayer)
			worldIn.setBlockState(pos, HarshenBlocks.HARSHEN_DIMENSIONAL_TIMER_PLATE_ACTIVE.getDefaultState(), 3);
		super.onEntityWalk(worldIn, pos, entityIn);
	}
}