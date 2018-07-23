package kenijey.harshenuniverse.enums.items;

import kenijey.harshenuniverse.api.IIDSet;
import net.minecraft.util.IStringSerializable;

public enum EnumPontusGateSpawnerParts implements IStringSerializable, IIDSet
{
	Part1(),
	Part2(),
	Part3();
	
	private int meta;

	@Override
	public String getName() {
		return String.valueOf(this.meta + 1);
	}
	
	public int getMeta()
	{
		return this.meta;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public static String[] getNames()
	{
		String s = "";
		for(EnumPontusGateSpawnerParts l : EnumPontusGateSpawnerParts.values())
			s += l.getName() + " ";
		return s.split(" ");
	}

	@Override
	public void setId(int id) {
		this.meta = id;
	}
}
