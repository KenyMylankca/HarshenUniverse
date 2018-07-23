package kenijey.harshenuniverse.intergration.jei.hereticritual;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.api.CauldronLiquid;
import kenijey.harshenuniverse.base.BaseJeiCategory;
import kenijey.harshenuniverse.items.GlassContainer;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class JEIHereticRitualCategory extends BaseJeiCategory
{
	public JEIHereticRitualCategory(String UID, IRecipeCategoryRegistration reg) {
		super(UID, reg);
	}
	
	@Override
	protected boolean render2PerPage() {
		return false;
	}
	
	private static HashMap<CauldronLiquid, IDrawable> fluidTypes = new HashMap<>();
	
	private IDrawable currentFluid;
	private IDrawable ritualFront;
	private String name;
	private CauldronLiquid catalyst;

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if(!(recipeWrapper instanceof JEIHereticRitualWrapper))
			return;
		JEIHereticRitualWrapper wrapper = (JEIHereticRitualWrapper) recipeWrapper;
		catalyst = wrapper.getCatalyst();
		if(!catalyst.hasState())
			currentFluid = fluidTypes.get(catalyst);
		else
			currentFluid = null;
		name = GlassContainer.getGlassContaining(catalyst);
		int index = 1;
		Point center = new Point(75, 46);
		Point point = new Point(center.x, center.y - 52);

		recipeLayout.getItemStacks().init(0, true, 7, 14);
		recipeLayout.getItemStacks().set(0, ingredients.getInputs(ItemStack.class).get(0));
		
		List<List<ItemStack>> stackList = ingredients.getInputs(ItemStack.class);
		List<List<ItemStack>> revampedList = new ArrayList<>();
		stackList.remove(0);
		for(int i = 0; i < 8; i++)
			revampedList.add(stackList.get(i));
		for(int i = 0; i < 8; i++) {
			recipeLayout.getItemStacks().init(index, true, point.x, point.y);
			recipeLayout.getItemStacks().set(index, revampedList.get(i));
			index += 1;
			point = HarshenUtils.rotatePointAbout(point, center, 45D);
		}

		recipeLayout.getItemStacks().init(index, false, 4, 88);
		recipeLayout.getItemStacks().set(index, ingredients.getOutputs(ItemStack.class).get(0));
	}
	
	@Override
	protected void createDrawable(IGuiHelper helper) {
		for(CauldronLiquid fluid : CauldronLiquid.ALL_LIQUIDS)
			if(!fluid.hasState() && fluid != CauldronLiquid.NONE)
				fluidTypes.put(fluid, helper.createDrawable((ResourceLocation) fluid.getStateOrLoc(), 0, 0, 17, 4, 17, 4));
		
		ritualFront = helper.createDrawable(new ResourceLocation(HarshenUniverse.MODID, "textures/gui/jei/heretic_ritual-front.png"), 0, 0, 150, 110, 150, 110);

	}
	
	@Override
	protected void drawMore(Minecraft minecraft) {
		if(currentFluid != null)
			currentFluid.draw(minecraft, 6, 46);
		else
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			TextureAtlasSprite textureSprite = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture((IBlockState) catalyst.getStateOrLoc());
			Minecraft.getMinecraft().currentScreen.drawTexturedModalRect(6, 46, textureSprite, 17, 17);

		}
        ritualFront.draw(minecraft);
	}
	
	@Override
	public List getTooltipStrings(int mouseX, int mouseY) {
		if(mouseX > 5 && mouseX < 24 && mouseY > 45 && mouseY < 51)
			return HarshenUtils.toArray(new TextComponentTranslation(name).getFormattedText());
		return super.getTooltipStrings(mouseX, mouseY);
	}
}