package kenymylankca.harshenuniverse.entity;

import kenymylankca.harshenuniverse.HarshenSounds;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityKazzendre extends EntityMob
{
	public EntityKazzendre(World worldIn) {
		super(worldIn);
		
		this.experienceValue = 800;
		this.isImmuneToFire = true;
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1d, true));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1d, 100));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 9.0F));
		this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0D));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(340);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3d);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(2d);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4d);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4d);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.2d);
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata)
	{
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));
		return livingdata;
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn)
	{
		if(this.rand.nextFloat() < 0.01 && this.isSwingInProgress && !entityIn.isCreative())
		{
			entityIn.attackEntityFrom(new DamageSource("kazzendre"), 8);
			this.heal(12);
			this.world.playSound(null, this.getPosition(), HarshenSounds.SOUL_HARSHER_SWORD_HIT, SoundCategory.MASTER, 1, (this.rand.nextFloat() + 1F)/2);
		}
		super.onCollideWithPlayer(entityIn);
	}
    
    @Override
    protected SoundEvent getAmbientSound() {
    	return HarshenSounds.KAZZENDRE_AMBIENT;
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
    	return HarshenSounds.KAZZENDRE_HURT;
    }
    
    @Override
    protected SoundEvent getDeathSound() {
    	return HarshenSounds.KAZZENDRE_AMBIENT;
    }
}