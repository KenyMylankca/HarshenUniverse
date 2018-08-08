package kenijey.harshenuniverse.inventory;

import kenijey.harshenuniverse.HarshenClientUtils;
import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.api.EnumAccessoryInventorySlots;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiHarshenInventory extends InventoryEffectRenderer
{
	/** The old x position of the mouse pointer */
    private float oldMouseX;
    /** The old y position of the mouse pointer */
    private float oldMouseY;
	
	public static ResourceLocation background = new ResourceLocation(HarshenUniverse.MODID,"textures/gui/inventory.png");

	private IInventory playerInv;
	
	public GuiHarshenInventory(EntityPlayer player)
	{
		super(new ContainerPlayerInventory(player));
		this.playerInv = player.inventory;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{	
		int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawDefaultBackground();
		this.mc.getTextureManager().bindTexture(background);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		for(EnumAccessoryInventorySlots slot : EnumAccessoryInventorySlots.values())
			if(!inventorySlots.getSlot(slot.getId()).getHasStack())
				HarshenClientUtils.drawTexture(slot.getPoint().x + this.guiLeft, slot.getPoint().y + this.guiTop,
						13.26f, 9.89f + (slot.getId()), 1f, 1f, 16, 16, 16, 16);
		GuiInventory.drawEntityOnScreen(i + 30, j + 75, 30, (float)(i + 51) - this.oldMouseX, (float)(j + 75 - 50) - this.oldMouseY, this.mc.player);
    }
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
		this.oldMouseX = (float)mouseX;
        this.oldMouseY = (float)mouseY;
	}
}