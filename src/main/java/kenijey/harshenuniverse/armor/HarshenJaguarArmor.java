package kenijey.harshenuniverse.armor;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.models.ModelJaguarArmor;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HarshenJaguarArmor extends ItemArmor
{
	public HarshenJaguarArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name) 
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation("harshenuniverse", name));
	}
	
	@Override
	public boolean hasOverlay(ItemStack stack) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,
			ModelBiped _default) {
		if (itemStack != null) {
			if (itemStack.getItem() instanceof HarshenJaguarArmor)
			{
				EntityEquipmentSlot type = ((ItemArmor) itemStack.getItem()).armorType;
				ModelJaguarArmor armorModel = new ModelJaguarArmor(1f);
				armorModel.slotActive = armorSlot;
				armorModel.isSneak = _default.isSneak;
				armorModel.isRiding = _default.isRiding;
				armorModel.isChild = _default.isChild;
				armorModel.rightArmPose = _default.rightArmPose;
				armorModel.leftArmPose = _default.leftArmPose;
				return armorModel;
			}
		}
		return null;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return HarshenUniverse.MODID + ":textures/models/armor/jaguar.png";
	}
}