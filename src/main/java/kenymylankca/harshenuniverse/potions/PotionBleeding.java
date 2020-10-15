package kenymylankca.harshenuniverse.potions;

import kenymylankca.harshenuniverse.base.BaseHarshenPotion;
import net.minecraft.entity.SharedMonsterAttributes;

public class PotionBleeding extends BaseHarshenPotion
{
	public PotionBleeding() {
		super(true, 0xAA0000);
		setRegistryName("bleeding");
		setPotionName("Bleeding");
		setIconIndex(3, 0);
		registerPotionAttributeModifier(SharedMonsterAttributes.FOLLOW_RANGE, "673fe7b0-6db1-4825-a548-eec16d866348", 0.15000000596046448D, 2);
	}
}