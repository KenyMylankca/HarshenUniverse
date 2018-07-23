package kenijey.harshenuniverse.renderers.item;

import javax.vecmath.Vector3f;

import kenijey.harshenuniverse.base.BaseItemRenderer;
import kenijey.harshenuniverse.tileentity.TileEntityPedestalSlab;

public class RendererPedestalSlab extends BaseItemRenderer<TileEntityPedestalSlab>
{
	@Override
	protected float getMovementSpeed(TileEntityPedestalSlab te) {
	return 4f;
	}

	@Override
	protected Vector3f movePos() {
		return new Vector3f(0.5f, 0.3f,0.5f);
	}
}