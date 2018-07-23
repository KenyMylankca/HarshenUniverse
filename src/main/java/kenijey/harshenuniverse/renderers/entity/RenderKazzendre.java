package kenijey.harshenuniverse.renderers.entity;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.entity.EntityKazzendre;
import kenijey.harshenuniverse.models.ModelKazzendre;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderKazzendre extends RenderLiving<EntityKazzendre>
{
    public RenderKazzendre(RenderManager manager)
    {
        super(manager, new ModelKazzendre(), 0.8f);
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityKazzendre entity) {
		return new ResourceLocation(HarshenUniverse.MODID, "textures/entity/kazzendre.png");
	}
}