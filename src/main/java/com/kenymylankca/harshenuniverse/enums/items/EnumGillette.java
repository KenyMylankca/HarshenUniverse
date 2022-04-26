package com.kenymylankca.harshenuniverse.enums.items;

import net.minecraft.util.IStringSerializable;

public enum EnumGillette implements IStringSerializable
{
	CLEAN("clean"),
	BLOODY("bloody");
	
	private String name;
	
	private EnumGillette(String name)
	{
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public static String[] getNames()
	{
		String s = "";
		for(EnumGillette l : EnumGillette.values())
			s += l.getName() + " ";
		return s.split(" ");
	}
}