package kenymylankca.harshenuniverse.models;

import java.util.ArrayList;
import java.util.HashMap;

import kenymylankca.harshenuniverse.base.BaseHarshenBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.Vec3d;

public class ModelJaguarArmor extends BaseHarshenBiped
{
	public EntityEquipmentSlot slotActive = null;
	
	private ArrayList<ModelRenderer> helmet = new ArrayList<>();
	private ArrayList<ModelRenderer> chestplate = new ArrayList<>();
	private ArrayList<ModelRenderer> leggingsLeft = new ArrayList<>();
	private ArrayList<ModelRenderer> leggingsRight = new ArrayList<>();
	private ArrayList<ModelRenderer> feetLeft = new ArrayList<>();
	private ArrayList<ModelRenderer> feetRight = new ArrayList<>();
	
	private HashMap<ModelRenderer, Vec3d> rotations = new HashMap<>();

	public ModelJaguarArmor(float scale)
	{
		super(scale, 0, 32, 64);
		 
		textureWidth = 32;
		textureHeight = 64;
	
		addRenderer(helmet,10,1,8,-5,-9,-3,0,0);
		addRenderer(helmet,10,3,1,-5,-7,4,36,0);
		addRenderer(helmet,10,3,1,-5,-3,4,36,4);
		addRenderer(helmet,1,10,1,1,-8,4,0,9);
		addRenderer(helmet,1,10,1,-2,-8,4,4,9);
		addRenderer(helmet,1,1,7,4,-1,-5,8,9);
		addRenderer(helmet,1,1,7,-5,-1,-5,24,9);
		addRenderer(helmet,1,3,1,4,-1,2,40,9);
		addRenderer(helmet,1,3,1,-5,-1,2,44,9);
		addRenderer(helmet,1,1,2,4,-3,-2,58,0);
		addRenderer(helmet,1,1,2,-5,-3,-2,58,3);
		addRenderer(helmet,1,5,1,-5,-6,0,0,20);
		addRenderer(helmet,1,5,1,4,-6,0,4,20);
		addRenderer(helmet,1,1,2,-6,-3,0,0,26);
		addRenderer(helmet,1,1,2,5,-3,0,0,29);
		addRenderer(helmet,1,1,4,-5,-5,-2,6,27);
		addRenderer(helmet,1,1,4,4,-5,-2,16,27);
		addRenderer(helmet,1,6,1,4,-8,2,26,25);
		addRenderer(helmet,1,6,1,-5,-8,2,30,25);
		addRenderer(helmet,1,3,1,-5,-4,-3,48,9);
		addRenderer(helmet,1,3,1,4,-4,-3,52,9);
		addRenderer(helmet,1,1,1,-6,-4,1,8,24);
		addRenderer(helmet,1,1,1,5,-4,1,12,24);
		rotations.put(addRenderer(helmet,1,5,1,4,-10,-2,40,13), new Vec3d(8.521d,0,0));
		rotations.put(addRenderer(helmet,1,5,1,-5,-10,-2,44,13), new Vec3d(8.521d,0,0));
		addRenderer(helmet,8,1,6,-4,-10,-2,8,17);
		 
		addRenderer(chestplate,8,11,1,-4,0,-3,0,32);
		addRenderer(chestplate,4,4,1,-3,1,-4,18,32);
		addRenderer(chestplate,1,3,1,-3,5,-4,18,37);
		addRenderer(chestplate,1,1,1,3,1,-4,22,37);
		addRenderer(chestplate,2,2,1,-2,2,-5,18,41);
		addRenderer(chestplate,3,1,1,1,2,-4,22,39);
		addRenderer(chestplate,2,1,1,1,4,-4,26,37);
		addRenderer(chestplate,1,2,1,-2,7,-4,28,32);
		addRenderer(chestplate,1,3,1,3,7,-4,32,32);
		addRenderer(chestplate,1,1,1,-3,0,-3,28,35);
		addRenderer(chestplate,1,1,1,0,5,-4,32,36);
		addRenderer(chestplate,1,1,1,-4,3,-4,24,41);
		addRenderer(chestplate,1,1,1,-4,1,-4,28,41);
		addRenderer(chestplate,1,2,1,1,6,-4,36,32);
		addRenderer(chestplate,8,3,1,-4,8,2,40,32);
		addRenderer(chestplate,8,3,1,-4,0,2,40,36);
		addRenderer(chestplate,8,3,1,-4,4,2,40,40);
		addRenderer(chestplate,1,5,1,1,3,2,36,35);
		addRenderer(chestplate,1,2,1,3,11,2,32,38);
		addRenderer(chestplate,1,5,1,-2,3,2,58,32);
		addRenderer(chestplate,1,2,1,-4,11,2,58,38);
		
		addRenderer(leggingsLeft,1,6,4,2,0,-2,0,44);
		addRenderer(leggingsLeft,5,1,1,-2,1,-3,52,46);
		addRenderer(leggingsLeft,5,4,1,-2,3,-3,20,44);
		addRenderer(leggingsLeft,2,6,1,-2,0,2,32,44);
		addRenderer(leggingsLeft,1,5,1,1,0,2,60,50);
		addRenderer(leggingsLeft,1,1,1,0,2,2,52,50);
		addRenderer(leggingsLeft,1,6,1,2,1,2,48,50);
		addRenderer(leggingsLeft,1,1,1,-2,0,-3,52,54);
		addRenderer(leggingsLeft,1,5,1,3,2,0,40,51);
		addRenderer(leggingsLeft,4,1,1,-2,0,3,44,44);
		
		addRenderer(leggingsRight,1,6,4,-3,0,-2,10,44);
		addRenderer(leggingsRight,5,1,1,-3,1,-3,52,48);
		addRenderer(leggingsRight,5,4,1,-3,3,-3,20,49);
		addRenderer(leggingsRight,1,3,1,-1,0,-3,44,46);
		addRenderer(leggingsRight,2,6,1,0,0,2,38,44);
		addRenderer(leggingsRight,1,5,1,-2,0,2,56,50);
		addRenderer(leggingsRight,1,1,1,-1,2,2,52,52);
		addRenderer(leggingsRight,1,6,1,-3,1,2,44,50);
		addRenderer(leggingsRight,1,1,1,1,0,-3,32,51);
		addRenderer(leggingsRight,1,5,1,-4,2,0,36,51);
		addRenderer(leggingsRight,4,1,1,-2,0,3,54,44);
		
		addRenderer(feetLeft,1,4,1,0,6.3f,-3,0,61);
		addRenderer(feetLeft,1,5,1,-2,5.3f,-3,12,57);
		addRenderer(feetLeft,5,1,1,-2,8,-3,20,57);
		addRenderer(feetLeft,4,2,1,-2,10,-3,20,59);
		addRenderer(feetLeft,1,5,4,2,7,-2,54,57);
		addRenderer(feetLeft,5,4,1,-2,8,2,20,62);
		addRenderer(feetLeft,5,1,1,-2,6,2,0,59);
		addRenderer(feetLeft,1,1,1,2,6,1,40,60);
		
		addRenderer(feetRight,1,4,1,-1,6.3f,-3,8,61);
		addRenderer(feetRight,1,5,1,1,5.3f,-3,16,57);
		addRenderer(feetRight,5,1,1,-2,8,-3,32,57);
		addRenderer(feetRight,4,2,1,-2,10,-3,30,59);
		addRenderer(feetRight,1,5,4,-3,7,-2,44,57);
		addRenderer(feetRight,5,4,1,-3,8,2,32,62);
		addRenderer(feetRight,5,1,1,-3,6,2,0,57);
		addRenderer(feetRight,1,1,1,-3,6,1,4,61);
	}
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		GlStateManager.pushMatrix();
		if (this.isSneak)
		{
			GlStateManager.translate(0.0F, 0.2F, 0.0F);
		}
		
