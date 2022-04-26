package com.kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.HarshenEvent;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import kenymylankca.harshenuniverse.network.HarshenNetwork;
import kenymylankca.harshenuniverse.network.packets.MessagePacketSummonFirework;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickEmpty;

public class ElytraPendant extends Item implements IHarshenAccessoryProvider
{
	public ElytraPendant() {
		setRegistryName("elytra_pendant");
		setUnlocalizedName("elytra_pendant");
		setMaxDamage(5000);
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.NECK;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return new HandlerElytraPendant();
	}
	
	public class HandlerElytraPendant
	{
		@HarshenEvent
		public void onRightClick(PlayerInteractEvent event)
		{
			if(event.getEntityPlayer().world.isRemote && HarshenUtils.toArray(RightClickBlock.class, RightClickEmpty.class).contains(event.getClass()) &&
					event.getEntityPlayer().getHeldItemMainhand().isEmpty() && event.getEntityPlayer().isElytraFlying())
			{
				HarshenNetwork.sendToServer(new MessagePacketSummonFirework());

				Vec3d vec3d = event.getEntityPlayer().getLookVec();
				event.getEntityPlayer().motionX += (vec3d.x * 0.1D + (vec3d.x * 2D - event.getEntityPlayer().motionX) * 0.5D);
				event.getEntityPlayer().motionY += (vec3d.y * 0.1D + (vec3d.y * 2D - event.getEntityPlayer().motionY) * 0.5D);
				event.getEntityPlayer().motionZ += (vec3d.z * 0.1D + (vec3d.z * 2D - event.getEntityPlayer().motionZ) * 0.5D);
			}
		}
	}
}