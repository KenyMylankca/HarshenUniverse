package com.kenymylankca.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenymylankca.harshenuniverse.base.BaseMessagePacket;
import kenymylankca.harshenuniverse.handlers.HandlerPontusAllowed;
import net.minecraft.entity.player.EntityPlayer;

public class MessagePacketPlayerHasAccess extends BaseMessagePacket<MessagePacketPlayerHasAccess>
{
	public MessagePacketPlayerHasAccess() {
	}
	
	private int level;
	
	public MessagePacketPlayerHasAccess(EntityPlayer player)
	{
		level = player.getEntityData().getInteger("PontusBiomeLevel");
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		level = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		buf.writeInt(level);
	}

	@Override
	public void onReceived(MessagePacketPlayerHasAccess message, EntityPlayer player) {	
		HandlerPontusAllowed.setAllowed(player, message.level);
	}
}