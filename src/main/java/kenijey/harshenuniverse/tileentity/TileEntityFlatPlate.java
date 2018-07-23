package kenijey.harshenuniverse.tileentity;

import kenijey.harshenuniverse.config.HarshenConfigs;
import kenijey.harshenuniverse.handlers.client.HandlerFlatPlateRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityFlatPlate extends TileEntity implements ITickable
{

	@Override
	public void update() {
		if(HarshenConfigs.GENERAL.renderPlates)
		HandlerFlatPlateRenderer.addPosition(pos);
	}
}
