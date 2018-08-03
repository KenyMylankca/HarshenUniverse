package kenijey.harshenuniverse.renderers.entity;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.entity.EntityBloodySheep;
import kenijey.harshenuniverse.models.ModelBloodySheep2;
import kenijey.harshenuniverse.renderers.entity.layers.LayerBloodySheepWool;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBloodySheep extends RenderLiving<EntityBloodySheep>
{
    public RenderBloodySheep(RenderManager rm)
    {
        super(rm, new ModelBloodySheep2(), 0.7F);
        this.addLayer(new LayerBloodySheepWool(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityBloodySheep entity)
    {
        return new ResourceLocation(HarshenUniverse.MODID, "textures/entity/bloody_sheep.png");
    }
}