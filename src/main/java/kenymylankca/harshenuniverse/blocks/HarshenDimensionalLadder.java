package kenymylankca.harshenuniverse.blocks;

import kenymylankca.harshenuniverse.items.DarkEwydoen;
import net.minecraft.block.BlockLadder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenDimensionalLadder extends BlockLadder
{
	public HarshenDimensionalLadder()
	{
        setUnlocalizedName("harshen_dimensional_ladder");
        setRegistryName("harshen_dimensional_ladder");
        setHarvestLevel("axe", 3);
        blockSoundType = blockSoundType.LADDER;
        setHardness(3000);
        setResistance(3000);
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if((playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR)? playerIn.getHeldItemOffhand() : playerIn.getHeldItemMainhand()).getItem() instanceof DarkEwydoen)
			setHardness(3);
		else
			setHardness(3000);
	}
}