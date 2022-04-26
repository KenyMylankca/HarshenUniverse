package com.kenymylankca.harshenuniverse.config;

import kenymylankca.harshenuniverse.base.BaseEnabledConfig;
import kenymylankca.harshenuniverse.base.HarshenStructure;
import net.minecraft.util.text.TextComponentTranslation;

public class StructuresEnabled extends BaseEnabledConfig<HarshenStructure>
{
	@Override
	public String getNameType() {
		return "Structures";
	}

	@Override
	protected String getComponantPathInConfig(HarshenStructure componant) {
		return componant.showName;
	}

	@Override
	protected String getComponantCommentName(HarshenStructure componant) {
		return new TextComponentTranslation("config.structure.name", componant.showName).getUnformattedText();
	}
}