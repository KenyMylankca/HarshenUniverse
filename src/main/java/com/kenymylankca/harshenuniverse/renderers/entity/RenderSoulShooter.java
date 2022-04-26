package com.kenymylankca.harshenuniverse.renderers.entity;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.base.BaseRenderBiped;
import kenymylankca.harshenuniverse.entity.EntitySoulShooter;
import kenymylankca.harshenuniverse.models.ModelSoulShooter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

public class RenderSoulShooter extends BaseRenderBiped<EntitySoulShooter>
{
    public RenderSoulShooter(RenderManager manager)
    {
        super(manager, new ModelSoulShooter());
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelSoulShooter(0.5F, true);
                this.modelArmor = new ModelSoulShooter(1.0F, true);
            }
        });
    }

    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
	protected ResourceLocation getEntityTexture(EntitySoulShooter entity) {
		return new ResourceLocation(HarshenUniverse.MODID, "textures/entity/soul_shooter.png");
	}
}