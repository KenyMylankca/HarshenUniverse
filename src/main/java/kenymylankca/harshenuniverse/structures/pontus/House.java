package kenymylankca.harshenuniverse.structures.pontus;

import kenymylankca.harshenuniverse.base.HarshenStructure;
import kenymylankca.harshenuniverse.dimensions.DimensionPontus;

public class House extends HarshenStructure
{
	public House() {
		super("pontus", "house", 1f, false, DimensionPontus.DIMENSION_ID, true);
	}
	
	@Override
	public boolean canLoadAt(int dimension, int chunkX, int chunkZ) {
		return false;
	}
	
	@Override
	public boolean canSpawnOnWater() {
		return true;
	}
}