package kenymylankca.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.base.BaseMessagePacket;
import kenymylankca.harshenuniverse.enums.particle.EnumHarshenParticle;
import kenymylankca.harshenuniverse.particle.ParticleItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessagePacketSpawnItemParticles extends BaseMessagePacket<MessagePacketSpawnItemParticles>{

	public MessagePacketSpawnItemParticles() {
	}
	
	private Vec3d position;
	private Vec3d directionSpeed;
	private float scale;
	private boolean disableMoving;
	private ItemStack stack;
	private int timesSpawn;
	private String tag;
	
	public MessagePacketSpawnItemParticles(ItemStack stack, Vec3d position, Vec3d directionSpeed, float scale, boolean disableMoving, int times, String tag)
	{
		this.position = position;
		this.stack = stack;
		if(this.stack.isEmpty())
			this.stack = ItemStack.EMPTY;
		this.timesSpawn = times;
		this.directionSpeed = directionSpeed;
		this.scale = scale;
		this.disableMoving = disableMoving;
		this.tag = tag;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		this.position = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
		this.directionSpeed = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
		this.stack = ByteBufUtils.readItemStack(buf);
		this.scale = buf.readFloat();
		this.disableMoving = buf.readBoolean();
		this.timesSpawn = buf.readInt();
		this.tag = ByteBufUtils.readUTF8String(buf);

	}

	@Override
	public void toBytes(ByteBuf buf) {		
		buf.writeDouble(position.x);
		buf.writeDouble(position.y);
		buf.writeDouble(position.z);
		
		buf.writeDouble(directionSpeed.x);
		buf.writeDouble(directionSpeed.y);
		buf.writeDouble(directionSpeed.z);
		
		ByteBufUtils.writeItemStack(buf, stack);
		
		buf.writeFloat(scale);
		
		buf.writeBoolean(disableMoving);
		
		buf.writeInt(timesSpawn);
		
		ByteBufUtils.writeUTF8String(buf, tag);
		
	}

	@Override
	public void onReceived(MessagePacketSpawnItemParticles message, EntityPlayer player) {	
		for(int i = 0; i < message.timesSpawn; i++)
			((ParticleItem)HarshenUniverse.proxy.spawnParticle(EnumHarshenParticle.ITEM, message.position, message.directionSpeed, message.scale, message.disableMoving, message.stack))
				.addToList(message.tag);
	}
}
