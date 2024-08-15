package com.kenymylankca.harshenuniverse.worldgen;

import com.kenymylankca.harshenuniverse.HarshenUniverse;
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class HarshenBiomeModifiers
{
    private static final DeferredRegister<MapCodec<? extends BiomeModifier>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, HarshenUniverse.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<HarshenBiomeModifier>> ADD_FEATURES = REGISTRY.register("add_features", () -> HarshenBiomeModifier.CODEC);

    @SubscribeEvent
    public static void register(IEventBus eventBus)
    {
        REGISTRY.register(eventBus);
    }
}