package com.kenymylankca.harshenuniverse.interfaces;

public interface IBloodSupply 
{
	/**
	 * The amount of blood that will be generated from this per second.
	 */
	public int getAmountPerSecond();
	
	/**
	 * You can set this to -1 if you want to make the supply infinite.
	 */
	public int ticksUntillUsed();
}