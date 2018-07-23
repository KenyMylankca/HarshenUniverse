package kenijey.harshenuniverse.base;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BaseHarshenLeaves extends Block
{

	public BaseHarshenLeaves() {
		super(Material.LEAVES);
		setRegistryName(getName());
		setUnlocalizedName(getName());
		this.setSoundType(SoundType.PLANT);
		this.setHardness(0.2F);
        this.setLightOpacity(1);
	}
	
	protected abstract String getName();
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public String getHarvestTool(IBlockState state) {
		return "shears";
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return  BlockRenderLayer.CUTOUT_MIPPED;
    }
	
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return true;
    }
}