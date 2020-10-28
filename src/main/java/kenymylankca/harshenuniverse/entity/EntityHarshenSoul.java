package kenymylankca.harshenuniverse.entity;

import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.interfaces.IBurnInDay;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityHarshenSoul extends EntityMob implements IBurnInDay {

	public EntityHarshenSoul(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(63d);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4d);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.3d);
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIAttackMelee(this, 0.5d, true));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityEnderman.class, 10f, 1d, 1.2d));
		this.tasks.addTask(0, new EntityAIWanderAvoidWater(this, 0.35));
		this.tasks.addTask(0, new EntityAIMoveTowardsTarget(this, 1d, 100));
		
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}
	
	@Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
    	return HarshenSounds.HARSHEN_MOB_HURT;
    }

	@Override
    protected SoundEvent getDeathSound() {
    	return HarshenSounds.HARSHEN_MOB_HURT;
    }
	
	@Override
	public void onDeath(DamageSource cause) {
		if (cause.getTrueSource() != null && cause.getTrueSource() instanceof EntityPlayer)
			((EntityPlayer) cause.getTrueSource()).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 190));
		super.onDeath(cause);
	}
}