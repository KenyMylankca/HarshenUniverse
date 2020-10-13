package kenymylankca.harshenuniverse.renderers.entity;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.base.BaseRenderBiped;
import kenymylankca.harshenuniverse.entity.EntityJacob;
import kenymylankca.harshenuniverse.models.ModelJacob;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

public class RenderJacob extends BaseRenderBiped<EntityJacob>
{
    public RenderJacob(RenderManager manager)
    {
        super(manager, new ModelJacob());
        this.addLayer(new LayerHeldItem(this));
    }
    
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityJacob entity) {
		return new ResourceLocation(HarshenUniverse.MODID, "textures/entity/jacob.png");
	}
}