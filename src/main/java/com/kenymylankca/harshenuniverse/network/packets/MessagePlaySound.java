package com.kenymylankca.harshenuniverse.network.packets;

import java.nio.charset.StandardCharsets;

import com.kenymylankca.harshenuniverse.HarshenUniverse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class MessagePlaySound implements CustomPacketPayload
{
	public static final CustomPacketPayload.Type<MessagePlaySound> TYPE = new CustomPacketPayload.Type<MessagePlaySound>(ResourceLocation.fromNamespaceAndPath(HarshenUniverse.MOD_ID, "message_play_sound"));
	public static final StreamCodec<FriendlyByteBuf, MessagePlaySound> CODEC = CustomPacketPayload.codec(MessagePlaySound::encode, MessagePlaySound::decode);
	
	String sound;
    SoundSource source;
    BlockPos pos;
    float volume;
    float pitch;
    
    public MessagePlaySound(SoundEvent sound, SoundSource source, BlockPos pos, float volume, float pitch)
    {
    	this(BuiltInRegistries.SOUND_EVENT.getKey(sound).toString(), source, pos, volume, pitch);
    }
    
    MessagePlaySound(String sound, SoundSource source, BlockPos pos, float volume, float pitch)
    {
    	this.sound = sound;
    	this.source = source;
    	this.pos = pos;
    	this.volume = volume;
    	this.pitch = pitch;
	}

	public static void encode(MessagePlaySound message, FriendlyByteBuf buffer)
    {
    	buffer.writeInt(message.sound.length());
    	buffer.writeCharSequence(message.sound, StandardCharsets.UTF_8);
    	buffer.writeEnum(message.source);
    	buffer.writeBlockPos(message.pos);
    	buffer.writeFloat(message.volume);
    	buffer.writeFloat(message.pitch);
    }
	
	public static MessagePlaySound decode(FriendlyByteBuf buffer)
	{
		int soundChars = buffer.readInt();
		return new MessagePlaySound(buffer.readCharSequence(soundChars, StandardCharsets.UTF_8).toString(),
				buffer.readEnum(SoundSource.class),
				buffer.readBlockPos(),
				buffer.readFloat(),
				buffer.readFloat()
				);
	}
	
	public static void handle(MessagePlaySound message, IPayloadContext context)
	{
		context.enqueueWork(() ->
		{
			SoundEvent sound = BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse(message.sound));
			
			if(sound!= null)
				Minecraft.getInstance().getSoundManager().play(new SimpleSoundInstance(sound, message.source, message.volume, message.pitch, context.player().getRandom(), message.pos));
		}
		);
	}
	
	@Override
	public Type<? extends CustomPacketPayload> type()
	{
		return TYPE;
	}
}