package kenijey.harshenuniverse.handlers;

import java.util.ArrayList;
import java.util.UUID;

import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.damagesource.DamageSourceReflectorPendant;
import kenijey.harshenuniverse.items.BloodyEarring;
import kenijey.harshenuniverse.items.CriminalPendant;
import kenijey.harshenuniverse.items.ElementalPendant;
import kenijey.harshenuniverse.items.ElytraPendant;
import kenijey.harshenuniverse.items.Fearring;
import kenijey.harshenuniverse.items.FeatherEarring;
import kenijey.harshenuniverse.items.FeedingEarring;
import kenijey.harshenuniverse.items.FieryRing;
import kenijey.harshenuniverse.items.LootingEarring;
import kenijey.harshenuniverse.items.OneRing;
import kenijey.harshenuniverse.items.PontusRing;
import kenijey.harshenuniverse.items.PunchyRing;
import kenijey.harshenuniverse.items.ReflectorPendant;
import kenijey.harshenuniverse.items.RingOfBlood;
import kenijey.harshenuniverse.items.SoulShield;
import kenijey.harshenuniverse.items.Telering;
import kenijey.harshenuniverse.items.WaterEarring;
import kenijey.harshenuniverse.items.ZombiPendant;
import kenijey.harshenuniverse.objecthandlers.HarshenItemStackHandler;
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
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class HandlerServerNeedingHarshenEffects
{
	int tick=0;
	int trusttimer=666;
	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		
		if(event.getSource().getDamageType() == "player")
		{
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
			HarshenItemStackHandler handler = HarshenUtils.getHandler(player);
			
			if(player.getHeldItemMainhand().isEmpty() && handler.getStackInSlot(3).getItem() instanceof PunchyRing)
			{
				event.setAmount(event.getAmount() + 3);
				HarshenUtils.damageItemInSlot(player, HarshenItems.PUNCHY_RING, 1, 3);
			}
			
			if(player.getHeldItemMainhand().isEmpty() && handler.getStackInSlot(4).getItem() instanceof PunchyRing)
			{
				event.setAmount(event.getAmount() + 3);
				HarshenUtils.damageItemInSlot(player, HarshenItems.PUNCHY_RING, 1, 4);
			}
				
			if(handler.getStackInSlot(0).getItem() instanceof Fearring || handler.getStackInSlot(1).getItem() instanceof Fearring)
				entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 150));
			
			if(!(event.getEntityLiving() instanceof EntityPlayer))
			{
				if(HarshenUtils.hasBloodyTorch(player))
					trusttimer=0;
			}
		}
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			HarshenItemStackHandler handler = HarshenUtils.getHandler(player);
			
			EntityPlayer entityplayer = (EntityPlayer) event.getEntityLiving();
			if(handler.getStackInSlot(2).getItem() instanceof ZombiPendant && (event.getSource()).getTrueSource() instanceof EntityZombie)
				event.setAmount(1);
			
			if(handler.getStackInSlot(0).getItem() instanceof Fearring || handler.getStackInSlot(1).getItem() instanceof Fearring)
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 20));
			
			if(handler.getStackInSlot(2).getItem() instanceof ElytraPendant)
				if(HarshenUtils.toArray(DamageSource.FLY_INTO_WALL, DamageSource.FALL).contains(event.getSource())
						&& event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA)
					event.setCanceled(true);
			
			if(handler.getStackInSlot(2).getItem() instanceof ReflectorPendant && event.getSource().getTrueSource() instanceof EntityLivingBase)
			{
				event.getSource().getTrueSource().attackEntityFrom(new DamageSourceReflectorPendant(), event.getAmount()/2f);
			}
			
			if(handler.getStackInSlot(2).getItem() instanceof SoulShield && !player.isCreative() && (handler.getStackInSlot(2).getMaxDamage() - handler.getStackInSlot(2).getItemDamage() > event.getAmount()))
			{
				HarshenUtils.damageFirstOccuringItem(player, HarshenItems.SOUL_SHIELD, (int) event.getAmount());
				
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
	public void onTick(TickEvent.WorldTickEvent event)
	{
		if(trusttimer < 700) trusttimer++;
		if(tick>600) tick =1; else tick++;
		for(EntityPlayer player : event.world.playerEntities)
		{
		
			HarshenItemStackHandler handler = HarshenUtils.getHandler(player);
		
			if(handler.getStackInSlot(0).getItem() instanceof FeatherEarring || handler.getStackInSlot(1).getItem() instanceof FeatherEarring)
			{
				if(handler.getStackInSlot(0).getItem() instanceof FeatherEarring && handler.getStackInSlot(1).getItem() instanceof FeatherEarring)
					player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 50, 1, false, false));
				else
					player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 50, 0, false, false));
			}
			
			if(handler.getStackInSlot(0).getItem() instanceof WaterEarring || handler.getStackInSlot(1).getItem() instanceof WaterEarring)
				player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 105));
		
			if(handler.getStackInSlot(3).getItem() instanceof OneRing || handler.getStackInSlot(4).getItem() instanceof OneRing)
				player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 7, 0, false, false));
		
			if(handler.getStackInSlot(3).getItem() instanceof Telering || handler.getStackInSlot(4).getItem() instanceof Telering)
				player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 105, 0, false, false));
		
			if(handler.getStackInSlot(3).getItem() instanceof PontusRing || handler.getStackInSlot(4).getItem() instanceof PontusRing)
			{
				if(handler.getStackInSlot(3).getItem() instanceof PontusRing && handler.getStackInSlot(4).getItem() instanceof PontusRing)
					player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 110, 1, false, false));
				else
					player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 110, 0, false, false));
			}
		
			if(tick % 200 == 0)
			{
				if(handler.getStackInSlot(0).getItem() instanceof BloodyEarring)
					player.heal(2f);
				if(handler.getStackInSlot(1).getItem() instanceof BloodyEarring)
					player.heal(2f);
			}
			
			if(tick % 500 == 0 && player.getFoodStats().getFoodLevel() < 20)
			{
				if(handler.getStackInSlot(0).getItem() instanceof FeedingEarring)
					player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel()+1);
				if(handler.getStackInSlot(1).getItem() instanceof FeedingEarring)
					player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel()+1);
			}
		
			if(handler.getStackInSlot(2).getItem() instanceof ElementalPendant)
			{
				IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("04d5b650-d8a8-45a9-88f1-01992a4f5785" + 2), "elementalPendantHealth" + 2, 4, 0).setSaved(true);
				if(!attributeHealth.hasModifier(modifierHealth))	
					attributeHealth.applyModifier(modifierHealth);
				
				IAttributeInstance attributeArmor = player.getEntityAttribute(SharedMonsterAttributes.ARMOR);
				AttributeModifier modifierArmor = new AttributeModifier(UUID.fromString("6c0d606c-b4ae-468d-a259-bcd5638055cb" + 2), "elementalPendantArmor" + 2, 4, 0).setSaved(true);
				if(!attributeArmor.hasModifier(modifierArmor))	
					attributeArmor.applyModifier(modifierArmor);
			}else
			{
				player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("04d5b650-d8a8-45a9-88f1-01992a4f5785" + 2));
				player.getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(UUID.fromString("6c0d606c-b4ae-468d-a259-bcd5638055cb" + 2));
				if(player.getMaxHealth() < player.getHealth()) player.setHealth(player.getMaxHealth());
			}
			
			if(handler.getStackInSlot(2).getItem() instanceof CriminalPendant)
			{
				IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("72eb8438-8f2b-11e7-bb31-be2e44b06b34"), "criminalPendantHealth6", 6, 0).setSaved(true);
				if(!attributeHealth.hasModifier(modifierHealth))	
					attributeHealth.applyModifier(modifierHealth);
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20, 0, false, false));
			}else
			{
				player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("72eb8438-8f2b-11e7-bb31-be2e44b06b34"));
				if(player.getMaxHealth() < player.getHealth()) player.setHealth(player.getMaxHealth());
			}
			
			if(handler.getStackInSlot(3).getItem() instanceof RingOfBlood)
			{
				IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("83cd6b7e-a838-11e7-abc4-cec278b6b50" + 3), "ringOfBloodHealth" + 3, 4, 0).setSaved(true);
				if(!attributeHealth.hasModifier(modifierHealth))	
					attributeHealth.applyModifier(modifierHealth);
			}else
			{
				player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("83cd6b7e-a838-11e7-abc4-cec278b6b50" + 3));
				if(player.getMaxHealth() < player.getHealth()) player.setHealth(player.getMaxHealth());
			}
			
			if(handler.getStackInSlot(4).getItem() instanceof RingOfBlood)
			{
				IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("83cd6b7e-a838-11e7-abc4-cec278b6b20" + 4), "ringOfBloodHealth" + 4, 4, 0).setSaved(true);
				if(!attributeHealth.hasModifier(modifierHealth))	
					attributeHealth.applyModifier(modifierHealth);
			}else
			{
				player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("83cd6b7e-a838-11e7-abc4-cec278b6b20" + 4));
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
		HarshenItemStackHandler handler = HarshenUtils.getHandler(player);
		
		if(handler.getStackInSlot(3).getItem() instanceof FieryRing || handler.getStackInSlot(4).getItem() instanceof FieryRing)
			HarshenUtils.cookAndReplaceStackList(event.getDrops());
		}
	}
	
	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent event)
	{
		if(event.getSource().getTrueSource() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
			HarshenItemStackHandler handler = HarshenUtils.getHandler(player);
			if(handler.getStackInSlot(3).getItem() instanceof FieryRing || handler.getStackInSlot(4).getItem() instanceof FieryRing)
				HarshenUtils.cookAndReplaceList(event.getDrops());
			
			if(!(event.getEntity() instanceof EntityPlayer))
				if(handler.getStackInSlot(0).getItem() instanceof LootingEarring || handler.getStackInSlot(1).getItem() instanceof LootingEarring)
				{
					if(handler.getStackInSlot(0).getItem() instanceof LootingEarring && handler.getStackInSlot(1).getItem() instanceof LootingEarring)
					{
						ArrayList<EntityItem> drops = new ArrayList<EntityItem>();
						for(EntityItem e : event.getDrops())
							if(event.getEntityLiving().getRNG().nextBoolean())
								drops.add(new EntityItem(e.world, e.posX, e.posY, e.posZ, e.getItem()));
						event.getDrops().addAll(drops);
						event.getDrops().addAll(drops);
					}
					else
					{
						ArrayList<EntityItem> drops = new ArrayList<EntityItem>();
						for(EntityItem e : event.getDrops())
							if(event.getEntityLiving().getRNG().nextBoolean())
								drops.add(new EntityItem(e.world, e.posX, e.posY, e.posZ, e.getItem()));
						event.getDrops().addAll(drops);
					}
				}
		}
	}
}