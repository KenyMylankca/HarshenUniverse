package kenymylankca.harshenuniverse.blocks;

import java.util.Random;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.items.SoulHarsherPickaxe;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class HarshenDimensionalDoor extends BlockDoor
{
	public HarshenDimensionalDoor()
	{
		 super(Material.ROCK);
		setUnlocalizedName("harshen_dimensional_door");
		setRegistryName("harshen_dimensional_door");
        setHarvestLevel("pickaxe", 3);
        setHardness(3000);
        setResistance(3000);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{FACING,HALF,HINGE,OPEN,POWERED});
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if((playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR)? playerIn.getHeldItemOffhand() : playerIn.getHeldItemMainhand()).getItem() instanceof SoulHarsherPickaxe)
			setHardness(3);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return false;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return HarshenItems.ITEM_HARSHEN_DIMENSIONAL_DOOR;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(HarshenItems.ITEM_HARSHEN_DIMENSIONAL_DOOR);
	}
}