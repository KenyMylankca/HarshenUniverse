package kenijey.harshenuniverse.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class PontusEmeraldOre extends Block
{
	public PontusEmeraldOre() 
	{
        super(Material.ROCK);
        setUnlocalizedName("pontus_emerald_ore");
        setRegistryName("pontus_emerald_ore");
        setHarvestLevel("pickaxe", 2);
		setHardness(31f);
		setResistance(100f);
    }

	private BlockPos pos;
	private World world;
	
	
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
			((WorldServer)world).spawnParticle(EnumParticleTypes.SMOKE_NORMAL, false, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 6,  0.5, 0.5, 0.6, 0, new int[EnumParticleTypes.SMOKE_NORMAL.getArgumentCount()]);
		return super.addHitEffects(state, worldObj, target, manager);
	}
	
	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.EMERALD;
	}
	
	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		return new Random().nextInt(2) + fortune;
	}
}