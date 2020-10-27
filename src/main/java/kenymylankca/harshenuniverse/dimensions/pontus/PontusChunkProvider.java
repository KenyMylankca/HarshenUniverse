package kenymylankca.harshenuniverse.dimensions.pontus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.biomes.PontusBiomeProvider;
import kenymylankca.harshenuniverse.fluids.HarshenFluids;
import kenymylankca.harshenuniverse.integration.noodle.NoodleEvent;
import kenymylankca.harshenuniverse.worldgenerators.pontus.PontusCaveGenerator;
import kenymylankca.harshenuniverse.worldgenerators.pontus.PontusRavineGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.common.MinecraftForge;

public class PontusChunkProvider implements IChunkGenerator
{
    private final Random rand;
    private NoiseGeneratorOctaves minLimitPerlinNoise;
    private NoiseGeneratorOctaves maxLimitPerlinNoise;
    private NoiseGeneratorOctaves mainPerlinNoise;
    private NoiseGeneratorPerlin surfaceNoise;
    public NoiseGeneratorOctaves scaleNoise;
    public NoiseGeneratorOctaves depthNoise;
    public NoiseGeneratorOctaves forestNoise;
    public static World world;
    private final WorldType terrainType;
    private final double[] heightMap;
    private final float[] biomeWeights;
    private IBlockState oceanBlock = HarshenFluids.HARSHEN_DIMENSIONAL_FLUID_BLOCK.getDefaultState();
    private double[] depthBuffer = new double[256];
    private MapGenBase caveGenerator = new PontusCaveGenerator();
    private MapGenBase ravineGenerator = new PontusRavineGenerator();
    double[] mainNoiseRegion;
    double[] minLimitRegion;
    double[] maxLimitRegion;
    double[] depthRegion;
    
    private final int seaLevel = 52;
    private final double depthNoiseScaleX = 200;
    private final double depthNoiseScaleZ = 200;
    private final double depthNoiseScaleExponent = 0.5;
    private final float coordinateScale = 676.94366f;
    private final float heightScale = 684.412f;
    private final float mainNoiseScaleX = 80;
    private final float mainNoiseScaleY = 300;
    private final float mainNoiseScaleZ = 80;
    private final float biomeDepthOffSet = 0;
    private final float biomeDepthWeight = 1;
    private final float biomeScaleOffset = 0;
    private final float biomeScaleWeight = 1;
    private final double baseSize = 8.5;
    private final double stretchY = 24;
    private final double lowerLimitScale = 512;
    private final double upperLimitScale = 512;

    public PontusChunkProvider(World worldIn, long seed)
    {
        {
            caveGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(caveGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);
            ravineGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(ravineGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE);
        }
        this.world = worldIn;
        this.terrainType = worldIn.getWorldInfo().getTerrainType();
        this.rand = new Random(seed);
        this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.mainPerlinNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.forestNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.heightMap = new double[825];
        this.biomeWeights = new float[25];

        for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {
                float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
                this.biomeWeights[i + 2 + (j + 2) * 5] = f;
            }
        }

        this.oceanBlock = HarshenFluids.HARSHEN_DIMENSIONAL_FLUID_BLOCK.getDefaultState();
        worldIn.setSeaLevel(this.seaLevel);

