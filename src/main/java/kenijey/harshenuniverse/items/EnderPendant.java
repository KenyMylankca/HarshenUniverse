package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.api.EnumInventorySlots;
import kenijey.harshenuniverse.api.HarshenEvent;
import kenijey.harshenuniverse.api.IHarshenProvider;
import kenijey.harshenuniverse.config.AccessoryConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class EnderPendant extends Item implements IHarshenProvider
{
	public EnderPendant() {
		setRegistryName("ender_pendant");
		setUnlocalizedName("ender_pendant");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return new EnderPendantHandler();
	}
	
	@Override
	public void onRemove(EntityPlayer player, int slot) {
		if(player.world.isRemote)
			for(Entity e : player.world.getLoadedEntityList())
				e.setGlowing(false);
	}
	
	@Override
	public void onAdd(EntityPlayer player, int slot) {
		player.playSound(SoundEvents.ENTITY_ENDERMEN_AMBIENT, 1f, 1f);
		IHarshenProvider.super.onAdd(player, slot);
	}
	
	public class EnderPendantHandler
	{
		@HarshenEvent
		public void renderGame(RenderGameOverlayEvent.Pre event)
		{
			for(Entity e : net.minecraft.client.Minecraft.getMinecraft().world.getLoadedEntityList())
				e.setGlowing(e instanceof EntityLivingBase &&
						net.minecraft.client.Minecraft.getMinecraft().player.getDistance(e) < AccessoryConfig.enderPendantDistance);
		}
	}
}