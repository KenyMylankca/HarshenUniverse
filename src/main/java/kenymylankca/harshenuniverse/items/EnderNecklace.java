package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.HarshenEvent;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import kenymylankca.harshenuniverse.config.AccessoryConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class EnderNecklace extends Item implements IHarshenAccessoryProvider
{
	public EnderNecklace() {
		setRegistryName("ender_necklace");
		setUnlocalizedName("ender_necklace");
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.NECK;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return new EnderNecklaceHandler();
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
		IHarshenAccessoryProvider.super.onAdd(player, slot);
	}
	
	public class EnderNecklaceHandler
	{
		@HarshenEvent
		public void renderGame(RenderGameOverlayEvent.Pre event)
		{
			for(Entity e : net.minecraft.client.Minecraft.getMinecraft().world.getLoadedEntityList())
				e.setGlowing(e instanceof EntityLivingBase &&
						net.minecraft.client.Minecraft.getMinecraft().player.getDistance(e) < AccessoryConfig.enderNecklaceDistance);
		}
	}
}