package kenijey.harshenuniverse.handlers;

import kenijey.harshenuniverse.containers.ContainerMagicTable;
import kenijey.harshenuniverse.gui.GuiBookScreen;
import kenijey.harshenuniverse.gui.GuiMagicTable;
import kenijey.harshenuniverse.gui.GuiXrayPendantScreen;
import kenijey.harshenuniverse.inventory.ContainerPlayerInventory;
import kenijey.harshenuniverse.inventory.GuiHarshenInventory;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenMagicTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public static final int HARSHENINVENTORY = 0; //THE ACCESSORY INVENTORY
	public static final int MAGICTABLE = 1;
	public static final int BOOK = 2;
	public static final int XRAY = 3;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == HARSHENINVENTORY)
			return new ContainerPlayerInventory(player);
		if (ID == MAGICTABLE)
			return new ContainerMagicTable((TileEntityHarshenMagicTable) world.getTileEntity(new BlockPos(x, y, z)), player);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == HARSHENINVENTORY)
			return new GuiHarshenInventory(player);
		if(ID == MAGICTABLE)
			return new GuiMagicTable((TileEntityHarshenMagicTable) world.getTileEntity(new BlockPos(x, y, z)), player);
		if(ID == BOOK)
			return new GuiBookScreen();
		if(ID == XRAY)
			return new GuiXrayPendantScreen(player.getHeldItemMainhand());
		return null;
	}
}