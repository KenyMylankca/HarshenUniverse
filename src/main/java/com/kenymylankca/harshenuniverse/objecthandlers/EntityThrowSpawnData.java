package com.kenymylankca.harshenuniverse.objecthandlers;

import net.minecraft.item.ItemStack;

public class EntityThrowSpawnData 
{
	public boolean isLocation;
	
	public ItemStack stack;
	public EntityThrowLocation location;
	
	public boolean ignoreBlocks;
	
	public EntityThrowSpawnData(int locationID) {
		this.isLocation = true;
		this.location = new EntityThrowLocation(locationID);
	}
	
	public EntityThrowSpawnData(ItemStack stack) {
		this.stack = stack;
	}
	
	public EntityThrowSpawnData setIgnoreBlocks(boolean ignoreBlocks) {
		this.ignoreBlocks = ignoreBlocks;
		return this;
	}
}
