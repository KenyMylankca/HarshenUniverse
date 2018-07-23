package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.HarshenSounds;
import kenijey.harshenuniverse.base.BaseHarshenBow;

public class EnionBow extends BaseHarshenBow
{
	public EnionBow()
	{
		super(HarshenSounds.LIGHTNING_HIT);
		setUnlocalizedName("enion_bow");
		setRegistryName("enion_bow");
	}

	@Override
	public int getMaxDamage() {
		return 1837;
	}

	@Override
	public double additionDamage() {
		return 2.0D;
	}
}