package kenijey.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.base.BaseMessagePacket;
import kenijey.harshenuniverse.enums.particle.EnumHarshenParticle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MessagePacketPlayerTeleportEffects extends BaseMessagePacket<MessagePacketPlayerTeleportEffects>
{
	public MessagePacketPlayerTeleportEffects() {
	}
	
	private Vec3d position;
	
	public MessagePacketPlayerTeleportEffects(Vec3d vec3d)
	{
		this.position = vec3d;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		this.position = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		buf.writeDouble(position.x);
		buf.writeDouble(position.y);
		buf.writeDouble(position.z);
	}

	@Override
	public void onReceived(MessagePacketPlayerTeleportEffects message, EntityPlayer player) {	
		World world = player.world;
		Vec3d blockpos = message.position;
		for (int j = 0; j < 128; ++j)
        {
            double d0 = world.rand.nextDouble();
            float f = (world.rand.nextFloat() - 0.5F) * 0.2F;
            float f1 = (world.rand.nextFloat() - 0.5F) * 1.2F;
            float f2 = (world.rand.nextFloat() - 0.5F) * 0.2F;
            double d1 = blockpos.x + (double)(player.getPosition().getX() - blockpos.x) * d0 + (world.rand.nextDouble() - 0.5D) + 0.5D;
            double d2 = blockpos.y + (double)(player.getPosition().getY() - blockpos.y) * d0 + world.rand.nextDouble() - 0.5D;
            double d3 = blockpos.z + (double)(player.getPosition().getZ() - blockpos.z) * d0 + (world.rand.nextDouble() - 0.5D) + 0.5D;
            HarshenUniverse.proxy.spawnParticle(EnumHarshenParticle.PORTAL, new Vec3d(d1, d2, d3), new Vec3d(f, f1, f2), 1f, false);
        }
	}
}