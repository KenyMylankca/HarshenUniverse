package kenymylankca.harshenuniverse.enums.items;

import kenymylankca.harshenuniverse.api.IIDSet;
import net.minecraft.util.IStringSerializable;

public enum EnumPontusGateSpawner implements IStringSerializable, IIDSet
{
	Normal("normal"),
	Enhanced("enhanced");
	
	private int meta;
	private String name;
	
	private EnumPontusGateSpawner(String name)
	{
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
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
		for(EnumPontusGateSpawner l : EnumPontusGateSpawner.values())
			s += l.getName() + " ";
		return s.split(" ");
	}

	@Override
	public void setId(int id) {
		this.meta = id;
	}
}