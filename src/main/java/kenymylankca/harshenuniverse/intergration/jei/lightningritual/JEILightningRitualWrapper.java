package kenymylankca.harshenuniverse.intergration.jei.lightningritual;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import kenymylankca.harshenuniverse.api.HarshenStack;
import kenymylankca.harshenuniverse.base.BaseJeiWrapper;
import kenymylankca.harshenuniverse.recipes.LightningRitualRecipes;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class JEILightningRitualWrapper extends BaseJeiWrapper
{
	private final List<List<ItemStack>> input;
	private final ItemStack output;

	@SuppressWarnings("unchecked")
	public JEILightningRitualWrapper(LightningRitualRecipes recipe) {
		ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
		for(HarshenStack hStack : recipe.getInputs())
			builder.add(hStack.getStackList());
		input = builder.build();
		output = recipe.getOutput();
	}
	
	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}
}