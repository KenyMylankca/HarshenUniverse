package kenymylankca.harshenuniverse.base;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public abstract class BaseRenderBiped<T extends EntityLiving>  extends RenderLiving<T>
{
    public BaseRenderBiped(RenderManager manager, ModelBiped model)
    {
        super(manager, model, 0.3F);
        this.addLayer(new LayerHeldItem(this));
    }
        
    @Override
    protected abstract ResourceLocation getEntityTexture(T entity);
}