package kenijey.harshenuniverse.dimensions;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class PontusBiomeDecorator extends BiomeDecorator
{
	 @Override
	public void decorate(World worldIn, Random random, Biome biome, BlockPos pos) {
		 if (this.decorating)
	     {
	         throw new RuntimeException("Already decorating");
	     }
		 else
		 {
			this.chunkProviderSettings = ChunkGeneratorSettings.Factory.jsonToFactory(worldIn.getWorldInfo().getGeneratorOptions()).build();
	        this.chunkPos = pos;
			this.genDecorations(biome, worldIn, random);
			decorating = false;
		 }
	}
	 
	 @Override
	protected void genDecorations(Biome biomeIn, World worldIn, Random random) {
		 			 	
		 	int k1 = this.treesPerChunk;
	        if (random.nextFloat() < this.extraTreeChance)
	        {
	            ++k1;
	        }

	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, chunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE))
	        for (int j2 = 0; j2 < k1; ++j2)
	        {
	            int k6 = random.nextInt(16) + 8;
	            int l = random.nextInt(16) + 8;
	            WorldGenAbstractTree worldgenabstracttree = biomeIn.getRandomTreeFeature(random);
	            worldgenabstracttree.setDecorationDefaults();
	            BlockPos blockpos = worldIn.getHeight(this.chunkPos.add(k6, 0, l));

	            if (worldgenabstracttree.generate(worldIn, random, blockpos))
	            {
	                worldgenabstracttree.generateSaplings(worldIn, random, blockpos);
	            }
	        }
	}
}
