package kenymylankca.harshenuniverse.enums.items;

import kenymylankca.harshenuniverse.api.IIDSet;
import net.minecraft.util.IStringSerializable;

public enum EnumBloodCollector implements IStringSerializable, IIDSet
{
	ZERO(0),
	TWO(2),
	FOUR(4),
	SIX(6),
	EIGHT(8),
	TEN(10);
	
	private int id;
	private int changeAmount;
	
	private EnumBloodCollector(int changeAmount)
	{
		this.changeAmount = changeAmount;
	}

	@Override
	public String getName() {
		return String.valueOf(id);
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getAmount()
	{
		return changeAmount;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public static String[] getNames()
	{
		String s = "";
		for(EnumBloodCollector l : EnumBloodCollector.values())
			s += l.getName() + " ";
		return s.split(" ");
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}
}
