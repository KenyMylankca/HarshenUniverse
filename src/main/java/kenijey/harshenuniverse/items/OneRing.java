package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.HarshenSounds;
import kenijey.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;

public class OneRing extends Item implements IHarshenProvider
{
	public OneRing()
	{
		setUnlocalizedName("one_ring");
		setRegistryName("one_ring");
	}

	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.RING1;
	}

	@Override
	public void onAdd(EntityPlayer player, int slot) {
		player.playSound(HarshenSounds.ONE_RING, 1f, 1f);
		IHarshenProvider.super.onAdd(player, slot);
	}
	
	@Override
	public void onRemove(EntityPlayer player, int slot) {
		player.playSound(SoundEvents.ENTITY_ENDEREYE_DEATH, 1f, 0.3f);
		IHarshenProvider.super.onRemove(player, slot);
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
}