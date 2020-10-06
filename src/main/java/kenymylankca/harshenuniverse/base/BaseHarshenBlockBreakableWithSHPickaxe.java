package kenymylankca.harshenuniverse.base;

import kenymylankca.harshenuniverse.items.SoulHarsherPickaxe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BaseHarshenBlockBreakableWithSHPickaxe extends Block
{
	public BaseHarshenBlockBreakableWithSHPickaxe()
	{
		super(Material.ROCK);
		setHarvestLevel("pickaxe", 3);
		blockSoundType = blockSoundType.STONE;
		setHardness(3000);
		setResistance(3000);
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if((playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR)? playerIn.getHeldItemOffhand() : playerIn.getHeldItemMainhand()).getItem() instanceof SoulHarsherPickaxe)
			setHardness(3);
		else
			setHardness(3000);
			
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) 
	{
		if(player.capabilities.isCreativeMode)
		{
			super.onBlockHarvested(worldIn, pos, state, player);
			return;
		}
		if(!((player.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR)? player.getHeldItemOffhand() : player.getHeldItemMainhand()).getItem() instanceof SoulHarsherPickaxe))
		{
			player.attackEntityFrom(DamageSource.MAGIC, 21);
			if(!worldIn.isRemote)
			{
				player.sendMessage((ITextComponent) new TextComponentTranslation("message.broken"));
			}
		}
	}
}