package com.kenymylankca.harshenuniverse.blocks;

import com.kenymylankca.harshenuniverse.base.HarshenFacedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockOfHeads extends HarshenFacedBlock
{
    public static final String registryName = "block_of_heads";

    public BlockOfHeads() {
        super(BlockBehaviour.Properties.of()
                .sound(SoundType.STONE)
                .strength(1.5F, 2)
                .requiresCorrectToolForDrops()
        );
    }
}