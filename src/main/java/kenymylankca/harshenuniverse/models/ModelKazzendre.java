package kenymylankca.harshenuniverse.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelKazzendre extends ModelBase
{
	public ModelRenderer bipedHead;
	public ModelRenderer headChild;
	public ModelRenderer headChild_1;
	public ModelRenderer headChild_2;
	public ModelRenderer headChild_3;
	public ModelRenderer headChild_4;
	public ModelRenderer headChild_5;
	public ModelRenderer headChild_6;
	public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer rightarmChild;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer leftarmChild;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedRightLeg;
    
    public ModelKazzendre() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        
        this.headChild = new ModelRenderer(this, 32, 0);
        this.headChild.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headChild.addBox(-3.0F, -10.0F, -1.0F, 6, 2, 2, 0.0F);
        this.leftarmChild = new ModelRenderer(this, 8, 34);
        this.leftarmChild.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftarmChild.addBox(3.0F, 4.7F, -2.0F, 1, 7, 3, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(bipedLeftArm, -1.598680019378662F, 0.0F, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(bipedRightArm, -1.5243705686918474F, 0.0F, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(bipedLeftLeg, 1.488157267228764E-15F, 0.0F, 0.0F);
        this.rightarmChild = new ModelRenderer(this, 0, 34);
        this.rightarmChild.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightarmChild.addBox(-4.01F, 5.1F, -2.0F, 1, 7, 3, 0.0F);
        this.headChild_3 = new ModelRenderer(this, 28, 56);
        this.headChild_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headChild_3.addBox(4.0F, -6.0F, 2.0F, 2, 6, 2, 0.0F);
        this.headChild_2 = new ModelRenderer(this, 1, 57);
        this.headChild_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headChild_2.addBox(4.0F, -10.0F, -6.0F, 2, 2, 2, 0.0F);
        this.headChild_5 = new ModelRenderer(this, 11, 57);
        this.headChild_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headChild_5.addBox(-6.0F, -10.0F, -6.0F, 2, 2, 2, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.headChild_4 = new ModelRenderer(this, 0, 44);
        this.headChild_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headChild_4.addBox(-6.0F, -8.0F, -6.0F, 2, 2, 10, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.headChild_6 = new ModelRenderer(this, 20, 56);
        this.headChild_6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headChild_6.addBox(-6.0F, -6.0F, 2.0F, 2, 6, 2, 0.0F);
        this.headChild_1 = new ModelRenderer(this, 24, 44);
        this.headChild_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headChild_1.addBox(4.0F, -8.0F, -6.0F, 2, 2, 10, 0.0F);
        this.bipedHead.addChild(this.headChild);
        this.bipedLeftArm.addChild(this.leftarmChild);
        this.bipedRightArm.addChild(this.rightarmChild);
        
        this.bipedHead.addChild(this.headChild_3);
        this.bipedHead.addChild(this.headChild_2);
        this.bipedHead.addChild(this.headChild_5);
        this.bipedHead.addChild(this.headChild_4);
        this.bipedHead.addChild(this.headChild_6);
        this.bipedHead.addChild(this.headChild_1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    	bipedHead.render(f5);
        bipedBody.render(f5);
        bipedLeftArm.render(f5);
        bipedRightArm.render(f5);
        bipedLeftLeg.render(f5);
        bipedRightLeg.render(f5);
    }
    
    @SuppressWarnings("incomplete-switch")
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).getTicksElytraFlying() > 4;
        this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;

        if (flag)
        {        
            this.bipedHead.rotateAngleX = -((float)Math.PI / 4F);
        }
        else
        {
            this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
        }

        this.bipedBody.rotateAngleY = 0.0F;
        this.bipedRightArm.rotationPointZ = 0.0F;
        this.bipedRightArm.rotationPointX = -5.0F;
        this.bipedLeftArm.rotationPointZ = 0.0F;
        this.bipedLeftArm.rotationPointX = 5.0F;
        float f = 1.0F;

        if (flag)
        {
            f = (float)(entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F)
        {
            f = 1.0F;
        }

        this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;
        this.bipedRightLeg.rotateAngleZ = 0.0F;
        this.bipedLeftLeg.rotateAngleZ = 0.0F;

        if (this.isRiding)
        {
            this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedRightLeg.rotateAngleX = -1.4137167F;
            this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.bipedRightLeg.rotateAngleZ = 0.07853982F;
            this.bipedLeftLeg.rotateAngleX = -1.4137167F;
            this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleZ = -0.07853982F;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedRightArm.rotateAngleZ = 0.0F;

        if (this.swingProgress > 0.0F)
        {
            EnumHandSide enumhandside = EnumHandSide.RIGHT;
            ModelRenderer modelrenderer = this.bipedRightArm;
            float f1 = this.swingProgress;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;

            if (enumhandside == EnumHandSide.LEFT)
            {
                this.bipedBody.rotateAngleY *= -1.0F;
            }

            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f1 = 1.0F - this.swingProgress;
            f1 = f1 * f1;
            f1 = f1 * f1;
            f1 = 1.0F - f1;
            float f2 = MathHelper.sin(f1 * (float)Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float)((double)modelrenderer.rotateAngleX - ((double)f2 * 1.2D + (double)f3));
            modelrenderer.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
        }

        this.bipedBody.rotateAngleX = 0.0F;
        this.bipedRightLeg.rotationPointZ = 0.1F;
        this.bipedLeftLeg.rotationPointZ = 0.1F;
        this.bipedRightLeg.rotationPointY = 12.0F;
        this.bipedLeftLeg.rotationPointY = 12.0F;
        this.bipedHead.rotationPointY = 0.0F;

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}