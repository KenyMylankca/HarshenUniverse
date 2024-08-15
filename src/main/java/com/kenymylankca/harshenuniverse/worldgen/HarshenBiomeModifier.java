package com.kenymylankca.harshenuniverse.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeGenerationSettingsBuilder;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

import java.util.List;

public record HarshenBiomeModifier(List<List<BiomeProp>> biomePropLists, HolderSet<PlacedFeature> features, GenerationStep.Decoration step) implements BiomeModifier
{
    public static final MapCodec<HarshenBiomeModifier> CODEC = RecordCodecBuilder.mapCodec(builder -> builder.group(
                    BiomeProp.CODEC.listOf().listOf().fieldOf("biomes").forGetter(HarshenBiomeModifier::biomePropLists),
                    PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(HarshenBiomeModifier::features),
                    GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(HarshenBiomeModifier::step))
            .apply(builder, HarshenBiomeModifier::new));

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder)
    {
        if (phase == Phase.ADD && matches(biome, this.biomePropLists))
        {
            BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
            this.features.forEach(holder -> generationSettings.addFeature(this.step, holder));
        }
    }

    public static boolean matches(Holder<Biome> biome, List<List<BiomeProp>> biomePropLists)
    {
        if (biomePropLists != null && !biomePropLists.isEmpty())
        {
            for (List<BiomeProp> biomeProps : biomePropLists)
            {
                boolean flag = true;

                for (BiomeProp biomeProp : biomeProps)
                {
                    if (!biomeProp.biomes().contains(biome) ^ biomeProp.negate())
                    {
                        flag = false;
                        break;
                    }
                }

                if (flag)
                {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec()
    {
        return HarshenBiomeModifiers.ADD_FEATURES.get();
    }

    public static record BiomeProp(HolderSet<Biome> biomes, boolean negate)
    {
        public static final Codec<BiomeProp> CODEC = RecordCodecBuilder.create((p -> {
            return p.group(Biome.LIST_CODEC.fieldOf("name").forGetter(BiomeProp::biomes), Codec.BOOL.optionalFieldOf("negate", false).forGetter(BiomeProp::negate)).apply(p, BiomeProp::new);
        }));
    }
}