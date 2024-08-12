package com.kenymylankca.harshenuniverse;

import com.kenymylankca.harshenuniverse.items.AkzeniaSoup;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HarshenItems
{
    public static final DeferredRegister.Items HARSHEN_ITEMS = DeferredRegister.createItems(HarshenUniverse.MOD_ID);

    public static final DeferredItem<Item> AKZENIA_SOUP = HARSHEN_ITEMS.register(AkzeniaSoup.registryName, AkzeniaSoup::new);

    public static void register(IEventBus eventBus)
    {
        HARSHEN_ITEMS.register(eventBus);
    }
}