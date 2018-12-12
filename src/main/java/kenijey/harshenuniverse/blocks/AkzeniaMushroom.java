package kenijey.harshenuniverse.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class AkzeniaMushroom extends Block
{
	public static PropertyInteger MUSHROOMS = PropertyInteger.create("mushrooms", 0, 4);
	private BlockPos pos;
	private World world;
	
	public AkzeniaMushroom() {
		super(Material.PLANTS);
		setRegistryName("akzenia_mushroom");
		setUnlocalizedName("akzenia_mushroom");
		setHardness(0.5F);
		setResistance(0.5F);
		this.setSoundType(SoundType.CLOTH);
		setTickRandomly(true);
	}
	
	public BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {MUSHROOMS});
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, NULL_AABB);
	}
	
	@Override	//slows down while the entity in the block
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.motionX *= 0.9D;
        entityIn.motionY *= 0.9D;
        entityIn.motionZ *= 0.9D;
        entityIn.fallDistance = 0f;
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.15f, 0f, 0.15f, 0.85f, 0.13f, 0.85f);
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		this.pos = pos;
		this.world = worldIn;
		super.onBlockClicked(worldIn, pos, playerIn);
	}
	
	@Override
	public boolean addHitEffects(IBlockState state, World worldObj, RayTraceResult target, ParticleManager manager) {
		if(world != null && pos != null)
		if (world instanceof WorldServer)
			((WorldServer)world).spawnParticle(EnumParticleTypes.PORTAL, false, pos.getX(), pos.getY(), pos.getZ(), 4,  0.7, 0.8, 0.6, 0, new int[EnumParticleTypes.PORTAL.getArgumentCount()]);
		return super.addHitEffects(state, world, target, manager);
	}

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if(rand.nextInt(5) == 0)
		switch (rand.nextInt(111))
		{
		case 11:
			if(worldIn.isSideSolid(pos.east().down(), EnumFacing.UP) && worldIn.isAirBlock(pos.east()))
				worldIn.setBlockState(pos.east(), this.getDefaultState());
		case 22:
			if(worldIn.isSideSolid(pos.west().down(), EnumFacing.UP) && worldIn.isAirBlock(pos.west()))
				worldIn.setBlockState(pos.west(), this.getDefaultState());
		case 33:
			if(worldIn.isSideSolid(pos.north().down(), EnumFacing.UP) && worldIn.isAirBlock(pos.north()))
				worldIn.setBlockState(pos.north(), this.getDefaultState());
		case 44:
			if(worldIn.isSideSolid(pos.south().down(), EnumFacing.UP) && worldIn.isAirBlock(pos.south()))
				worldIn.setBlockState(pos.south(), this.getDefaultState());
		default: break;
		}
		
		int count=0;
		if(worldIn.getBlockState(pos.north()).getBlock() instanceof AkzeniaMushroom)
			count++;
		if(worldIn.getBlockState(pos.west()).getBlock() instanceof AkzeniaMushroom)
			count++;
		if(worldIn.getBlockState(pos.east()).getBlock() instanceof AkzeniaMushroom)
			count++;
		if(worldIn.getBlockState(pos.south()).getBlock() instanceof AkzeniaMushroom)
			count++;
		worldIn.setBlockState(pos, state.withProperty(MUSHROOMS, count));
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(!worldIn.isSideSolid(pos.down(), EnumFacing.UP))
			worldIn.destroyBlock(pos, true);
		else
			worldIn.setBlockState(pos, state.withProperty(MUSHROOMS, 0));
		super.onBlockAdded(worldIn, pos, state);
	}
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        this.checkForDrop(worldIn, pos, state);
    }

    private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isSideSolid(pos.down(), EnumFacing.UP))
        {
            worldIn.destroyBlock(pos, true);
            return false;
        }
        else
        {
            return true;
        }
    }
	
	@Override
	public int getMetaFromState(IBlockState state) {
		if(state.getValue(MUSHROOMS) == 0)
			return 0;
		else if(state.getValue(MUSHROOMS) == 1)
			return 1;
		else if(state.getValue(MUSHROOMS) == 2)
			return 2;
		else if(state.getValue(MUSHROOMS) == 3)
			return 3;
		else
			return 4;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta == 0)
			return this.getDefaultState().withProperty(MUSHROOMS, 0);
		else if(meta == 1)
			return this.getDefaultState().withProperty(MUSHROOMS, 1);
		else if(meta == 2)
			return this.getDefaultState().withProperty(MUSHROOMS, 2);
		else if(meta == 3)
			return this.getDefaultState().withProperty(MUSHROOMS, 3);
		else
			return this.getDefaultState().withProperty(MUSHROOMS, 4);
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) 
	{
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}