package kenijey.harshenuniverse.armor;

import java.util.ArrayList;

import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.HarshenUniverse;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenArmors 
{
	public static ArmorMaterial harshen_material = EnumHelper.addArmorMaterial("harshen", "harshenuniverse:Harshen", 100, new int[] {3,8,6,3}, 9, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.0F)
			.setRepairItem(new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));

	public static ItemArmor HARSHEN_JAGUAR_ARMOR_HELMET;
	public static ItemArmor HARSHEN_JAGUAR_ARMOR_CHESTPLATE;
	public static ItemArmor HARSHEN_JAGUAR_ARMOR_LEGGINGS;
	public static ItemArmor HARSHEN_JAGUAR_ARMOR_BOOTS;
	
	public static void preInit()
	{
		HARSHEN_JAGUAR_ARMOR_HELMET = new HarshenJaguarArmor(harshen_material, 1, EntityEquipmentSlot.HEAD, "harshen_jaguar_armor_helmet");
		HARSHEN_JAGUAR_ARMOR_CHESTPLATE = new HarshenJaguarArmor(harshen_material, 1, EntityEquipmentSlot.CHEST, "harshen_jaguar_armor_chestplate");
		HARSHEN_JAGUAR_ARMOR_LEGGINGS = new HarshenJaguarArmor(harshen_material, 1, EntityEquipmentSlot.LEGS, "harshen_jaguar_armor_leggings");
		HARSHEN_JAGUAR_ARMOR_BOOTS = new HarshenJaguarArmor(harshen_material, 1, EntityEquipmentSlot.FEET, "harshen_jaguar_armor_boots");
	}
	
	private static ArrayList<ItemArmor> armours = new ArrayList<ItemArmor>();
	
	public static void register()
	{
		registerItem(HARSHEN_JAGUAR_ARMOR_HELMET);
		registerItem(HARSHEN_JAGUAR_ARMOR_CHESTPLATE);
		registerItem(HARSHEN_JAGUAR_ARMOR_LEGGINGS);
		registerItem(HARSHEN_JAGUAR_ARMOR_BOOTS);
	}
	
	public static void regRenders()
	{
		for(ItemArmor item : armours)
			regRender(item);
	}
	
	private static void registerItem(ItemArmor item)
	{
		armours.add(item);
		ForgeRegistries.ITEMS.register((Item) item);
	}
	
	private static void regRender(ItemArmor item)
	{
		item.setCreativeTab(HarshenUniverse.harshenTab);
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}