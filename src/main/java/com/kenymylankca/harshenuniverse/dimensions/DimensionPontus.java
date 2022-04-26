package com.kenymylankca.harshenuniverse.dimensions;

import kenymylankca.harshenuniverse.config.IdConfig;
import kenymylankca.harshenuniverse.dimensions.pontus.PontusWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class DimensionPontus 
{
	public static final int DIMENSION_ID= IdConfig.PontusDimension;
	public static final String DIM_NAME = "Pontus Dimension";
	public static final DimensionType PONTUS_DIMENSION = DimensionType.register("pontus", "_pontus", DIMENSION_ID, PontusWorldProvider.class, false);
			
	public static void mainRegistry()
	{
		DimensionManager.registerDimension(DIMENSION_ID, DimensionPontus.PONTUS_DIMENSION);
	}
	
	public static boolean isTentDimension(World world)
	{
		return isTentDimension(world.provider.getDimension());
	}
	
	public static boolean isTentDimension(int id)
	{
		return id == DimensionPontus.DIMENSION_ID;
	}
}