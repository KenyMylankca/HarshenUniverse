package com.kenymylankca.harshenuniverse.items;

import java.util.List;

import com.kenymylankca.harshenuniverse.HarshenUniverse;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

public class AkzeniaSoup extends Item
{
    public static final String registryName = "akzenia_soup";

    public AkzeniaSoup() {
        super(new Item.Properties().food(new FoodProperties.Builder()
                .alwaysEdible()
                .effect(() -> new MobEffectInstance((MobEffects.INVISIBILITY), 1200, 1), 1F)
                .effect(() -> new MobEffectInstance((MobEffects.DAMAGE_BOOST), 1200, 1), 1F)
                .effect(() -> new MobEffectInstance((MobEffects.DAMAGE_RESISTANCE), 1200, 1), 1F)
                .build()).rarity(Rarity.EPIC)
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip." + HarshenUniverse.MOD_ID + ".akzenia_soup").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}