package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;

public class FeatherEarring extends Item implements IHarshenAccessoryProvider
{
	public FeatherEarring()
	{
		setUnlocalizedName("feather_earring");
		setRegistryName("feather_earring");
	}

	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.LEFT_EAR;
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
	
	@Override
	public void onTick(EntityPlayer player, int slot)
	{
		player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 50, HarshenUtils.hasAccessoryTimes(player, HarshenItems.FEATHER_EARRING)-1, false, false));
		IHarshenAccessoryProvider.super.onTick(player, slot);
	}
}