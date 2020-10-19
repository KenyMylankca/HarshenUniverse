package kenymylankca.harshenuniverse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenDataFileManager
{
	private static File file;
	
	public HarshenDataFileManager(World world)
	{
		int dim = world.provider.getDimension();
		
		file = new File(world.getSaveHandler().getWorldDirectory(), HarshenUniverse.MODID + "data.nbt");
		
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void writeStructurePosToFile(World world, BlockPos pos, String structureName)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		int posArray [] = {pos.getX(), pos.getY(), pos.getZ()};
		
		if(file.exists())
			if(readNBTFromFile(file) != null)
				nbt = readNBTFromFile(file);
		
		nbt.setIntArray(structureName + "Pos", posArray);
		writeNBTToFile(nbt, file);
	}
	
	public void writeNBTToFile(NBTTagCompound nbt, File file)
	{
		try {
			OutputStream outStream = null;
			outStream = new FileOutputStream(file);
			CompressedStreamTools.writeCompressed(nbt, outStream);
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public NBTTagCompound readNBTFromFile(File file)
	{
		if(file.exists())
		{
			InputStream stream = null;
			try {
				stream = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			if (stream != null) {
				NBTTagCompound nbt = null;
				try {
					nbt = CompressedStreamTools.readCompressed(stream);
				} catch (IOException ex) {
					return null;
				}
				if (nbt != null)
					return nbt;
			}
		}
		return null;
	}
	
	public BlockPos readStructurePosFromFile(String structureName)
	{
		if(readNBTFromFile(file) != null)
			if(readNBTFromFile(file).hasKey(structureName + "Pos"))
			{
				BlockPos pos = new BlockPos(readNBTFromFile(file).getIntArray(structureName + "Pos")[0],
						readNBTFromFile(file).getIntArray(structureName + "Pos")[1],
						readNBTFromFile(file).getIntArray(structureName + "Pos")[2]);
				return pos;
			}
		return null;
	}
}