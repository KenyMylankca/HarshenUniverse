package kenijey.harshenuniverse.renderers.item;

import javax.vecmath.Vector3f;

import kenijey.harshenuniverse.base.BaseItemRendererActive;
import kenijey.harshenuniverse.tileentity.TileEntityBloodFactory;

public class RendererBloodFactory extends BaseItemRendererActive<TileEntityBloodFactory>
{
	@Override
	protected Vector3f movePos() {
		return new Vector3f(0.5f,0.65f,0.5f);
	}
}