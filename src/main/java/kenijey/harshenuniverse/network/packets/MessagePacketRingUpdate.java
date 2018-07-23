package kenijey.harshenuniverse.network.packets;

import java.util.ArrayList;
import java.util.Random;

import io.netty.buffer.ByteBuf;
import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.base.BaseMessagePacket;
import kenijey.harshenuniverse.network.HarshenNetwork;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MessagePacketRingUpdate extends BaseMessagePacket<MessagePacketRingUpdate>{

	public MessagePacketRingUpdate() {
	}
	
	private int ringType;
	
	public MessagePacketRingUpdate(int ringType)
	{
		this.ringType = ringType;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		ringType = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		buf.writeInt(ringType);
	}

	@Override
	public void onReceived(MessagePacketRingUpdate message, EntityPlayer player) {
		if(message.ringType < 2)
		{
			ArrayList<Item> ringTypeItem = new ArrayList<Item>();
			ringTypeItem.add(HarshenItems.TELERING);
			ringTypeItem.add(HarshenItems.MINERING);
			if(HarshenUtils.containsItem(player,ringTypeItem.get(message.ringType)))
			{
				World world = player.world;
				Vec3d vec = new Vec3d(player.posX + (player.getLookVec().x * 4f),
						player.posY + (player.getLookVec().y * 4f), player.posZ + (player.getLookVec().z* 4f));
				BlockPos blockpos = message.ringType == 0? HarshenUtils.getTopBlock(world, vec) : HarshenUtils.getBottomBlockAir(world, vec);
				Vec3d vecPos = new Vec3d(blockpos).addVector(0.5, 0, 0.5);
				if(blockpos.getY() != -1 && player.getFoodStats().getFoodLevel() > 0)
				{
					((EntityPlayerMP)player).velocityChanged = true;
					((EntityPlayerMP)player).fallDistance = 0;
					HarshenNetwork.sendToPlayer(player, new MessagePacketPlayerTeleportEffects(vecPos));
					((EntityPlayerMP)player).setPositionAndUpdate(vecPos.x, vecPos.y, vecPos.z);
					world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
	                player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
	                player.addExhaustion(0.5F);
	                HarshenUtils.damageFirstOccuringItem(player, ringTypeItem.get(message.ringType));
				}
			}			
		}
	}
	
	private static double movePos()
	{
		return (new Random().nextDouble() - 0.5D) * 2D;
	}
}
