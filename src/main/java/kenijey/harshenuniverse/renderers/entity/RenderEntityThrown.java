package kenijey.harshenuniverse.renderers.entity;

import kenijey.harshenuniverse.entity.EntityThrown;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderEntityThrown extends Render<EntityThrown>
{
    private static final ResourceLocation EXPERIENCE_ORB_TEXTURES = new ResourceLocation("textures/entity/experience_orb.png");

    public RenderEntityThrown(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize = 0.15F;
        this.shadowOpaque = 0.75F;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityThrown entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	if(entity.isLocation())
    	{
    		GlStateManager.pushMatrix();
            GlStateManager.translate((float)x, (float)y, (float)z);
            GlStateManager.disableLighting();
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
            this.bindEntityTexture(entity);
            int i = entity.getLocation().getId();
            if(i == 1)
            	GlStateManager.depthFunc(519);
            float f = (float)(i % 4 * 16 + 0) / 64.0F;
            float f1 = (float)(i % 4 * 16 + 16) / 64.0F;
            float f2 = (float)(i / 4 * 16 + 0) / 64.0F;
            float f3 = (float)(i / 4 * 16 + 16) / 64.0F;
            GlStateManager.translate(0.0F, -0.1F, 0.0F);
            GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            float f7 = 0.3F;
            GlStateManager.scale(0.3F, 0.3F, 0.3F);
            GlStateManager.translate(0.1F, 0.0F, 0.0F);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
            if (this.renderOutlines)
            {
                GlStateManager.enableColorMaterial();
                GlStateManager.enableOutlineMode(this.getTeamColor(entity));
            }
            bufferbuilder.pos(-0.5D, -0.25D, 0.0D).tex((double)f, (double)f3).color(255, 255, 255, 255).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(0.5D, -0.25D, 0.0D).tex((double)f1, (double)f3).color(255, 255, 255, 255).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(0.5D, 0.75D, 0.0D).tex((double)f1, (double)f2).color(255, 255, 255, 255).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(-0.5D, 0.75D, 0.0D).tex((double)f, (double)f2).color(255, 255, 255, 255).normal(0.0F, 1.0F, 0.0F).endVertex();
            tessellator.draw();
            GlStateManager.disableRescaleNormal();
        	GlStateManager.depthFunc(515);
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
    	}
    	else
    	{
    		GlStateManager.pushMatrix();
            GlStateManager.translate((float)x, (float)y, (float)z);
            GlStateManager.enableRescaleNormal();
            GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            
            if (this.renderOutlines)
            {
                GlStateManager.enableColorMaterial();
                GlStateManager.enableOutlineMode(this.getTeamColor(entity));
            }
            
            Minecraft.getMinecraft().getRenderItem().renderItem(entity.getStack(), ItemCameraTransforms.TransformType.GROUND);

            if (this.renderOutlines)
            {
                GlStateManager.disableOutlineMode();
                GlStateManager.disableColorMaterial();
            }

            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
    	}
    }
    
    @SubscribeEvent
    public void renderWorldLast(RenderWorldLastEvent event)
	{
		EntityPlayer player = net.minecraft.client.Minecraft.getMinecraft().player;
		for(Entity e : player.world.getLoadedEntityList())
			if(e instanceof EntityThrown)
				Minecraft.getMinecraft().getRenderManager().renderEntityStatic(e, event.getPartialTicks(), false);

	}
 
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityThrown entity)
    {
        return entity.isLocation() ? entity.getLocation() : TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}