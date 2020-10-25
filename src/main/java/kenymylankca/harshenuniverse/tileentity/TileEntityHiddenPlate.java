package kenymylankca.harshenuniverse.tileentity;

import kenymylankca.harshenuniverse.config.HarshenConfigs;
import kenymylankca.harshenuniverse.handlers.client.HandlerHiddenPlateRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityHiddenPlate extends TileEntity implements ITickable
{
	@SideOnly(Side.CLIENT)
	@Override
	public void update() {
		if(HarshenConfigs.GENERAL.renderHiddenPlates)
			HandlerHiddenPlateRenderer.addPosition(pos);
	}
}