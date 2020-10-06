package kenymylankca.harshenuniverse.renderers.entity;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.base.BaseRenderBiped;
import kenymylankca.harshenuniverse.entity.EntityHarshenSoul;
import kenymylankca.harshenuniverse.models.ModelHarshenSoul;
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