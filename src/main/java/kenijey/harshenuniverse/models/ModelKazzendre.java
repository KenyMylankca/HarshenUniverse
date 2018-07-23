package kenijey.harshenuniverse.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKazzendre extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer headpartrp1;
    ModelRenderer headpartrp2;
    ModelRenderer headpartrp3;
    ModelRenderer headpartlp1;
    ModelRenderer headpartlp2;
    ModelRenderer headpartlp3;
    ModelRenderer headparttop;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer rightarmpart;
    ModelRenderer leftarm;
    ModelRenderer leftarmpart;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
  
  public ModelKazzendre()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -8F, -4F, 8, 8, 8);
      head.setRotationPoint(0F, 0F, 0F);
      head.setTextureSize(64, 64);
      head.mirror = false;
      setRotation(head, 0F, 0F, 0F);
      headpartrp1 = new ModelRenderer(this, 0, 44);
      headpartrp1.addBox(-6F, -8F, -6F, 2, 2, 10);
      headpartrp1.setRotationPoint(0F, 0F, 0F);
      headpartrp1.setTextureSize(64, 64);
      headpartrp1.mirror = false;
      setRotation(headpartrp1, 0F, 0F, 0F);
      headpartrp2 = new ModelRenderer(this, 11, 57);
      headpartrp2.addBox(-6F, -10F, -6F, 2, 2, 2);
      headpartrp2.setRotationPoint(0F, 0F, 0F);
      headpartrp2.setTextureSize(64, 64);
      headpartrp2.mirror = false;
      setRotation(headpartrp2, 0F, 0F, 0F);
      headpartrp3 = new ModelRenderer(this, 20, 56);
      headpartrp3.addBox(-6F, -6F, 2F, 2, 6, 2);
      headpartrp3.setRotationPoint(0F, 0F, 0F);
      headpartrp3.setTextureSize(64, 64);
      headpartrp3.mirror = false;
      setRotation(headpartrp3, 0F, 0F, 0F);
      headpartlp1 = new ModelRenderer(this, 24, 44);
      headpartlp1.addBox(4F, -8F, -6F, 2, 2, 10);
      headpartlp1.setRotationPoint(0F, 0F, 0F);
      headpartlp1.setTextureSize(64, 64);
      headpartlp1.mirror = false;
      setRotation(headpartlp1, 0F, 0F, 0F);
      headpartlp2 = new ModelRenderer(this, 1, 57);
      headpartlp2.addBox(4F, -10F, -6F, 2, 2, 2);
      headpartlp2.setRotationPoint(0F, 0F, 0F);
      headpartlp2.setTextureSize(64, 64);
      headpartlp2.mirror = false;
      setRotation(headpartlp2, 0F, 0F, 0F);
      headpartlp3 = new ModelRenderer(this, 28, 56);
      headpartlp3.addBox(4F, -6F, 2F, 2, 6, 2);
      headpartlp3.setRotationPoint(0F, 0F, 0F);
      headpartlp3.setTextureSize(64, 64);
      headpartlp3.mirror = false;
      setRotation(headpartlp3, 0F, 0F, 0F);
      headparttop = new ModelRenderer(this, 32, 0);
      headparttop.addBox(-3F, -10F, -1F, 6, 2, 2);
      headparttop.setRotationPoint(0F, 0F, 0F);
      headparttop.setTextureSize(64, 64);
      headparttop.mirror = false;
      setRotation(headparttop, 0F, 0F, 0F);
      body = new ModelRenderer(this, 16, 16);
      body.addBox(-4F, 0F, -2F, 8, 12, 4);
      body.setRotationPoint(0F, 0F, 0F);
      body.setTextureSize(64, 64);
      body.mirror = false;
      setRotation(body, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 40, 16);
      rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
      rightarm.setRotationPoint(-5F, 2F, 0F);
      rightarm.setTextureSize(64, 64);
      rightarm.mirror = false;
      setRotation(rightarm, -1.524323F, 0F, 0F);
      rightarmpart = new ModelRenderer(this, 0, 34);
      rightarmpart.addBox(-4F, 4F, -2F, 1, 7, 3);
      rightarmpart.setRotationPoint(-5F, 2F, 0F);
      rightarmpart.setTextureSize(64, 64);
      rightarmpart.mirror = false;
      setRotation(rightarmpart, -1.524323F, 0F, 0F);
      leftarm = new ModelRenderer(this, 40, 16);
      leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
      leftarm.setRotationPoint(5F, 2F, 0F);
      leftarm.setTextureSize(64, 64);
      leftarm.mirror = false;
      setRotation(leftarm, -1.59868F, 0F, 0F);
      leftarmpart = new ModelRenderer(this, 8, 34);
      leftarmpart.addBox(3F, 4F, -2F, 1, 7, 3);
      leftarmpart.setRotationPoint(5F, 2F, 0F);
      leftarmpart.setTextureSize(64, 64);
      leftarmpart.mirror = false;
      setRotation(leftarmpart, -1.59868F, 0F, 0F);
      rightleg = new ModelRenderer(this, 0, 16);
      rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
      rightleg.setRotationPoint(-2F, 12F, 0F);
      rightleg.setTextureSize(64, 64);
      rightleg.mirror = false;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 0, 16);
      leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
      leftleg.setRotationPoint(2F, 12F, 0F);
      leftleg.setTextureSize(64, 64);
      leftleg.mirror = false;
      setRotation(leftleg, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    headpartrp1.render(f5);
    headpartrp2.render(f5);
    headpartrp3.render(f5);
    headpartlp1.render(f5);
    headpartlp2.render(f5);
    headpartlp3.render(f5);
    headparttop.render(f5);
    body.render(f5);
    rightarm.render(f5);
    rightarmpart.render(f5);
    leftarm.render(f5);
    leftarmpart.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  @Override
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}