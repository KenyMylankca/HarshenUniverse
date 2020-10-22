package kenymylankca.harshenuniverse.blocks;

import java.util.List;
import java.util.Random;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.items.SoulHarsherPickaxe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class HarshenSoulOre extends Block
{
	public HarshenSoulOre() 
	{
        super(Material.ROCK);
        setUnlocalizedName("harshen_soul_ore");
        setRegistryName("harshen_soul_ore");
        setHarvestLevel("pickaxe", 2);
		setHardness(31f);
		setResistance(100f);
		this.setLightLevel(0.35f);
    }
	private BlockPos pos;
	private World world;
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return null;
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) 
	{
		if(player.capabilities.isCreativeMode)
		{
			super.onBlockHarvested(worldIn, pos, state, player);
			return;
		}
		ItemStack item = player.inventory.getCurrentItem();
		if(item.getItem() instanceof SoulHarsherPickaxe)
		{
			if(!worldIn.isRemote)
			{
				InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT, worldIn.rand.nextInt(3) + 1));
				super.onBlockHarvested(worldIn, pos, state, player);
				player.sendMessage((ITextComponent) new TextComponentTranslation("message.success"));
			}
			else
				player.playSound(HarshenSounds.SOUL_HARSHER_SWORD_HIT, 0.35F, 0.3F);
		}
		else
		{
			player.attackEntityFrom(DamageSource.MAGIC, 21);
			if(!worldIn.isRemote)
				player.sendMessage((ITextComponent) new TextComponentTranslation("message.failed"));
		}
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return null;
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		this.pos = pos;
		this.world = worldIn;
		super.onBlockClicked(worldIn, pos, playerIn);
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public boolean addHitEffects(IBlockState state, World worldObj, RayTraceResult target, ParticleManager manager) {
		if(world != null && pos != null)
		if (world instanceof WorldServer)
			((WorldServer)world).spawnParticle(EnumParticleTypes.CLOUD, false, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 4,  0.7, 0.8, 0.6, 0, new int[EnumParticleTypes.CLOUD.getArgumentCount()]);
		return super.addHitEffects(state, worldObj, target, manager);
	}
	
	@Override
	protected boolean canSilkHarvest() {
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("ore1").getFormattedText());
		tooltip.add("\u00A73" + new TextComponentTranslation("item.soul_harsher_pickaxe.name").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}