        net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld ctx =
                new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld(minLimitPerlinNoise, maxLimitPerlinNoise, mainPerlinNoise, surfaceNoise, scaleNoise, depthNoise, forestNoise);
        ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx);
        this.minLimitPerlinNoise = ctx.getLPerlin1();
        this.maxLimitPerlinNoise = ctx.getLPerlin2();
        this.mainPerlinNoise = ctx.getPerlin();
        this.surfaceNoise = ctx.getHeight();
        this.scaleNoise = ctx.getScale();
        this.depthNoise = ctx.getDepth();
        this.forestNoise = ctx.getForest();
    }
    
    public Block[] getBlocks(Block... blocks)
    {
    	return blocks;
    }
    
    public void setBlocksInChunk(int x, int z, ChunkPrimer primer)
    {
        this.generateHeightmap(x * 4, 0, z * 4);

        for (int i = 0; i < 4; ++i)
        {
            int j = i * 5;
            int k = (i + 1) * 5;

            for (int l = 0; l < 4; ++l)
            {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;

                for (int i2 = 0; i2 < 32; ++i2)
                {
                    double d0 = 0.125D;
                    double d1 = this.heightMap[i1 + i2];
                    double d2 = this.heightMap[j1 + i2];
                    double d3 = this.heightMap[k1 + i2];
                    double d4 = this.heightMap[l1 + i2];
                    double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
                    double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
                    double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
                    double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;

                    for (int j2 = 0; j2 < 8; ++j2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.25D;
                        double d13 = (d4 - d2) * 0.25D;

                        for (int k2 = 0; k2 < 4; ++k2)
                        {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * 0.25D;
                            double lvt_45_1_ = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2)
                            	if ((lvt_45_1_ += d16) > 0.0D)
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, getRandomBlock(HarshenUtils.getPontusBlocks(x, i2 * 8 + j2, z)).getDefaultState()); 
                                else if (i2 * 8 + j2 < this.seaLevel)
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.oceanBlock);
                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }
    
    public Block getRandomBlock(ArrayList<Block> blockList) {
		return blockList.get(new Random().nextInt(blockList.size()));
	}

	public Block getRandomBlock(Block... blocks)
    {
    	return blocks[new Random().nextInt(blocks.length)];
    }

    public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome biomeIn)
    {
        if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.world)) return;
        double d0 = 0.03125D;
        this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, (double)(x * 16), (double)(z * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);
        for (int i = 0; i < 16; ++i)
        	for (int j = 0; j < 16; ++j)
            	biomeIn.genTerrainBlocks(this.world, this.rand, primer, x * 16 + i, z * 16 + j, this.depthBuffer[j + i * 16]);
    }

    /**
     * Generates the chunk at the specified position, from scratch
     */
    public Chunk generateChunk(int x, int z)
    {	
        this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(x, z, chunkprimer);

        this.replaceBiomeBlocks(x, z, chunkprimer, PontusBiomeProvider.biomeFromPosition(x, z));
        
        this.caveGenerator.generate(this.world, x, z, chunkprimer);
        this.ravineGenerator.generate(this.world, x, z, chunkprimer);

        NoodleEvent event = new NoodleEvent(this, world, x, z, chunkprimer);
        MinecraftForge.EVENT_BUS.post(event);
        chunkprimer = event.getPrimer();
        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        
        Biome[] biomes = this.world.getBiomeProvider().getBiomes(null, x * 16, z * 16, 16, 16);

        byte[] abyte = chunk.getBiomeArray();

                for (int i = 0; i < abyte.length; ++i)
                {
                    abyte[i] = (byte)Biome.getIdForBiome(biomes[i]);
                }
        
        chunk.generateSkylightMap();
        return chunk;
    }
    
    private int getMetaFromStateLog(IBlockState state)
    {
        switch ((BlockLog.EnumAxis)state.getValue(BlockLog.LOG_AXIS))
        {
            case X: return 0b0100;
            case Y: return 0b0000;
            case Z: return 0b1000;
            case NONE: return 0b1100;
        }
        return 0;
    }

    private void generateHeightmap(int p_185978_1_, int p_185978_2_, int p_185978_3_)
    {
        this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, p_185978_1_, p_185978_3_, 5, 5, (double)this.depthNoiseScaleX, (double)this.depthNoiseScaleZ, (double)this.depthNoiseScaleExponent);
        float f = this.coordinateScale;
        float f1 = this.heightScale;
        this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5, (double)(f / this.mainNoiseScaleX), (double)(f1 / this.mainNoiseScaleY), (double)(f / this.mainNoiseScaleZ));
        this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5, (double)f, (double)f1, (double)f);
        this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, p_185978_1_, p_185978_2_, p_185978_3_, 5, 33, 5, (double)f, (double)f1, (double)f);
        int i = 0;
        int j = 0;

        for (int k = 0; k < 5; ++k)
        {
            for (int l = 0; l < 5; ++l)
            {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                int i1 = 2;
                int x = p_185978_1_ / 4;
                int z = p_185978_3_ / 4;
                Biome biome2 = PontusBiomeProvider.biomeFromPosition(x, z);
                for (int j1 = -2; j1 <= 2; ++j1)
                {
                    for (int k1 = -2; k1 <= 2; ++k1)
                    {
                        Biome biome1 = PontusBiomeProvider.biomeFromPosition(x + j1,z + k1);
                        
                        float f5 = this.biomeDepthOffSet + biome1.getBaseHeight() * this.biomeDepthWeight;
                        float f6 = this.biomeScaleOffset + biome1.getHeightVariation() * this.biomeScaleWeight;

                        float f7 = this.biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);
                        
                        if (biome1.getBaseHeight() > biome2.getBaseHeight())
                        {
                            f7 /= 2.0F;
                        }

                        f2 += f6 * f7;
                        f3 += f5 * f7;
                        f4 += f7;
                    }
                }

                f2 = f2 / f4;
                f3 = f3 / f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = this.depthRegion[j] / 8000.0D;

                if (d7 < 0.0D)
                {
                    d7 = -d7 * 0.3D;
                }

                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D)
                {
                    d7 = d7 / 2.0D;

                    if (d7 < -1.0D)
                    {
                        d7 = -1.0D;
                    }

                    d7 = d7 / 1.4D;
                    d7 = d7 / 2.0D;
                }
                else
                {
                    if (d7 > 1.0D)
                    {
                        d7 = 1.0D;
                    }

                    d7 = d7 / 8.0D;
                }

                ++j;
                double d8 = (double)f3;
                double d9 = (double)f2;
                d8 = d8 + d7 * 0.2D;
                d8 = d8 * (double)this.baseSize / 8.0D;
                double d0 = (double)this.baseSize + d8 * 4.0D;

                for (int l1 = 0; l1 < 33; ++l1)
                {
                    double d1 = ((double)l1 - d0) * (double)this.stretchY * 128.0D / 256.0D / d9;

                    if (d1 < 0.0D)
                    {
                        d1 *= 4.0D;
                    }

                    double d2 = this.minLimitRegion[i] / (double)this.lowerLimitScale;
                    double d3 = this.maxLimitRegion[i] / (double)this.upperLimitScale;
                    double d4 = (this.mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

                    if (l1 > 29)
                    {
                        double d6 = (double)((float)(l1 - 29) / 3.0F);
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    this.heightMap[i] = d5;
                    ++i;
                }
            }
        }
    }

    /**
     * Generate initial structures in this chunk, e.g. mineshafts, temples, lakes, and dungeons
     */
    public void populate(int x, int z)
    {
        BlockFalling.fallInstantly = true;
        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        Biome biome = world.getBiome(blockpos.add(16, 0, 16));
        this.rand.setSeed(this.world.getSeed());
        long k = this.rand.nextLong() / 2L * 2L + 1L;
        long l = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)x * k + (long)z * l ^ this.world.getSeed());
        ChunkPos chunkpos = new ChunkPos(x, z);

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, false);


        if (this.rand.nextInt(1) == 0)
        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE))
        {
            int i1 = this.rand.nextInt(16) + 8;
            int j1 = this.rand.nextInt(256);
            int k1 = this.rand.nextInt(16) + 8;
            (new WorldGenLakes(HarshenFluids.HARSHEN_DIMENSIONAL_FLUID_BLOCK)).generate(this.world, this.rand, blockpos.add(i1, j1, k1));
        }

        if (this.rand.nextInt(5) == 0)
        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAVA))
        {
            int i2 = this.rand.nextInt(16) + 8;
            int l2 = this.rand.nextInt(this.rand.nextInt(248) + 8);
            int k3 = this.rand.nextInt(16) + 8;

            if (l2 < this.world.getSeaLevel() || this.rand.nextInt(2) == 0)
            {
                (new WorldGenLakes(HarshenFluids.HARSHING_WATER_BLOCK)).generate(this.world, this.rand, blockpos.add(i2, l2, k3));
            }
        }
        biome.decorate(this.world, this.rand, new BlockPos(i, 0, j));
        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS))
        	{
        		performWorldGenSpawning(this.world, EnumCreatureType.CREATURE, new BlockPos(x*16, 100, z*16), i + 8, j + 8, 16, 16, this.rand);
        		performWorldGenSpawning(this.world, EnumCreatureType.MONSTER, new BlockPos(x*16, 100, z*16) , i + 8, j + 8, 16, 16, this.rand);
        	}

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, false);

        BlockFalling.fallInstantly = false;
    }
    
    private void performWorldGenSpawning(World worldIn, EnumCreatureType type, BlockPos pos ,int p_77191_2_, int p_77191_3_, int p_77191_4_, int p_77191_5_, Random randomIn)
    {
    	float amount;
    	switch (type) {
		case MONSTER:
			amount = 0.1f;
			break;
		default:
			amount = 0.3f;
			break;
		}
    	List<SpawnListEntry> enteries = world.getBiome(pos).getSpawnableList(type);
    	SpawnListEntry entry = enteries.get(rand.nextInt(enteries.size()));
    	while (randomIn.nextFloat() < amount)
        {
            int i = entry.minGroupCount + randomIn.nextInt(1 + entry.maxGroupCount - entry.minGroupCount);
            IEntityLivingData ientitylivingdata = null;
            int j = p_77191_2_ + randomIn.nextInt(p_77191_4_);
            int k = p_77191_3_ + randomIn.nextInt(p_77191_5_);
            int l = j;
            int i1 = k;

            for (int j1 = 0; j1 < i; ++j1)
            {
                boolean flag = false;

                for (int k1 = 0; !flag && k1 < 4; ++k1)
                {
                    BlockPos blockpos = worldIn.getTopSolidOrLiquidBlock(new BlockPos(j, 0, k));

                    if (WorldEntitySpawner.canCreatureTypeSpawnAtLocation(EntityLiving.SpawnPlacementType.ON_GROUND, worldIn, blockpos))
                    {
                        EntityLiving entityliving;

                        try
                        {
                            entityliving = entry.newInstance(worldIn);
                        }
                        catch (Exception exception)
                        {
                            exception.printStackTrace();
                            continue;
                        }

                        entityliving.setLocationAndAngles((double)((float)j + 0.5F), (double)blockpos.getY(), (double)((float)k + 0.5F), randomIn.nextFloat() * 360.0F, 0.0F);
                        worldIn.spawnEntity(entityliving);
                        ientitylivingdata = entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), ientitylivingdata);
                        flag = true;
                    }

                    j += randomIn.nextInt(5) - randomIn.nextInt(5);

                    for (k += randomIn.nextInt(5) - randomIn.nextInt(5); j < p_77191_2_ || j >= p_77191_2_ + p_77191_4_ || k < p_77191_3_ || k >= p_77191_3_ + p_77191_4_; k = i1 + randomIn.nextInt(5) - randomIn.nextInt(5))
                    {
                        j = l + randomIn.nextInt(5) - randomIn.nextInt(5);
                    }
                }
            }
        }
    }


    public boolean generateStructures(Chunk chunkIn, int x, int z)
    {
        return false;
    }

    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
    	ArrayList<Biome.SpawnListEntry> list = new ArrayList<Biome.SpawnListEntry>();
    	list.add(new Biome.SpawnListEntry(EntityEndermite.class, 3, 2, 4));
        return list;
    }
    

    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos)
    {
        return false;
    }

    @Nullable
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored)
    {
        return null;
    }

    public void recreateStructures(Chunk chunkIn, int x, int z)
    {
    }
}