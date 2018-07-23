package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.api.EnumInventorySlots;
import kenijey.harshenuniverse.api.HarshenEvent;
import kenijey.harshenuniverse.api.IHarshenProvider;
import kenijey.harshenuniverse.network.HarshenNetwork;
import kenijey.harshenuniverse.network.packets.MessagePacketSummonFirework;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickEmpty;

public class ElytraPendant extends Item implements IHarshenProvider
{

	public ElytraPendant() {
		setRegistryName("elytra_pendant");
		setUnlocalizedName("elytra_pendant");
		setMaxDamage(5000);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
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