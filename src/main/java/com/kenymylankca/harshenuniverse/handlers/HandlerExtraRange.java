package com.kenymylankca.harshenuniverse.handlers;

import kenymylankca.harshenuniverse.HarshenClientUtils;
import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseHarshenScythe;
import kenymylankca.harshenuniverse.config.AccessoryConfig;
import kenymylankca.harshenuniverse.network.HarshenNetwork;
import kenymylankca.harshenuniverse.network.packets.MessagePacketHitWithRange;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.MouseInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HandlerExtraRange 
{
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onClick(MouseInputEvent event)
	{
		if(Minecraft.getMinecraft().gameSettings.keyBindAttack.isPressed())
		{
			KeyBinding.onTick(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode());
			RayTraceResult ray = HarshenClientUtils.getMouseOver(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof BaseHarshenScythe ?
					((BaseHarshenScythe)Minecraft.getMinecraft().player.getHeldItemMainhand().getItem()).getReach() : AccessoryConfig.reachPendantLength);
			if(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof BaseHarshenScythe && HarshenUtils.containsItem(Minecraft.getMinecraft().player, HarshenItems.REACH_PENDANT))
				ray = HarshenClientUtils.getMouseOver(((BaseHarshenScythe)Minecraft.getMinecraft().player.getHeldItemMainhand().getItem()).getReach() + AccessoryConfig.reachPendantLength);
			if(ray != null && (Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof BaseHarshenScythe || HarshenUtils.containsItem(Minecraft.getMinecraft().player, HarshenItems.REACH_PENDANT)))
				HarshenNetwork.sendToServer(new MessagePacketHitWithRange(ray.entityHit.getEntityId()));
		}
	}
}