package kenijey.harshenuniverse.tileentity;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.base.BaseHarshenTileEntity;
import kenijey.harshenuniverse.blocks.BloodVessel;
import kenijey.harshenuniverse.enums.particle.EnumHarshenParticle;
import kenijey.harshenuniverse.interfaces.IHudDisplay;
import kenijey.harshenuniverse.network.HarshenNetwork;
import kenijey.harshenuniverse.network.packets.MessagePacketTileEntityBloodPlacerUpdated;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class TileEntityBloodVessel extends BaseHarshenTileEntity implements IHudDisplay, ITickable
{
	private int bloodLevel = 0;
	public static final int maxLevel = 50;
		
	public void change(int amount)
	{
		bloodLevel = MathHelper.clamp(bloodLevel += amount, 0, maxLevel);
		markDirty();
		HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketTileEntityBloodPlacerUpdated(pos, bloodLevel));
	}
	
	public boolean canRemove(int amount)
	{
		return bloodLevel - amount >= 0;
	}
	
	public boolean canAdd(int amount)
	{
		return bloodLevel + amount <= maxLevel;
	}
		
	@Override
	public String getText() {
		return String.valueOf(bloodLevel) + "/" + String.valueOf(maxLevel);
	}

	public int getPossibleAdd()
	{
		return maxLevel - bloodLevel;
	}
	
	public void setBloodLevel(int bloodLevel) {
		this.bloodLevel = bloodLevel;
	}
	
	public int getPossibleRemove()
	{
		return bloodLevel;
	}
	
	public int getMax()
	{
		return this.maxLevel;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.bloodLevel = compound.getInteger("BloodLevel");
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("BloodLevel", this.bloodLevel);
		return super.writeToNBT(nbt);
	}
	
	
	@Override
	public void update() {
		for(int i = 0; i < this.bloodLevel / (maxLevel / 10); i++)
			for(int x = 0; x < 3; x++)
				for(int z = 0; z < 3; z++)
					HarshenUniverse.proxy.spawnParticle(EnumHarshenParticle.BLOOD, new Vec3d(this.pos).addVector((0.15d*x) + 0.35d, (Float.valueOf(i * (maxLevel / 10)) / Float.valueOf(this.maxLevel)) * 0.7f, (0.15d*z) + 0.35d), Vec3d.ZERO, 1f, true);
		if(BloodVessel.updateMap.containsKey(pos))
		{
			world.setBlockState(pos, world.getBlockState(pos).getBlock().getStateFromMeta(BloodVessel.updateMap.get(pos)), 16);
			BloodVessel.updateMap.remove(pos);
			((TileEntityBloodVessel)world.getTileEntity(pos)).setBloodLevel(this.bloodLevel);
		}
	}
}
