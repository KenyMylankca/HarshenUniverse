package com.kenymylankca.harshenuniverse;

import com.kenymylankca.harshenuniverse.blocks.AkzeniaMushroom;
import com.kenymylankca.harshenuniverse.blocks.Archive;
import com.kenymylankca.harshenuniverse.blocks.BlockOfHeads;
import com.kenymylankca.harshenuniverse.blocks.Blood;
import com.kenymylankca.harshenuniverse.items.AkzeniaSoup;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HarshenItems
{
    public static final DeferredRegister.Items HARSHEN_ITEMS = DeferredRegister.createItems(HarshenUniverse.MOD_ID);

    //ITEMS
    public static final DeferredItem<Item> AKZENIA_SOUP = HARSHEN_ITEMS.register(AkzeniaSoup.registryName, AkzeniaSoup::new);

    //BLOCKS
    public static final DeferredItem<BlockItem> AKZENIA_MUSHROOM =
            HARSHEN_ITEMS.register(AkzeniaMushroom.registryName, () -> new BlockItem(HarshenBlocks.AKZENIA_MUSHROOM.get(), new Item.Properties().rarity(Rarity.EPIC)));
    public static final DeferredItem<BlockItem> ARCHIVE =
            HARSHEN_ITEMS.register(Archive.registryName, () -> new BlockItem(HarshenBlocks.ARCHIVE.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final DeferredItem<BlockItem> BLOCK_OF_HEADS =
            HARSHEN_ITEMS.register(BlockOfHeads.registryName, () -> new BlockItem(HarshenBlocks.BLOCK_OF_HEADS.get(), new Item.Properties().rarity(Rarity.COMMON)));
    public static final DeferredItem<BlockItem> BLOOD =
            HARSHEN_ITEMS.register(Blood.registryName, () -> new BlockItem(HarshenBlocks.BLOOD.get(), new Item.Properties().rarity(Rarity.COMMON)));

    public static void register(IEventBus eventBus)
    {
        HARSHEN_ITEMS.register(eventBus);
    }
}