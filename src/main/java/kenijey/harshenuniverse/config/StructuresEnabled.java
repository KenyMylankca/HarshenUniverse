package kenijey.harshenuniverse.config;

import kenijey.harshenuniverse.base.BaseEnabledConfig;
import kenijey.harshenuniverse.base.HarshenStructure;
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