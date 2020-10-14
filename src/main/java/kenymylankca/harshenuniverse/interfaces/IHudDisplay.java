package kenymylankca.harshenuniverse.interfaces;

import kenymylankca.harshenuniverse.HarshenUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IHudDisplay
{
	@SideOnly(Side.CLIENT)
	default void displayHud(Minecraft minecraft, EntityPlayer player, RayTraceResult posHit,
			ScaledResolution resolution)
	{
        minecraft.fontRenderer.drawStringWithShadow(getText(), resolution.getScaledWidth()/2 - TextFormatting.getTextWithoutFormattingCodes(getText()).length() * 2.5f, resolution.getScaledHeight()/2 + 15, HarshenUtils.DECIMAL_COLOR_WHITE);
	}
	
	String getText();
}