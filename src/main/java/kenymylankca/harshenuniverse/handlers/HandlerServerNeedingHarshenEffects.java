package kenymylankca.harshenuniverse.handlers;

import java.util.ArrayList;
import java.util.UUID;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseBloodyBed;
import kenymylankca.harshenuniverse.config.AccessoryConfig;
import kenymylankca.harshenuniverse.config.GeneralConfig;
import kenymylankca.harshenuniverse.damagesource.DamageSourceReflectorPendant;
import kenymylankca.harshenuniverse.handlers.CooldownHandler.ICooldownHandler;
import kenymylankca.harshenuniverse.items.HarshenNightBlade;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class HandlerServerNeedingHarshenEffects
{
	int tick=0;
	int trusttimer=666;
	int nocturnetimer=0;
	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		
		if(event.getSource().getDamageType() == "player")
		{
			EntityPlayer attackerPlayer = (EntityPlayer) event.getSource().getTrueSource();
			
			if(attackerPlayer.getHeldItemMainhand().isEmpty() && HarshenUtils.hasAccessoryTimes(attackerPlayer, HarshenItems.PUNCHY_RING) > 0)
			{
				event.setAmount((float) (event.getAmount() + AccessoryConfig.punchyRingAttackDamage*HarshenUtils.hasAccessoryTimes(attackerPlayer, HarshenItems.PUNCHY_RING)));
				HarshenUtils.damageAllOccuringItems(attackerPlayer, HarshenItems.PUNCHY_RING, HarshenUtils.hasAccessoryTimes(attackerPlayer, HarshenItems.PUNCHY_RING));
			}
				
			if(HarshenUtils.hasAccessoryTimes(attackerPlayer, HarshenItems.FEARRING) > 0)
				entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 150));
			
			if(!(event.getEntityLiving() instanceof EntityPlayer))
			{
				if(HarshenUtils.hasBloodyTorch(attackerPlayer))
					trusttimer=0;
			}
		}
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer attackedPlayer = (EntityPlayer) event.getEntityLiving();
			
			if(HarshenUtils.hasAccessoryTimes(attackedPlayer, HarshenItems.ZOMBI_PENDANT) > 0 && event.getSource().getTrueSource() instanceof EntityZombie)
				event.setAmount(1);
			
			if(HarshenUtils.hasAccessoryTimes(attackedPlayer, HarshenItems.FEARRING) > 0)
				attackedPlayer.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 20));
			
			if(HarshenUtils.hasAccessoryTimes(attackedPlayer, HarshenItems.ELYTRA_PENDANT) > 0)
				if(HarshenUtils.toArray(DamageSource.FLY_INTO_WALL, DamageSource.FALL).contains(event.getSource())
						&& event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA)
					event.setCanceled(true);
			
			if(HarshenUtils.hasAccessoryTimes(attackedPlayer, HarshenItems.REFLECTOR_PENDANT) > 0 && event.getSource().getTrueSource() instanceof EntityLivingBase)
			{
				event.getSource().getTrueSource().attackEntityFrom(new DamageSourceReflectorPendant(), event.getAmount()/2f);
				HarshenUtils.damageFirstOccuringItem(attackedPlayer, HarshenItems.REFLECTOR_PENDANT);
			}
			
			if(HarshenUtils.hasAccessoryTimes(attackedPlayer, HarshenItems.SOUL_SHIELD) > 0 && !attackedPlayer.isCreative())
			{
				HarshenUtils.damageFirstOccuringItem(attackedPlayer, HarshenItems.SOUL_SHIELD, (int) event.getAmount());
				event.setAmount(0);
			}
		}
	}
	
	@SubscribeEvent
    public void onEntityTargetedEvent(LivingSetAttackTargetEvent event)
	{
		if(event.getTarget() instanceof EntityPlayer && !(event.getEntityLiving() instanceof EntityPlayer))
		{
			if(HarshenUtils.hasBloodyTorch((EntityPlayer) event.getTarget()) && trusttimer > 666)
				((EntityLiving) event.getEntityLiving()).setAttackTarget(null);
		}
	}
	
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event)
	{
		if(trusttimer < 700) trusttimer++;
		if(tick>600) tick =1; else tick++;
		for(EntityPlayer player : event.world.playerEntities)
		{
			if(HarshenUtils.isInBlocksDistanceOrHolding(player, HarshenBlocks.NOCTURNAL_TORCH, GeneralConfig.nocturnalDistance))
			{
				nocturnetimer++;
				if(nocturnetimer > 180)
					player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 205));
				if(nocturnetimer > 500)
					player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 402));
				if(nocturnetimer > 800)
					player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 702));
				if(nocturnetimer > 1300)
					player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1202));
				if(nocturnetimer > 2000)
					player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 2002));
			}
			else if(HarshenUtils.isInBlocksDistanceOrHolding(player, HarshenBlocks.NOCTURNE_BLOOM, GeneralConfig.nocturnalDistance))
			{
				nocturnetimer++;
				if(nocturnetimer > 200)
					player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 90));
				if(nocturnetimer > 500)
					player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 230));
			}
			else
				nocturnetimer=0;
			
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.FEATHER_EARRING) > 0)
			{
				player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 50, HarshenUtils.hasAccessoryTimes(player, HarshenItems.FEATHER_EARRING)-1, false, false));
			}
			
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.WATER_EARRING) > 0)
				player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 105));
		
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.ONE_RING) > 0)
				player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 7, 0, false, false));
		
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.TELERING) > 0)
				player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 105, HarshenUtils.hasAccessoryTimes(player, HarshenItems.TELERING)-1, false, false));
		
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.PONTUS_RING) > 0)
			{
				player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 110, HarshenUtils.hasAccessoryTimes(player, HarshenItems.PONTUS_RING)-1, false, false));
			}
		
			if(tick % 200 == 0)
			{
				if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.BLOODY_EARRING) > 0)
					player.heal(2f*HarshenUtils.hasAccessoryTimes(player, HarshenItems.BLOODY_EARRING));
			}
			
			if(tick % 500 == 0 && player.getFoodStats().getFoodLevel() < 20)
			{
				if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.FEEDING_EARRING) > 0)
					player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + HarshenUtils.hasAccessoryTimes(player, HarshenItems.FEEDING_EARRING));
			}
		
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.ELEMENTAL_PENDANT) > 0)
			{
				IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("04d5b650-d8a8-45a9-88f1-01992a4f5785"), "elementalPendantHealth", 6, 0).setSaved(true);
				if(!attributeHealth.hasModifier(modifierHealth))	
					attributeHealth.applyModifier(modifierHealth);
				
				IAttributeInstance attributeArmor = player.getEntityAttribute(SharedMonsterAttributes.ARMOR);
				AttributeModifier modifierArmor = new AttributeModifier(UUID.fromString("6c0d606c-b4ae-468d-a259-bcd5638055cb"), "elementalPendantArmor", 4, 0).setSaved(true);
				if(!attributeArmor.hasModifier(modifierArmor))	
					attributeArmor.applyModifier(modifierArmor);
			}else
			{
				player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("04d5b650-d8a8-45a9-88f1-01992a4f5785"));
				player.getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(UUID.fromString("6c0d606c-b4ae-468d-a259-bcd5638055cb"));
				if(player.getMaxHealth() < player.getHealth()) player.setHealth(player.getMaxHealth());
			}
			
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.CRIMINAL_PENDANT) > 0)
			{
				IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("72eb8438-8f2b-11e7-bb31-be2e44b06b34"), "criminalPendantHealth6", 8, 0).setSaved(true);
				if(!attributeHealth.hasModifier(modifierHealth))	
					attributeHealth.applyModifier(modifierHealth);
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 19, 0, false, false));
			}else
			{
				player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("72eb8438-8f2b-11e7-bb31-be2e44b06b34"));
				if(player.getMaxHealth() < player.getHealth()) player.setHealth(player.getMaxHealth());
			}
			
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.RING_OF_BLOOD) == 1)
			{
				IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("83cd6b7e-a838-11e7-abc4-cec278b6b50"), "ringOfBloodHealth", 6, 0).setSaved(true);
				if(!attributeHealth.hasModifier(modifierHealth))	
					attributeHealth.applyModifier(modifierHealth);
			}else
			{
				player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("83cd6b7e-a838-11e7-abc4-cec278b6b50"));
				if(player.getMaxHealth() < player.getHealth()) player.setHealth(player.getMaxHealth());
			}
			
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.RING_OF_BLOOD) == 2)
			{
				IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("c37d585a-5a65-4fb1-b604-3f5a8a42d392"), "doubleRingOfBloodHealth", 12, 0).setSaved(true);
				if(!attributeHealth.hasModifier(modifierHealth))	
					attributeHealth.applyModifier(modifierHealth);
			}else
			{
				player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("c37d585a-5a65-4fb1-b604-3f5a8a42d392"));
				if(player.getMaxHealth() < player.getHealth()) player.setHealth(player.getMaxHealth());
			}
		}
	}
	
	@SubscribeEvent
	public void onBlockBroken(HarvestDropsEvent event)
	{
		if(event.getHarvester() instanceof EntityPlayer)
		{
		EntityPlayer player = event.getHarvester();
		
		if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.FIERY_RING) > 0)
			HarshenUtils.cookAndReplaceStackList(event.getDrops());
		}
	}
	
	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent event)
	{
		if(event.getSource().getTrueSource() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
			if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.FIERY_RING) > 0)
				HarshenUtils.cookAndReplaceList(event.getDrops());
			
			if(!(event.getEntity() instanceof EntityPlayer))
				if(HarshenUtils.hasAccessoryTimes(player, HarshenItems.LOOTING_EARRING) > 0)
				{
					ArrayList<EntityItem> drops = new ArrayList<EntityItem>();
					for(EntityItem e : event.getDrops())
						if(event.getEntityLiving().getRNG().nextBoolean())
							drops.add(new EntityItem(e.world, e.posX, e.posY, e.posZ, e.getItem()));
					event.getDrops().addAll(drops);
				}
		}
	}

	@SubscribeEvent
	public void onPlayerWakeUp(PlayerWakeUpEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		if(!player.world.isRemote)
		{
			if(player.world.getBlockState(player.bedLocation).getBlock() instanceof BaseBloodyBed)
				if(player.isPotionActive(MobEffects.REGENERATION))
					player.removePotionEffect(MobEffects.REGENERATION);
		}
	}
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		if(event.player.getHeldItemMainhand().getItem() instanceof HarshenNightBlade)
		{
			ICooldownHandler cap = event.player.getHeldItemMainhand().getCapability(CooldownHandler.COOLDOWN, EnumFacing.DOWN);
			if(event.player.world.getWorldTime() % 24000 > 12000 || event.player.world.isRaining())
				cap.addProgress();
		}
		if(event.player.getHeldItemOffhand().getItem() instanceof HarshenNightBlade)
		{
			ICooldownHandler cap = event.player.getHeldItemOffhand().getCapability(CooldownHandler.COOLDOWN, EnumFacing.DOWN);
			if(event.player.world.getWorldTime() % 24000 > 12000 || event.player.world.isRaining())
				cap.addProgress();
		}
	}
}