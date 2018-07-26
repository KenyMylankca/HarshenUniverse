package kenijey.harshenuniverse.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BloodyArmor extends ItemArmor
{
	public BloodyArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name) 
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation("harshenuniverse", name));
	}
	
	@Override
	public boolean hasOverlay(ItemStack stack) {
		return false;
	}
}