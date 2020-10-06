package kenymylankca.harshenuniverse.enums.items;

import kenymylankca.harshenuniverse.api.IIDSet;
import net.minecraft.util.IStringSerializable;

public enum EnumRitualStick implements IStringSerializable, IIDSet
{
	NormalCauldron(0x6E4D0E),
	AdvancedCauldron(0xE45511);
	
	private int color;
	private int meta;
	
	private EnumRitualStick(int color)
	{
		this.color = color;
	}
	
	public static int getColorFromMeta(int meta)
	{
		for(EnumRitualStick stick : EnumRitualStick.values())
			if(stick.meta == meta)
				return stick.color;
		return -1;
	}
	
	@Override
	public void setId(int id) {
		this.meta = id;
	}
	
	public int getColor() {
		return color;
	}

	@Override
	public String getName() {
		return this.name().toLowerCase();
	}

	public static String[] getNames() {
		String[] names = new String[values().length];
		for(int i = 0; i < values().length; i ++)
			names[i] = values()[i].getName();
		return names;
	}
}
