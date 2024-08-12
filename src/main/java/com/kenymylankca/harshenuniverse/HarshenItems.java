package com.kenymylankca.harshenuniverse;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HarshenItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HarshenUniverse.MODID);

    public static final DeferredItem<Item> AKZENIA_SOUP = ITEMS.register("akzenia_soup", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}