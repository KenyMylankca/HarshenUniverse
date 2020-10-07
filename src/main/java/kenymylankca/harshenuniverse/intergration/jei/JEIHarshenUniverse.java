package kenymylankca.harshenuniverse.intergration.jei;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.armor.HarshenArmors;
import kenymylankca.harshenuniverse.containers.ContainerMagicTable;
import kenymylankca.harshenuniverse.enchantment.HarshenEnchantmetns;
import kenymylankca.harshenuniverse.gui.GuiMagicTable;
import kenymylankca.harshenuniverse.intergration.jei.hereticcauldron.JEIHereticCauldronCategory;
import kenymylankca.harshenuniverse.intergration.jei.hereticcauldron.JEIHereticCauldronHandler;
import kenymylankca.harshenuniverse.intergration.jei.hereticritual.JEIHereticRitualCategory;
import kenymylankca.harshenuniverse.intergration.jei.hereticritual.JEIHereticRitualHandler;
import kenymylankca.harshenuniverse.intergration.jei.lightningritual.JEILightningRitualCategory;
import kenymylankca.harshenuniverse.intergration.jei.lightningritual.JEILightningRitualHandler;
import kenymylankca.harshenuniverse.intergration.jei.magictable.JEIMagicTableCategory;
import kenymylankca.harshenuniverse.intergration.jei.magictable.JEIMagicTableHandler;
import kenymylankca.harshenuniverse.intergration.jei.pedestalslab.JEIPedestalSlabCategory;
import kenymylankca.harshenuniverse.intergration.jei.pedestalslab.JEIPedestalSlabHandler;
import kenymylankca.harshenuniverse.internal.HarshenAPIHandler;
import kenymylankca.harshenuniverse.recipes.CauldronRecipes;
import kenymylankca.harshenuniverse.recipes.HereticRitualRecipes;
import kenymylankca.harshenuniverse.recipes.LightningRitualRecipes;
import kenymylankca.harshenuniverse.recipes.MagicTableRecipe;
import kenymylankca.harshenuniverse.recipes.PedestalSlabRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@JEIPlugin
public class JEIHarshenUniverse implements IModPlugin 
{
	private IModRegistry registry;
	@Override
	public void register(IModRegistry registry) { 
		this.registry = registry;

		registry.handleRecipes(LightningRitualRecipes.class, new JEILightningRitualHandler(), JEICategoryUIDs.LIGHTNING_RITUAL);
		registry.handleRecipes(CauldronRecipes.class, new JEIHereticCauldronHandler(), JEICategoryUIDs.HERETIC_CAULDRON);
		registry.handleRecipes(PedestalSlabRecipes.class, new JEIPedestalSlabHandler(), JEICategoryUIDs.PENDESTAL_SLAB);
		registry.handleRecipes(HereticRitualRecipes.class, new JEIHereticRitualHandler(), JEICategoryUIDs.HERETIC_RITUAL);
		registry.handleRecipes(MagicTableRecipe.class, new JEIMagicTableHandler(), JEICategoryUIDs.MAGIC_TABLE);
		
		registry.getRecipeTransferRegistry().addRecipeTransferHandler(ContainerMagicTable.class, JEICategoryUIDs.MAGIC_TABLE, 0, 5, 5, 36);
		registry.addRecipeClickArea(GuiMagicTable.class, 105, 77, 59, 34, JEICategoryUIDs.MAGIC_TABLE);
		for(Item item : ForgeRegistries.ITEMS.getValues())
			if(HarshenEnchantmetns.MIXUP.canApply(new ItemStack(item)))
			{
				if(item.isEnchantable(new ItemStack(item)))
					continue;
				ItemStack stack = new ItemStack(item);
				stack.addEnchantment(HarshenEnchantmetns.MIXUP, 1);
				registry.addAnvilRecipe(new ItemStack(item), HarshenUtils.toArray(HarshenUtils.getMixupBook()), HarshenUtils.toArray(stack));
			}		
		
		registry.addRecipes(HarshenAPIHandler.allRitualRecipes, JEICategoryUIDs.LIGHTNING_RITUAL);
		registry.addRecipes(HarshenAPIHandler.allCauldronRecipes, JEICategoryUIDs.HERETIC_CAULDRON);
		registry.addRecipes(HarshenAPIHandler.allPedestalRecipes, JEICategoryUIDs.PENDESTAL_SLAB);
		registry.addRecipes(HarshenAPIHandler.allHereticCauldronRecipes, JEICategoryUIDs.HERETIC_RITUAL);
		registry.addRecipes(HarshenAPIHandler.allMagicTableRecipes, JEICategoryUIDs.MAGIC_TABLE);
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.HARSHEN_DIMENSIONAL_PEDESTAL), JEICategoryUIDs.LIGHTNING_RITUAL);
		registry.addRecipeCatalyst(new ItemStack(HarshenItems.RITUAL_STICK), JEICategoryUIDs.HERETIC_CAULDRON);
		registry.addRecipeCatalyst(new ItemStack(HarshenItems.RITUAL_STICK, 1, 1), JEICategoryUIDs.HERETIC_RITUAL);
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.PEDESTAL_SLAB), JEICategoryUIDs.PENDESTAL_SLAB);
		registry.addRecipeCatalyst(new ItemStack(HarshenBlocks.HARSHEN_MAGIC_TABLE), JEICategoryUIDs.MAGIC_TABLE);
		
		info(HarshenItems.HARSHEN_SOUL_FRAGMENT);
		info(HarshenItems.HARSHEN_CRYSTAL);
		info(HarshenItems.LIGHT_EMITTED_SEED);
		info(HarshenItems.LIGHT_EMITTED_ESSENCE);
		info(HarshenItems.SOUL_HARSHER_PICKAXE);
		info(HarshenItems.PONTUS_WORLD_GATE_SPAWNER_PARTS, new int[] {0,1,2});
		info(HarshenItems.BLOOD_ESSENCE);
		info(HarshenItems.BLOOD_COLLECTOR);
		info(HarshenItems.VALOR_BADGE);
		info(HarshenItems.IRON_HEART);
		info(HarshenItems.LIGHTNING_STAFF);
		info(HarshenItems.GLASS_CONTAINER, 1);
		info(HarshenItems.GLASS_CONTAINER, 6);
		info(HarshenItems.GLASS_CONTAINER, 8);
		info(HarshenItems.GLASS_CONTAINER, 10);
		info(HarshenItems.GLASS_CONTAINER, 11);
		info(HarshenItems.GLASS_CONTAINER, new int[] {4,5,7,9});
		info(HarshenItems.SOUL_SHIELD);
		info(HarshenItems.SOUL_HARSHER_SPADE);
		info(HarshenItems.DARK_EWYDOEN);
		
		info("harshen_jaguar_armor", HarshenArmors.HARSHEN_JAGUAR_ARMOR_BOOTS, HarshenArmors.HARSHEN_JAGUAR_ARMOR_CHESTPLATE, 
			HarshenArmors.HARSHEN_JAGUAR_ARMOR_HELMET, HarshenArmors.HARSHEN_JAGUAR_ARMOR_LEGGINGS);
		
		info(HarshenBlocks.HERETIC_CAULDRON);
		info(HarshenBlocks.BLOOD_VESSEL);
		info(HarshenBlocks.HARSHEN_SOUL_FLOWER);
		info(HarshenBlocks.BLOOD_BLOCK);
		info(HarshenBlocks.JEWEL_DIRT, 0);
		info(HarshenBlocks.BLOOD_PLACER);
		info(HarshenBlocks.AKZENIA_MUSHROOM);
		info(HarshenBlocks.HARSHEN_DISPLAY_BLOCK);
	}
	
	private void info(String name, Item... items)
	{
		for(Item item : items)
		registry.addIngredientInfo(new ItemStack(item), ItemStack.class, "jei." + name + ".info");
	}

	private void info(Block block)
	{
		registry.addIngredientInfo(new ItemStack(block), ItemStack.class, "jei." + block.getRegistryName().getResourcePath() + ".info");
	}
	
	private void info(Block block, int meta)
	{
		registry.addIngredientInfo(new ItemStack(block, 1, meta), ItemStack.class, "jei." + block.getRegistryName().getResourcePath() + "_" + meta + ".info");
	}
	
	private void info(Item item)
	{
		registry.addIngredientInfo(new ItemStack(item), ItemStack.class, "jei." + item.getRegistryName().getResourcePath() + ".info");
	}
	
	private void info(Item item, int meta)
	{
		registry.addIngredientInfo(new ItemStack(item, 1, meta), ItemStack.class, "jei." + item.getRegistryName().getResourcePath() + "_" + meta + ".info");
	}
	
	private void info(Item item, int[] meta)
	{
		for(int i=0; i<meta.length; i++)
		{
			registry.addIngredientInfo(new ItemStack(item, 1, meta[i]), ItemStack.class, "jei." + item.getRegistryName().getResourcePath() + "_" + meta[0] + ".info");
		}
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(
		new JEILightningRitualCategory(JEICategoryUIDs.LIGHTNING_RITUAL, registry),
		new JEIHereticCauldronCategory(JEICategoryUIDs.HERETIC_CAULDRON, registry),
		new JEIPedestalSlabCategory(JEICategoryUIDs.PENDESTAL_SLAB, registry),
		new JEIHereticRitualCategory(JEICategoryUIDs.HERETIC_RITUAL, registry),
		new JEIMagicTableCategory(JEICategoryUIDs.MAGIC_TABLE, registry));
	}
}