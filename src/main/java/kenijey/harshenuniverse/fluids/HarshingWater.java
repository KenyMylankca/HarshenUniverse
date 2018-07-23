package kenijey.harshenuniverse.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class HarshingWater extends Fluid {
    
    public static final String NAME = "harshing_water";

    public HarshingWater()
    {
        super(NAME, new ResourceLocation("harshenuniverse:blocks/harshing_water_still"), new ResourceLocation("harshenuniverse:blocks/harshing_water_flowing"));
        setViscosity(1000);
		setDensity(500);
    }
}