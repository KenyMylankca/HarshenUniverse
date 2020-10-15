package kenymylankca.harshenuniverse.potions;

import kenymylankca.harshenuniverse.HarshenUniverse;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionCureal extends Potion
{
	protected PotionCureal()
	{
		super(false, 0xFFFFFF);
		setRegistryName("cureal");
		setPotionName("Cureal");
		setIconIndex(2, 0);
		setBeneficial();
	}
	
	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(HarshenUniverse.MODID, "textures/gui/inventory.png"));
		return super.getStatusIconIndex();
	}
}