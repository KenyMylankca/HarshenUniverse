package kenymylankca.harshenuniverse.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class HarshenDimensionalFluid extends Fluid {
    public static final String NAME = "harshen_dimensional_fluid";

    public HarshenDimensionalFluid()
    {
        super(NAME, new ResourceLocation("harshenuniverse:blocks/harshen_dimensional_fluid_still"), new ResourceLocation("harshenuniverse:blocks/harshen_dimensional_fluid_flowing"));
		setViscosity(400);
		setDensity(10);
    }
}