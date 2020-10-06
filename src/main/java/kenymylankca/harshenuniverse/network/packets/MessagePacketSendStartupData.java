package kenymylankca.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenymylankca.harshenuniverse.base.BaseMessagePacket;
import kenymylankca.harshenuniverse.config.AccessoryConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessagePacketSendStartupData extends BaseMessagePacket<MessagePacketSendStartupData>
{
	public MessagePacketSendStartupData() {
	}
	
	public MessagePacketSendStartupData(NBTTagCompound compound)
	{
		this.compound = compound;
	}
	
	private NBTTagCompound compound;

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, compound);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.compound = ByteBufUtils.readTag(buf);
		
	}
	
	@Override
	public void onReceived(MessagePacketSendStartupData message, EntityPlayer player) {
		AccessoryConfig.reachPendantLength = message.compound.getDouble("reach_distance");
	}
}