package com.kenymylankca.harshenuniverse;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HarshenSounds
{
	public static final DeferredRegister<SoundEvent> HARSHEN_SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, HarshenUniverse.MOD_ID);
	
	public static final DeferredHolder<SoundEvent, SoundEvent> BLOOD_SPLASH = HARSHEN_SOUND_EVENTS.register(
            "blood_splash", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(HarshenUniverse.MOD_ID, "blood_splash"))
    );
}