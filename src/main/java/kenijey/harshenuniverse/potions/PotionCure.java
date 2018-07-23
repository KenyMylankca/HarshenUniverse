package kenijey.harshenuniverse.potions;

import kenijey.harshenuniverse.HarshenUniverse;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionCure extends Potion
{
	protected PotionCure() {
		super(false, 0xFFFFFF);
		setRegistryName("cure");
		setPotionName("Cure");
		setIconIndex(2, 0);
		setBeneficial();
	}
	
	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(HarshenUniverse.MODID, "textures/gui/inventory.png"));
		return super.getStatusIconIndex();
	}
}