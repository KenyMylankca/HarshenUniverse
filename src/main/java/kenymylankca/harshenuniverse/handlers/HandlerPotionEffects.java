package kenymylankca.harshenuniverse.handlers;

import java.util.ArrayList;
import java.util.UUID;

import kenymylankca.harshenuniverse.HarshenPotions;
import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.config.GeneralConfig;
import kenymylankca.harshenuniverse.damagesource.DamageSourceBleeding;
import kenymylankca.harshenuniverse.damagesource.DamageSourceHarshed;
import kenymylankca.harshenuniverse.entity.EntitySoulPart;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerPotionEffects
{
	private ArrayList<EntityLivingBase> livingsWithBleedingEffect = new ArrayList<EntityLivingBase>();
	private ArrayList<EntityLivingBase> livingsWithHarshedEffect = new ArrayList<EntityLivingBase>();
	private ArrayList<EntityLivingBase> livingsWithCurealEffect = new ArrayList<EntityLivingBase>();
	
	private ArrayList<HandlerBleedingEffect> arrayBleedingEffectManager = new ArrayList<HandlerBleedingEffect>();
	private ArrayList<HandlerCurealEffect> arrayCurealEffectManager = new ArrayList<HandlerCurealEffect>();
	private ArrayList<HandlerHarshedEffect> arrayHarshedEffectManager = new ArrayList<HandlerHarshedEffect>();
	
	private ArrayList<EntityLivingBase> arrayLivingNoSoul = new ArrayList<EntityLivingBase>();
	private ArrayList<EntityLivingBase> arrayNeedingToBeCured = new ArrayList<EntityLivingBase>();

	
	@SubscribeEvent
	public void livingTick(LivingUpdateEvent event)
	{
		try
		{
			if(event.getEntityLiving() instanceof EntitySoulPart && ((EntityLiving)event.getEntityLiving()).getAttackTarget().isPotionActive(HarshenPotions.potionSoulless))
				((EntityLiving)event.getEntityLiving()).setAttackTarget(null);
		}
		catch (NullPointerException e) {
		}
		if(event.getEntityLiving().isPotionActive(HarshenPotions.potionSoulless))
		{
			if(!arrayLivingNoSoul.contains(event.getEntityLiving()))
			{
				arrayLivingNoSoul.add(event.getEntityLiving());
				IAttributeInstance attribute = event.getEntityLiving().getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				AttributeModifier modifier = new AttributeModifier(UUID.fromString("81c41407-0bb1-435d-91ca-449b8c8a0eec"), "healthTo1", -(event.getEntityLiving().getMaxHealth() - 2D), 0).setSaved(true);
				if(!attribute.hasModifier(modifier))	
					attribute.applyModifier(modifier);
			}
			if(event.getEntity().world.isRemote && !Minecraft.getMinecraft().entityRenderer.isShaderActive() && event.getEntityLiving().equals(HarshenUniverse.proxy.getPlayer()))
				Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("minecraft", "shaders/post/desaturate.json"));
		}
		else if(arrayLivingNoSoul.contains(event.getEntityLiving()))
		{
			if(event.getEntity().world.isRemote && event.getEntityLiving().equals(HarshenUniverse.proxy.getPlayer()))
			{
				arrayLivingNoSoul.remove(event.getEntityLiving());
				Minecraft.getMinecraft().entityRenderer.stopUseShader();
			}
			event.getEntityLiving().getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("81c41407-0bb1-435d-91ca-449b8c8a0eec"));
			event.getEntityLiving().setHealth(event.getEntityLiving().getHealth());
		}
		
		if(!event.getEntityLiving().world.isRemote)
		{
			String[] AllowedEntities = GeneralConfig.bleedableEntities;
			
			if(HarshenUtils.toArray(AllowedEntities).contains(event.getEntityLiving().getName().toLowerCase()) || event.getEntityLiving() instanceof EntityPlayer)
			{
				if(event.getEntityLiving().isPotionActive(HarshenPotions.potionBleeding))
				{
					if(!livingsWithBleedingEffect.contains(event.getEntityLiving()))
					{
						livingsWithBleedingEffect.add(event.getEntityLiving());
						for(PotionEffect effect : event.getEntityLiving().getActivePotionEffects())
						{
							if(effect.getPotion().equals(HarshenPotions.potionBleeding))
								arrayBleedingEffectManager.add(new HandlerBleedingEffect(event.getEntityLiving(), effect.getAmplifier()));
						}
					}
					arrayBleedingEffectManager.get(livingsWithBleedingEffect.indexOf(event.getEntityLiving())).addEffect();
				}
				else if(livingsWithBleedingEffect.contains(event.getEntityLiving()))
				{
					arrayBleedingEffectManager.remove(livingsWithBleedingEffect.indexOf(event.getEntityLiving()));
					livingsWithBleedingEffect.remove(event.getEntityLiving());
				}
			}
			
			if(event.getEntityLiving().isPotionActive(HarshenPotions.potionCureal))
			{
				if(!livingsWithCurealEffect.contains(event.getEntityLiving()))
				{
					livingsWithCurealEffect.add(event.getEntityLiving());
					for(PotionEffect effect : event.getEntityLiving().getActivePotionEffects())
					{
						if(effect.getPotion().equals(HarshenPotions.potionCureal))
							arrayCurealEffectManager.add(new HandlerCurealEffect(event.getEntityLiving(), effect.getAmplifier()));
					}
				}
				arrayCurealEffectManager.get(livingsWithCurealEffect.indexOf(event.getEntityLiving())).addEffect();
			}
			else if(livingsWithCurealEffect.contains(event.getEntityLiving()))
			{
				arrayCurealEffectManager.remove(livingsWithCurealEffect.indexOf(event.getEntityLiving()));
				livingsWithCurealEffect.remove(event.getEntityLiving());
			}
			
			if(event.getEntityLiving().isPotionActive(HarshenPotions.potionHarshed))
			{
				if(!livingsWithHarshedEffect.contains(event.getEntityLiving()))
				{
					livingsWithHarshedEffect.add(event.getEntityLiving());
					for(PotionEffect effect : event.getEntityLiving().getActivePotionEffects())
					{
						if(effect.getPotion().equals(HarshenPotions.potionHarshed))
							arrayHarshedEffectManager.add(new HandlerHarshedEffect(event.getEntityLiving(), effect.getAmplifier()));
					}
				}
				arrayHarshedEffectManager.get(livingsWithHarshedEffect.indexOf(event.getEntityLiving())).addEffect();
			}
			else if(livingsWithHarshedEffect.contains(event.getEntityLiving()))
			{
				arrayHarshedEffectManager.remove(livingsWithHarshedEffect.indexOf(event.getEntityLiving()));
				livingsWithHarshedEffect.remove(event.getEntityLiving());
			}
		}
	}
}

