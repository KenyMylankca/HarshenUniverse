package kenijey.harshenuniverse.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.enums.gui.EnumGuiPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiBookScreen extends GuiScreen
{
	private GuiButton buttonExit;
	private GuiTextField mainText;
	private ArrayList<GuiButton> categoryButtons = new ArrayList<GuiButton>();
	private ArrayList<Gui> allComponants = new ArrayList<Gui>();
	private EnumGuiPage mode = EnumGuiPage.values()[0];
	private int scroll;
    
	
	@Override
	public void initGui() {
		this.buttonExit = button(0, this.width / 2 - 100, (int) this.height - 30, 200, 20, "gui.done");
		categorybuttons();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, "Harshen Universe " + mode.getTag(), this.width / 2, 4, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}	
	
	@Override
	public void handleMouseInput() throws IOException {
		if(mode.getId() != -1)
			if(Mouse.getEventDWheel() == -120)
				scroll += 15;
			else if(Mouse.getEventDWheel() == 120)
				scroll -= 15;
		super.handleMouseInput();
	}
	
	@Override
	public void drawDefaultBackground() {
		super.drawDefaultBackground();
		ResourceLocation location = new ResourceLocation(HarshenUniverse.MODID, "textures/gui/category/" + this.mode.getName().toLowerCase()  + ".png");
		this.mc.getTextureManager().bindTexture(location);
        try {
    		BufferedImage i = TextureUtil.readBufferedImage(Minecraft.getMinecraft().getResourceManager().getResource(location).getInputStream());
			this.drawModalRectWithCustomSizedTexture(50, 50, 0, scroll, this.width - 100, this.height - 100, this.width - 100, (this.width - 100) * ((float)i.getHeight() / i.getWidth()));
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button){
		if(button.id == 0)
			this.mc.displayGuiScreen((GuiScreen)null);
		if(button.id >= 8800)
			this.mode = EnumGuiPage.buttonPages()[button.id - 8800];
	}
	
	
	private void categorybuttons()
	{
		for(EnumGuiPage page : EnumGuiPage.buttonPages())
			categoryButtons.add(button(page.getId() + 8800, (page.getId() * (75 + 10)) + this.width / 2 - (((75 + 10)
					* EnumGuiPage.buttonPages().length - 10) / 2), 15, 75, 20, "gui.category." + page.getName().toLowerCase()));
		for(GuiButton b : categoryButtons)
			allComponants.add(b);
	}
	private GuiButton button(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText)
	{
		allComponants.add(addButton(new GuiButton(buttonId, x, y, widthIn, heightIn, I18n.format(buttonText))));
		return (GuiButton) allComponants.get(allComponants.size() - 1);
	}
}
