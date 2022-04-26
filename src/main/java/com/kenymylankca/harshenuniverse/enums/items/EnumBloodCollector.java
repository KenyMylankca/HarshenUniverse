package com.kenymylankca.harshenuniverse.enums.items;

import kenymylankca.harshenuniverse.api.IIDSet;
import kenymylankca.harshenuniverse.items.BloodCollector;
import net.minecraft.util.IStringSerializable;

public enum EnumBloodCollector implements IStringSerializable, IIDSet
{
	STEP0((getCapacity() / 5 ) * 0),
	STEP1((getCapacity() / 5 ) * 1),
	STEP2((getCapacity() / 5 ) * 2),
	STEP3((getCapacity() / 5 ) * 3),
	STEP4((getCapacity() / 5 ) * 4),
	STEP5((getCapacity() / 5 ) * 5);
	
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
	
	private static int getCapacity()
	{
		return BloodCollector.getCapacity();
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