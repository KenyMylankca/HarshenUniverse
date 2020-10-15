package kenymylankca.harshenuniverse.potions;

import kenymylankca.harshenuniverse.base.BaseHarshenPotion;

public class PotionCureal extends BaseHarshenPotion
{
	protected PotionCureal() {
		super(false, 0xFFFFFF);
		setRegistryName("cureal");
		setPotionName("Cureal");
		setIconIndex(2, 0);
		setBeneficial();
	}
}