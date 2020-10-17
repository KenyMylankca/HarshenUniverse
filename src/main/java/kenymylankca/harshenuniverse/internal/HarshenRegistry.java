package kenymylankca.harshenuniverse.internal;

import java.util.HashMap;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.api.BlockItem;
import kenymylankca.harshenuniverse.api.CauldronLiquid;
import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.HarshenStack;
import kenymylankca.harshenuniverse.api.IHarshenHelper;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import kenymylankca.harshenuniverse.api.IHarshenRegistry;
import kenymylankca.harshenuniverse.enums.items.GlassContainerValue;
import kenymylankca.harshenuniverse.objecthandlers.VanillaProviderToInterface;
import kenymylankca.harshenuniverse.recipes.CauldronRecipes;
import kenymylankca.harshenuniverse.recipes.HereticRitualRecipes;
import kenymylankca.harshenuniverse.recipes.LightningRitualRecipes;
import kenymylankca.harshenuniverse.recipes.MagicTableRecipe;
import kenymylankca.harshenuniverse.recipes.PedestalSlabRecipes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;

public class HarshenRegistry implements IHarshenRegistry
{
	
	private static final HashMap<ItemStack, CauldronLiquid> INPUT_MAP = new HashMap<>();
	private static final HashMap<CauldronLiquid, ItemStack> OUTPUT_MAP = new HashMap<>();
	private static final HashMap<ItemStack, CauldronLiquid> ITEMLIQUID_MAP = new HashMap<>();
	
	private static final HashMap<ItemStack, Integer> INPUT_FILLBY = new HashMap<>();
	private static final HashMap<ItemStack, Integer> OUTPUT_FILLBY = new HashMap<>();
	
	private static final HashMap<Fluid, Integer> FORGE_INPUT_AMOUNT = new HashMap<>();
	private static final HashMap<Fluid, CauldronLiquid> FORGE_BUCKET_MAP = new HashMap<>();
	private static final HashMap<Fluid, Integer> FORGE_BUCKET_AMOUNT_MAP = new HashMap<>();

	private final String modID;
	
	public HarshenRegistry(String modID) 
	{
		this.modID = modID;
	}
	
	@Override
	public void registerLightningRecipe(ItemStack output, HarshenStack input1, HarshenStack input2, HarshenStack input3, HarshenStack input4) {
		LightningRitualRecipes.addRecipe(HarshenUtils.toArray(input1, input2, input3, input4), output);
	}

	@Override
	public void registerCauldronRecipe(HarshenStack input, ItemStack output, CauldronLiquid liquid) {
		CauldronRecipes.addRecipe(input, output, liquid);
	}

	@Override
	public void registerHereticRecipe(HarshenStack cauldronItem, ItemStack output, CauldronLiquid catalyst,  HarshenStack input1, HarshenStack input2, HarshenStack input3, HarshenStack input4, 
			 HarshenStack input5, HarshenStack input6, HarshenStack input7, HarshenStack input8) {
		HereticRitualRecipes.addRecipe(cauldronItem, output, catalyst, HarshenUtils.listOf(input1, input2, input3, input4, input5, input6, input7, input8));
	}

	@Override
	public void registerPedestalSlabRecipe(HarshenStack input, ItemStack output) {
		PedestalSlabRecipes.addRecipe(input, output);
	}

	@Override
	public void registerMagicTableRecipe(ItemStack output, HarshenStack input1, HarshenStack input2, HarshenStack input3, HarshenStack input4) {
			MagicTableRecipe.addRecipe(HarshenUtils.toArray(input1, input2, input3, input4), output);
	}

	@Override
	public CauldronLiquid registerCauldronLiquid(ItemStack fullItem, ItemStack emptyItem, CauldronLiquid liquid, int fillBy) {
		fillBy = MathHelper.clamp(fillBy, 1, 3);
		liquid.setModID(modID);
		if(emptyItem == null || emptyItem.isEmpty())
			return registerItemLiquid(fullItem, liquid);
		INPUT_FILLBY.put(fullItem, fillBy);
		OUTPUT_FILLBY.put(emptyItem, fillBy);
		INPUT_MAP.put(fullItem, liquid);
		OUTPUT_MAP.put(liquid, emptyItem);
		return liquid;
	}
	
	@Override
	public CauldronLiquid registerCauldronLiquid(FluidStack fluid, CauldronLiquid liquid, int fillBy) {
		liquid.setModID(modID);
		FORGE_INPUT_AMOUNT.put(fluid.getFluid(), fillBy);
		FORGE_BUCKET_MAP.put(fluid.getFluid(), liquid);
		FORGE_BUCKET_AMOUNT_MAP.put(fluid.getFluid(), fluid.amount);
		return liquid;
	}
	
