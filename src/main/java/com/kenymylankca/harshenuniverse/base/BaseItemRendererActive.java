package com.kenymylankca.harshenuniverse.base;

public abstract class BaseItemRendererActive<T extends BaseTileEntityHarshenSingleItemInventoryActive>  extends BaseItemRenderer<T>
{
	@Override
	protected float getMovementSpeed(T te) {
		return te.isActive() ? te.getActiveTimer() / 10f: 1f;
	}
}