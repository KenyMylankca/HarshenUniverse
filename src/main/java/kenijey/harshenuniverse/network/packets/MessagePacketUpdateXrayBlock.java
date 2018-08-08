package kenijey.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.base.BaseMessagePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessagePacketUpdateXrayBlock extends BaseMessagePacket<MessagePacketUpdateXrayBlock>{

	public MessagePacketUpdateXrayBlock() {
	}
	
	private NBTTagCompound tag;
	
	public MessagePacketUpdateXrayBlock(NBTTagCompound tag)
	{
		this.tag = tag;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		tag = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		ByteBufUtils.writeTag(buf, tag);
	}

	@Override
	public void onReceived(MessagePacketUpdateXrayBlock message, EntityPlayer player) {
		HarshenUtils.getNBT(player.getHeldItemMainhand()).setString("BlockToSearch", message.tag.getCompoundTag("tag").getString("BlockToSearch"));
	}
}