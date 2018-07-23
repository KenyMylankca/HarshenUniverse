package kenijey.harshenuniverse.biomes;

import java.util.Random;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.base.BasePineTreeGenerator;
import kenijey.harshenuniverse.base.BasePontusResourceBiome;
import kenijey.harshenuniverse.entity.EntitySoullessKnight;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary.Type;

public class PontusFarBiome extends BasePontusResourceBiome {
		
	public PontusFarBiome() {
		super("Pontus_Far");
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityEnderman.class, 10, 2, 7));
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntitySoullessKnight.class, 12, 3, 7));

		this.decorator.extraTreeChance = 0.7f;		
		
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		this.decorator.treesPerChunk = 3;
		return new BasePineTreeGenerator(HarshenBlocks.PONTUS_FAR_WOOD, HarshenBlocks.PONTUS_FAR_LEAVES.getDefaultState());
	}	
	
	@Override
	public int getLevel() {
		return 2;
	}
	
	@Override
	public int distanceStartSpawn() {
		return 20000;
	}
	
	@Override
	public float scrollAcrossSpeed() {
		return 0;
	}
	
	@Override
	public float scrollDownSpeed() {
		return 2;
	}


	@Override
	public Block[] getGroundBlocks() {
		return HarshenUtils.listOf(HarshenBlocks.HARSHEN_FAR_ROCK, HarshenBlocks.HARSHEN_CHAOTIC_ROCK);
	}


	@Override
	public Block getMergerBlockDownLevel() {
		return HarshenBlocks.HARSHEN_FAR_ROCK;
	}	
	
	@Override
	protected Block getMergerBlockUpLevel() {
		return HarshenBlocks.HARSHEN_CHAOTIC_ROCK;
	}
	
	@Override
	public Type[] getTypes() {
		return new Type[]{Type.COLD, Type.MOUNTAIN};
	}	

}
