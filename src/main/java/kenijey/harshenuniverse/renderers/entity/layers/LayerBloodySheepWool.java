package kenijey.harshenuniverse.renderers.entity.layers;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.entity.EntityBloodySheep;
import kenijey.harshenuniverse.models.ModelBloodySheep1;
import kenijey.harshenuniverse.renderers.entity.RenderBloodySheep;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerBloodySheepWool implements LayerRenderer<EntityBloodySheep>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(HarshenUniverse.MODID, "textures/entity/bloody_sheep_fur.png");
    private final RenderBloodySheep sheepRenderer;
    private final ModelBloodySheep1 sheepModel = new ModelBloodySheep1();

    public LayerBloodySheepWool(RenderBloodySheep sheepRendererIn)
    {
        this.sheepRenderer = sheepRendererIn;
    }

    public void doRenderLayer(EntityBloodySheep entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (!((EntityBloodySheep)entitylivingbaseIn).getSheared() && !entitylivingbaseIn.isInvisible())
        {
            this.sheepRenderer.bindTexture(TEXTURE);

            if (entitylivingbaseIn.hasCustomName() && "jeb_".equals(entitylivingbaseIn.getCustomNameTag()))
            {
                int i1 = 25;
                int i = entitylivingbaseIn.ticksExisted / 25 + entitylivingbaseIn.getEntityId();
                float f = ((float)(entitylivingbaseIn.ticksExisted % 25) + partialTicks) / 25.0F;
            }

            this.sheepModel.setModelAttributes(this.sheepRenderer.getMainModel());
            this.sheepModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.sheepModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    public boolean shouldCombineTextures()
    {
        return true;
    }
}