class HandlerBleedingEffect
{
	private int timer = 1;
	private final int level;
	private final EntityLivingBase entity;
	private BlockPos position;
	public HandlerBleedingEffect(EntityLivingBase entity, int level)
	{
		this.entity = entity;
		this.position = this.entity.getPosition();
		this.level = level;
	}

	public void addEffect()
	{
		if(timer++ >= entity.world.rand.nextInt(20) + 15)
		{
			timer = 0;
			this.entity.attackEntityFrom(new DamageSourceBleeding(), entity.world.rand.nextFloat() * (float)this.level + 1f);
		}
	}
}

class HandlerCurealEffect
{
	private final EntityLivingBase entity;
	public HandlerCurealEffect(EntityLivingBase entity, int level)
	{
		this.entity = entity;
	}

	public void addEffect()
	{
		ArrayList<Potion> potionsToRemove = new ArrayList<>();
		for(PotionEffect effect : entity.getActivePotionEffects())
			if(effect.getPotion().isBadEffect())
				potionsToRemove.add(effect.getPotion());
		
		for(Potion potion : potionsToRemove)
			entity.removePotionEffect(potion);
		
		entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("81c41407-0bb1-435d-91ca-449b8c8a0eec"));
	}
}

class HandlerHarshedEffect
{
	private int timer = 1;
	private final int level;
	private final EntityLivingBase entity;
	private BlockPos position;
	public HandlerHarshedEffect(EntityLivingBase entity, int level)
	{
		this.entity = entity;
		this.position = this.entity.getPosition();
		this.level = level;
		this.timer = (int) ((entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() - entity.getMaxHealth() + 1) * 20);
	}

	public void addEffect()
	{
		if(timer++ % 20 == 0)
		{
			entity.attackEntityFrom(new DamageSourceHarshed(), 1);
			if(entity.getMaxHealth() > 2)
			{
				IAttributeInstance attribute = entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				AttributeModifier modifier = new AttributeModifier(UUID.fromString("81c41407-0bb1-435d-91ca-449b8c8a0eec"), "drainHealth", -timer/20, 0).setSaved(true);
				
				if(attribute.hasModifier(modifier))
				{
					attribute.removeAllModifiers();
					attribute.applyModifier(modifier);
				}
				else
					attribute.applyModifier(modifier);
			}
		}
	}
}