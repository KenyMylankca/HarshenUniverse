package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.HarshenUniverse;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenDimensionalDoor extends Item
{
	ItemDoor itemDoor = new ItemDoor(HarshenBlocks.HARSHEN_DIMENSIONAL_DOOR);
	Block block = HarshenBlocks.HARSHEN_DIMENSIONAL_DOOR;
	
	public HarshenDimensionalDoor()
	{
		setUnlocalizedName("item_harshen_dimensional_door");
		setRegistryName(HarshenUniverse.MODID, "item_harshen_dimensional_door");
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		EnumActionResult a = itemDoor.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		worldIn.setBlockState(pos, worldIn.getBlockState(pos), 3);
		return a;
	}
}
