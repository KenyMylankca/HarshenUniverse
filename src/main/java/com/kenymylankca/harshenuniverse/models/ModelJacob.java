package com.kenymylankca.harshenuniverse.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelJacob extends ModelBiped
{
    public ModelRenderer bipedHeadChild;
    public ModelRenderer bipedHeadChild_1;
    public ModelRenderer bipedHeadChild_2;
    public ModelRenderer bipedHeadChild_3;
    public boolean isSneak;
    
    public ModelJacob()
    {
        this(0.0F, false);
    }

    public ModelJacob(float modelSize, boolean p_i46303_2_)
    {
    	super(modelSize, 0.0F, 64, 32);
    	
    	this.leftArmPose = ModelBiped.ArmPose.EMPTY;
        this.rightArmPose = ModelBiped.ArmPose.EMPTY;
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.bipedHeadChild_1 = new ModelRenderer(this, 15, 47);
        this.bipedHeadChild_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHeadChild_1.addBox(-5.0F, -8.0F, 4.0F, 10, 8, 1, 0.0F);
        this.setRotateAngle(bipedHeadChild_1, 0.017453292519943295F, 0.0F, 0.0F);
        this.bipedHeadChild = new ModelRenderer(this, 16, 49);
        this.bipedHeadChild.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHeadChild.addBox(-4.0F, -8.0F, -6.0F, 8, 2, 2, 0.0F);
        this.bipedHeadChild_2 = new ModelRenderer(this, 15, 38);
        this.bipedHeadChild_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHeadChild_2.addBox(-5.0F, -8.0F, -6.0F, 1, 8, 10, 0.0F);
        this.setRotateAngle(bipedHeadChild_2, 0.0F, 0.0F, 0.017453292519943295F);
        this.bipedRightArm = new ModelRenderer(this, 40, 18);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(bipedRightArm, 0.0F, 0.0F, 0.10000736613927509F);
        this.bipedHeadChild_3 = new ModelRenderer(this, 15, 38);
        this.bipedHeadChild_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHeadChild_3.addBox(4.0F, -8.0F, -6.0F, 1, 8, 10, 0.0F);
        this.setRotateAngle(bipedHeadChild_3, 0.0F, 0.0F, -0.017453292519943295F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 18);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(bipedLeftArm, 0.0F, 0.0F, -0.10000736613927509F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.10000000149011612F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.10000000149011612F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        
        this.bipedHead.addChild(this.bipedHeadChild_1);
        this.bipedHead.addChild(this.bipedHeadChild);
        this.bipedHead.addChild(this.bipedHeadChild_2);
        this.bipedHead.addChild(this.bipedHeadChild_3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.bipedRightArm.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedBody.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedHead.render(f5);
    }
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}