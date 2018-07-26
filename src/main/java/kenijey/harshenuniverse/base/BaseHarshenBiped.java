package kenijey.harshenuniverse.base;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class BaseHarshenBiped extends ModelBiped 
{
	public BaseHarshenBiped(float modelSize)
    {
        this(modelSize, 0.0F, 64, 32);
    }
	
	public BaseHarshenBiped(float modelSize, float p_i1149_2_, int textureWidthIn, int textureHeightIn)
    {
		super(modelSize, p_i1149_2_, textureWidthIn, textureHeightIn);
    }
	
	protected ModelRenderer newRender(int dimensionX, int dimensionY, int dimensionZ, float positionX, float positionY, float positionZ,
			float offsetX, float offsetY, float offsetZ, int textureX, int textureY, boolean mirror, ModelBase model)
	{
		ModelRenderer renderer = new ModelRenderer(model, textureX, textureY);
		renderer.mirror = mirror;
		renderer.addBox(offsetX, offsetY, offsetZ, dimensionX, dimensionY, dimensionZ, 0);
		renderer.setRotationPoint(positionX, positionY, positionZ);
		return renderer;
	}
}