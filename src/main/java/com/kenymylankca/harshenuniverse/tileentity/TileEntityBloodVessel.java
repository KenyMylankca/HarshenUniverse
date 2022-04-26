package com.kenymylankca.harshenuniverse.tileentity;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseHarshenTileEntity;
import kenymylankca.harshenuniverse.blocks.BloodVessel;
import kenymylankca.harshenuniverse.enums.particle.EnumHarshenParticle;
import kenymylankca.harshenuniverse.interfaces.IHudDisplay;
import kenymylankca.harshenuniverse.network.HarshenNetwork;
import kenymylankca.harshenuniverse.network.packets.MessagePacketTileEntityBloodPlacerUpdated;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.Vec3d;

public class TileEntityBloodVessel extends BaseHarshenTileEntity implements IHudDisplay, ITickable
{
	private int bloodLevel = 0;
	private static final int capacity = 100;
		
	@Override
	public String getText() {
		return String.valueOf(bloodLevel) + "/" + String.valueOf(capacity);
	}
	
	public void addBlood(int blood)
	{
		if(capacity - bloodLevel < blood)
		{
			bloodLevel = capacity;
			HarshenUtils.splashBlood(pos, world, 1, blood - (capacity - bloodLevel));
		}
		else
			bloodLevel += blood;
		markDirty();
		HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketTileEntityBloodPlacerUpdated(pos, bloodLevel));
	}
	
	public void drainBlood(int blood)
	{
		bloodLevel -= blood;
		markDirty();
		HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketTileEntityBloodPlacerUpdated(pos, bloodLevel));
	}
	
	public int getBloodLevel()
	{
		return bloodLevel;
	}
	
	public int getCapacity()
	{
		return capacity;
	}
	
	public void setBloodLevel(int level)
	{
		bloodLevel = level;
	}
	
	public boolean isFull()
	{
		return bloodLevel == capacity;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		bloodLevel = compound.getInteger("BloodLevel");
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("BloodLevel", bloodLevel);
		return super.writeToNBT(nbt);
	}
	
	@Override
	public void update() {
		for(int i = 0; i < bloodLevel / (capacity / 10); i++)
			for(int x = 0; x < 3; x++)
				for(int z = 0; z < 3; z++)
					HarshenUniverse.commonProxy.spawnParticle(EnumHarshenParticle.BLOOD, new Vec3d(this.pos).addVector((0.15d*x) + 0.35d, (Float.valueOf(i * (capacity / 10)) / Float.valueOf(capacity)) * 0.7f, (0.15d*z) + 0.35d), Vec3d.ZERO, 1f, true);
		if(BloodVessel.updateMap.containsKey(pos))
		{
			world.setBlockState(pos, world.getBlockState(pos).getBlock().getStateFromMeta(BloodVessel.updateMap.get(pos)), 16);
			BloodVessel.updateMap.remove(pos);
			((TileEntityBloodVessel)world.getTileEntity(pos)).bloodLevel = bloodLevel;
		}
	}
}