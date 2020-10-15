package kenymylankca.harshenuniverse.base;

import kenymylankca.harshenuniverse.HarshenUniverse;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class BaseHarshenPotion extends Potion
{
	protected BaseHarshenPotion(boolean isBadEffect, int liquidColorIn)
	{
		super(isBadEffect, liquidColorIn);
	}
	
	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(HarshenUniverse.MODID, "textures/gui/inventory.png"));
		return super.getStatusIconIndex();
	}
}