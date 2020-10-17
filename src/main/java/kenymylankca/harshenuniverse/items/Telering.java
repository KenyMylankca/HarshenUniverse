package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;

public class Telering extends Item implements IHarshenAccessoryProvider
{
	public Telering()
	{
		setUnlocalizedName("telering");
		setRegistryName("telering");
		setMaxDamage(700);
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.RING1;
	}
	
	@Override
	public void onTick(EntityPlayer player, int slot)
	{
		player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 105, HarshenUtils.hasAccessoryTimes(player, HarshenItems.TELERING)-1, false, false));
		IHarshenAccessoryProvider.super.onTick(player, slot);
	}
}