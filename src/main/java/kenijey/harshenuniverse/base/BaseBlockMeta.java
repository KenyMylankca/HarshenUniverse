package kenijey.harshenuniverse.base;

import kenijey.harshenuniverse.interfaces.IMetaItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BaseBlockMeta extends ItemBlock
{
	public BaseBlockMeta(Block block) {
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + "." + ((IMetaItemBlock)this.block).getNames()[stack.getMetadata()];
	}

}
