package kenijey.harshenuniverse.renderers.entity;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.base.BaseRenderBiped;
import kenijey.harshenuniverse.entity.EntitySoullessKnight;
import kenijey.harshenuniverse.models.ModelSoullessKnight;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSoullessKnight extends BaseRenderBiped<EntitySoullessKnight>
{
    public RenderSoullessKnight(RenderManager manager)
    {
        super(manager, new ModelSoullessKnight());
    }

	@Override
	protected ResourceLocation getEntityTexture(EntitySoullessKnight entity) {
		return new ResourceLocation(HarshenUniverse.MODID, "textures/entity/soulless_knight.png");
	}
}