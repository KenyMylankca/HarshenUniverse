package kenymylankca.harshenuniverse.inventory;

import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotHarshenInventory extends SlotItemHandler
{
	private final EnumAccessoryInventorySlots slotType;
	
	public SlotHarshenInventory(IItemHandler itemHandler, EnumAccessoryInventorySlots slotType, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		this.slotType = slotType;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return HarshenUtils.hasProvider(stack) && HarshenUtils.isSlotAllowed(stack, this.slotType, HarshenUtils.getProvider(stack).getSlot());
	}
}