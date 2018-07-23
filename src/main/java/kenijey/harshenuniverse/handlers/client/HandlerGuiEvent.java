package kenijey.harshenuniverse.handlers.client;

import kenijey.harshenuniverse.inventory.GuiHarshenButton;
import kenijey.harshenuniverse.inventory.GuiPlayerInventoryExtended;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerGuiEvent 
{
	@SubscribeEvent
	public void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
		if (event.getGui() instanceof GuiInventory || event.getGui() instanceof GuiPlayerInventoryExtended){
			GuiContainer gui = (GuiContainer) event.getGui();
			event.getButtonList().add(new GuiHarshenButton(8178, gui, 140, 51, 24, 24,
					I18n.translateToLocalFormatted((event.getGui() instanceof GuiInventory) ? "button.harsheninventory" : "button.normal"), event.getGui() instanceof GuiInventory));
		}
	}
}
