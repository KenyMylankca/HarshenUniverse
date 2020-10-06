package kenymylankca.harshenuniverse.objecthandlers;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenProvider;
import net.minecraft.item.ItemStack;

public class VanillaProviderToInterface implements IHarshenProvider
{
	private final EnumAccessoryInventorySlots slot;
	private final Object provider;
	
	private final boolean multiplyEvent;
	private final int toolTipLines;

	
	public VanillaProviderToInterface(EnumAccessoryInventorySlots slot, Object provider, boolean multiplyEvent, int toolTipLines) {
		this.slot = slot;
		this.provider = provider;
		this.multiplyEvent = multiplyEvent;
		this.toolTipLines = toolTipLines;
	}
	
	public boolean isMultiplyEvent(ItemStack stack) {
		return multiplyEvent;
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
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