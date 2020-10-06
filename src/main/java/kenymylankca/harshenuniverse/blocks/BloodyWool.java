package kenymylankca.harshenuniverse.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BloodyWool extends Block
{
	public BloodyWool() 
	{
		super(Material.CARPET);
        setUnlocalizedName("bloody_wool");
        setRegistryName("bloody_wool");
        setHardness(1.0f);
        setResistance(1.0f);
        setHarvestLevel("shear", 0);
        this.blockSoundType = blockSoundType.CLOTH;
    }
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if(playerIn.getHeldItemMainhand().getItem() instanceof ItemShears)
		{
			setHardness(0.3f);
			setResistance(0.3f);
		}
	}
}