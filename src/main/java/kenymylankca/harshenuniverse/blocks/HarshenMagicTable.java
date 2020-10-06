package kenymylankca.harshenuniverse.blocks;

import java.util.List;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.handlers.GuiHandler;
import kenymylankca.harshenuniverse.tileentity.TileEntityHarshenMagicTable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class HarshenMagicTable extends Block implements ITileEntityProvider
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	
	public HarshenMagicTable()
	{
        super(Material.WOOD);
		setRegistryName("harshen_magic_table");
		setUnlocalizedName("harshen_magic_table");
		setHardness(5.0F);
		setResistance(5.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}	
	
	@Override
	 public boolean isOpaqueCube(IBlockState state)
	 {
	  return false;
	 }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		playerIn.openGui(HarshenUniverse.instance, GuiHandler.MAGICTABLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		for(ItemStack stack : ((TileEntityHarshenMagicTable)worldIn.getTileEntity(pos)).getHandler().getStacks())
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	}
	
	@Override
	 public boolean isFullCube(IBlockState state) 
	 {
		return false;
	 }
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("magic_table1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override	
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityHarshenMagicTable();
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