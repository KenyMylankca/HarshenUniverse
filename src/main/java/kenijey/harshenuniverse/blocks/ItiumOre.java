package kenijey.harshenuniverse.blocks;

import java.util.List;
import java.util.Random;

import kenijey.harshenuniverse.HarshenItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItiumOre extends Block
{
	public ItiumOre() 
	{
        super(Material.ROCK);
        setUnlocalizedName("itium_ore");
        setRegistryName("itium_ore");
        setHarvestLevel("pickaxe", 2);
		setHardness(21f);
		setResistance(31f);
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
		return HarshenItems.ITIUM;
	}
	
	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		return new Random().nextInt(2) + fortune;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("itiumore1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}