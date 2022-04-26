package com.kenymylankca.harshenuniverse.base;

import net.minecraft.nbt.NBTTagCompound;

public abstract class BaseTileEntityHarshenSingleItemInventoryActive extends BaseTileEntityHarshenSingleItemInventory 
{
	protected boolean isActive;
	protected int activeTimer = 0;
	
	@Override
	public void update() {
		if(isActive)
		{
			activeTimer++;
			if(activeTimer > getTicksUntillDone())
			{
				finishedTicking();
				deactivate();
			}
			else if(!checkForCompletion(true))
				deactivate();
		}
		else
			activeTimer = 0;
		super.update();
	}
	
	@Override
	protected void onItemAdded() {
		checkForCompletion(false);
	}
	
	protected boolean checkForCompletion(boolean checkingUp)
	{
		return false;
	}
	
	public void deactivate()
	{
		this.isActive = false;
		this.activeTimer = 0;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public int getActiveTimer() {
		return activeTimer;
	}
	
	public void activate()
	{
		this.activeTimer = 0;
		this.isActive = true;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		isActive = compound.getBoolean("isActive");
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("isActive", isActive);
		return super.writeToNBT(nbt);
	}
	
	protected abstract int getTicksUntillDone();
	
	protected abstract void finishedTicking();
}