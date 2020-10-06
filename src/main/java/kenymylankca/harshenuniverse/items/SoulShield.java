package kenymylankca.harshenuniverse.items;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenSounds;
import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SoulShield extends Item implements IHarshenProvider
{
	public SoulShield() {
		setRegistryName("soul_shield");
		setUnlocalizedName("soul_shield");
		setMaxDamage(270);
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.NECK;
	}
	
	@Override
	public void onAdd(EntityPlayer player, int slot) {
		player.playSound(HarshenSounds.SOUL_SHIELD_ADD, 1f, 1f);
		IHarshenProvider.super.onAdd(player, slot);
	}
	
	@Override
	public void onRemove(EntityPlayer player, int slot) {
		player.playSound(HarshenSounds.SOUL_SHIELD_REMOVE, 1f, 1f);
		IHarshenProvider.super.onRemove(player, slot);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == HarshenItems.HARSHEN_SOUL_FRAGMENT;
	}
}