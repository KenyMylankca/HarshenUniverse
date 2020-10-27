package kenymylankca.harshenuniverse.fluids;

import java.util.HashMap;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.fluids.blocks.HarshenDimensionalFluidBlock;
import kenymylankca.harshenuniverse.fluids.blocks.HarshingWaterBlock;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenFluids
{
	private final static HashMap<Block, String> BLOCK_MAP = new HashMap<>();
	
	public final static Fluid HARSHEN_DIMENSIONAL_FLUID = registerFluid(new HarshenDimensionalFluid());
	public final static Block HARSHEN_DIMENSIONAL_FLUID_BLOCK = registerFluidBlock(HARSHEN_DIMENSIONAL_FLUID, new HarshenDimensionalFluidBlock(), HarshenDimensionalFluid.NAME);
	
	public final static Fluid HARSHING_WATER = registerFluid(new HarshingWater());
	public final static Block HARSHING_WATER_BLOCK = registerFluidBlock(HARSHING_WATER, new HarshingWaterBlock(), HarshingWater.NAME);

	private static Fluid registerFluid(Fluid fluid)
	{
        FluidRegistry.addBucketForFluid(fluid);
        return fluid;
	}
		
	private static Block registerFluidBlock(Fluid fluid, Block fluidBlock, String name)
    {
        BLOCK_MAP.put(fluidBlock, name);
        fluid.setBlock(fluidBlock);
        return fluidBlock;
    }
	
	public static void preInit()
	{
		for(Block fluidBlock : BLOCK_MAP.keySet())
		{
			fluidBlock.setRegistryName(new ResourceLocation(HarshenUniverse.MODID, BLOCK_MAP.get(fluidBlock)));
	        ForgeRegistries.BLOCKS.register(fluidBlock);
		}
	}
	
	public static void regRenders()
	{
		for(Block fluidBlock : BLOCK_MAP.keySet())
	        HarshenUniverse.commonProxy.registerFluidBlockRendering(fluidBlock, BLOCK_MAP.get(fluidBlock));
	}
}