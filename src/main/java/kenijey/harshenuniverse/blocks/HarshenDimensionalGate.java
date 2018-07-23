package kenijey.harshenuniverse.blocks;

import java.util.List;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.dimensions.DimensionPontus;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenDimensionalGate;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HarshenDimensionalGate extends Block implements ITileEntityProvider
{

	public static final PropertyBool ACTIVE = PropertyBool.create("active");
	public static final PropertyBool FOREVER = PropertyBool.create("ignore_countdown");
	public static final PropertyInteger TIMER = PropertyInteger.create("seconds_left", 0, TileEntityHarshenDimensionalGate.TOTAL_TICKS / 20);

	
	public HarshenDimensionalGate() {
		super(Material.ROCK);
		setRegistryName("harshen_dimensional_gate");
		setUnlocalizedName("harshen_dimensional_gate");
		setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, false).withProperty(FOREVER, false).withProperty(TIMER, 0));
		setHardness(10f);
		setResistance(50f);
	}
	
	public void deactivate(World worldIn, BlockPos pos)
	{
		worldIn.setBlockState(pos, this.getDefaultState(), 3);
		worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_PORTAL_TRIGGER, SoundCategory.BLOCKS, 1f, 1f, false);
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(TIMER, Math.min(599, ((TileEntityHarshenDimensionalGate)worldIn.getTileEntity(pos)).getTick() / 20));
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		if(entityIn instanceof EntityLivingBase)
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0625f, 0.0625f, 0.0625f, 0.9375f, 0.9375f, 0.9375f));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 0f, 0f, 1, 0.0625, 0.0625));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 0f, 0f, 0.0625, 0.0625, 1));
		addCollisionBoxToList(pos, entityBox, collidingBoxes,  new AxisAlignedBB(0f, 0f, 0f, 0.0625, 0.0625, 1));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 0f, 0.9375f, 1f, 0.0625, 1f));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.9375f, 0f, 0f, 1f, 0.0625, 1f));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 1f, 0f, 1,0.9375f, 0.0625));
		addCollisionBoxToList(pos, entityBox, collidingBoxes,  new AxisAlignedBB(0f, 1f, 0f, 0.0625, 0.9375f, 1));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 1f, 0.9375f, 1f, 0.9375f, 1f));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.9375f, 1f, 0f, 1f, 0.9375f, 1f));
								
	}
		
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
			if(((Boolean)state.getValue(ACTIVE)).booleanValue())
			{
				Boolean goHome = playerIn.dimension == DimensionPontus.DIMENSION_ID;
				if(playerIn instanceof EntityPlayerMP)
					if(goHome) 
						HarshenUtils.transferPlayerToDimension((EntityPlayerMP) playerIn, 0, pos);
					else
						HarshenUtils.transferPlayerToDimension((EntityPlayerMP) playerIn, DimensionPontus.DIMENSION_ID, pos, getStateFromMeta(3));
			}
			else
				playerIn.sendStatusMessage(new TextComponentTranslation("block.gate.reactivate"), true);
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(ACTIVE, Boolean.valueOf(Math.floorDiv(meta, 2) == 1)).withProperty(FOREVER, meta % 2 == 1);
    }

    public int getMetaFromState(IBlockState state)
    {
        return (((Boolean)state.getValue(FOREVER)).booleanValue() ? 1 : 0) + (((Boolean)state.getValue(ACTIVE)).booleanValue() ? 0 : 2);
    }
    

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {ACTIVE, FOREVER, TIMER});
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityHarshenDimensionalGate();
	}
}