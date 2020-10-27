package kenymylankca.harshenuniverse.entity;

import java.io.PrintStream;
import java.io.Serializable;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseHarshenParticle;
import kenymylankca.harshenuniverse.enums.particle.EnumHarshenParticle;
import kenymylankca.harshenuniverse.objecthandlers.EntityThrowLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityThrown extends EntityThrowable
{
	private EntityThrowLocation location;
	private ItemStack stack;
	private HitResult hitResult;
	
	private boolean ignoreBlocks = false;
	
	public EntityThrown(World worldIn) {
		super(worldIn);
		hitResult = new HitResult.EmptyResult();
	}
	
	public EntityThrown(World worldIn, EntityLivingBase throwerIn, HitResult hitResult, Object object)
	{
		super(worldIn, throwerIn);
		if(object instanceof EntityThrowLocation)
			this.location = (EntityThrowLocation)object;
		else if(object instanceof ItemStack)
			this.stack = (ItemStack)object;
		else
			throw new IllegalArgumentException("Tried to register class " + object.getClass() + " in EntityThrown. Only EntityThrowLocation and ItemStack can be used");
		this.hitResult = hitResult;
	}
	
	public boolean isLocation()
	{
		return this.location != null;
	}
	
	public EntityThrown setIgnoreBlocks(boolean ignoreBlocks) {
		this.ignoreBlocks = ignoreBlocks;
		return this;
	}
	
	public EntityThrowLocation getLocation() {
		return location;
	}
	
	public ItemStack getStack() {
		return stack == null ? ItemStack.EMPTY : stack;
	}
	
	public boolean isIgnoreBlocks() {
		return ignoreBlocks;
	}

	@Override
	protected void onImpact(RayTraceResult result)
	{
		hitResult.onHit(this, result, !world.isRemote);
		if(!ignoreBlocks && !(result.entityHit == thrower))
				this.setDead();
	}
	
	@Override
	public void setDead() {
		
		if(world.isRemote)
		for(int i = 0; i < 16; i++)
			try
			{
				((BaseHarshenParticle)HarshenUniverse.commonProxy.spawnParticle(isLocation() ? EnumHarshenParticle.CAULDRON : EnumHarshenParticle.ITEM, new Vec3d(this.posX, this.posY, this.posZ),
						new Vec3d(((double)this.rand.nextFloat() - 0.5D) / 30D, ((double)this.rand.nextFloat() - 0.5D) / 30D, ((double)this.rand.nextFloat() - 0.5D) / 30D),
						3f, false, isLocation() ? location : stack)).setParticleGravity(1f);
			}
			catch (NullPointerException e) {
				continue;
			}
		super.setDead();
	}
	
	@Override
	public void onUpdate() {
		noClip = ignoreBlocks;
		super.onUpdate();
		noClip = false;
	}
	
	public interface HitResult extends Serializable {
		
		public static class EmptyResult implements HitResult, Serializable {@Override public void onHit(EntityThrown entity, RayTraceResult result, boolean isServer) {}}
		
		public void onHit(EntityThrown entity, RayTraceResult result, boolean isServer);
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.ignoreBlocks = compound.getBoolean("ignore_blocks");
		this.location = compound.getInteger("location_id") > -1 ? new EntityThrowLocation(compound.getInteger("location_id")) : null;
		this.stack = new ItemStack(compound.getCompoundTag("inner_stack"));
		Object obj = HarshenUtils.deserialize(compound.getByteArray("hit_result"));
		if(!(obj instanceof HitResult))
			new IllegalArgumentException("Object was corrupeted").printStackTrace(new PrintStream(System.out));
		else
			hitResult = (HitResult)obj;
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("ignore_blocks", this.ignoreBlocks);
		compound.setInteger("location_id", isLocation() ? location.getId() : -1);
		compound.setTag("inner_stack", (this.stack == null ? ItemStack.EMPTY : this.stack).serializeNBT());
		compound.setByteArray("hit_result", HarshenUtils.serialize(hitResult));
		return super.writeToNBT(compound);
	}
	
	@Override
	public boolean isInRangeToRender3d(double x, double y, double z) {
		return true;
	}
	
	@Override
	public boolean shouldRenderInPass(int pass) {
		return true;
	}
}
