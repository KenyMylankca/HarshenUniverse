package kenijey.harshenuniverse.renderers.entity;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.base.BaseRenderBiped;
import kenijey.harshenuniverse.entity.EntityHarshenSoul;
import kenijey.harshenuniverse.models.ModelHarshenSoul;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHarshenSoul extends BaseRenderBiped<EntityHarshenSoul> {

	public RenderHarshenSoul(RenderManager manager) {
		super(manager, new ModelHarshenSoul());
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHarshenSoul entity) {
		return new ResourceLocation(HarshenUniverse.MODID, "textures/entity/harshen_soul.png");
	}
}