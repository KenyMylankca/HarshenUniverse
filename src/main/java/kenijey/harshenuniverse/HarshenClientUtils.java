package kenijey.harshenuniverse;

import java.awt.Color;
import java.util.List;

import javax.annotation.Nullable;
import javax.vecmath.Vector4f;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import kenijey.harshenuniverse.handlers.GuiHandler;
import kenijey.harshenuniverse.network.HarshenNetwork;
import kenijey.harshenuniverse.network.packets.MessagePacketOpenInv;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fluids.BlockFluidBase;

public class HarshenClientUtils 
{
	public static void drawTexture(int x, int y, float u, float v, float uWidth, float vHeight, int width, int height, float tileWidth, float tileHeight)
	{
		float f = 1.0F / tileWidth;
        float f1 = 1.0F / tileHeight;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)x, (double)(y + height), 0.0D).tex((double)(u * f), (double)((v + vHeight) * f1)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), 0.0D).tex((double)((u + uWidth) * f), (double)((v + vHeight) * f1)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)y, 0.0D).tex((double)((u + uWidth) * f), (double)(v * f1)).endVertex();
        bufferbuilder.pos((double)x, (double)y, 0.0D).tex((double)(u * f), (double)(v * f1)).endVertex();
        tessellator.draw();
	}
	
	public static void renderFaceAt(EnumFacing face, int x, int y, int z, float partialTicks, Vector4f color)
	{
		BufferBuilder bufferbuilder = prepNoDepthLineRender(partialTicks);
		switch (face) {
		case DOWN:
			bufferbuilder.pos(x,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			break;
		case EAST:
			bufferbuilder.pos(x+1,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();	
			break;
		case NORTH:
			bufferbuilder.pos(x,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			break;
		case SOUTH:
			bufferbuilder.pos(x,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			break;
		case UP:
			bufferbuilder.pos(x,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			break;
		case WEST:
			bufferbuilder.pos(x,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			break;
		}
        postNoDepthLineRender();
	}
	
	public static void renderGhostBlock(IBlockState state, BlockPos position, Color color, boolean noDepth, float partialTicks)
	{
		if(!(state.getBlock() instanceof BlockLiquid) && !(state.getBlock() instanceof BlockFluidBase))
		{
			renderGhostModel(Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state), position, color, noDepth, partialTicks);
			return;
		}
		GlStateManager.enableBlend();
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_DST_COLOR);
		BufferBuilder vb;
		if(noDepth)
		{
            GlStateManager.depthFunc(519);
			vb = prepRenderBlockDepth(partialTicks, true);
		}
		else
	        vb = prepRender(partialTicks, true);
        vb.begin(7, DefaultVertexFormats.BLOCK);
        World world = Minecraft.getMinecraft().world;
        Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlock(state, position.add(0, noDepth ? 500 : 0, 0), world, vb);
        for(int i = 0; i < vb.getVertexCount(); i++)
        	vb.putColorMultiplier(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, i);
        vb.color(1, 1, 1, 0.1f);
        postRender();
        GlStateManager.disableBlend();
        GlStateManager.depthFunc(515);
	}
	
	public static void renderGhostModel(IBakedModel model, BlockPos position, Color color, boolean noDepth, float partialTicks)
	{
		GlStateManager.enableBlend();
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_DST_COLOR);
		BufferBuilder vb;
		if(noDepth)
		{
            GlStateManager.depthFunc(519);
			vb = prepRenderBlockDepth(partialTicks, true);
		}
		else
	        vb = prepRender(partialTicks, true);
        vb.begin(7, DefaultVertexFormats.BLOCK);
        World world = Minecraft.getMinecraft().world;
        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        dispatcher.getBlockModelRenderer().renderModel(world, model, Minecraft.getMinecraft().world.getBlockState(position), position.add(0, noDepth ? 500 : 0, 0), vb, false);
        for(int i = 0; i < vb.getVertexCount(); i++)
        	vb.putColorMultiplier(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, i);
        vb.color(1, 1, 1, 0.1f);
        postRender();
        GlStateManager.depthFunc(515);
        GlStateManager.disableBlend();
	}
	
	public static void renderGhostModel(IBakedModel model, BlockPos position, boolean noDepth, float partialTicks)
	{
		renderGhostModel(model, position, Color.WHITE, noDepth, partialTicks);
	}
	
	public static void renderGhostBlock(IBlockState state, BlockPos position, boolean noDepth, float partialTicks)
	{
		renderGhostBlock(state, position, new Color(1f, 1f, 1f), noDepth, partialTicks);
	}
	
	private final static Vector4f WHITE = new Vector4f(1, 1, 1, 1);
	
	public static void renderFaceAt(EnumFacing face, BlockPos pos, float partialTicks, Vector4f color, float line)
	{
		setLine(line);
		renderFaceAt(face, pos.getX(), pos.getY(), pos.getZ(), partialTicks, color);
	}
	
	public static void renderFullBoxAt(BlockPos pos, float partialTicks, Vector4f color, float line)
	{
		for(EnumFacing face : EnumFacing.values())
			renderFaceAt(face, pos, partialTicks, color, line);
	}
	
	public static void renderFaceAt(EnumFacing face, BlockPos pos, float partialTicks)
	{
		renderFaceAt(face, pos.getX(), pos.getY(), pos.getZ(), partialTicks, WHITE);
	}
	
	private static float line;
	
	public static void setLine(float line) {
		HarshenClientUtils.line = line;
	}
	
	public static void renderFullBoxAt(BlockPos pos, float partialTicks)
	{
		for(EnumFacing face : EnumFacing.values())
			renderFaceAt(face, pos, partialTicks);
	}
		
	public static BufferBuilder prepNoDepthLineRender(float partialTicks)
	{
        GlStateManager.depthFunc(519);
        return prepLineRender(partialTicks);

	}
	
	public static void postNoDepthLineRender()
	{
		postLineRender();
        GlStateManager.depthFunc(515);
	}
	
	public static BufferBuilder prepLineRender(float partialTicks)
	{
		GlStateManager.disableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.glLineWidth(line);
        BufferBuilder bufferbuilder = prepRender(partialTicks, true);
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        return bufferbuilder;
	}
	
	public static BufferBuilder prepRender(float partialTicks, boolean movePosition)
	{
		GlStateManager.pushMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        if(movePosition)
        	translateCamera(0, 0, 0, partialTicks);
        return bufferbuilder;
	}
	
	public static BufferBuilder prepRenderBlockDepth(float partialTicks, boolean movePosition)
	{
		GlStateManager.pushMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        if(movePosition)
        	translateCamera(0, 500, 0, partialTicks);
        return bufferbuilder;
	}
	
	public static void translateCamera(int addX, int addY, int addZ, float partialTicks)
	{
		EntityPlayer entityplayer = Minecraft.getMinecraft().player;
        double d0 = (entityplayer.lastTickPosX + (entityplayer.posX - entityplayer.lastTickPosX) * (double)partialTicks) + addX;
        double d1 = (entityplayer.lastTickPosY + (entityplayer.posY - entityplayer.lastTickPosY) * (double)partialTicks) + addY;
        double d2 = (entityplayer.lastTickPosZ + (entityplayer.posZ - entityplayer.lastTickPosZ) * (double)partialTicks) + addZ;
        Tessellator.getInstance().getBuffer().setTranslation(-d0, -d1, -d2);
	}
	
	public static void postRender()
	{
		Tessellator.getInstance().draw();
        Tessellator.getInstance().getBuffer().setTranslation(0, 0, 0);
        GlStateManager.popMatrix();
	}
		
	public static void postLineRender()
	{
		postRender();
        GlStateManager.glLineWidth(1.0F);
        line = 0.5f;
        GlStateManager.enableBlend();
        GlStateManager.enableTexture2D();
	}
	
	public static void openInventory()
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		player.openGui(HarshenUniverse.instance, GuiHandler.HARSHENINVENTORY, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
		HarshenNetwork.sendToServer(new MessagePacketOpenInv());
	}
	
	public static class Rotation
	{
		public final float angle;
		public final Vector3f rotate;
		
		public Rotation(float angle, Vector3f rotate) {
			this.angle = angle;
			this.rotate = rotate;
		}
	}
	
	public static RayTraceResult getMouseOver(double distance)
    {
    	Minecraft mc = Minecraft.getMinecraft();
    	float partialTicks = mc.getRenderPartialTicks();
        Entity entity = mc.getRenderViewEntity();
        RayTraceResult rayResult = null;
        if (entity != null)
        {
            if (mc.world != null)
            {
                double d0 = distance;
                RayTraceResult mouseOver = entity.rayTrace(d0, partialTicks);
                Vec3d vec3d = entity.getPositionEyes(partialTicks);
                double d1 = d0;

                if (mouseOver != null)
                {
                    d1 = mouseOver.hitVec.distanceTo(vec3d);
                }

                Vec3d vec3d1 = entity.getLook(1.0F);
                Vec3d vec3d2 = vec3d.addVector(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
                Vec3d vec3d3 = null;
                float f = 1.0F;
                List<Entity> list = mc.world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().expand(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).grow(1.0D, 1.0D, 1.0D), Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>()
                {
                    public boolean apply(@Nullable Entity p_apply_1_)
                    {
                        return p_apply_1_ != null && p_apply_1_.canBeCollidedWith();
                    }
                }));
                double d2 = d1;

                for (int j = 0; j < list.size(); ++j)
                {
                    Entity entity1 = list.get(j);
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow((double)entity1.getCollisionBorderSize());
                    RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);
                    if (axisalignedbb.contains(vec3d))
                    {
                        if (d2 >= 0.0D)
                        {
                            rayResult = new RayTraceResult(entity1);
                            vec3d3 = raytraceresult == null ? vec3d : raytraceresult.hitVec;
                            d2 = 0.0D;
                        }
                    }
                    else if (raytraceresult != null)
                    {
                        double d3 = vec3d.distanceTo(raytraceresult.hitVec);

                        if (d3 < d2 || d2 == 0.0D)
                        {
                            if (entity1.getLowestRidingEntity() == entity.getLowestRidingEntity() && !entity1.canRiderInteract())
                            {
                                if (d2 == 0.0D)
                                {
                                    rayResult = new RayTraceResult(entity1);
                                    vec3d3 = raytraceresult.hitVec;
                                }
                            }
                            else
                            {
                                rayResult = new RayTraceResult(entity1);
                                vec3d3 = raytraceresult.hitVec;
                                d2 = d3;
                            }
                        }
                    }
                }
                if(rayResult != null && rayResult.entityHit != null)
	                return new RayTraceResult(rayResult.entityHit, vec3d3);
             }
        }
        return null;
    }
	
	public static IBakedModel getModel(ResourceLocation resourceLocation) 
	{
		IBakedModel bakedModel;
		IModel model;
		try {
	        model = ModelLoaderRegistry.getModel(resourceLocation);
		} catch (Exception e) {
          throw new RuntimeException(e);
		}
		bakedModel = model.bake(TRSRTransformation.identity(), DefaultVertexFormats.BLOCK,
				location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString()));
	    return bakedModel;
	}
}