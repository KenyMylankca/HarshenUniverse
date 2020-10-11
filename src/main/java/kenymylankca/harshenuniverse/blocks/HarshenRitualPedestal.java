package kenymylankca.harshenuniverse.blocks;

import kenymylankca.harshenuniverse.base.BaseBlockHarshenSingleInventory;
import kenymylankca.harshenuniverse.base.BaseTileEntityHarshenSingleItemInventory;
import kenymylankca.harshenuniverse.tileentity.TileEntityHarshenRitualPedestal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HarshenRitualPedestal extends BaseBlockHarshenSingleInventory
{
	public HarshenRitualPedestal() {
		super(Material.ROCK);
		setRegistryName("harshen_ritual_pedestal");
		setUnlocalizedName("harshen_ritual_pedestal");
		setHardness(5.0F);
		setResistance(5.0F);
	}
	
	@Override
	public BaseTileEntityHarshenSingleItemInventory getTile() {
		return new TileEntityHarshenRitualPedestal();
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return new AxisAlignedBB(0.25f, 0f, 0.25f, 0.50f, 0.875f, 0.50f);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25f, 0f, 0.25f, 0.75f, 0.875f, 0.75f);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return ((TileEntityHarshenRitualPedestal)worldIn.getTileEntity(pos)).isActive() ? true : super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
}