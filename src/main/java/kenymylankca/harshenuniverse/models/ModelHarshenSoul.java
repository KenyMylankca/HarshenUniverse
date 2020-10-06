package kenymylankca.harshenuniverse.models;

import java.util.ArrayList;

import kenymylankca.harshenuniverse.base.BaseHarshenBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHarshenSoul extends BaseHarshenBiped
{
	public ArrayList<ModelRenderer> headParts = new ArrayList<>();
	public ArrayList<ModelRenderer> bodyParts = new ArrayList<>();
	public ArrayList<ModelRenderer> leftArmParts = new ArrayList<>();
	public ModelHarshenSoul()
	{
		this(0);
	}
	 
	public ModelHarshenSoul(float modelSize)
	{
		super(modelSize, 0.0F, 128, 64);
		this.bipedHead = newRender(5, 1, 1, 0, 0, 0, -4, -8, 3, 18, 0, false, this);
		render(headParts, 8, 5, 8, -4, -5, -4, 16, 19);
		render(headParts, 6, 1, 7, -4, -8, -4, 86, 0);
		render(headParts, 2, 2, 5, 2, -7, -4, 72, 0);
		render(headParts, 1, 1, 2, 2, -6, 1, 122, 0);
		render(headParts, 2, 1, 4, 2, -8, -4, 32, 0);
		render(headParts, 1, 1, 1, 3, -8, 0, 32, 10);
		render(headParts, 6, 2, 8, -4, -7, -4, 44, 0);
		
		this.bipedLeftArm = newRender(4, 5, 4, 5, 2, 0, -1, -2, -2, 56, 10, true, this);
		render(leftArmParts, 4, 5, 4, 5, 2, 0, -1, 5, -2, 40, 10, true);
		render(leftArmParts, 1, 2, 2, 5, 2, 0, 0, 3, -1, 122, 5, true);
		render(leftArmParts, 1, 2, 1, 5, 2, 0, 1, 3, -1, 122, 9, true);
		
		this.bipedBody = newRender(1, 1, 3, 0, 0, 0, 1.5f, 9.5f, -2, 0, 60, false, this);
		render(bodyParts, 8, 6, 4, -4, 0, -2, 8, 54);
		render(bodyParts, 8, 2, 4, -4, 10, -2, 32, 58);
		render(bodyParts, 1, 2, 1, 1, 8, -1, 0, 57);
		render(bodyParts, 1, 4, 1, -0.5f, 6, -0.5f, 124, 12);
		render(bodyParts, 2, 4, 4, 2, 6, -2, 56, 56);
		render(bodyParts, 1, 2, 1, 1.8f, 6, -1, 4, 57);
		render(bodyParts, 1, 1, 2, -3, 9.6f, -1, 0, 53);
		render(bodyParts, 2, 1, 2, -3.5f, 9.9f, -1, 0, 50);
		render(bodyParts, 1, 1, 1, -2.5f, 9.9f, -1.7f, 0, 48);
		render(bodyParts, 3, 1, 2, -3, 5.7f, -1, 0, 44);
		render(bodyParts, 2, 1, 1, -1, 9.6f, -1, 5, 47);
		this.bipedHeadwear.isHidden = true;
		this.bipedRightArm = newRender(4, 12, 4, -5, 2, 0, -3, -2, -2, 0, 0, false, this);
		this.bipedRightLeg = newRender(4, 12, 4, -2, 12, 0, -2, 0, -2, 0, 16, false, this);
		this.bipedLeftLeg = newRender(4, 12, 4, 2, 12, 0, -2, 0, -2, 0, 16, true, this);
	}
	
	private void render(ArrayList<ModelRenderer> list, int dimensionX, int dimensionY, int dimensionZ, float offsetX, float offsetY, float offsetZ, int textureX, int textureY)
	{
		this.render(list, dimensionX, dimensionY, dimensionZ, 0, 0, 0, offsetX, offsetY, offsetZ, textureX, textureY,false);
	}
	
	private void render(ArrayList<ModelRenderer> list, int dimensionX, int dimensionY, int dimensionZ, float positionX, float positionY, float positionZ,
			float offsetX, float offsetY, float offsetZ, int textureX, int textureY, boolean mirror) {
		list.add(newRender(dimensionX, dimensionY, dimensionZ, positionX, positionY, positionZ, offsetX, offsetY, offsetZ, textureX, textureY, mirror, this));
	}
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		for(ModelRenderer render : headParts)
			render.render(scale);
		for(ModelRenderer render : leftArmParts)
			render.render(scale);
		for(ModelRenderer render : bodyParts)
			render.render(scale);
		this.bipedHead.render(scale);
        this.bipedBody.render(scale);
        this.bipedRightArm.render(scale);
        this.bipedLeftArm.render(scale);
        this.bipedRightLeg.render(scale);
        this.bipedLeftLeg.render(scale);
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		for(ModelRenderer render : headParts)
			setAllRotSame(this.bipedHead, render);
		for(ModelRenderer render : leftArmParts)
			setAllRotSame(this.bipedLeftArm, render);
		for(ModelRenderer render : bodyParts)
			setAllRotSame(this.bipedBody, render);
	}
	
	private void setAllRotSame(ModelRenderer parent, ModelRenderer render)
	{
		render.rotateAngleX = parent.rotateAngleX;
		render.rotateAngleY = parent.rotateAngleY;
		render.rotateAngleZ = parent.rotateAngleZ;
	}
}