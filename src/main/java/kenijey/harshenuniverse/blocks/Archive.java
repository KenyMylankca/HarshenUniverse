package kenijey.harshenuniverse.blocks;

import java.util.List;

import kenijey.harshenuniverse.base.BaseHarshenFacedBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Archive extends BaseHarshenFacedBlock
{
	public Archive()
	{
		super(Material.WOOD);
		setRegistryName("archive");
		setUnlocalizedName("archive");
		setHardness(2.0F);
		setResistance(2.0F);
	}

	@Override
	 public boolean isOpaqueCube(IBlockState state)
	 {
	  return false;
	 }
	
	@Override
	 public boolean isFullCube(IBlockState state) 
	 {
	  return false;
	 }
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return new AxisAlignedBB(0.12f, 0f, 0.12f, 0.88f, 0.83f, 0.94f);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.12f, 0f, 0.12f, 0.88f, 0.83f, 0.94f);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A76" + new TextComponentTranslation("archive1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}