package kenijey.harshenuniverse.blocks;

import kenijey.harshenuniverse.base.BaseHarshenFacedBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BloodyBedHead extends BaseHarshenFacedBlock
{
	@Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	public BloodyBedHead()
	{
		super(Material.CLOTH);
		setRegistryName("bloody_bed_head");
		setUnlocalizedName("bloody_bed");
		setHardness(2F);
		setResistance(2F);
		setHarvestLevel("axe", 1);
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
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return new AxisAlignedBB(0f, 0f, 0f, 1f, 0.6f, 1f);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0f, 0f, 0f, 1f, 0.6f, 1f);
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
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if(worldIn.getBlockState(pos.offset(state.getValue(FACING))).getBlock() instanceof BloodyBed)
		{
			worldIn.getBlockState(pos.offset(state.getValue(FACING))).getBlock().onBlockHarvested(worldIn, pos.offset(state.getValue(FACING)), worldIn.getBlockState(pos.offset(state.getValue(FACING))), player);
			worldIn.destroyBlock(pos.offset(state.getValue(FACING)), !player.isCreative());
		}
	}
}