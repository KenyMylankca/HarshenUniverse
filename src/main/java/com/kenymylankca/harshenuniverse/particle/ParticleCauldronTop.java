package com.kenymylankca.harshenuniverse.particle;

import kenymylankca.harshenuniverse.base.BaseHarshenParticle;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleCauldronTop extends BaseHarshenParticle
{
	private int fxLayer = 3;
	private IBlockState state;
	public ParticleCauldronTop(World world, double xCoordIn, double yCoordIn, double zCoordIn, float par14, ResourceLocation location) {
		super(world, xCoordIn, yCoordIn, zCoordIn, 0, 0, 0, par14, true, location);
		setCauldronTop();
	}
	
	public ParticleCauldronTop(World world, double xCoordIn, double yCoordIn, double zCoordIn, float par14, IBlockState state) {
		this(world, xCoordIn, yCoordIn, zCoordIn, par14, (ResourceLocation)null);
		fxLayer = 1;
		this.state = state;
		this.setParticleTexture(Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(state));
	}

	@Override
	public int getFXLayer() {
		return fxLayer;
	}
	
	@Override
	protected boolean isFullTexture() {
		return fxLayer == 3;
	}
	
	@Override
	protected int getXIndex() {
		return 0;
	}

	@Override
	protected int getYIndex() {
		return 0;
	}
	
	@Override
	public void onUpdate() {
		particleAge = 0;
		super.onUpdate();
	}
}