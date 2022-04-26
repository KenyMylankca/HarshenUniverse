package com.kenymylankca.harshenuniverse.renderers.entity;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.entity.EntitySoulPart;
import kenymylankca.harshenuniverse.models.ModelSoulPart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSoulPart extends RenderBiped<EntitySoulPart>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(HarshenUniverse.MODID, "textures/entity/soul_part.png");
    private int modelVersion;

    public RenderSoulPart(RenderManager p_i47190_1_)
    {
        super(p_i47190_1_, new ModelSoulPart(), 0.3F);
        this.modelVersion = ((ModelSoulPart)this.mainModel).getModelVersion();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySoulPart entity)
    {
        return TEXTURE;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntitySoulPart entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        int i = ((ModelSoulPart)this.mainModel).getModelVersion();

        if (i != this.modelVersion)
        {
            this.mainModel = new ModelSoulPart();
            this.modelVersion = i;
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntitySoulPart entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(0.4F, 0.4F, 0.4F);
    }
}