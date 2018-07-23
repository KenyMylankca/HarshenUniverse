package kenijey.harshenuniverse.blocks;

import java.util.HashMap;
import java.util.Random;

import kenijey.harshenuniverse.base.BaseBlockHarshenSingleInventory;
import kenijey.harshenuniverse.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenDisplayBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenDisplayBlock extends BaseBlockHarshenSingleInventory
{
	private static HashMap<BlockPos, Boolean> creativeBreakMap = new HashMap<>();

	public HarshenDisplayBlock() {
		super(Material.ROCK);
		setRegistryName("harshen_display_block");
		setUnlocalizedName("harshen_display_block");
		setHardness(15.0F);
		setResistance(15.0F);
		this.setLightLevel(0.4f);
	}

	@Override
	public BaseTileEntityHarshenSingleItemInventory getTile() {
		return new TileEntityHarshenDisplayBlock();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return playerIn.capabilities.isCreativeMode ? super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ) : false;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	protected boolean isBreakNBT(ItemStack stack) {
		return true;
	}
}