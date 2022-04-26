package com.kenymylankca.harshenuniverse.particle;

import kenymylankca.harshenuniverse.base.BaseHarshenParticle;
import kenymylankca.harshenuniverse.proxy.ClientProxy;
import net.minecraft.world.World;

public class ParticleBlood extends BaseHarshenParticle
{
	public ParticleBlood(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn,
			double motionYIn, double motionZIn, float par14, boolean disableMoving) {
		super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn, par14, disableMoving, ClientProxy.particleTexturesLocation);
	}

	@Override
	protected int getXIndex() {
		return 7;
	}

	@Override
	protected int getYIndex() {
		return 0;
	}
}