package kenymylankca.harshenuniverse.renderers.entity;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.base.BaseRenderBiped;
import kenymylankca.harshenuniverse.entity.EntitySoullessKnight;
import kenymylankca.harshenuniverse.models.ModelSoullessKnight;
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