	private CauldronLiquid registerItemLiquid(ItemStack inputItem, CauldronLiquid liquid)
	{
		CauldronLiquid l = liquid.hasState() ? new CauldronLiquid(liquid.getName().split(":")[1], (IBlockState)liquid.getStateOrLoc()).setModID(liquid.getName().split(":")[0])
				: new CauldronLiquid(liquid.getName().split(":")[1], (ResourceLocation) liquid.getStateOrLoc()).setModID(liquid.getName().split(":")[0]);
		ITEMLIQUID_MAP.put(inputItem, l);
		return l;
	}

	public static CauldronLiquid getLiquidFromStack(ItemStack key)
	{
		if(key.getItem() instanceof UniversalBucket && FluidStack.loadFluidStackFromNBT(key.getTagCompound()) != null &&
				FORGE_BUCKET_MAP.containsKey(FluidStack.loadFluidStackFromNBT(key.getTagCompound()).getFluid()))
			return FORGE_BUCKET_MAP.get(FluidStack.loadFluidStackFromNBT(key.getTagCompound()).getFluid());
		return HarshenUtils.getObjectFromItemMap(HarshenUtils.getObjectFromItemMap(INPUT_MAP, key) != null ? INPUT_MAP : ITEMLIQUID_MAP, key);
	}
	
	public static ItemStack getOutPutItem(ItemStack inputItem, CauldronLiquid liquid)
	{
		if((inputItem.getItem() instanceof UniversalBucket) && FORGE_BUCKET_MAP.containsValue(liquid))
			return new ItemStack(Items.BUCKET);
		else
			for(CauldronLiquid l : OUTPUT_MAP.keySet())
				if(l == liquid)
					return OUTPUT_MAP.get(l).copy(); 
		return ItemStack.EMPTY;
	}
	
	public static int getRemoveFill(ItemStack stack, CauldronLiquid prevLiquid)
	{
		if(stack.getItem() == Items.BUCKET)
			for(Fluid fluid : FORGE_BUCKET_MAP.keySet())
				if(FORGE_BUCKET_MAP.get(fluid).getName().equals(prevLiquid.getName()))
					return FORGE_INPUT_AMOUNT.get(fluid);
		return HarshenUtils.getObjectFromItemMap(OUTPUT_FILLBY, stack);
	}
	
	public static int getFill(ItemStack stack)
	{
		if(stack.getItem() instanceof UniversalBucket)
			return FORGE_INPUT_AMOUNT.get(FluidStack.loadFluidStackFromNBT(stack.getTagCompound()).getFluid());
		for(ItemStack stack1 : ITEMLIQUID_MAP.keySet())
			if(stack1.isItemEqual(stack))
				return 64;
		return HarshenUtils.getObjectFromItemMap(INPUT_FILLBY, stack);
	}
	
	public static ItemStack getInputFromOutput(ItemStack inputStack, CauldronLiquid prevLiquid)
	{
		if(inputStack.getItem() == Items.BUCKET)
			for(Fluid fluid : FORGE_BUCKET_MAP.keySet())
				if(FORGE_BUCKET_MAP.get(fluid) == prevLiquid)
					return FluidUtil.getFilledBucket(new FluidStack(fluid, FORGE_BUCKET_AMOUNT_MAP.get(fluid)));
		boolean flag = false;
		for(ItemStack stack : OUTPUT_FILLBY.keySet())
			if(stack.isItemEqual(inputStack))
				flag = true;
		if(flag)
			for(ItemStack stack : INPUT_MAP.keySet())
				if(INPUT_MAP.get(stack) == prevLiquid)
					return stack.copy();
		return ItemStack.EMPTY;
	}
	
	public static CauldronLiquid getRelativeFluid(CauldronLiquid prevLiquid)
	{
		for(ItemStack stack : INPUT_MAP.keySet())
			if(INPUT_MAP.get(stack).getName().equals(prevLiquid.getName()))
				return INPUT_MAP.get(stack);
		return prevLiquid;
	}

	@Override
	public void registerInventoryItem(BlockItem item, EnumAccessoryInventorySlots slot, Object provider, boolean multiplyEvent,
			int toolTipLines) 
	{		
		registerInventoryItem(item, new VanillaProviderToInterface(slot, provider, multiplyEvent, toolTipLines));
	}

	@Override
	public void registerInventoryItem(BlockItem item, IHarshenAccessoryProvider provider) {
		HarshenUtils.registerInventoryItem(item, provider);
	}

	@Override
	public IHarshenHelper getHelper() {
		return new HarshenHelper();
	}

	@Override
	public int registerGlassContainer(String name, CauldronLiquid liquid, int color) {
		int meta = GlassContainerValue.registerGlassContainer(name, color, liquid);
		INPUT_FILLBY.put(new ItemStack(HarshenItems.GLASS_CONTAINER, 1, meta), 1);
		return meta;
	}

	@Override
	public int registerGlassContainer(String name, int color, PotionEffect... effects) {
		return GlassContainerValue.registerGlassContainer(name, color, effects);
	}
	
	public static class HarshenHelper implements IHarshenHelper
	{
	}
}