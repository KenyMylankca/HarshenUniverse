package kenijey.harshenuniverse.blocks;

import kenijey.harshenuniverse.items.SoulHarsherPickaxe;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenDimensionalGlass extends BlockGlass
{
	public HarshenDimensionalGlass() {
		super(Material.GLASS, false);
		setUnlocalizedName("harshen_dimensional_glass");
		setRegistryName("harshen_dimensional_glass");
		setHarvestLevel("pickaxe", 3);
		blockSoundType = blockSoundType.GLASS;
        setHardness(3000);
        setResistance(3000);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if((playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR)? playerIn.getHeldItemOffhand() : playerIn.getHeldItemMainhand()).getItem() instanceof SoulHarsherPickaxe)
			setHardness(3);
		else
			setHardness(3000);
	}
}