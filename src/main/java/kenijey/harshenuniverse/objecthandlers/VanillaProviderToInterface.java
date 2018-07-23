package kenijey.harshenuniverse.objecthandlers;

import kenijey.harshenuniverse.api.EnumInventorySlots;
import kenijey.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.ItemStack;

public class VanillaProviderToInterface implements IHarshenProvider
{
	private final EnumInventorySlots slot;
	private final Object provider;
	
	private final boolean multiplyEvent;
	private final int toolTipLines;

	
	public VanillaProviderToInterface(EnumInventorySlots slot, Object provider, boolean multiplyEvent, int toolTipLines) {
		this.slot = slot;
		this.provider = provider;
		this.multiplyEvent = multiplyEvent;
		this.toolTipLines = toolTipLines;
	}
	
	public boolean isMultiplyEvent(ItemStack stack) {
		return multiplyEvent;
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return slot;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return provider;
	}
	
	@Override
	public int toolTipLines() {
		return toolTipLines;
	}
}