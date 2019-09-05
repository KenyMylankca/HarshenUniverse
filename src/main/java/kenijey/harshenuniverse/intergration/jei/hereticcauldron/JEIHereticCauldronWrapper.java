package kenijey.harshenuniverse.intergration.jei.hereticcauldron;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.api.CauldronLiquid;
import kenijey.harshenuniverse.base.BaseJeiWrapper;
import kenijey.harshenuniverse.enums.items.GlassContainerValue;
import kenijey.harshenuniverse.recipes.CauldronRecipes;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class JEIHereticCauldronWrapper extends BaseJeiWrapper
{
	private final List<List<ItemStack>> input;
	private final ItemStack output;
	private final CauldronLiquid catalyst;

	@SuppressWarnings("unchecked")
	public JEIHereticCauldronWrapper(CauldronRecipes recipe) {
		ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
		builder.add(recipe.getInput().getStackList());
		builder.add(ImmutableList.of(GlassContainerValue.getContainerFromType(recipe.getCatalyst()).getStack()));
		builder.add(ImmutableList.of(new ItemStack(HarshenBlocks.HERETIC_CAULDRON)));
		input = builder.build();
		output = recipe.getOutput();
		catalyst = recipe.getCatalyst();
	}

	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}
	
	public CauldronLiquid getCatalyst() 
	{
		return catalyst;
	}
}