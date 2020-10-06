package kenymylankca.harshenuniverse.potions;

import kenymylankca.harshenuniverse.HarshenUniverse;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionSoulless extends Potion
{
	protected PotionSoulless() {
		super(false, 0x000000);
		setRegistryName("soulless");
		setPotionName("Soulless");
		setIconIndex(0, 0);
		setBeneficial();
	}
	
	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(HarshenUniverse.MODID, "textures/gui/inventory.png"));
		return super.getStatusIconIndex();
	}
}