package kenymylankca.harshenuniverse.integration.jei.hereticritual;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.api.CauldronLiquid;
import kenymylankca.harshenuniverse.api.HarshenStack;
import kenymylankca.harshenuniverse.base.BaseJeiWrapper;
import kenymylankca.harshenuniverse.enums.items.GlassContainerValue;
import kenymylankca.harshenuniverse.recipes.HereticRitualRecipes;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class JEIHereticRitualWrapper extends BaseJeiWrapper	
{
	private final ItemStack output;
	private final CauldronLiquid catalyst;
	private final List<List<ItemStack>> allInputs;

	@SuppressWarnings("unchecked")
	public JEIHereticRitualWrapper(HereticRitualRecipes recipe) {
		ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
		builder.add(recipe.getCauldronInput().getStackList());
		for(HarshenStack stack : recipe.getPedestalItems())
			builder.add(stack.getStackList());
		builder.add(ImmutableList.of(GlassContainerValue.getContainerFromType(recipe.getCatalyst()).getStack()));
		builder.add(ImmutableList.of(new ItemStack(HarshenBlocks.HERETIC_CAULDRON)));
		output = recipe.getOutput();
		allInputs = builder.build();
		catalyst = recipe.getCatalyst();
	}
	
	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, allInputs);
		ingredients.setOutput(ItemStack.class, output);
	}
	
	public CauldronLiquid getCatalyst() 
	{
		return catalyst;
	}
}