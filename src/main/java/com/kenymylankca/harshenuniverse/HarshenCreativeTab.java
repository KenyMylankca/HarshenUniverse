package com.kenymylankca.harshenuniverse;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HarshenCreativeTab
{
    public static final DeferredRegister<CreativeModeTab> HARSHEN_CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HarshenUniverse.MOD_ID);

    public static final Supplier<CreativeModeTab> EXAMPLE_TAB = HARSHEN_CREATIVE_TABS.register("example", () -> CreativeModeTab.builder()
            //Set the title of the tab. Don't forget to add a translation!
            .title(Component.translatable(HarshenUniverse.MOD_ID))
            //Set the icon of the tab.
            .icon(() -> new ItemStack(HarshenItems.AKZENIA_SOUP.get()))
            //Add your items to the tab.
            .displayItems((params, output) -> {
                for(DeferredHolder<Item, ? extends Item> item : HarshenItems.HARSHEN_ITEMS.getEntries())
                {
                    output.accept(item.get());
                }
            })
            .backgroundTexture(ResourceLocation.parse("textures/gui/creativetab/harshen_tab.png"))
            .withSearchBar(57)
            .build()
    );
}