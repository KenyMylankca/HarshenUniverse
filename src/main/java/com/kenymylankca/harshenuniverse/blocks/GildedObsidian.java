package com.kenymylankca.harshenuniverse.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GildedObsidian extends Block
{
	public GildedObsidian() 
	{
		super(Material.ROCK);
        setUnlocalizedName("gilded_obsidian");
        setRegistryName("gilded_obsidian");
        setHardness(80.0f);
        setResistance(40.0f);
        setHarvestLevel("pickaxe", 3);
    }
}