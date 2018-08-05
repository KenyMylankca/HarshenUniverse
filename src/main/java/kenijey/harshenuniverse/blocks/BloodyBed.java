package kenijey.harshenuniverse.blocks;

import java.util.List;

import javax.annotation.Nullable;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.base.BaseHarshenFacedBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BloodyBed extends BaseHarshenFacedBlock
{
	private EntityLivingBase placer;
	private boolean occupied=false;
	
	@Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	
	public BloodyBed()
	{
		super(Material.CLOTH);
		setRegistryName("bloody_bed");
		setUnlocalizedName("bloody_bed");
		setHardness(2.0F);
		setResistance(2.0F);
		setHarvestLevel("axe", 1);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("bed1").getFormattedText());
		tooltip.add("\u00A73" + new TextComponentTranslation("bed2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!playerIn.isSneaking() && !occupied)
        {
        	playerIn.renderOffsetX = -1.8F * (float)state.getValue(FACING).getFrontOffsetX();
        	playerIn.renderOffsetZ = -1.8F * (float)state.getValue(FACING).getFrontOffsetZ();
        	playerIn.heal(2f);
        	occupied=true;
        	return true;
        }
        else
        	return false;
    }
	
	@Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		this.placer=placer;
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        EnumFacing enumfacing = placer.getHorizontalFacing().getOpposite();
		if(!(worldIn.getBlockState(pos.offset(state.getValue(FACING).getOpposite())).getBlock() instanceof BloodyBedHead) && worldIn.getBlockState(pos.offset(state.getValue(FACING).getOpposite())).getBlock().isReplaceable(worldIn, pos.offset(state.getValue(FACING).getOpposite())))
			worldIn.setBlockState(pos.offset(state.getValue(FACING).getOpposite()), HarshenBlocks.BLOODY_BED_HEAD.getDefaultState().withProperty(this.FACING, enumfacing), 3);
		else
			worldIn.destroyBlock(pos, true);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if(worldIn.getBlockState(pos.offset(state.getValue(FACING).getOpposite())).getBlock() instanceof BloodyBedHead)
			worldIn.setBlockToAir(pos.offset(state.getValue(FACING).getOpposite()));
		super.onBlockHarvested(worldIn, pos, state, player);
	}

	@Override
	 public boolean isOpaqueCube(IBlockState state)
	 {
	  return false;
	 }
	
	@Override
	 public boolean isFullCube(IBlockState state) 
	 {
	  return false;
	 }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return new AxisAlignedBB(0f, 0f, 0f, 1f, 0.6f, 1f);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0f, 0f, 0f, 1f, 0.6f, 1f);
	}
	
	@Nullable
    private EntityPlayer getPlayerInBed(World worldIn, BlockPos pos)
    {
        for (EntityPlayer entityplayer : worldIn.playerEntities)
        {
            if (entityplayer.isPlayerSleeping() && entityplayer.bedLocation.equals(pos))
            {
                return entityplayer;
            }
        }
        return null;
    }
	
	//JUMP
	@Override
	public void onLanded(World worldIn, Entity entityIn)
    {
        if (entityIn.isSneaking())
        {
            super.onLanded(worldIn, entityIn);
        }
        else if (entityIn.motionY < 0.0D)
        {
            entityIn.motionY = -entityIn.motionY * 0.6600000262260437D;

            if (!(entityIn instanceof EntityLivingBase))
            {
                entityIn.motionY *= 0.8D;
            }
        }
    }
	
	//LIGHT FALL
	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        super.onFallenUpon(worldIn, pos, entityIn, fallDistance * 0.5F);
    }
}