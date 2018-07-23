package kenijey.harshenuniverse.network;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.network.packets.MessagePacketHitWithRange;
import kenijey.harshenuniverse.network.packets.MessagePacketKillAllWithTag;
import kenijey.harshenuniverse.network.packets.MessagePacketOpenInv;
import kenijey.harshenuniverse.network.packets.MessagePacketPlayerHasAccess;
import kenijey.harshenuniverse.network.packets.MessagePacketPlayerTeleportEffects;
import kenijey.harshenuniverse.network.packets.MessagePacketRequestInv;
import kenijey.harshenuniverse.network.packets.MessagePacketReviveInventory;
import kenijey.harshenuniverse.network.packets.MessagePacketRingUpdate;
import kenijey.harshenuniverse.network.packets.MessagePacketSendStartupData;
import kenijey.harshenuniverse.network.packets.MessagePacketSetItemInSlot;
import kenijey.harshenuniverse.network.packets.MessagePacketSpawnBloodParticle;
import kenijey.harshenuniverse.network.packets.MessagePacketSpawnItemParticles;
import kenijey.harshenuniverse.network.packets.MessagePacketSummonFirework;
import kenijey.harshenuniverse.network.packets.MessagePacketTileEntityBloodPlacerUpdated;
import kenijey.harshenuniverse.network.packets.MessagePacketUpdateComplexEntity;
import kenijey.harshenuniverse.network.packets.MessagePacketUpdateXrayBlock;
import kenijey.harshenuniverse.network.packets.MessageSendPlayerInvToClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class HarshenNetwork 
{
	private static SimpleNetworkWrapper INSTANCE;
	
	public static void preInit()
	{
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(HarshenUniverse.MODID);
		registerMessage(MessagePacketPlayerHasAccess.class, Side.CLIENT);
		registerMessage(MessagePacketTileEntityBloodPlacerUpdated.class, Side.CLIENT);
		registerMessage(MessageSendPlayerInvToClient.class, Side.CLIENT);
		registerMessage(MessagePacketPlayerTeleportEffects.class, Side.CLIENT);
		registerMessage(MessagePacketSpawnItemParticles.class, Side.CLIENT);
		registerMessage(MessagePacketSpawnBloodParticle.class, Side.CLIENT);
		registerMessage(MessagePacketReviveInventory.class, Side.CLIENT);
		registerMessage(MessagePacketUpdateComplexEntity.class, Side.CLIENT);
		registerMessage(MessagePacketKillAllWithTag.class, Side.CLIENT);
		
		
		registerMessage(MessagePacketOpenInv.class, Side.SERVER);
		registerMessage(MessagePacketRingUpdate.class, Side.SERVER);
		registerMessage(MessagePacketSetItemInSlot.class, Side.SERVER);
		registerMessage(MessagePacketUpdateXrayBlock.class, Side.SERVER);
		registerMessage(MessagePacketSummonFirework.class, Side.SERVER);
		registerMessage(MessagePacketRequestInv.class, Side.SERVER);
		registerMessage(MessagePacketHitWithRange.class, Side.SERVER);
		registerMessage(MessagePacketSendStartupData.class, Side.SERVER);
	}
	
	private static int idCount = -1;
    public static void registerMessage(Class claz, Side recievingSide)
    {
    	INSTANCE.registerMessage(claz, claz, idCount++, recievingSide);
    }
    
	public static void sendToServer(IMessage message)
	{
		INSTANCE.sendToServer(message);
	}
	
	public static void sendToPlayer(EntityPlayer player, IMessage message)
	{
		INSTANCE.sendTo(message, (EntityPlayerMP) player);
	}
	
	public static void sendToPlayersInWorld(World world, IMessage message)
	{
		if(world == null)
			sendToAll(message);
		else if(!world.isRemote)
			INSTANCE.sendToDimension(message, world.provider.getDimension());
	}
	
	public static void sendToAll(IMessage message)
	{
		INSTANCE.sendToAll(message);
	}
}