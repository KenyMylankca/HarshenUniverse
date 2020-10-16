package kenymylankca.harshenuniverse.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import kenymylankca.harshenuniverse.tileentity.TileEntityBloodVessel;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BloodVessel extends Block implements ITileEntityProvider
{
	public static PropertyBool NODE = PropertyBool.create("node_active");
	private static HashMap<BlockPos, Boolean> creativeBreakMap = new HashMap<>();
	public static HashMap<BlockPos, Integer> updateMap = new HashMap<>();
	
	public BloodVessel() {
		super(Material.GLASS);
		setRegistryName("blood_vessel");
		setUnlocalizedName("blood_vessel");
		setDefaultState(this.blockState.getBaseState().withProperty(NODE, false));
		setHardness(5.0F);
		setResistance(5.0F);
		this.setSoundType(SoundType.GLASS);
	}
	
	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		if(world.getBlockState(pos.up()).getBlock() instanceof BloodFactory && !world.getBlockState(pos).getValue(NODE))
			updateMap.put(pos, 1);
		else if(!(world.getBlockState(pos.up()).getBlock() instanceof BloodFactory) && world.getBlockState(pos).getValue(NODE))
			updateMap.put(pos, 0);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return new AxisAlignedBB(0f, 0f, 0f, 1f, 0.83f, 1f);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.12f, 0f, 0.12f, 0.88f, 0.83f, 0.88f);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBloodVessel();
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		((TileEntityBloodVessel)worldIn.getTileEntity(pos)).setBloodLevel(stack.serializeNBT().getCompoundTag("tag").getInteger("BloodLevel"));
		checkForUpdate(worldIn, pos, state);
	}
	
	public void checkForUpdate(World worldIn, BlockPos pos, IBlockState state)
	{
		if(worldIn.getBlockState(pos.up()).getBlock() instanceof BloodFactory && !state.getValue(NODE))
			setState(worldIn, pos, getStateFromMeta(1));
		else if(!(worldIn.getBlockState(pos.up()).getBlock() instanceof BloodFactory) && state.getValue(NODE))
			setState(worldIn, pos, getStateFromMeta(0));
	}
	
	private void setState(World worldIn, BlockPos pos, IBlockState state)
	{
		int amount = ((TileEntityBloodVessel)worldIn.getTileEntity(pos)).getBloodLevel();
		worldIn.setBlockState(pos, state, 16);
		((TileEntityBloodVessel)worldIn.getTileEntity(pos)).setBloodLevel(amount);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityBloodVessel te = (TileEntityBloodVessel) worldIn.getTileEntity(pos);
		int amount = te.getBloodLevel();
		int max = te.capacity;
		worldIn.removeTileEntity(pos);
		ItemStack stack = new ItemStack(this);
		if(amount != 0)
		{
			NBTTagCompound nbttagcompound = new NBTTagCompound();
	        nbttagcompound.setInteger("BloodLevel", amount);
	        stack.setTagCompound(nbttagcompound);
	        stack.setStackDisplayName("§r" + getLocalizedName() + " [" + amount + "/" + max + "]");
		}
		if(!creativeBreakMap.containsKey(pos) || !creativeBreakMap.get(pos))
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack));
		creativeBreakMap.remove(pos);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		creativeBreakMap.put(pos, player.capabilities.isCreativeMode);
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("vessel1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(NODE, meta==1);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(NODE) ? 1 : 0;
	}
	
	public BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {NODE});
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
}