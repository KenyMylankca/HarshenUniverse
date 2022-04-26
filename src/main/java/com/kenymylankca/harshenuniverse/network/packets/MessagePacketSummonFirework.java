package com.kenymylankca.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseMessagePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class MessagePacketSummonFirework extends BaseMessagePacket<MessagePacketSummonFirework>
{
	@Override
	public void fromBytes(ByteBuf buf) {		
	}

	@Override
	public void toBytes(ByteBuf buf) {		
	}

	@Override
	public void onReceived(MessagePacketSummonFirework message, EntityPlayer player) {	
		Vec3d vec3d = player.getLookVec();
        player.motionX += vec3d.x * 0.1D + (vec3d.x * 2.5D - player.motionX) * 0.5D;
        player.motionY += vec3d.y * 0.1D + (vec3d.y * 2.5D - player.motionY) * 0.5D;
        player.motionZ += vec3d.z * 0.1D + (vec3d.z * 2.5D - player.motionZ) * 0.5D;
		HarshenUtils.damageFirstOccuringItem(player, HarshenItems.ELYTRA_PENDANT);
	}
}