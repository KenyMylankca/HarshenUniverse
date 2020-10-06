package kenymylankca.harshenuniverse.renderers.tileentity;

import java.awt.Point;

import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.tileentity.TileEntityHarshenMagicTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class RendererMagicTable extends TileEntitySpecialRenderer<TileEntityHarshenMagicTable>
{
	public static EntityItem RESULT_ITEM;
	@Override
	public void render(TileEntityHarshenMagicTable te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		int rotateAngle2 = te.getTimer() * 6;
		RESULT_ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, te.getItem(4));
		RESULT_ITEM.hoverStart = 1.55f;
		GlStateManager.pushMatrix();
		{
			if (!te.getItem(4).isEmpty()) Minecraft.getMinecraft().getRenderManager().renderEntity(RESULT_ITEM, x+0.5, y+0.52, z+0.5, 0, 0, false);
		} GlStateManager.popMatrix();
		
		if(te.getWorld().getBlockState(te.getPos().up()).isOpaqueCube())
			return;
		int itemsTotal = 0;
		int getter = 0;
		for(int i = 0; i < 4; i++)
			itemsTotal += te.getHandler().getStackInSlot(i).isEmpty() ? 0 : 1;
		for(int i = 0; i < 4; i++)
		{
			if(te.getHandler().getStackInSlot(i).isEmpty())
				continue;
			ItemStack stack = te.getHandler().getStackInSlot(i).copy();
			stack.setCount(1);
			EntityItem item = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, stack);
			item.hoverStart = 0;
			int rotateAngle = te.getTimer() * 6;
			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(x, y, z);
				float accuracy = 1000000f;
				Point p = HarshenUtils.getPointsAroundCenter(new Point((int) (Math.sin(te.getTimer() / 10f % 100f) * accuracy), (int) (Math.cos(te.getTimer() / 10f % 100f) * accuracy)), new Point(0, 0), itemsTotal).get(getter++);
				GlStateManager.translate(p.x / accuracy / 2 + 0.5f, 1f, p.y / accuracy / 2 + 0.5f);
				GlStateManager.scale(0.7f, 0.7f, 0.7f);
				GlStateManager.translate(0, Math.sin(rotateAngle) / 20f, 0);
				GlStateManager.rotate(te.getTimer() % 360 * 6f , 0, 1, 0);
				Minecraft.getMinecraft().getRenderManager().renderEntity(item, 0f, 0f, 0f, 0f, 0f, false);
			}
			GlStateManager.popMatrix();
		}
	}
}