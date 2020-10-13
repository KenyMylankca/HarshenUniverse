package kenymylankca.harshenuniverse.handlers.client;

import kenymylankca.harshenuniverse.inventory.GuiHarshenButton;
import kenymylankca.harshenuniverse.inventory.GuiHarshenInventory;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HandlerGuiEvent 
{
	@SubscribeEvent
	public void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
		if (event.getGui() instanceof GuiInventory || event.getGui() instanceof GuiHarshenInventory){
			GuiContainer gui = (GuiContainer) event.getGui();
			event.getButtonList().add(new GuiHarshenButton(8178, gui, 140, 51, 24, 24,
					I18n.translateToLocalFormatted((event.getGui() instanceof GuiInventory) ? "button.harsheninventory" : "button.normal"), event.getGui() instanceof GuiInventory));
		}
	}
}