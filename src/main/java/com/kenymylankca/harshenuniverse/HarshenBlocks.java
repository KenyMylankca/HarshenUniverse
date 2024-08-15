package com.kenymylankca.harshenuniverse;

import com.kenymylankca.harshenuniverse.blocks.AkzeniaMushroom;
import com.kenymylankca.harshenuniverse.blocks.Archive;
import com.kenymylankca.harshenuniverse.blocks.BlockOfHeads;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HarshenBlocks
{
    public static final DeferredRegister.Blocks HARSHEN_BLOCKS = DeferredRegister.createBlocks(HarshenUniverse.MOD_ID);

    public static final DeferredBlock<Block> AKZENIA_MUSHROOM = HARSHEN_BLOCKS.register(AkzeniaMushroom.registryName, AkzeniaMushroom::new);
    public static final DeferredBlock<Block> ARCHIVE = HARSHEN_BLOCKS.register(Archive.registryName, Archive::new);
    public static final DeferredBlock<Block> BLOCK_OF_HEADS = HARSHEN_BLOCKS.register(BlockOfHeads.registryName, BlockOfHeads::new);

    public static void register(IEventBus eventBus)
    {
        HARSHEN_BLOCKS.register(eventBus);
    }
}