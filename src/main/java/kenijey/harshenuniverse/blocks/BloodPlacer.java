package kenijey.harshenuniverse.blocks;

import java.util.HashMap;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.network.HarshenNetwork;
import kenijey.harshenuniverse.network.packets.MessagePacketTileEntityBloodPlacerUpdated;
import kenijey.harshenuniverse.tileentity.TileEntityBloodVessel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BloodPlacer extends BlockHorizontal
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static HashMap<BlockPos, Boolean> blocksOnMap = new HashMap<>(	);
	
	public BloodPlacer() {
		super(Material.ROCK);
		setRegistryName("blood_placer");
		setUnlocalizedName("blood_placer");
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		BlockPos frontPos = pos.offset(state.getValue(FACING));
		
		if(worldIn.isBlockPowered(pos) && worldIn.isAirBlock(frontPos) &&
				worldIn.getBlockState(pos.up()).getBlock() instanceof BloodVessel &&
					((TileEntityBloodVessel)worldIn.getTileEntity(pos.up())).canRemove(1))
		{
			for(int i=0; i<10; i++)
			{
				if(!(worldIn.getBlockState(frontPos.down(i)).getBlock() instanceof BloodBlock) &&
					!blocksOnMap.containsKey(frontPos.down(i)) && worldIn.isSideSolid(frontPos.down(i), EnumFacing.UP))
				{
					BlockPos bloodpos = frontPos.down(i).up();
					if(worldIn.getBlockState(bloodpos).getBlock().canPlaceBlockAt(worldIn, bloodpos) )
					{
						blocksOnMap.put(bloodpos, true);
						((TileEntityBloodVessel)worldIn.getTileEntity(pos.up())).change(-1);
						HarshenNetwork.sendToAll(new MessagePacketTileEntityBloodPlacerUpdated(pos.up(), ((TileEntityBloodVessel)worldIn.getTileEntity(pos.up())).getPossibleRemove()));
						worldIn.setBlockState(bloodpos, HarshenBlocks.BLOOD_BLOCK.getDefaultState(), 3);
						break;
					}
				}
			}
		}
		else if(!worldIn.isBlockPowered(pos) && blocksOnMap.containsKey(pos))
			blocksOnMap.remove(pos);
	}

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
    	for(EnumFacing facing : EnumFacing.HORIZONTALS)
    		if(facing.getHorizontalIndex() == meta)
    			return this.getDefaultState().withProperty(FACING, facing);
    	return this.getDefaultState();
    }
    
    @Override
    public int getMetaFromState(IBlockState state) {
    	return state.getValue(FACING).getHorizontalIndex();
    }
    
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
}