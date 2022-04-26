package com.kenymylankca.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.base.BaseMessagePacket;
import kenymylankca.harshenuniverse.handlers.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;

public class MessagePacketOpenInv extends BaseMessagePacket<MessagePacketOpenInv>
{
	@Override
	public void fromBytes(ByteBuf buf) {		
		
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		
	}

	@Override
	public void onReceived(MessagePacketOpenInv message, EntityPlayer player) {
		player.openGui(HarshenUniverse.instance, GuiHandler.HARSHENINVENTORY, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
	}
}