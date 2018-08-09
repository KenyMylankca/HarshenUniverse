package kenijey.harshenuniverse.items;

import java.util.List;

import kenijey.harshenuniverse.base.BaseItemMetaData;
import kenijey.harshenuniverse.enums.items.EnumRitualStick;

public class RitualStick extends BaseItemMetaData
{
	public RitualStick()
	{
		setUnlocalizedName("ritual_stick");
		setRegistryName("ritual_stick");
	}

	@Override
	protected List<Integer> getMetaForTab() {
		return null;
	}

	@Override
	protected String[] getNames() {
		return EnumRitualStick.getNames();
	}
}