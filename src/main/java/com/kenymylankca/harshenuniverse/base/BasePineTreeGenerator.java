package com.kenymylankca.harshenuniverse.base;

import java.util.Random;

import kenymylankca.harshenuniverse.HarshenUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BasePineTreeGenerator extends WorldGenAbstractTree
{
	private final IBlockState TRUNK;
	private final IBlockState LEAF;
	
    public BasePineTreeGenerator(Block trunk, IBlockState leaf) {
		super(false);
		TRUNK = trunk.getDefaultState();
		LEAF = leaf;
	}

	public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        int i = rand.nextInt(5) + 7;
        int j = i - rand.nextInt(2) - 3;
        int k = i - j;
        int l = 1 + rand.nextInt(k + 1);

        if (position.getY() >= 1 && position.getY() + i + 1 <= 256)
        {
            boolean flag = true;

            for (int i1 = position.getY(); i1 <= position.getY() + 1 + i && flag; ++i1)
            {
                int j1 = 1;

                if (i1 - position.getY() < j)
                {
                    j1 = 0;
                }
                else
                {
                    j1 = l;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int k1 = position.getX() - j1; k1 <= position.getX() + j1 && flag; ++k1)
                {
                    for (int l1 = position.getZ() - j1; l1 <= position.getZ() + j1 && flag; ++l1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            if (!this.isReplaceable(worldIn,blockpos$mutableblockpos.setPos(k1, i1, l1)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }
            if (!flag)
            {
                return false;
            }
            else
            {
                BlockPos down = position.down();
                IBlockState state = worldIn.getBlockState(down);
                if (worldIn.getBiome(down) instanceof BasePontusResourceBiome && HarshenUtils.toArray(((BasePontusResourceBiome)worldIn.getBiome(down)).getGroundBlocks()).contains(state.getBlock())
                		&& position.getY() < 256 - i - 1)
                {
                    state.getBlock().onPlantGrow(state, worldIn, down, position);
                    int k2 = 0;
                    for (int l2 = position.getY() + i; l2 >= position.getY() + j; --l2)
                    {
                        for (int j3 = position.getX() - k2; j3 <= position.getX() + k2; ++j3)
                        {
                            int k3 = j3 - position.getX();

                            for (int i2 = position.getZ() - k2; i2 <= position.getZ() + k2; ++i2)
                            {
                                int j2 = i2 - position.getZ();

                                if (Math.abs(k3) != k2 || Math.abs(j2) != k2 || k2 <= 0)
                                {
                                    BlockPos blockpos = new BlockPos(j3, l2, i2);
                                    state = worldIn.getBlockState(blockpos);

                                    if (state.getBlock().canBeReplacedByLeaves(state, worldIn, blockpos))
                                    {
                                        this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF);
                                    }
                                }
                            }
                        }

                        if (k2 >= 1 && l2 == position.getY() + j + 1)
                        {
                            --k2;
                        }
                        else if (k2 < l)
                        {
                            ++k2;
                        }
                    }

                    for (int i3 = 0; i3 < i - 1; ++i3)
                    {
                        BlockPos upN = position.up(i3);
                        state = worldIn.getBlockState(upN);

                        if (state.getBlock().isAir(state, worldIn, upN) || state.getBlock().isLeaves(state, worldIn, upN))
                        {
                            this.setBlockAndNotifyAdequately(worldIn, position.up(i3), TRUNK);
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
}