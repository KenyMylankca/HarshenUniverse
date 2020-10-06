package kenymylankca.harshenuniverse.enums.items;

import net.minecraft.util.IStringSerializable;

public enum EnumRitualCrystal implements IStringSerializable
{
	PASSIVE("passive", false),
	ACTIVE("active", true);
	
	private boolean active;
	private String name;
	
	private EnumRitualCrystal(String name, boolean active)
	{
		this.name = name;
		this.active = active;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public boolean isActive()
	{
		return this.active;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public static String[] getNames()
	{
		String s = "";
		for(EnumRitualCrystal l : EnumRitualCrystal.values())
			s += l.getName() + " ";
		return s.split(" ");
	}
}