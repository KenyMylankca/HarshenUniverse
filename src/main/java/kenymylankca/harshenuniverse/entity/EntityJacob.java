package kenymylankca.harshenuniverse.entity;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.damagesource.DamageSourceJacobDraining;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityJacob extends EntityMob
{
	private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.<Integer>createKey(EntityJacob.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> MOVING = EntityDataManager.<Boolean>createKey(EntityJacob.class, DataSerializers.BOOLEAN);
	private EntityLivingBase targetedEntity;
	protected float clientSideTailAnimation;
    protected float clientSideTailAnimationO;
    protected float clientSideTailAnimationSpeed;
	private int clientSideAttackTime;
	private int healingCounter = 0;
	private int drainingCounter = 0;
	private int drainDuration = 120;
	private boolean draining = false;
	
	public EntityJacob(World worldIn)
	{
		super(worldIn);
		this.experienceValue = 966;
		this.clientSideTailAnimation = this.rand.nextFloat();
        this.clientSideTailAnimationO = this.clientSideTailAnimation;
	}
	
	@Override
	protected void initEntityAI()
	{
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.4d, true));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 1.4d, 80));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 9.0F));
		this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0D));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(111);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3d);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(3d);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4d);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.45d);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8d);
		this.isImmuneToFire = false;
	}
	
	@Override
	public void onLivingUpdate()
	{
		healingCounter++;
		if(healingCounter > 23)
		{
			healingCounter=0;
			this.heal(2.1f);
		}
		
		if(this.getAttackTarget() instanceof EntityPlayer && !this.isSwingInProgress && rand.nextFloat() < 0.19F && healingCounter > 22 && !draining)
			if(!((EntityPlayer)this.getAttackTarget()).isCreative())
			{
				draining = true;
				this.world.playSound(null, this.getPosition(), HarshenSounds.JACOB_DRAINING, SoundCategory.NEUTRAL, 1F, this.rand.nextFloat() / 2 + 0.5F);
			}
		
		if(draining)
		{
			drainingCounter++;
			this.motionX=0;
			this.motionY=0;
			this.motionZ=0;
			
			EntityPlayer player = (EntityPlayer) this.getAttackTarget();
			Vec3d vec3d = this.getLook(0);
			
			for(float i = 1; i<15; i++)
				Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, false,
						 (player.posX - i/2 * vec3d.x),
						 (player.posY + 1D) + vec3d.y,
						 (player.posZ - i/2 * vec3d.z),
						 0.1D, 0.1D, 0.1D);
			
			if(!HarshenUtils.hasJaguarArmorSet(player))
			{
				if(drainingCounter % 10 == 0)
				{
					player.attackEntityFrom(new DamageSourceJacobDraining(), 1);
					this.heal(1);
				}
			}
			
			if(drainingCounter > drainDuration || player.getDistanceSqToCenter(this.getPosition()) > 60)
			{
				draining=false;
				drainingCounter=0;
			}
		}
		super.onLivingUpdate();
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata)
	{
		this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(HarshenItems.SOUL_HARSHER_SPADE));
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));
		return livingdata;
	}
    
    @Override
    protected SoundEvent getAmbientSound() {
    	return HarshenSounds.JACOB_AMBIENT;
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
    	return HarshenSounds.JACOB_AMBIENT;
    }
    
    @Override
    protected SoundEvent getDeathSound() {
    	return HarshenSounds.JACOB_DEATH;
    }
}