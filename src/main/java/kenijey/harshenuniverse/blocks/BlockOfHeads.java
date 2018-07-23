package kenijey.harshenuniverse.blocks;

import kenijey.harshenuniverse.base.BaseHarshenFacedBlock;
import net.minecraft.block.material.Material;

public class BlockOfHeads extends BaseHarshenFacedBlock
{	
	public BlockOfHeads()
	{
		super(Material.ROCK);
		setRegistryName("block_of_heads");
		setUnlocalizedName("block_of_heads");
		setHardness(5.0F);
		setResistance(5.0F);
	}
}