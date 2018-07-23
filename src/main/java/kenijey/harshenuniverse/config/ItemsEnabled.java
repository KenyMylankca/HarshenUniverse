package kenijey.harshenuniverse.config;

import kenijey.harshenuniverse.base.BaseEnabledConfig;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentTranslation;

public class ItemsEnabled extends BaseEnabledConfig<Item>
{
	@Override
	public String getNameType() {
		return "Items";
	}
	
	@Override
	protected boolean testIfLegit(Item componant) {
		boolean legit = componant.getRegistryName() != null;
		if(!legit)
			new NullPointerException("Tried to config a Item with no registry name. Item: " + componant.getClass());
		return super.testIfLegit(componant);
	}

	@Override
	protected String getComponantPathInConfig(Item componant) {
		return componant.getRegistryName().getResourcePath();
	}

	@Override
	protected String getComponantCommentName(Item componant) {
		return new TextComponentTranslation(componant.getUnlocalizedName() + ".name").getUnformattedText();
	}
}