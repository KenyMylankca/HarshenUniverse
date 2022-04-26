package com.kenymylankca.harshenuniverse.items;

import java.util.List;

import kenymylankca.harshenuniverse.base.BaseItemMetaData;
import kenymylankca.harshenuniverse.enums.items.EnumRitualStick;

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