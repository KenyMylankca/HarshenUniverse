package kenijey.harshenuniverse.worldgenerators;

import java.util.Random;

import net.minecraft.block.BlockChest;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ChestGenerator extends WorldGenerator
{
	private final BlockPos size;
	private final float chance;
	private final ResourceLocation lootTable;
	private final boolean setBlock;
	
	public ChestGenerator(BlockPos size, float chance, ResourceLocation lootTable, boolean setBlock) {
		this.lootTable = lootTable;
		this.chance = chance;
		this.size = size; 
		this.setBlock = setBlock;
	}
	
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if(size.equals(BlockPos.ORIGIN) && rand.nextFloat() < this.chance)
		{
			worldIn.setBlockState(position, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.HORIZONTALS[rand.nextInt(4)]), 3);
			((TileEntityChest)worldIn.getTileEntity(position)).setLootTable(lootTable, rand.nextLong());
			return false;
		}
		for(int x = 0; x < size.getX(); x++)
			for(int z = 0; z < size.getZ(); z++)
				if(rand.nextFloat() < this.chance)
				{
					BlockPos blockpos = new BlockPos(position.add(x, 0, z));
					boolean flag = false;
					for(EnumFacing facing : EnumFacing.HORIZONTALS)
						if(worldIn.getBlockState(blockpos.offset(facing)).getBlock() == Blocks.CHEST)
							flag = true;
					if(flag || !worldIn.isAirBlock(blockpos))
						continue;
					if(setBlock)
						worldIn.setBlockState(blockpos, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.HORIZONTALS[rand.nextInt(4)]), 3);
					((TileEntityChest)worldIn.getTileEntity(blockpos)).setLootTable(lootTable, rand.nextLong());
				}
		return false;
	}

}
