package kenymylankca.harshenuniverse.potions;

import kenymylankca.harshenuniverse.base.BaseHarshenPotion;

public class PotionSoulless extends BaseHarshenPotion
{
	protected PotionSoulless() {
		super(true, 0x000000);
		setRegistryName("soulless");
		setPotionName("Soulless");
		setIconIndex(0, 0);
		setBeneficial();
	}
}