package kenymylankca.harshenuniverse.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.api.CauldronLiquid;
import kenymylankca.harshenuniverse.base.BaseBlockHarshenSingleInventory;
import kenymylankca.harshenuniverse.base.BaseTileEntityHarshenSingleItemInventory;
import kenymylankca.harshenuniverse.items.GlassContainer;
import kenymylankca.harshenuniverse.tileentity.TileEntityHereticCauldron;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HereticCauldron extends BaseBlockHarshenSingleInventory
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	protected static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
	protected static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_WALL_BASE = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1D, 0.2D, 1D);

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_BASE);
    }
	 
	@Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	
	public HereticCauldron() {
		super(Material.IRON);
		setRegistryName("heretic_cauldron");
		setUnlocalizedName("heretic_cauldron");
		setHardness(5.0F);
		setResistance(5.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setSoundType(SoundType.METAL);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.06f, 0f, 0.06f, 0.94f, 0.95f, 0.94f);
	}
	
	@Override
	public BaseTileEntityHarshenSingleItemInventory getTile() {
		return new TileEntityHereticCauldron();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		boolean flag = getTile(worldIn, pos).onActivated(playerIn);
		boolean flag2 =  flag ? flag : super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitZ, hitZ, hitZ);
		return flag2;
    }
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(!(worldIn.getBlockState(pos.up()).getBlock() instanceof HereticCauldronTop) && worldIn.getBlockState(pos.up()).getBlock().isReplaceable(worldIn, pos.up()))
			worldIn.setBlockState(pos.up(), HarshenBlocks.HERETIC_CAULDRON_TOP.getDefaultState(), 3);
		else
			worldIn.destroyBlock(pos, true);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		Minecraft.getMinecraft().getSoundHandler().stop(HarshenSounds.HERETIC_RITUAL.getSoundName().toString(), SoundCategory.BLOCKS);
		Minecraft.getMinecraft().getSoundHandler().stop(HarshenSounds.HERETIC_CAULDRON_BLENDING.getSoundName().toString(), SoundCategory.BLOCKS);
		if(worldIn.getBlockState(pos.up()).getBlock() instanceof HereticCauldronTop)
			worldIn.setBlockToAir(pos.up());
		((TileEntityHereticCauldron)worldIn.getTileEntity(pos)).killRitual();
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }
	
	@Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return getTile(worldIn, pos).getLevel();
    }
	
	private TileEntityHereticCauldron getTile(World worldIn, BlockPos pos)
	{
		return ((TileEntityHereticCauldron)worldIn.getTileEntity(pos));
	}
	
	private TileEntityHereticCauldron getTile(IBlockAccess worldIn, BlockPos pos)
	{
		return ((TileEntityHereticCauldron)worldIn.getTileEntity(pos));
	}
	
	@Override
	protected boolean isBreakNBT(ItemStack stack) {
		return true;
	}
	
	@Override
	protected void addNBT(ItemStack stack, NBTTagCompound nbt, World worldIn, BlockPos pos) {
		if(getTile(worldIn, pos).getLevel() == 0)
		{
			stack.setTagCompound(null);
			return;
		}
		nbt.setString("FluidValue", getTile(worldIn, pos).getFluid().getName());
		nbt.setInteger("FluidLevel", getTile(worldIn, pos).getLevel());
	}
	
	@Override
	protected String extraName(NBTTagCompound nbt, boolean isItem) {
		return nbt.getInteger("FluidLevel") == 0 ? "" : 
			(isItem? " & " : "") +  GlassContainer.getGlassContaining(CauldronLiquid.getFromName(nbt.getString("FluidValue")));
	}
	
	@Override
	protected void readNBT(BaseTileEntityHarshenSingleItemInventory tileEntity, ItemStack stack)
	{
		if(!hasKey(stack, "FluidValue"))
			return;
		((TileEntityHereticCauldron)tileEntity).setFluid(CauldronLiquid.getFromName(stack.getTagCompound().getString("FluidValue")));
		((TileEntityHereticCauldron)tileEntity).setLevel(stack.getTagCompound().getInteger("FluidLevel"));
	}
	
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
	
	@Override
    public IBlockState getStateFromMeta(int meta) {
    	for(EnumFacing facing : EnumFacing.HORIZONTALS)
    		if(facing.getHorizontalIndex() == meta)
    			return this.getDefaultState().withProperty(FACING, facing);
    	return this.getDefaultState();
    }
	
	@Override
    public int getMetaFromState(IBlockState state) {
    	return state.getValue(FACING).getHorizontalIndex();
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
}