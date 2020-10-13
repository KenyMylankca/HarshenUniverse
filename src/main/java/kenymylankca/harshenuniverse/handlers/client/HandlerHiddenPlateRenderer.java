package kenymylankca.harshenuniverse.handlers.client;

import java.util.ArrayList;

import javax.vecmath.Vector4f;

import kenymylankca.harshenuniverse.HarshenClientUtils;
import kenymylankca.harshenuniverse.blocks.HarshenDimensionalHiddenPlate;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HandlerHiddenPlateRenderer 
{
	private static ArrayList<BlockPos> platePositions = new ArrayList<BlockPos>();
	
	@SubscribeEvent
	public void lastRender(RenderWorldLastEvent event)
	{
		if(!Minecraft.getMinecraft().player.capabilities.isCreativeMode && !Minecraft.getMinecraft().player.isSpectator())
			return;
		ArrayList<BlockPos> nonRemovedPlates = new ArrayList<>();
		int time = platePositions.size();
		for(int i = 0; i < time; i++)
			if(Minecraft.getMinecraft().world.getBlockState(platePositions.get(i)).getBlock() instanceof HarshenDimensionalHiddenPlate)
				nonRemovedPlates.add(renderAt(platePositions.get(i), event.getPartialTicks()));
		platePositions = nonRemovedPlates;
	}
	
	private BlockPos renderAt(BlockPos pos, float partialTicks)
	{
		HarshenClientUtils.renderFaceAt(EnumFacing.DOWN, pos, partialTicks, new Vector4f(0, 0, 1, 1), 1f);
        return pos;
	}
	
	public static void addPosition(BlockPos pos)
	{
		if(platePositions.contains(pos))
			return;
		platePositions.add(pos);
	}
}