package kenijey.harshenuniverse.base;

import java.util.Random;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.dimensions.PontusBiomeDecorator;
import kenijey.harshenuniverse.worldgenerators.pontus.PontusWorldGeneratorDestroyedPlants;
import kenijey.harshenuniverse.worldgenerators.pontus.PontusWorldGeneratorStone;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;

public abstract class BasePontusResourceBiome extends Biome
{
	public BasePontusResourceBiome(String biomeName) {
		super(new Biome.BiomeProperties(biomeName).setTemperature(5f).setRainDisabled().setBaseHeight(0.7f).setHeightVariation(2f));
		
		setRegistryName(HarshenUniverse.MODID, biomeName);
		this.decorator = new PontusBiomeDecorator();
	}
	
	public abstract int getLevel();
	
	public abstract Block[] getGroundBlocks();
		
	protected abstract Block getMergerBlockDownLevel();
	
	protected abstract Block getMergerBlockUpLevel();
	
	public Block getMergerBlock(boolean isLevelDown)
	{
		if(isLevelDown)
			return getMergerBlockDownLevel();
		else
			return getMergerBlockUpLevel();
	}
	
	public abstract int distanceStartSpawn();
	
	public abstract BiomeDictionary.Type[] getTypes();
	
	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		super.decorate(worldIn, rand, pos);
		for (int i = 0; i < 13; ++i)
        {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            new PontusWorldGeneratorStone().generate(worldIn, rand, worldIn.getTopSolidOrLiquidBlock(pos.add(j, 0, k)));
        }
	}
	
	public float scrollAcrossSpeed()
	{
		return 1f;
	}
	
	public float scrollDownSpeed()
	{
		return 1f;
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return new PontusWorldGeneratorDestroyedPlants();
	}
	
	public Block[] getNonHightBlocks()
	{
		return null;
	}
	
	public int getHeightForNonHeightBlocks()
	{
		return 90;
	}
}