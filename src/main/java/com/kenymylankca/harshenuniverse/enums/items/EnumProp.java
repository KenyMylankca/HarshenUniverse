package com.kenymylankca.harshenuniverse.enums.items;

import kenymylankca.harshenuniverse.api.IIDSet;
import net.minecraft.util.IStringSerializable;

public enum EnumProp implements IStringSerializable, IIDSet
{
	KnightSword("knight_sword");
	
	private int meta;
	private String name;
	
	private EnumProp(String name)
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
		for(EnumProp l : EnumProp.values())
			s += l.getName() + " ";
		return s.split(" ");
	}

	@Override
	public void setId(int id) {
		this.meta = id;
	}
}