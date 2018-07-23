package kenijey.harshenuniverse.blocks;

import kenijey.harshenuniverse.base.BaseBlockHarshenSingleInventory;
import kenijey.harshenuniverse.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshenuniverse.damagesource.DamageSourceBleeding;
import kenijey.harshenuniverse.tileentity.TileEntityBloodFactory;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BloodFactory extends BaseBlockHarshenSingleInventory
{
	public BloodFactory() {
		super(Material.ROCK);
		setRegistryName("blood_factory");
		setUnlocalizedName("blood_factory");
	}

	@Override
	public BaseTileEntityHarshenSingleItemInventory getTile() {
		return new TileEntityBloodFactory();
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return null;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.2f, 0f, 0.2f, 0.8f, 0.9f, 0.8f);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if(worldIn.getBlockState(pos.down()).getBlock() instanceof BloodVessel)
			((BloodVessel)worldIn.getBlockState(pos.down()).getBlock()).checkForUpdate(worldIn, pos.down(), worldIn.getBlockState(pos.down()));
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	
	@Override	//slows down while the entity in the block
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.motionX *= 0.5D;
        entityIn.motionY *= 0.5D;
        entityIn.motionZ *= 0.5D;
        entityIn.fallDistance = 0f;
        entityIn.attackEntityFrom(new DamageSourceBleeding(), 0.8f);
    }
}