package com.kenymylankca.harshenuniverse.structures.overworld;

import java.util.Random;

import kenymylankca.harshenuniverse.HarshenLootTables;
import kenymylankca.harshenuniverse.base.HarshenStructure;
import kenymylankca.harshenuniverse.worldgenerators.ChestGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Graveyard extends HarshenStructure
{
	public Graveyard() {
		super("overworld", "graveyard", 1f, false, 0, true);
	}
	
	@Override
	public boolean canLoadAt(int dimension, int chunkX, int chunkZ) {
		return false;
	}
	
	@Override
	public boolean canSpawnOnWater() {
		return true;
	}
	
	@Override
	public void postAddition(World world, BlockPos pos, Random random) {
		BlockPos[] possOfFillableChests = {new BlockPos(13, 1, 4), new BlockPos(8, 1, 1), new BlockPos(6, 1, 7), new BlockPos(1, 1, 3)};
		for(BlockPos chestPos : possOfFillableChests)
			new ChestGenerator(BlockPos.ORIGIN, 1f, HarshenLootTables.graveyard, false).generate(world, random, pos.add(chestPos));
	}
}