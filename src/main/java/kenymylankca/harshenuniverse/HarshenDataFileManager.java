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
	private static File dataFile;
	
	public HarshenDataFileManager(World world)
	{
		dataFile = new File(world.getSaveHandler().getWorldDirectory(), HarshenUniverse.MODID + "data.nbt");
	}
	
	public void writeStructurePosToFile(World world, BlockPos pos, String structureName)
	{
		if(!dataFile.exists())
			try {
				dataFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		NBTTagCompound nbt = new NBTTagCompound();
		int posArray [] = {pos.getX(), pos.getY(), pos.getZ()};
		
		if(dataFile.exists())
			if(readNBTFromFile(dataFile) != null)
				nbt = readNBTFromFile(dataFile);
		
		nbt.setIntArray(structureName + "Pos", posArray);
		writeNBTToFile(nbt, dataFile);
	}
	
	public void writebooleanToFile(String name, boolean bool)
	{
		if(!dataFile.exists())
			try {
				dataFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		NBTTagCompound nbt = new NBTTagCompound();
		
		if(dataFile.exists())
			if(readNBTFromFile(dataFile) != null)
				nbt = readNBTFromFile(dataFile);
		
		nbt.setBoolean(name, bool);
		writeNBTToFile(nbt, dataFile);
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
		if(readNBTFromFile(dataFile) != null)
			if(readNBTFromFile(dataFile).hasKey(structureName + "Pos"))
			{
				BlockPos pos = new BlockPos(readNBTFromFile(dataFile).getIntArray(structureName + "Pos")[0],
						readNBTFromFile(dataFile).getIntArray(structureName + "Pos")[1],
						readNBTFromFile(dataFile).getIntArray(structureName + "Pos")[2]);
				return pos;
			}
		return null;
	}
	
	public boolean readBooleanFromFile(String name)
	{
		if(readNBTFromFile(dataFile) != null)
			if(readNBTFromFile(dataFile).hasKey(name))
				return readNBTFromFile(dataFile).getBoolean(name);
		return false;
	}
}