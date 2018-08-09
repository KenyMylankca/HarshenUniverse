package kenijey.harshenuniverse.items;

import java.util.List;

import kenijey.harshenuniverse.base.BaseItemMetaData;
import kenijey.harshenuniverse.enums.items.EnumRitualCrystal;

public class RitualCrystal extends BaseItemMetaData
{
	public RitualCrystal() {
		setRegistryName("ritual_crystal");
		setUnlocalizedName("ritual_crystal");
		this.setHasSubtypes(true);
	}
	
	@Override
	protected String[] getNames() {
		return EnumRitualCrystal.getNames();
	}

	@Override
	protected List<Integer> getMetaForTab() {
		return null;
	}
}