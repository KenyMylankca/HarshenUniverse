package kenymylankca.harshenuniverse.entity;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.entity.AI.AIEntityWanderNoWater;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntitySoullessKnight extends EntityMob
{
	public EntitySoullessKnight(World worldIn) {
		super(worldIn);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIAttackMelee(this, 1d, true));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityEnderman.class, 50f, 1d, 2d));
		this.tasks.addTask(0, new AIEntityWanderNoWater(this, 1d, 0));
		this.tasks.addTask(0, new EntityAIMoveTowardsTarget(this, 1d, 100));
		this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityPigZombie.class}));
		
		this.experienceValue = 50;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(18d);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(6d);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4d);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3d);
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		setItemStackToSlot(this.isLeftHanded() ? EntityEquipmentSlot.OFFHAND : EntityEquipmentSlot.MAINHAND, new ItemStack(HarshenItems.PROPS, 1, 0));
		try {
			setItemStackToSlot(this.isLeftHanded() ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND, new ItemStack(JsonToNBT.getTagFromJson("{id:\"minecraft:shield\",Count:1b,tag:{BlockEntityTag:{Patterns:[{Pattern:\"ss\",Color:6},{Pattern:\"flo\",Color:1}],Base:8}},Damage:0s}")));
		} catch (NBTException e) {
			e.printStackTrace();
		}
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));
        this.setLeftHanded(false);
		return livingdata;
	}
    
    @Override
    protected SoundEvent getAmbientSound() {
    	return null;
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
    	return HarshenSounds.HARSHEN_MOB_HURT;
    }
    
    @Override
    protected SoundEvent getDeathSound() {
    	return HarshenSounds.HARSHEN_MOB_HURT;
    }
}