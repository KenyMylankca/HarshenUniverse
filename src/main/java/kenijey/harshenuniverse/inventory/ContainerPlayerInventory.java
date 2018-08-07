package kenijey.harshenuniverse.inventory;

import java.awt.Point;

import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.api.EnumInventorySlots;
import kenijey.harshenuniverse.base.BaseHarshenContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ContainerPlayerInventory extends BaseHarshenContainer
{
	public ContainerPlayerInventory(EntityPlayer player)
	{
		super(HarshenUtils.getHandler(player), player);
	}
	
	@Override
	protected Slot getSlot(ItemStackHandler handler, int index, int xPosition, int yPosition) {
		return new SlotHarshenInventory(handler, EnumInventorySlots.getFromMeta(index), index, xPosition, yPosition);
	}
	
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
		ItemStack stack = super.slotClick(slotId, dragType, clickTypeIn, player);
		player.getEntityData().setTag("harshenInventory", this.handler.serializeNBT());
		return stack;
	}

	@Override
	protected Point getPoint(int index) {
		return EnumInventorySlots.getFromMeta(index).getPoint();
	}

	@Override
	protected Point getInventoryStart() {
		return new Point(8, 84);
	}
}