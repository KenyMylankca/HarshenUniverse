package com.kenymylankca.harshenuniverse.biomes;

import java.util.Random;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseLargeTreeGenerator;
import kenymylankca.harshenuniverse.base.BasePontusResourceBiome;
import kenymylankca.harshenuniverse.worldgenerators.pontus.PontusWorldGeneratorDestroyedPlants;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary.Type;

public class PontusOuterBiome extends BasePontusResourceBiome
{
	public PontusOuterBiome() {
		super("Pontus_Chaotic");
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityEnderman.class, 10, 2, 7));
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityEndermite.class, 100, 5, 17));

		this.decorator.extraTreeChance = 0.5f;
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return new BaseLargeTreeGenerator(false, HarshenBlocks.PONTUS_CHAOTIC_LEAVES.getDefaultState(), HarshenBlocks.PONTUS_CHAOTIC_WOOD);
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return new PontusWorldGeneratorDestroyedPlants();
	}
	
	@Override
	public int getLevel() {
		return 1;
	}
	
	@Override
	public int distanceStartSpawn() {
		return 7500;
	}


	@Override
	public Block[] getGroundBlocks() {
		return HarshenUtils.listOf(HarshenBlocks.HARSHEN_DIMENSIONAL_ROCK, HarshenBlocks.PONTUS_CHAOTIC_ROCK);
	}


	@Override
	public Block getMergerBlockDownLevel() {
		return HarshenBlocks.PONTUS_CHAOTIC_ROCK;
	}
	
	@Override
	public Block getMergerBlockUpLevel() {
		return HarshenBlocks.HARSHEN_DIMENSIONAL_ROCK;
	}
	
	@Override
	public Type[] getTypes() {
		return new Type[]{Type.DRY, Type.MOUNTAIN};
	}
}