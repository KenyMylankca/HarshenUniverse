package kenymylankca.harshenuniverse.handlers;

import java.util.ArrayList;
import java.util.List;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.HarshenDataFileManager;
import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.HarshenWorldGen;
import kenymylankca.harshenuniverse.base.BaseBloodyBed;
import kenymylankca.harshenuniverse.config.AccessoryConfig;
import kenymylankca.harshenuniverse.config.GeneralConfig;
import kenymylankca.harshenuniverse.damagesource.DamageSourceReflectorPendant;
import kenymylankca.harshenuniverse.handlers.CooldownHandler.ICooldownHandler;
import kenymylankca.harshenuniverse.items.HarshenNightBlade;
import kenymylankca.harshenuniverse.network.HarshenNetwork;
import kenymylankca.harshenuniverse.network.packets.MessagePacketPlaySound;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class HandlerServerNeedingHarshenEffects
{
	int tick=0;
	int trustTimer=666;
	int nocturnalTimer=0;
	int castleSoundTimer=3000;
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
		if(event.player instanceof EntityOtherPlayerMP)
			return;
		
		if(event.side.isServer())
			if(event.player.world.getSunBrightness(0) < 0.76)
				if(event.player.world.isChunkGeneratedAt(HarshenWorldGen.castleChunks[0], HarshenWorldGen.castleChunks[1]))
				{
					HarshenDataFileManager manager = new HarshenDataFileManager();
					if(manager.readStructurePosFromFile("castle") != null)
					{
						BlockPos castlePos = manager.readStructurePosFromFile("castle");
						if(event.player.getDistanceSq(castlePos) < 1000)
							if(castleSoundTimer++ > 3320)
							{
								HarshenNetwork.sendToPlayer(event.player, new MessagePacketPlaySound(HarshenSounds.CASTLE_AMBIENT, 1f, 1f, castlePos));
								castleSoundTimer=0;
							}
					}
				}
		
		if(trustTimer < 669)
			trustTimer++;
		
		if(tick>600)
			tick =1;
		else
			tick++;
		
		if(HarshenUtils.isInBlocksDistanceOrHolding(event.player, HarshenBlocks.NOCTURNAL_TORCH, GeneralConfig.nocturnalDistance))
		{
			nocturnalTimer++;
			if(nocturnalTimer % 400 == 0 && nocturnalTimer < 4001)
				event.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, nocturnalTimer/2));
			if(nocturnalTimer > 4000)
				nocturnalTimer=0;
			
		}
		else if(HarshenUtils.isInBlocksDistanceOrHolding(event.player, HarshenBlocks.NOCTURNE_BLOOM, GeneralConfig.nocturnalDistance))
		{
			nocturnalTimer++;
			if(nocturnalTimer % 400 == 0 && nocturnalTimer < 1201)
				event.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, nocturnalTimer/4));
			if(nocturnalTimer > 1200)
				nocturnalTimer=0;
		}
		else
			nocturnalTimer=0;
		
		if(event.player.getHeldItemMainhand().getItem() instanceof HarshenNightBlade || event.player.getHeldItemOffhand().getItem() instanceof HarshenNightBlade)
		{
			EnumHand hand;
			if(event.player.getHeldItemMainhand().getItem() instanceof HarshenNightBlade)
				hand=EnumHand.MAIN_HAND;
			else
				hand=EnumHand.OFF_HAND;
			ICooldownHandler cap = event.player.getHeldItem(hand).getCapability(CooldownHandler.COOLDOWN, EnumFacing.DOWN);
			
			if(event.player.world.getSunBrightness(0) < 0.76)
			{
				cap.addProgress();
				if(cap.isReady())
					event.player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 40, 0));
			}
		}
	}
	
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
				if(HarshenUtils.isInBlocksDistanceOrHolding(attackerPlayer, HarshenBlocks.BLOODY_TORCH, GeneralConfig.bloodyTorchDistance))
					trustTimer=0;
			}
		}
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer attackedPlayer = (EntityPlayer) event.getEntityLiving();
			
			if(HarshenUtils.hasAccessoryTimes(attackedPlayer, HarshenItems.ZOMBI_PENDANT) > 0 && event.getSource().getTrueSource() instanceof EntityZombie)
			{
				event.getSource().getTrueSource().attackEntityFrom(DamageSource.GENERIC, 1);
				event.setAmount(0);
			}
			
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
			if((HarshenUtils.isInBlocksDistanceOrHolding((EntityPlayer) event.getTarget(), HarshenBlocks.BLOODY_TORCH, GeneralConfig.bloodyTorchDistance) && trustTimer > 666) || (event.getTarget().isInvisible() && GeneralConfig.trueInvisibility && HarshenUtils.isStacklistEmpty((List<ItemStack>) event.getTarget().getArmorInventoryList())))
				((EntityLiving) event.getEntityLiving()).setAttackTarget(null);
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
}