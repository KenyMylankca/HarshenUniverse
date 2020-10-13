package kenymylankca.harshenuniverse.handlers.client;

import java.util.ArrayList;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HandlerRendererGuiInventory 
{
	String previousXrayBlock = "";
	
	@SubscribeEvent
	public void onGameOverlay(RenderGameOverlayEvent.Post event)
	{
		Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = minecraft.player;

		if(event.getType() != RenderGameOverlayEvent.ElementType.ALL || minecraft.currentScreen != null)
			return;
		ArrayList<Block> blocks = new ArrayList<>();
		ItemStack xrayStack = ItemStack.EMPTY;
		for(int i = 0; i < player.inventory.getSizeInventory(); i++)
			if(player.inventory.getStackInSlot(i).getItem() == HarshenItems.XRAY_PENDANT)
			{
				xrayStack = player.inventory.getStackInSlot(i);
				if(player.inventory.getStackInSlot(i).hasTagCompound())
					blocks.addAll(HarshenUtils.getBlocksFromString(player.inventory.getStackInSlot(i).getTagCompound().getString("BlockToSearch")));
			}
		if(HarshenUtils.getFirstOccuringItem(player,  HarshenItems.XRAY_PENDANT).hasTagCompound())
			blocks.addAll(HarshenUtils.getBlocksFromString(HarshenUtils.getFirstOccuringItem(player,  HarshenItems.XRAY_PENDANT).getTagCompound().getString("BlockToSearch")));
		ItemStack testStack = HarshenUtils.getFirstOccuringItem(player,  HarshenItems.XRAY_PENDANT);
		xrayStack = testStack.isEmpty() ? xrayStack : testStack;
		boolean flag = false;
		if(xrayStack.isEmpty())
			flag = true;
		if(!flag)
		{
			String brokenBlock = "";
			if(xrayStack.hasTagCompound())
				brokenBlock = xrayStack.getTagCompound().getString("BlockToSearch");
			if(blocks.isEmpty() && !previousXrayBlock.equalsIgnoreCase(brokenBlock))
			{
				player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_RED + new TextComponentTranslation("xray.blocknotfound", brokenBlock).getUnformattedText()), true); 
				previousXrayBlock = brokenBlock;
			}
		}
		
		for(int i = 0; i < HarshenUtils.getHandler(player).getSlots(); i++)
        {
        	ItemStack stack  = HarshenUtils.getHandler(player).getStackInSlot(i);
            if(!stack.isEmpty())
            {
         	   float f = (float)stack.getAnimationsToGo() - event.getPartialTicks();
         	   int x = event.getResolution().getScaledWidth() - (20 * (5 - i));
               int y = event.getResolution().getScaledHeight() - 19;
                if (f > 0.0F)
                {
                    GlStateManager.pushMatrix();
                    float f1 = 1.0F + f / 5.0F;
                    GlStateManager.translate((float)(x + 8), (float)(y + 12), 0.0F);
                    GlStateManager.scale(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
                    GlStateManager.translate((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
                }

                minecraft.getRenderItem().renderItemAndEffectIntoGUI(player, stack, x, y);
                
                if (f > 0.0F)
                    GlStateManager.popMatrix();
                minecraft.getRenderItem().renderItemOverlays(minecraft.fontRenderer, stack, x, y);
            }
        }
	}
}
