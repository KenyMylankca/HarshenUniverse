package kenijey.harshenuniverse.blocks;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.items.SoulHarsherPickaxe;
import net.minecraft.block.BlockStairs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenDimensionalStairs extends BlockStairs
{
	public HarshenDimensionalStairs() {
		super(HarshenBlocks.HARSHEN_DIMENSIONAL_STONE.getDefaultState());
		setUnlocalizedName("harshen_dimensional_stairs");
		setRegistryName("harshen_dimensional_stairs");
		setHarvestLevel("pickaxe", 3);
		useNeighborBrightness = true;
		setHardness(3000);
		setResistance(3000);
	}

	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if((playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR)? playerIn.getHeldItemOffhand() : playerIn.getHeldItemMainhand()).getItem() instanceof SoulHarsherPickaxe)
			setHardness(3);
	}
}