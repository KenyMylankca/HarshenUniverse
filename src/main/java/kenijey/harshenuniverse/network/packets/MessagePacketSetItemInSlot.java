package kenijey.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.base.BaseMessagePacket;
import kenijey.harshenuniverse.objecthandlers.HarshenItemStackHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessagePacketSetItemInSlot extends BaseMessagePacket<MessagePacketSetItemInSlot>
{
	public MessagePacketSetItemInSlot() {
	}
	
	private int slotId;
	private ItemStack stack;
	
	public MessagePacketSetItemInSlot(int slotId, ItemStack stack)
	{
		this.slotId = slotId;
		this.stack = stack;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		slotId = buf.readInt();
		stack = ByteBufUtils.readItemStack(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {	
		buf.writeInt(slotId);
		ByteBufUtils.writeItemStack(buf, stack);
	}

	@Override
	public void onReceived(MessagePacketSetItemInSlot message, EntityPlayer player) 
	{
		HarshenItemStackHandler handler = HarshenUtils.getHandler(player);
		handler.setStackInSlot(message.slotId, message.stack);
		player.getEntityData().setTag("harshenInventory", handler.serializeNBT());
	}
}