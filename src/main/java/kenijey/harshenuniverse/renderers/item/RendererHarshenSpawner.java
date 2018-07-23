package kenijey.harshenuniverse.renderers.item;

import kenijey.harshenuniverse.tileentity.TileEntityHarshenSpawner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RendererHarshenSpawner extends TileEntitySpecialRenderer<TileEntityHarshenSpawner>
{
	public static Entity ENTITY;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void render(TileEntityHarshenSpawner te, double x, double y, double z, float partialTicks,
			int destroyStage, float alpha) {
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		ENTITY = te.getEntity(te.getItem());
		if(ENTITY == null)
			return;
		GlStateManager.pushMatrix();
		{	
			GlStateManager.translate(x, y, z);
			GlStateManager.translate(0.5f,0.1f,0.5f);
			float i = 0.7f / ENTITY.height;
			GlStateManager.scale(i, i, i);
			GlStateManager.rotate(te.getTimer() % 360 * 10f, 0, 1, 0);
			Minecraft.getMinecraft().getRenderManager().renderEntity(ENTITY, 0f, 0f, 0f, 0f, 0f, false);
			
		}
		GlStateManager.popMatrix();
	}
}