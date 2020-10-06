package kenymylankca.harshenuniverse.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.network.HarshenNetwork;
import kenymylankca.harshenuniverse.network.packets.MessagePacketUpdateXrayBlock;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class GuiXrayPendantScreen extends GuiScreen
{
	private GuiButton buttonExit;
	private GuiTextField textInput;
	private ItemStack stack;
	
	public GuiXrayPendantScreen(ItemStack itemStack) {
		stack = itemStack;
	}

	@Override
	public void initGui() {
        Keyboard.enableRepeatEvents(true);
		this.buttonExit = addButton(new GuiButton(0, this.width / 2 - 100, (int) this.height - 30, 200, 20, I18n.format("gui.done")));
		this.textInput =  new GuiTextField(1, this.fontRenderer, this.width / 2 - 152, this.height / 2, 300, 20);
		this.textInput.setMaxStringLength(60);
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		this.textInput.setText(stack.getTagCompound().getString("BlockToSearch"));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, I18n.format("gui.xray.block"), this.width / 2, this.height / 2 - 20, 16777215);
		this.drawCenteredString(this.fontRenderer, I18n.format("gui.xray.example"), this.width / 2, this.height / 2 + 40, 16777215);
		this.textInput.drawTextBox();
		this.textInput.setFocused(true);
		ArrayList<Block> usedBlocks = new ArrayList<>();
		ArrayList<ItemStack> renderStacks = new ArrayList<>();
		Block overblock = Block.getBlockFromName(textInput.getText());
		if(overblock != null)
		{
			NonNullList<ItemStack> items = NonNullList.create();
			overblock.getSubBlocks(CreativeTabs.SEARCH, items);
			for(ItemStack stack : items)
				if(!stack.isEmpty())
						renderStacks.add(stack);
		}
		else
		{
			for(ItemStack stack : OreDictionary.getOres(textInput.getText(), false))
				if(stack.getItem() instanceof ItemBlock)
				{
					if(stack.getMetadata() == OreDictionary.WILDCARD_VALUE)
					{
						NonNullList<ItemStack> items = NonNullList.create();
						stack.getItem().getSubItems(CreativeTabs.SEARCH, items);
						for(ItemStack stack1 : items)
							if(!stack1.isEmpty())
									renderStacks.add(stack1);
					}
					else
						renderStacks.add(stack);
				}
		}
		boolean flag = false;
		int amount = 0;
		GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.0F, 32.0F);
        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableLighting();
        short short1 = 240;
        short short2 = 240;
        net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, short1 / 1.0F, short2 / 1.0F);
		while(!flag)
			flag = renderStacks(renderStacks, amount++);
		GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}	
	
	private boolean renderStacks(ArrayList<ItemStack> renderStacks, int amount)
	{
		boolean flag = renderStacks.size() > 16;
        int renderamount = flag ? 16 : renderStacks.size();
        for(int i = 0; i < renderamount; i ++)
    		itemRender.renderItemAndEffectIntoGUI(renderStacks.get(i), (this.width / 2) - (flag || renderamount % 2== 0 ? -3 : 7) - (((renderamount / 2) - (i % 16)) * 20), this.height / 2 - 50 - (amount*25));
        ArrayList<ItemStack> newStackList = new ArrayList<>();
        if(flag)
        	for(int i = 16; i < renderStacks.size(); i++)
        		newStackList.add(renderStacks.get(i));
        renderStacks.clear();
        renderStacks.addAll(newStackList);
        return !flag;
	}
	
	@Override
	public void drawDefaultBackground() {
		super.drawDefaultBackground();
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button){
		if(button.id == 0)
			closeGui();
	}
	
	private void closeGui()
	{
		this.mc.displayGuiScreen((GuiScreen)null);
		HarshenUtils.getNBT(stack).setString("BlockToSearch", textInput.getText());
		HarshenNetwork.sendToServer(new MessagePacketUpdateXrayBlock(stack.serializeNBT()));
	}
	
	int timeOver = 0;
	List<String> dictonaryList = new ArrayList<>();
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if(keyCode == Keyboard.KEY_TAB)
		{			this.textInput.setMaxStringLength(60);
		
			ArrayList<String> stringList = new ArrayList<>();
			if(dictonaryList.isEmpty())
			{
				stringList.addAll(CommandBase.getListOfStringsMatchingLastWord(HarshenUtils.listOf(this.textInput.getText()), Block.REGISTRY.getKeys()));
				for(String s : HarshenUtils.getAllOreDictionaryList())
					for(ItemStack stack : OreDictionary.getOres(s))
						if(Block.getBlockFromItem(stack.getItem()) != Blocks.AIR)
							stringList.add(s);
				dictonaryList = CommandBase.getListOfStringsMatchingLastWord(HarshenUtils.listOf(this.textInput.getText()), stringList);
			}
			if(!dictonaryList.isEmpty())
				this.textInput.setText(dictonaryList.get(timeOver++%dictonaryList.size()));
		}
		else
		{
			timeOver = 0;
			dictonaryList.clear();
		}
		
		if(keyCode == Keyboard.KEY_RETURN)
			closeGui();
		this.textInput.textboxKeyTyped(typedChar, keyCode);
		super.keyTyped(typedChar, keyCode);
	}
}