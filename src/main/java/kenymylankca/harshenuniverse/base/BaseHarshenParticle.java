package kenymylankca.harshenuniverse.base;

import kenymylankca.harshenuniverse.objecthandlers.EntityThrowLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public abstract class BaseHarshenParticle extends Particle
{
	private boolean disableMoving;
	private ResourceLocation location;
	
	public void kill()
	{
		this.isExpired = true;
	}
	
	protected abstract int getXIndex();
	protected abstract int getYIndex();
	
	private final double rX, rY;
	
    public BaseHarshenParticle(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn, float par14, boolean disableMoving, ResourceLocation location)
    {
        super(world, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
        this.particleTextureIndexX = getXIndex();
        this.particleTextureIndexY = getYIndex();
        this.motionX *= 0.10000000149011612D;
        this.motionY *= 0.10000000149011612D;
        this.motionZ *= 0.10000000149011612D;
        this.motionX += motionXIn;
        this.motionY += motionYIn;
        this.motionZ += motionZIn;
        this.particleScale *= 0.75F;
        this.particleScale *= par14;
        this.particleMaxAge = (int)((8.0D / (Math.random() * 0.8D + 0.2D)) * 8);
        this.particleMaxAge = (int)(this.particleMaxAge * par14);
        this.particleAge = (particleMaxAge / 2) + (particleMaxAge / 2) * world.rand.nextInt(7);
        this.particleAlpha = 1.0F;
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.canCollide = false;
        this.disableMoving = disableMoving;
        this.location = location;
        rX = rand.nextDouble() * 5d;
        rY = rand.nextDouble() * 5d;
    }
    
	public BaseHarshenParticle(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn, float par14, boolean disableMoving2) 
	{
		this(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn, par14, disableMoving2, null);
	}
	
	private boolean isCauldronTop;
	
	public void setCauldronTop()
	{
		isCauldronTop = true;
	}
	
	public void setLocation(ResourceLocation location) {
		this.location = location;
	}
	
	@Override
    public int getFXLayer()
    {
        return 3;
    }
	
	protected boolean shouldRender()
	{
		return true;
	}
	
	public BaseHarshenParticle setParticleGravity(float particleGravity) {
		this.particleGravity = particleGravity;
		this.canCollide = particleGravity > 0;
		return this;
	}
	
	public float getParticleGravity() {
		return particleGravity;
	}
		
    @Override
    public void renderParticle(BufferBuilder buffer, Entity entity, float partialTicks, float rotX, float rotZ, float rotYZ, float rotXY, float rotXZ)
    {		
    	if(!shouldRender())
    		return;
    	if(isCauldronTop)
    	{
    		rotX = 0f;
            rotXY = 1f;
            rotZ = 1.2246469E-16f;
            rotYZ = 1f;
            rotXZ =  1.2246469E-16f;
    	}
    	if(getFXLayer() != 3)
    	{
    		if(!isCauldronTop)
    		{
    			float scaleMultiplier = (this.particleAge + partialTicks) / this.particleMaxAge * 32.0F;
                scaleMultiplier = MathHelper.clamp(scaleMultiplier, 0.0F, 1.0F);
                this.particleScale = this.particleScale * scaleMultiplier;
    		}
            this.particleTextureIndexX = getXIndex();
            this.particleTextureIndexY = getYIndex();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            float f = ((float)this.particleTextureIndexX + this.particleTextureJitterX / 4.0F) / 16.0F;
            float f1 = f + 0.015609375F;
            float f2 = ((float)this.particleTextureIndexY + this.particleTextureJitterY / 4.0F) / 16.0F;
            float f3 = f2 + 0.015609375F;
            float f4 = directScale > 0 ? directScale : (0.1F * (isCauldronTop ? 3.15f : this.particleScale));
            if (this.particleTexture != null)
            {
            	f = this.particleTexture.getInterpolatedU((double)(this.particleTextureJitterX / 4.0F * 16.0F));
                f1 = this.particleTexture.getInterpolatedU((double)((this.particleTextureJitterX + 1.0F) / 4.0F * 16.0F));
                f2 = this.particleTexture.getInterpolatedV((double)(this.particleTextureJitterY / 4.0F * 16.0F));
                f3 = this.particleTexture.getInterpolatedV((double)((this.particleTextureJitterY + 1.0F) / 4.0F * 16.0F));
                
            	if(isCauldronTop)
            	{
            		f = this.particleTexture.getMinU();
                    f1 = this.particleTexture.getMaxU();
                    f2 = this.particleTexture.getMinV();
                    f3 = this.particleTexture.getMaxV();
            	}
            }
            
            float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
            float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
            float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
            int i = this.getBrightnessForRender(partialTicks);
            int j = i >> 16 & 65535;
            int k = i & 65535;
            buffer.pos((double)(f5 - rotX * f4 - rotXY * f4), (double)(f6 - rotZ * f4), (double)(f7 - rotYZ * f4 - rotXZ * f4)).tex((double)f, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
            buffer.pos((double)(f5 - rotX * f4 + rotXY * f4), (double)(f6 + rotZ * f4), (double)(f7 - rotYZ * f4 + rotXZ * f4)).tex((double)f, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
            buffer.pos((double)(f5 + rotX * f4 + rotXY * f4), (double)(f6 + rotZ * f4), (double)(f7 + rotYZ * f4 + rotXZ * f4)).tex((double)f1, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
            buffer.pos((double)(f5 + rotX * f4 - rotXY * f4), (double)(f6 - rotZ * f4), (double)(f7 + rotYZ * f4 - rotXZ * f4)).tex((double)f1, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
            return;
    	}
        GlStateManager.disableLighting();
        float f3 =(float)((this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX) - (Minecraft.getMinecraft().player.motionX / 2f));
        float f4 = (float)((this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY) - (Minecraft.getMinecraft().player.motionY / 2f));
        float f5 = (float)((this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ) - (Minecraft.getMinecraft().player.motionZ / 2f));
        int i = this.getBrightnessForRender(partialTicks);
        int j11 = i >> 16 & 65535;
        int k11 = i & 65535;
        float size = directScale > 0 ? directScale : (0.1F * (isCauldronTop ? 3.15f : this.particleScale));
        Minecraft.getMinecraft().getTextureManager().bindTexture(location);
        float k = (float)this.particleTextureIndexX / 16.0F;
        float k1 = isFullTexture() ? 1 : k + 0.0624375F;
        float k2 = (float)this.particleTextureIndexY / 16.0F;
        float k3 = isFullTexture() ? 1 : k2 + 0.0624375F;
        double[] t = {k1, k3, k1, k2, k, k2, k, k3};
        if(location instanceof EntityThrowLocation)
        {
        	int o = ((EntityThrowLocation)location).getId();
        	float fi = (float)(o % 4 * 16 + rX) / 64.0F;
            float fi1 = (float)(o % 4 * 16 + 16 - rX) / 64.0F;
            float fi2 = (float)(o / 4 * 16 + rY) / 64.0F;
            float fi3 = (float)(o / 4 * 16 + 16 - rY) / 64.0F;
            double[] t1 = {fi, fi3, fi1, fi3, fi1, fi2, fi, fi2};
            t = t1;
        }
        float f6 = MathHelper.clamp( world.getLight(new BlockPos(posX, posY, posZ)) / 16f, 0.3f, 1f);
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos((double)(f3 - rotX * size - rotXY * size), (double)(f4 - rotZ * size), (double)(f5 - rotYZ * size - rotXZ * size)).tex(t[0], t[1]).color(f6, f6, f6, this.particleAlpha).endVertex();
        buffer.pos((double)(f3 - rotX * size + rotXY * size), (double)(f4 + rotZ * size), (double)(f5 - rotYZ * size + rotXZ * size)).tex(t[2], t[3]).color(f6, f6, f6, this.particleAlpha).endVertex();
        buffer.pos((double)(f3 + rotX * size + rotXY * size), (double)(f4 + rotZ * size), (double)(f5 + rotYZ * size + rotXZ * size)).tex(t[4], t[5]).color(f6, f6, f6, this.particleAlpha).endVertex();
        buffer.pos((double)(f3 + rotX * size - rotXY * size), (double)(f4 - rotZ * size), (double)(f5 + rotYZ * size - rotXZ * size)).tex(t[6], t[7]).color(f6, f6, f6, this.particleAlpha).endVertex();
        Tessellator.getInstance().draw();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
    }
    
    protected boolean isFullTexture() 
    {
    	return false;
	}
    
    float directScale = -1f;
    
    public BaseHarshenParticle setDirectScale(float directScale) {
		this.directScale = directScale;
		return this;
	}
    
    @Override
    public int getBrightnessForRender(float p_189214_1_)
    {
    	BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
        return this.world.isBlockLoaded(blockpos) ? getLightCombonation(blockpos.add(0, 1, 0), 0) : 0;
    }
    
    private int getLightCombonation(BlockPos pos, int lightValue)
    {
    	int i = world.getLightFromNeighborsFor(EnumSkyBlock.SKY, pos);
        int j = world.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, pos);

        if (j < lightValue)
        {
            j = lightValue;
        }
        return i << 20 | j << 4;
    }
    
    @Override
    public void onUpdate()
    {
    	this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (particleAge++ >= particleMaxAge)
        {
            this.setExpired();
        }

        this.particleTextureIndexX = 7 - particleAge * 8 / particleMaxAge;
        if(disableMoving)
    	{
        	motionX = 0;
        	motionY = 0;
        	motionZ = 0;
        	if(world.isAirBlock(new BlockPos(posX, posY, posZ)))
        		this.setExpired();
    	}
        this.motionY -= 0.04D * (double)this.particleGravity;
        this.move(motionX, motionY, motionZ);
        if (posY == prevPosY)
        {
            motionX *= 1.1D;
            motionZ *= 1.1D;
        }
        motionX *= 0.9599999785423279D;
        motionY *= 0.9599999785423279D;
        motionZ *= 0.9599999785423279D;

        if (this.onGround)
        {
            motionX *= 0.699999988079071D;
            motionZ *= 0.699999988079071D;
        }
    }   
}