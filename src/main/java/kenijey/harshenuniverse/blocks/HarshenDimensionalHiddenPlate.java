package kenijey.harshenuniverse.blocks;

import kenijey.harshenuniverse.tileentity.TileEntityHiddenPlate;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HarshenDimensionalHiddenPlate extends BlockPressurePlate implements ITileEntityProvider
{
	public HarshenDimensionalHiddenPlate() {
		super(Material.ROCK, BlockPressurePlate.Sensitivity.MOBS);
		setUnlocalizedName("harshen_dimensional_hidden_plate");
		setRegistryName("harshen_dimensional_hidden_plate");
		setHarvestLevel("pickaxe", 3);
        setHardness(3000.0f);
        setResistance(3000.0f);
	}
	
	@Override
	protected void playClickOffSound(World worldIn, BlockPos pos) {
	}
	
	@Override
	protected void playClickOnSound(World worldIn, BlockPos color) {
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 0, 0.1, 0);
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
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityHiddenPlate();
	}
}