package kenymylankca.harshenuniverse.potions;

import kenymylankca.harshenuniverse.HarshenUniverse;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionBleeding extends Potion
{
	protected PotionBleeding() {
		super(true, 0xAA0000);
		setRegistryName("bleeding");
		setPotionName("Bleeding");
		setIconIndex(3, 0);
		registerPotionAttributeModifier(SharedMonsterAttributes.FOLLOW_RANGE, "673fe7b0-6db1-4825-a548-eec16d866348", 0.15000000596046448D, 2);
	}
	
	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(HarshenUniverse.MODID, "textures/gui/inventory.png"));
		return super.getStatusIconIndex();
	}
}