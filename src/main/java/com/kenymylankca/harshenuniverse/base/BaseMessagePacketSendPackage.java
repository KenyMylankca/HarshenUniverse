package com.kenymylankca.harshenuniverse.base;

import io.netty.buffer.ByteBuf;
import kenymylankca.harshenuniverse.HarshenUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public abstract class BaseMessagePacketSendPackage<REQ extends IMessage> extends BaseMessagePacket<REQ>
{
	protected Object[] objects;
	
	protected BaseMessagePacketSendPackage(Object... objects)
	{
		this.objects = objects;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("size", objects.length);
		for(int i = 0; i < objects.length; i++) compound.setByteArray("byte" + i, HarshenUtils.serialize(objects[i]));
		ByteBufUtils.writeTag(buf, compound);

	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		NBTTagCompound compound = ByteBufUtils.readTag(buf);
		for(int i = 0; i < compound.getInteger("size"); i++)
			objects[i] = HarshenUtils.deserialize(compound.getByteArray("byte" + i));
	}
}