package kenymylankca.harshenuniverse.potions;

import kenymylankca.harshenuniverse.base.BaseHarshenPotion;
import net.minecraft.entity.SharedMonsterAttributes;

public class PotionHarshed extends BaseHarshenPotion
{
	public PotionHarshed() {
		super(true, 0xAA00AA);
		setRegistryName("harshed");
		setPotionName("Harshed");
		setIconIndex(1, 0);
		registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160891", -0.15000000596046448D, 2);
	}
}