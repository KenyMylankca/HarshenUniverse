package kenymylankca.harshenuniverse.base;

import java.util.ArrayList;
import java.util.Random;

import kenymylankca.harshenuniverse.HarshenUniverse;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.interfaces.ICommandStructure;
import kenymylankca.harshenuniverse.template.HarshenTemplate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;

public class HarshenStructure implements ICommandStructure
{
	public static ArrayList<HarshenStructure> allStructures = new ArrayList<>();
	
	protected BlockPos originAddition;
	protected final ResourceLocation location;
	protected final String name;
	public String showName;
	protected BlockPos size;
	private static boolean hasLoaded;
	protected final float chance;
	protected final boolean useRuin;
	protected final boolean displaceDownwards;
	protected final int dimension;
	
	public HarshenStructure(String parentFolder, String name, float chance, boolean useRuin, int dimension, boolean displaceDownwards)
	{
		this.dimension = dimension;
		this.useRuin = useRuin;
		this.chance = chance;
		this.displaceDownwards = displaceDownwards;
		this.name = parentFolder + "/" + name;
		showName = name;
		this.location = new ResourceLocation(HarshenUniverse.MODID, this.name);
	}
	
	public HarshenStructure(String parentFolder, String name, float chance, boolean useRuin, int dimension) {
		this(parentFolder, name, chance, useRuin, dimension, true);
	}
	
	public static ArrayList<HarshenStructure> get(int dimension)
	{
		ArrayList<HarshenStructure> structures = new ArrayList<>();
		for(HarshenStructure struc : allStructures)
			if(struc.dimension == dimension)
				structures.add(struc);
		return structures;
	}
	
	public static void load()
	{
		if(hasLoaded)
			return;
		for(HarshenStructure struc : allStructures)
		{
			HarshenTemplate t = HarshenTemplate.getTemplate(struc.location);
			struc.setSize(t.getSize());
			struc.setPos(t.getPos());
			ICommandStructure.ALL_STRUCTURES.add(struc);
		}
		hasLoaded = true;
	}
	
	public String getShowName() {
		return showName;
	}
	
	public boolean canLoadAt(int dimension, int chunkX, int chunkZ)
	{
		return true;
	}
	
	public BlockPos addPos()
	{
		return BlockPos.ORIGIN;
	}
	
	public void preAddition(World world, BlockPos pos, Random random){};
	public void postAddition(World world, BlockPos pos, Random random){};
	
	private void setSize(BlockPos size) {
		this.size = size;
	}
	
	private void setPos(BlockPos pos) {
		this.originAddition = pos;
	}
	
	public void loadIntoWorld(World world, BlockPos pos, Random random, boolean useRuin)
	{
		if(world.isRemote)
			return;
		preAddition(world, pos, random);
		HarshenTemplate.getTemplate(location).addBlocksToWorld(world, pos, new PlacementSettings().setIgnoreEntities(false).setIgnoreStructureBlock(true), random, useRuin);
		postAddition(world, pos, random);
	}
	
	public boolean generateStucture(World world, Random random, int chunkX, int chunkZ)
	{
		if(random.nextFloat() < chance)
		{
	        int x = chunkX * 16 + random.nextInt(16);
	        int z = chunkZ * 16 + random.nextInt(16);
	        BlockPos pos = HarshenUtils.getTopBlock(world, new BlockPos(x, 0, z)).add(originAddition).add(addPos());
	        if(pos.getY() < 0)
	        	pos = new BlockPos(pos.getX(), 5, pos.getZ());
	        if(world.getBlockState(pos).getBlock() instanceof BlockLiquid && !canSpawnOnWater())
	        	return false;
	        loadIntoWorld(world, pos, random, useRuin);
	        for(int x1 = 0; x1 < size.getX(); x1++) 
	        	for(int z1 = 0; z1 < size.getZ(); z1++)
		        	if(world.getBlockState(pos.add(x1, -1, z1)).getBlock().isReplaceable(world, pos.add(x1, -1, z1)) && !world.isAirBlock(pos.add(x1, 0, z1)))
		        		for(int y1 = 1; world.getBlockState(pos.add(x1, -y1, z1)).getBlock().isReplaceable(world, pos.add(x1, -y1, z1)) && pos.getY() - y1 > -1; y1++)
		        			world.setBlockState(pos.add(x1, -y1, z1), world.getBlockState(pos.add(x1, 0, z1)));
	        return true;
		}
		return false;
	}
	
	public boolean canSpawnOnWater() {
		return false;
	}
	
	public BlockPos getOriginAddition() {
		return originAddition;
	}
	
	public ResourceLocation getLocation() {
		return location;
	}

	@Override
	public String structureName() {
		return showName;
	}

	@Override
	public void addToWorld(World world, BlockPos pos, Random random, boolean useRuin) {
		HarshenTemplate.getTemplate(location).addBlocksToWorld(world, pos, new PlacementSettings().setIgnoreEntities(false).setIgnoreStructureBlock(true), random, useRuin);
	}
}