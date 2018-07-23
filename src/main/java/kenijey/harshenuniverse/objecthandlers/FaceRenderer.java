package kenijey.harshenuniverse.objecthandlers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

public class FaceRenderer 
{
	private final IBlockState state;
	private final BlockPos position;
	
	public FaceRenderer(BlockPos position, IBlockState state) {
		this.position = position;
		this.state = state;
	}
	
	public IBlockState getState() {
		return state;
	}

	public BlockPos getPosition() {
		return position;
	}
}
