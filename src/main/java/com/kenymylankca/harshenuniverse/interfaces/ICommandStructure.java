package com.kenymylankca.harshenuniverse.interfaces;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ICommandStructure 
{
	public static ArrayList<ICommandStructure> ALL_STRUCTURES = new ArrayList<>();
	
	String structureName();
	
	void addToWorld(World world, BlockPos pos, Random random, boolean useRuin);
}