package kenijey.harshenuniverse.config;

import kenijey.harshenuniverse.base.BaseEnabledConfig;
import net.minecraft.block.Block;
import net.minecraft.util.text.TextComponentTranslation;

public class BlocksEnabled extends BaseEnabledConfig<Block>
{
	@Override
	public String getNameType() {
		return "Blocks";
	}
	
	@Override
	protected boolean testIfLegit(Block componant) {
		boolean legit = componant.getRegistryName() != null;
		if(!legit)
			new NullPointerException("Tried to config a Item with no registry name. Item: " + componant.getClass());
		return super.testIfLegit(componant);
	}

	@Override
	protected String getComponantPathInConfig(Block componant) {
		return componant.getRegistryName().getResourcePath();
	}

	@Override
	protected String getComponantCommentName(Block componant) {
		return new TextComponentTranslation(componant.getUnlocalizedName() + ".name").getUnformattedText();
	}
}