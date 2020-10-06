package kenymylankca.harshenuniverse.biomes;

import java.util.Random;

import kenymylankca.harshenuniverse.HarshenBlocks;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BasePontusResourceBiome;
import kenymylankca.harshenuniverse.entity.EntitySoulPart;
import kenymylankca.harshenuniverse.worldgenerators.pontus.PontusWorldGeneratorDestroyedPlants;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary.Type;

public class PontusBiome extends BasePontusResourceBiome
{
	public PontusBiome() {
		super("Pontus");
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityEnderman.class, 25, 1, 1));
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityEndermite.class, 40, 1, 2));
		
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySoulPart.class, 30, 1, 1));
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return new WorldGenTrees(false, 3, HarshenBlocks.PONTUS_DEAD_WOOD.getDefaultState(), HarshenBlocks.PONTUS_DEAD_LEAVES.getDefaultState(), false);
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return new PontusWorldGeneratorDestroyedPlants();
	}
	
	@Override
	public Block[] getNonHightBlocks() {
		return HarshenUtils.listOf(HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT);
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public int distanceStartSpawn() {
		return -1;
	}


	@Override
	public Block[] getGroundBlocks() {
		return HarshenUtils.listOf(HarshenBlocks.HARSHEN_DIMENSIONAL_ROCK, HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT);
	}


	@Override
	public Block getMergerBlockDownLevel() {
		return HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT;
	}
	
	@Override
	public Block getMergerBlockUpLevel() {
		return HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT;
	}


	@Override
	public Type[] getTypes() {
		return new Type[]{Type.DRY, Type.HILLS};
	}
}