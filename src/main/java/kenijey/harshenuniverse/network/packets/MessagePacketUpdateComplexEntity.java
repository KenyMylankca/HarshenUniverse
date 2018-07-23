package kenijey.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshenuniverse.base.BaseMessagePacket;
import kenijey.harshenuniverse.handlers.client.HandlerEntityUpdater;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessagePacketUpdateComplexEntity extends BaseMessagePacket<MessagePacketUpdateComplexEntity>
{
	private NBTTagCompound compound;
	private int entityId;
	
	public MessagePacketUpdateComplexEntity() {
	}
	
	public MessagePacketUpdateComplexEntity(int entityId, NBTTagCompound compound) 
	{
		this.compound = compound;
		this.entityId = entityId;
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeTag(buf, compound);
		buf.writeInt(entityId);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.compound = ByteBufUtils.readTag(buf);
		this.entityId = buf.readInt();
	}

	@Override
	public void onReceived(MessagePacketUpdateComplexEntity message, EntityPlayer player) {
		HandlerEntityUpdater.addToMap(message.entityId, message.compound);
	}
}