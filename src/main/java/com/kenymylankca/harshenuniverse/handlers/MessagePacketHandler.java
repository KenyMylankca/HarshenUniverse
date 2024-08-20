package com.kenymylankca.harshenuniverse.handlers;

import com.kenymylankca.harshenuniverse.network.packets.MessagePlaySound;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class MessagePacketHandler
{
	@SubscribeEvent
	public static void registerPackets(RegisterPayloadHandlersEvent event)
	{
		final PayloadRegistrar registrar = event.registrar("1.0");
		
		registrar.playToClient(MessagePlaySound.TYPE, MessagePlaySound.CODEC, MessagePlaySound::handle);
	}
}