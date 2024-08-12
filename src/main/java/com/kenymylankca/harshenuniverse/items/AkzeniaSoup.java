package com.kenymylankca.harshenuniverse.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class AkzeniaSoup extends Item
{
    public AkzeniaSoup() {
        super(new Item.Properties().food(new FoodProperties.Builder()
                .alwaysEdible()
                .effect(() -> new MobEffectInstance((MobEffects.INVISIBILITY), 1200, 1), 1F)
                .effect(() -> new MobEffectInstance((MobEffects.DAMAGE_BOOST), 1200, 1), 1F)
                .effect(() -> new MobEffectInstance((MobEffects.DAMAGE_RESISTANCE), 1200, 1), 1F)
                .build())
        );
    }
}