		switch (slotActive) {
		case HEAD:
			renderAndRotate(helmet, bipedHead, scale);
			break;
			
		case CHEST:
			renderAndRotate(chestplate, bipedBody, scale);
			break;
		
		case LEGS:
			renderAndRotate(leggingsLeft, bipedLeftLeg, scale);
			renderAndRotate(leggingsRight, bipedRightLeg, scale);
			break;
			
		case FEET:
			renderAndRotate(feetLeft, bipedLeftLeg, scale);
			renderAndRotate(feetRight, bipedRightLeg, scale);
			break;
			
		default:
			break;
		}
		GlStateManager.popMatrix();
	}
	
	private ModelRenderer addRenderer(ArrayList list, int dimensionX, int dimensionY, int dimensionZ,
		float offsetX, float offsetY, float offsetZ, int textureX, int textureY) 
	{
		ModelRenderer r = newRender(dimensionX, dimensionY, dimensionZ, 0, 0, 0, offsetX, offsetY, offsetZ, textureX, textureY, false, this);
		list.add(r);
		return r;
	}
	
	private void renderAndRotate(ArrayList<ModelRenderer> list, ModelRenderer parent, float scale)
	{
		for(ModelRenderer renderer : list)
		{
			copyModelAngles(parent, renderer);
			if(rotations.keySet().contains(renderer))
				addAngle(renderer);
			renderer.render(scale);
		}
	}
	
	private void addAngle(ModelRenderer renderer)
	{
		Vec3d vec = rotations.get(renderer);
		renderer.rotateAngleX += vec.x / 50f;
		renderer.rotateAngleY += vec.y / 50f;
		renderer.rotateAngleZ += vec.z / 50f;
	}
}