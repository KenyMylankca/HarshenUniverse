package kenijey.harshenuniverse.base;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class BaseBlockHarshenSingleInventory extends Block implements ITileEntityProvider
{
	private static HashMap<BlockPos, Boolean> creativeBreakMap = new HashMap<>();
	
	public BaseBlockHarshenSingleInventory(Material materialIn) 
	{
		super(materialIn);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return getTile();
	}
	
	public abstract BaseTileEntityHarshenSingleItemInventory getTile();
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		ItemStackHandler handlerStack = new ItemStackHandler(1);
		handlerStack.deserializeNBT(stack.serializeNBT().getCompoundTag("tag").getCompoundTag("ItemStackHandler"));
		((BaseTileEntityHarshenSingleItemInventory)worldIn.getTileEntity(pos)).setItem(handlerStack.getStackInSlot(0));
		readNBT(((BaseTileEntityHarshenSingleItemInventory)worldIn.getTileEntity(pos)), stack);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{	
		BaseTileEntityHarshenSingleItemInventory te = (BaseTileEntityHarshenSingleItemInventory) worldIn.getTileEntity(pos);
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

		if(!worldIn.isRemote)
			if(isBreakNBT(handler.getStackInSlot(0)))
			{
				ItemStackHandler handlerStack = new ItemStackHandler(1);
				handlerStack.setStackInSlot(0, handler.getStackInSlot(0));
				ItemStack stack = new ItemStack(this);
		        String stackName = "";
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				if(handlerStack.getStackInSlot(0).getItem() != Items.AIR)
				{
			        nbttagcompound.setTag("ItemStackHandler", handlerStack.serializeNBT());
			        stackName +=  I18n.translateToLocal(handlerStack.getStackInSlot(0).getItem().getUnlocalizedName() + ".name");
				}
				addNBT(handler.getStackInSlot(0), nbttagcompound, worldIn, pos);
				if(!nbttagcompound.getKeySet().isEmpty() || !handlerStack.getStackInSlot(0).isEmpty())
				{
					stack.setTagCompound(nbttagcompound);
					stackName += extraName(nbttagcompound, handlerStack.getStackInSlot(0).getItem() != Items.AIR);
					stackName = stackName.equals("")? "§r" + getLocalizedName() : "§r" + getLocalizedName() + " (" + stackName + ")";
					stack.setStackDisplayName(stackName);
				}
				if(!creativeBreakMap.containsKey(pos) || !creativeBreakMap.get(pos))
					worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack));
				creativeBreakMap.remove(pos);
			}
			else
				InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), handler.getStackInSlot(0));
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack item = playerIn.getHeldItem(hand);
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if(tileEntity instanceof BaseTileEntityHarshenSingleItemInventory)
			if(((BaseTileEntityHarshenSingleItemInventory)tileEntity).canAddItem() && Item.getItemFromBlock(Blocks.AIR) != item.getItem())
			{
				int i =  item.getCount() - 1;
				if(((BaseTileEntityHarshenSingleItemInventory)tileEntity).setItem(item))
				{
					ItemStack stackToGive=new ItemStack(item.getItem(), i, item.getMetadata());
					stackToGive.setTagCompound(item.getTagCompound());
					playerIn.setHeldItem(hand, stackToGive);
				}
			}
			else if (!((BaseTileEntityHarshenSingleItemInventory) tileEntity).canAddItem())
			{
				ItemStack stack = ((BaseTileEntityHarshenSingleItemInventory)tileEntity).getItem();
				((BaseTileEntityHarshenSingleItemInventory)tileEntity).setItemAir();
				if(!worldIn.isRemote)
					InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY() + 0.7f, pos.getZ(), stack);
			}
		return true;
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		creativeBreakMap.put(pos, player.capabilities.isCreativeMode);
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	protected boolean isBreakNBT(ItemStack stack)
	{
		return false;
	}
	
	protected String extraName(NBTTagCompound nbt, boolean isItem)
	{
		return "";
	}
	
	protected void addNBT(ItemStack stack, NBTTagCompound nbt, World worldIn, BlockPos pos)
	{
		
	}
	
	protected void readNBT(BaseTileEntityHarshenSingleItemInventory tileEntity, ItemStack stack)
	{
		
	}
	
	protected boolean hasKey(ItemStack stack, String key)
	{
		return stack.hasTagCompound() && stack.getTagCompound().hasKey(key);
	}
}