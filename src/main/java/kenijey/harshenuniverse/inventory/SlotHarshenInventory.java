package kenijey.harshenuniverse.inventory;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.api.EnumInventorySlots;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotHarshenInventory extends SlotItemHandler
{
	
	private final EnumInventorySlots slotType;
	
	public SlotHarshenInventory(IItemHandler itemHandler, EnumInventorySlots slotType, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		this.slotType = slotType;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return HarshenUtils.hasProvider(stack) && HarshenUtils.isSlotAllowed(stack, this.slotType, HarshenUtils.getProvider(stack).getSlot());
	}
	
}
