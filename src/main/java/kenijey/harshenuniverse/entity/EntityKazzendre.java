package kenijey.harshenuniverse.entity;

import kenijey.harshenuniverse.HarshenSounds;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityKazzendre extends EntityMob
{
	public EntityKazzendre(World worldIn) {
		super(worldIn);
		
		this.experienceValue = 500;
		this.isImmuneToFire = true;
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAIAttackMelee(this, 1d, true));
		this.tasks.addTask(0, new EntityAIMoveTowardsTarget(this, 1d, 100));
		this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(380);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4d);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(4d);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5d);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4d);
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata)
	{
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));
		return livingdata;
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