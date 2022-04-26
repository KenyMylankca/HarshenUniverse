package com.kenymylankca.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenymylankca.harshenuniverse.base.BaseMessagePacket;
import kenymylankca.harshenuniverse.network.HarshenNetwork;
import net.minecraft.entity.player.EntityPlayer;

public class MessagePacketRequestHarshenInv extends BaseMessagePacket<MessagePacketRequestHarshenInv>
{
	@Override
	public void fromBytes(ByteBuf buf) {		
		
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		
	}

	@Override
	public void onReceived(MessagePacketRequestHarshenInv message, EntityPlayer player) {
		HarshenNetwork.sendToPlayer(player, new MessageSendPlayerInvToClient(player));
	}
}