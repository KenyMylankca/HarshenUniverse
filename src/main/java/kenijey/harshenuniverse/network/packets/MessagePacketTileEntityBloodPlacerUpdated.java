package kenijey.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshenuniverse.base.BaseMessagePacket;
import kenijey.harshenuniverse.tileentity.TileEntityBloodVessel;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class MessagePacketTileEntityBloodPlacerUpdated extends BaseMessagePacket<MessagePacketTileEntityBloodPlacerUpdated>{

	public MessagePacketTileEntityBloodPlacerUpdated() {
	}
	
	private int amount;
	private BlockPos position;
	
	public MessagePacketTileEntityBloodPlacerUpdated(BlockPos position, int amount)
	{
		this.amount = amount;
		this.position = position;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		amount = buf.readInt();
		this.position = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		buf.writeInt(amount);
		buf.writeInt(position.getX());
		buf.writeInt(position.getY());
		buf.writeInt(position.getZ());
	}

	@Override
	public void onReceived(MessagePacketTileEntityBloodPlacerUpdated message, EntityPlayer player) {
		if(Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().world.getTileEntity(message.position) instanceof TileEntityBloodVessel)
			((TileEntityBloodVessel)Minecraft.getMinecraft().world.getTileEntity(message.position)).setBloodLevel(message.amount);
	}
}
