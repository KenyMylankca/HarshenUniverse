package kenijey.harshenuniverse.base;

import javax.annotation.Nonnull;

import kenijey.harshenuniverse.HarshenUniverse;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public abstract class BaseJeiCategory implements IRecipeCategory{

	protected final IDrawable background;
	protected final String localizedName;
	protected final IDrawable overlay;
	protected final String UID;

	public BaseJeiCategory(String UID, IRecipeCategoryRegistration reg) {
		IGuiHelper guiHelper = reg.getJeiHelpers().getGuiHelper();
		this.UID = UID;
		String name = UID.split("\\.")[UID.split("\\.").length-1];
		background = guiHelper.createBlankDrawable(150, 100 + (render2PerPage()? 0 : 10));
		localizedName = I18n.format("jei." + name + ".name");
		overlay = getOverlay(guiHelper, name);
		createDrawable(guiHelper);
	}
	
	protected boolean render2PerPage()
	{
		return true;
	}
	
	protected IDrawable getOverlay(IGuiHelper guiHelper, String name)
	{
		return guiHelper.createDrawable(new ResourceLocation(HarshenUniverse.MODID, "textures/gui/jei/" + name + ".png"), 0, 0, 150, 110, 150, 110);
	}

	@Override
	public String getUid() {
		return UID;
	}

	@Override
	public String getTitle() {
		return localizedName;
	}

	@Override
	public String getModName() {
		return HarshenUniverse.MODID;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	public void drawExtras(@Nonnull Minecraft minecraft) {
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		overlay.draw(minecraft);
		drawMore(minecraft);
		GlStateManager.disableBlend();
		GlStateManager.disableAlpha();
	}
	
	protected void drawMore(@Nonnull Minecraft minecraft)
	{
	}
	
	protected void createDrawable(IGuiHelper helper)
	{
	}
}
	
