package com.kenymylankca.harshenuniverse.worldgen;

import com.kenymylankca.harshenuniverse.HarshenUniverse;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HarshenBiomeFeatures
{
    private static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(Registries.FEATURE, HarshenUniverse.MOD_ID);

    @SubscribeEvent
    public static void register(IEventBus eventBus)
    {
        REGISTRY.register(eventBus);
    }
}