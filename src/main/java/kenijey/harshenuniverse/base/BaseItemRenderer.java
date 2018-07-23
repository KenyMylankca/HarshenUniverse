package kenijey.harshenuniverse.base;


import javax.vecmath.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;

public abstract class BaseItemRenderer<T extends BaseTileEntityHarshenSingleItemInventory> extends TileEntitySpecialRenderer<T>
{
	@Override
	public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		EntityItem item = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, te.getItem());
		item.hoverStart = 0.0f;
		int rotateAngle = te.getTimer() * 6;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x, y, z);
			GlStateManager.translate(movePos().x, movePos().y, movePos().z);
			GlStateManager.scale(0.7f, 0.7f, 0.7f);
			GlStateManager.translate(0, Math.sin(rotateAngle) / 20f, 0);
			moveMore(te);
			GlStateManager.rotate(te.getTimer() % 360 * getMovementSpeed(te), 0, 1, 0);
			Minecraft.getMinecraft().getRenderManager().renderEntity(item, 0f, 0f, 0f, 0f, 0f, false);
			
		}
		GlStateManager.popMatrix();
	}
	
	
	protected abstract float getMovementSpeed(T te);
	
	protected abstract Vector3f movePos();
	
	protected void moveMore(T te)
	{
		
	}
}
