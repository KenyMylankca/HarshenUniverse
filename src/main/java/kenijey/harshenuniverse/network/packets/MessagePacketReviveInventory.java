package kenijey.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshenuniverse.base.BaseMessagePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessagePacketReviveInventory extends BaseMessagePacket<MessagePacketReviveInventory>
{
	
	public MessagePacketReviveInventory() {
	}
	
	public MessagePacketReviveInventory(EntityPlayer player)
	{
		nbtList = player.inventory.writeToNBT(new NBTTagList());
	}
	
	private NBTTagList nbtList;

	@Override
	public void fromBytes(ByteBuf buf) {
		nbtList = new NBTTagList();
		int tagSize = buf.readInt();
		for(int i = 0; i < tagSize; i ++)
			nbtList.appendTag(ByteBufUtils.readTag(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(nbtList.tagCount());
		for(int i = 0; i < nbtList.tagCount(); i++)
			ByteBufUtils.writeTag(buf, nbtList.getCompoundTagAt(i));
	}

	@Override
	public void onReceived(MessagePacketReviveInventory message, EntityPlayer player) {
		player.inventory.readFromNBT(message.nbtList);
	}

}
