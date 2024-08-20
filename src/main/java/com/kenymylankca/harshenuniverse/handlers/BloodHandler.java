package com.kenymylankca.harshenuniverse.handlers;

import com.kenymylankca.harshenuniverse.HarshenUtils;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class BloodHandler
{
	@SubscribeEvent
    public static void onLivingHurt(LivingDamageEvent.Post event) {
        if(!(event.getEntity() instanceof Animal)
                && !(event.getEntity() instanceof Player)
                &&  !(event.getEntity() instanceof Zombie)
                &&  !(event.getEntity() instanceof Villager))
            return;
        
        if(event.getSource().type().equals(DamageTypes.CACTUS)
                || event.getSource().type().equals(DamageTypes.BAD_RESPAWN_POINT)
                || event.getSource().type().equals(DamageTypes.CAMPFIRE)
                || event.getSource().type().equals(DamageTypes.DROWN)
                || event.getSource().type().equals(DamageTypes.FREEZE)
                || event.getSource().type().equals(DamageTypes.STARVE)
                || event.getSource().type().equals(DamageTypes.HOT_FLOOR)
                || event.getSource().type().equals(DamageTypes.FALL)
                || event.getSource().type().equals(DamageTypes.FALLING_BLOCK)
        )
        return;
        
        if(event.getSource().getEntity() != null)
        	if(event.getSource().getEntity().getWeaponItem().is(Items.AIR))
        			return;
            
        if(event.getEntity().getRandom().nextInt(9) == 1)
            HarshenUtils.splashBlood(event.getEntity().blockPosition(), event.getEntity().level(), 1);
        return;
    }

	@SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if(!(event.getEntity() instanceof Animal)
                && !(event.getEntity() instanceof Player)
                &&  !(event.getEntity() instanceof Zombie)
                &&  !(event.getEntity() instanceof Villager))
            return;

        if(event.getEntity().getRandom().nextInt(4) == 1)
            HarshenUtils.splashBlood(event.getEntity().blockPosition(), event.getEntity().level(), 1);
    }
}