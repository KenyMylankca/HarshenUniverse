package kenymylankca.harshenuniverse.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import kenymylankca.harshenuniverse.tileentity.TileEntityHereticCauldron;
import kenymylankca.harshenuniverse.tileentity.TileEntityHereticCauldronTop;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HereticCauldronTop extends Block implements ITileEntityProvider	
{
	public HereticCauldronTop() {
		super(Material.IRON);
		setRegistryName("heretic_cauldron_top");
		setUnlocalizedName("heretic_cauldron");
		setHardness(10.0F);
		setResistance(10.0F);
	}
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
    {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, NULL_AABB);
    }

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return new AxisAlignedBB(0.3, 0, 0.3, 0.7, 0.6, 0.7);
    }
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(!(worldIn.getBlockState(pos.down()).getBlock() instanceof HereticCauldron) && worldIn.getBlockState(pos.down()).getBlock().isReplaceable(worldIn, pos.up()))
			worldIn.setBlockToAir(pos);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if(worldIn.getBlockState(pos.down()).getBlock() instanceof HereticCauldron)
		{
			worldIn.getBlockState(pos.down()).getBlock().onBlockHarvested(worldIn, pos.down(), worldIn.getBlockState(pos.down()), player);
			worldIn.destroyBlock(pos.down(), !player.isCreative());
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(worldIn.getTileEntity(pos.down()) instanceof TileEntityHereticCauldron)
			return ((HereticCauldron)worldIn.getBlockState(pos.down()).getBlock()).onBlockActivated(worldIn, pos.down(), worldIn.getBlockState(pos.down()), playerIn, hand, facing, hitX, hitY, hitZ);
		return false;
	}
	
	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
    protected boolean canSilkHarvest()
    {
        return false;
    }
	
	 @Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityHereticCauldronTop();
	}
}