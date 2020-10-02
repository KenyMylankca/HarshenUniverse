package kenijey.harshenuniverse.blocks;

import kenijey.harshenuniverse.items.SoulHarsherPickaxe;
import net.minecraft.block.BlockLadder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenDimensionalLadder extends BlockLadder
{
	public HarshenDimensionalLadder() {
		setTickRandomly(true);
        setUnlocalizedName("harshen_dimensional_ladder");
        setRegistryName("harshen_dimensional_ladder");
        setHarvestLevel("axe", 3);
        blockSoundType = blockSoundType.LADDER;
        setHardness(3000.0f);
        setResistance(3000.0f);
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if((playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR)? playerIn.getHeldItemOffhand() : playerIn.getHeldItemMainhand()).getItem() instanceof SoulHarsherPickaxe)
		{
			setHardness(3f);
			setResistance(3f);
		}
	}
}