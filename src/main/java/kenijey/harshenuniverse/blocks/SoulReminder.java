package kenijey.harshenuniverse.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class SoulReminder extends Block
{
	private World world;
	private BlockPos pos;
	private boolean isTicking;
	
	public SoulReminder()
	{
		super(Material.WEB);
		setUnlocalizedName("soul_reminder");
        setRegistryName("soul_reminder");
		setHardness(-1);
		setResistance(-1);
		setTickRandomly(true);
	}
	
	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		if(!isTicking)
			updateTick(worldIn, pos, state, random);
		super.randomTick(worldIn, pos, state, random);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, NULL_AABB);
	}
	
	@Override	//slows down while the entity in the block
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.motionX *= 0.8D;
        entityIn.motionY *= 0.8D;
        entityIn.motionZ *= 0.8D;
        entityIn.fallDistance = 0f;
        if(entityIn instanceof EntityPlayer)
        {
        	if(!((EntityPlayer) entityIn).isCreative())
        	{
        		((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200));
        		worldIn.spawnParticle(EnumParticleTypes.CLOUD, true, pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1, 0.5, 0.5, 0.5);
        		worldIn.setBlockToAir(pos);
        	}
        }
    }

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleBlockUpdate(pos, this, 10, 3);
		super.onBlockAdded(worldIn, pos, state);
	}
	
	@Override
	public boolean isFullCube(IBlockState state) 
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		isTicking = true;
		this.world = worldIn;
		this.pos = pos;
		worldIn.scheduleBlockUpdate(pos, this, 10, 3);
		if(world != null && pos != null)
			if (world instanceof WorldServer)
				((WorldServer)world).spawnParticle(EnumParticleTypes.CLOUD, false, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 7,  0.3, 0.2, 0.3, 0, new int[EnumParticleTypes.CLOUD.getArgumentCount()]);
		super.updateTick(worldIn, pos, state, rand);
	}
}