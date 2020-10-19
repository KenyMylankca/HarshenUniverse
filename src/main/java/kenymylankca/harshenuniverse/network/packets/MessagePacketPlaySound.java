package kenymylankca.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenymylankca.harshenuniverse.base.BaseMessagePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessagePacketPlaySound extends BaseMessagePacket<MessagePacketPlaySound>
{
	public MessagePacketPlaySound() {
	}
	
	private String sound;
	private NBTTagCompound nbt = new NBTTagCompound();
	
	public MessagePacketPlaySound(SoundEvent sound, float volume, float pitch)
	{
		this.sound = sound.getRegistryName().toString();
		this.nbt.setFloat("volume", volume);
		this.nbt.setFloat("pitch", pitch);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		sound = ByteBufUtils.readUTF8String(buf);
		nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, sound);
		ByteBufUtils.writeTag(buf, nbt);
	}

	@Override
	public void onReceived(MessagePacketPlaySound message, EntityPlayer player)
	{
		SoundEvent soundEvent = new SoundEvent(new ResourceLocation(message.sound));
		player.playSound(soundEvent, message.nbt.getFloat("volume"), message.nbt.getFloat("pitch"));
	}
}