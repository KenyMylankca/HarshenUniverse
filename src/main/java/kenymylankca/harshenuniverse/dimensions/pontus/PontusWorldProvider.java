package kenymylankca.harshenuniverse.dimensions.pontus;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.biomes.PontusBiomeProvider;
import kenymylankca.harshenuniverse.dimensions.DimensionPontus;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PontusWorldProvider extends WorldProviderHell
{
	@Override
	public void init() {
		this.hasSkyLight = false;
		this.biomeProvider = new PontusBiomeProvider();
		HarshenUniverse.commonProxy.setWorldRenderer(this);
	}
	
	@Override
	protected void generateLightBrightnessTable() {
		float[] d = {0,0,0,0,0,0,0,0,0.017543858f, 0.058823526f, 0.11111113f, 0.1794872f, 0.2727273f, 0.40740743f, 0.61904764f, 1.0f};
        for (int i = 0; i <= 15; ++i)
        	this.lightBrightnessTable[i] = d[i];
	}
	
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return 0;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new PontusChunkProvider(this.world, this.getSeed());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks)
	{
		return null;
	}
	
	@Override
	public BlockPos getRandomizedSpawnPoint() {
		return world.getTopSolidOrLiquidBlock(new BlockPos(0, 0, 0));
	}

	
	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
	{
//		generateLightBrightnessTable();
//        float f = MathHelper.cos(p_76562_1_ * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
//        f = MathHelper.clamp(f, 0.0F, 1.0F);
//        int color = 0;
//        float f1 = ((color >> 16) & 0xFF) * 255;
//        float f2 = ((color >> 8) & 0xFF) * 255;
//        float f3 = ((color >> 0) & 0xFF) * 255;
//        f1 = f1 * (f * 0.0F + 0.15F);
//        f2 = f2 * (f * 0.0F + 0.15F);
//        f3 = f3 * (f * 0.0F + 0.15F);
//        return new Vec3d((double)f1, (double)f2, (double)f3);
		return Vec3d.ZERO;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isSkyColored()
	{
		return false;
	}

	@Override
	public boolean canRespawnHere()
	{
		return false;
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return false;
	}


	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int x, int z)
	{
		return false;
	}

	@Override
	public DimensionType getDimensionType() {
		return DimensionPontus.PONTUS_DIMENSION;
	}
	
	public WorldBorder createWorldBorder()
    {
        return new WorldBorder();
    }
}