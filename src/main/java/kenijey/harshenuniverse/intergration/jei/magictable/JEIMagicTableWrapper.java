package kenijey.harshenuniverse.intergration.jei.magictable;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import kenijey.harshenuniverse.api.HarshenStack;
import kenijey.harshenuniverse.base.BaseJeiWrapper;
import kenijey.harshenuniverse.recipes.MagicTableRecipe;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class JEIMagicTableWrapper extends BaseJeiWrapper
{
	private final List<List<ItemStack>> input;
	private final ItemStack output;

	@SuppressWarnings("unchecked")
	public JEIMagicTableWrapper(MagicTableRecipe recipe) {
		ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
		for(HarshenStack stack : recipe.getInputStacks())
			builder.add(stack.getStackList());
		input = builder.build();
		output = recipe.getOutput();
	}
	
	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}
}