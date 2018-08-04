package kenijey.harshenuniverse.tileentity;

import kenijey.harshenuniverse.config.HarshenConfigs;
import kenijey.harshenuniverse.handlers.client.HandlerHiddenPlateRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityHiddenPlate extends TileEntity implements ITickable
{
	@Override
	public void update() {
		if(HarshenConfigs.GENERAL.renderHiddenPlates)
		HandlerHiddenPlateRenderer.addPosition(pos);
	}
}