package kenijey.harshenuniverse.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NocturnalTorch extends BlockTorch
{
	public NocturnalTorch()
	{
		setRegistryName("nocturnal_torch");
		setUnlocalizedName("nocturnal_torch");
		this.setLightLevel(0.7f);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
		EnumParticleTypes particle = EnumParticleTypes.SPELL_MOB_AMBIENT;
        EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;
        double d3 = 0.22D;
        double d4 = 0.27D;

        if (enumfacing.getAxis().isHorizontal())
        {
            EnumFacing enumfacing1 = enumfacing.getOpposite();
            worldIn.spawnParticle(particle, d0 + 0.27D * (double)enumfacing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * (double)enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
        }
        else
        	worldIn.spawnParticle(particle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("nocturnaltorch1").getFormattedText());
		tooltip.add("\u00a73" + new TextComponentTranslation("nocturnaltorch2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}