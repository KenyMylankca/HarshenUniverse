package com.kenymylankca.harshenuniverse.particle;

import kenymylankca.harshenuniverse.base.BaseHarshenParticle;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleCauldron extends BaseHarshenParticle
{
	private int fxLayer;
	private IBlockState state;
	
    public ParticleCauldron(World world, ResourceLocation location, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn, float par14, boolean disableMoving)
    {
        super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn, par14, disableMoving, location);
        fxLayer = 3;
    }
    
    public ParticleCauldron(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn, float par14, boolean disableMoving, IBlockState state) {
    	this(world, (ResourceLocation)null, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn, par14, disableMoving);
    	fxLayer = 1;
		this.state = state;
		this.setParticleTexture(Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(state));
	}
    
    @Override
    public int getFXLayer() {
    	return fxLayer;
    }

	@Override
	protected int getXIndex() {
		return 0;
	}
	
	@Override
	protected int getYIndex() {
		return 0;
	}
}