package kenijey.harshenuniverse.armor;

import java.util.ArrayList;

import kenijey.harshenuniverse.HarshenBlocks;
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
	public static ArmorMaterial JAGUAR_MATERIAL = EnumHelper.addArmorMaterial("harshen", HarshenUniverse.MODID + ":Harshen", 100, new int[] {3,8,6,3}, 9, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.0F)
			.setRepairItem(new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
	public static ArmorMaterial BLOODY_MATERIAL = EnumHelper.addArmorMaterial("bloody", HarshenUniverse.MODID + ":Bloody", 40, new int[] {1,3,2,1}, 2, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0F)
			.setRepairItem(new ItemStack(HarshenBlocks.BLOODY_WOOL));

	public static ItemArmor HARSHEN_JAGUAR_ARMOR_HELMET;
	public static ItemArmor HARSHEN_JAGUAR_ARMOR_CHESTPLATE;
	public static ItemArmor HARSHEN_JAGUAR_ARMOR_LEGGINGS;
	public static ItemArmor HARSHEN_JAGUAR_ARMOR_BOOTS;
	
	public static ItemArmor BLOODY_ARMOR_HELMET;
	public static ItemArmor BLOODY_ARMOR_CHESTPLATE;
	public static ItemArmor BLOODY_ARMOR_LEGGINGS;
	public static ItemArmor BLOODY_ARMOR_BOOTS;
	
	public static void preInit()
	{
		HARSHEN_JAGUAR_ARMOR_HELMET = new HarshenJaguarArmor(JAGUAR_MATERIAL, 1, EntityEquipmentSlot.HEAD, "harshen_jaguar_armor_helmet");
		HARSHEN_JAGUAR_ARMOR_CHESTPLATE = new HarshenJaguarArmor(JAGUAR_MATERIAL, 1, EntityEquipmentSlot.CHEST, "harshen_jaguar_armor_chestplate");
		HARSHEN_JAGUAR_ARMOR_LEGGINGS = new HarshenJaguarArmor(JAGUAR_MATERIAL, 1, EntityEquipmentSlot.LEGS, "harshen_jaguar_armor_leggings");
		HARSHEN_JAGUAR_ARMOR_BOOTS = new HarshenJaguarArmor(JAGUAR_MATERIAL, 1, EntityEquipmentSlot.FEET, "harshen_jaguar_armor_boots");
		
		BLOODY_ARMOR_HELMET = new BloodyArmor(BLOODY_MATERIAL, 1, EntityEquipmentSlot.HEAD, "bloody_armor_helmet");
		BLOODY_ARMOR_CHESTPLATE = new BloodyArmor(BLOODY_MATERIAL, 1, EntityEquipmentSlot.CHEST, "bloody_armor_chestplate");
		BLOODY_ARMOR_LEGGINGS = new BloodyArmor(BLOODY_MATERIAL, 1, EntityEquipmentSlot.LEGS, "bloody_armor_leggings");
		BLOODY_ARMOR_BOOTS = new BloodyArmor(BLOODY_MATERIAL, 1, EntityEquipmentSlot.FEET, "bloody_armor_boots");
	}
	
	private static ArrayList<ItemArmor> armours = new ArrayList<ItemArmor>();
	
	public static void register()
	{
		registerItem(HARSHEN_JAGUAR_ARMOR_HELMET);
		registerItem(HARSHEN_JAGUAR_ARMOR_CHESTPLATE);
		registerItem(HARSHEN_JAGUAR_ARMOR_LEGGINGS);
		registerItem(HARSHEN_JAGUAR_ARMOR_BOOTS);
		
		registerItem(BLOODY_ARMOR_HELMET);
		registerItem(BLOODY_ARMOR_CHESTPLATE);
		registerItem(BLOODY_ARMOR_LEGGINGS);
		registerItem(BLOODY_ARMOR_BOOTS);
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