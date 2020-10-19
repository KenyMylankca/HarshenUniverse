package kenymylankca.harshenuniverse.network;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.network.packets.MessagePacketHitWithRange;
import kenymylankca.harshenuniverse.network.packets.MessagePacketKillAllWithTag;
import kenymylankca.harshenuniverse.network.packets.MessagePacketOpenInv;
import kenymylankca.harshenuniverse.network.packets.MessagePacketPlaySound;
import kenymylankca.harshenuniverse.network.packets.MessagePacketPlayerHasAccess;
import kenymylankca.harshenuniverse.network.packets.MessagePacketPlayerTeleportEffects;
import kenymylankca.harshenuniverse.network.packets.MessagePacketRequestHarshenInv;
import kenymylankca.harshenuniverse.network.packets.MessagePacketReviveInventory;
import kenymylankca.harshenuniverse.network.packets.MessagePacketRingUpdate;
import kenymylankca.harshenuniverse.network.packets.MessagePacketSendStartupData;
import kenymylankca.harshenuniverse.network.packets.MessagePacketSetItemInSlot;
import kenymylankca.harshenuniverse.network.packets.MessagePacketSpawnBloodParticle;
import kenymylankca.harshenuniverse.network.packets.MessagePacketSpawnItemParticles;
import kenymylankca.harshenuniverse.network.packets.MessagePacketSummonFirework;
import kenymylankca.harshenuniverse.network.packets.MessagePacketTileEntityBloodPlacerUpdated;
import kenymylankca.harshenuniverse.network.packets.MessagePacketUpdateComplexEntity;
import kenymylankca.harshenuniverse.network.packets.MessagePacketUpdateXrayBlock;
import kenymylankca.harshenuniverse.network.packets.MessageSendPlayerInvToClient;
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
		registerMessage(MessagePacketPlaySound.class, Side.CLIENT);
		
		registerMessage(MessagePacketSetItemInSlot.class);
		
		registerMessage(MessagePacketOpenInv.class, Side.SERVER);
		registerMessage(MessagePacketRingUpdate.class, Side.SERVER);
		registerMessage(MessagePacketUpdateXrayBlock.class, Side.SERVER);
		registerMessage(MessagePacketSummonFirework.class, Side.SERVER);
		registerMessage(MessagePacketRequestHarshenInv.class, Side.SERVER);
		registerMessage(MessagePacketHitWithRange.class, Side.SERVER);
		registerMessage(MessagePacketSendStartupData.class, Side.SERVER);
	}
	
	private static int idCount = -1;
    public static void registerMessage(Class claz, Side recievingSide)
    {
    	INSTANCE.registerMessage(claz, claz, idCount++, recievingSide);
    }
    
    public static void registerMessage(Class claz)
    {
    	INSTANCE.registerMessage(claz, claz, idCount++, Side.CLIENT);
    	INSTANCE.registerMessage(claz, claz, idCount++, Side.SERVER);
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