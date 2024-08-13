package com.kenymylankca.harshenuniverse;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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
                for(int i=0; i<HarshenItems.HARSHEN_ITEMS.getEntries().size(); i++) {
                    output.accept(HarshenItems.HARSHEN_ITEMS.getEntries().stream().toList().get(i).get());
                    // Accepts an ItemLike. This assumes that MY_BLOCK has a corresponding item.
                    //output.accept(MyBlocksClass.MY_BLOCK.get());
                }
            })
            .backgroundTexture(ResourceLocation.parse("textures/gui/creativetab/harshen_tab.png"))
            .withSearchBar(57)
            .build()
    );
}