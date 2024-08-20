package com.kenymylankca.harshenuniverse;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(HarshenUniverse.MOD_ID)
public class HarshenUniverse
{
    public static final String MOD_ID = "harshenuniverse";
    public static final String MOD_NAME = "Harshen Universe";
    private static final Logger LOGGER = LogUtils.getLogger();

    public HarshenUniverse(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        HarshenCreativeTab.HARSHEN_CREATIVE_TABS.register(modEventBus);
        HarshenItems.register(modEventBus);
        HarshenBlocks.register(modEventBus);
        HarshenSounds.HARSHEN_SOUND_EVENTS.register(modEventBus);
        
        modContainer.registerConfig(ModConfig.Type.COMMON, HarshenConfig.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}