package kenymylankca.harshenuniverse.renderers.tileentity;

import java.util.HashMap;
import java.util.Random;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.enums.particle.EnumHarshenParticle;
import kenymylankca.harshenuniverse.particle.ParticleCauldronTop;
import kenymylankca.harshenuniverse.tileentity.TileEntityHereticCauldron;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RendererHereticCauldron extends TileEntitySpecialRenderer<TileEntityHereticCauldron>
{
	public static EntityItem ITEM;
	private boolean switched = false, switchedItem = false;
	public boolean finished = false;
	private HashMap<BlockPos, Particle> particleMap = new HashMap<>();
	private HashMap<BlockPos, Double> levelMove = new HashMap<>();
	private HashMap<BlockPos, Integer> serialMove = new HashMap<>();
	
	@SideOnly(Side.CLIENT)
	@Override
	public void render(TileEntityHereticCauldron te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, te.getItemStack());
		ITEM.hoverStart = 0.0f;
		GlStateManager.pushMatrix();
		{
			if((ParticleCauldronTop)particleMap.get(te.getPos()) != null)
				((ParticleCauldronTop)particleMap.get(te.getPos())).kill();
			if(!levelMove.containsKey(te.getPos()))
				levelMove.put(te.getPos(), (double) te.getLevel());
			if(!serialMove.containsKey(te.getPos()))
				serialMove.put(te.getPos(), 0);
			serialMove.put(te.getPos(), serialMove.get(te.getPos()) + 1);
			if(levelMove.get(te.getPos()).floatValue() != te.getLevel())
			{
				float moveBy = 0.08f;
				levelMove.put(te.getPos(), levelMove.get(te.getPos()) + (Math.min(levelMove.get(te.getPos()), te.getLevel()) == te.getLevel()? - moveBy : moveBy));
			}
			if(te.getLevel() > 0)
				particleMap.put(te.getPos(), HarshenUniverse.commonProxy.spawnParticle(EnumHarshenParticle.CAULDRON_LIQUID, new Vec3d(te.getPos()).addVector(0.5D, MathHelper.clamp(Math.sin(serialMove.get(te.getPos()) / 200d) / 50d + 0.05D + levelMove.get(te.getPos()) / 4d, 0.2D, 1D), 0.5D), Vec3d.ZERO, -1, true, te.getFluid().getStateOrLoc()));
			GlStateManager.translate(x, y, z);
			GlStateManager.translate(0.5f, 1.45f,0.5f);
			boolean flag = true;
			if(te.isActive)
			{
				if(te.getActiveTimer() < 50)
					GlStateManager.translate(0, -te.getActiveTimer() / 40f, 0);
				else 
				{
					GlStateManager.translate(0, -1.225f, 0);
					if(te.getActiveTimer() >= 130)
						GlStateManager.translate(0, ((te.getActiveTimer()-130) / 40f), 0);
					else if(te.getActiveTimer() >= 50)
					{
						ItemStack stack = null;
						if(new Random().nextInt(MathHelper.floor(MathHelper.clamp(130 - te.getActiveTimer(), 1, 130))) <= 2 && !te.getItemStack().isEmpty())
							stack = te.getItemStack();
						else if(!te.getSwitchedItem().isEmpty())
							stack = te.getSwitchedItem();
						flag = false;
						for(int i = 0; i < 45; i ++)
						{
							HarshenUniverse.commonProxy.spawnParticle(EnumHarshenParticle.ITEM, 
									new Vec3d(te.getPos()).addVector(0.5d, 0.6d + (te.getActiveTimer() >= 130? (te.getActiveTimer()-130) / 40f : 0), 0.5d), Vec3d.ZERO, 1f, false, stack);
						}
					}
					else if (te.getActiveTimer() <= 105)
						for(int i = 0; i < 15; i ++)
						{
							Vec3d vec = new Vec3d((new Random().nextDouble() - 0.5D) / 1.5D, new Random().nextDouble() / 4D, (new Random().nextDouble() - 0.5D) / 1.5D);
							HarshenUniverse.commonProxy.spawnParticle(EnumHarshenParticle.CAULDRON, new Vec3d(te.getPos()).addVector(0.5d, 0.225d, 0.5d).add(vec), 
									new Vec3d(-vec.x / 20D, -vec.y / 20D, -vec.z / 20D), 1f, false, te.getWorkingFluid().getStateOrLoc());
						}
				}	
			}
			else
				GlStateManager.translate(0, Math.sin(te.getTimer() / 5f) / 15f, 0);

			GlStateManager.scale(0.7f, 0.7f, 0.7f);
			GlStateManager.rotate(te.getTimer() % 360 * 10, 0, 1, 0);
			if(flag)
				Minecraft.getMinecraft().getRenderManager().renderEntity(ITEM, 0f, 0f, 0f, 0f, 0f, false);
		}
		GlStateManager.popMatrix();
	}
}