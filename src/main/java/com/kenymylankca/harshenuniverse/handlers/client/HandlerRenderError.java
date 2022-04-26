package com.kenymylankca.harshenuniverse.handlers.client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import kenymylankca.harshenuniverse.HarshenClientUtils;
import kenymylankca.harshenuniverse.objecthandlers.FaceRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HandlerRenderError 
{
	public static ArrayList<FaceRenderer> erroredPositions = new ArrayList<>();
	
	@SubscribeEvent
	public void playerOverLay(RenderWorldLastEvent event)
	{
		GlStateManager.pushMatrix();
		{
			GlStateManager.enableBlend();
	        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_COLOR);
			ArrayList<FaceRenderer> erroredPositions = new ArrayList<>(this.erroredPositions);
			HashMap<Double, FaceRenderer> map = new HashMap<>();
			for(FaceRenderer erroredPosition : this.erroredPositions)
				map.put(Minecraft.getMinecraft().player.getDistanceSq(erroredPosition.getPosition()), erroredPosition);
			ArrayList<Double> dMap = new ArrayList<>();
			for(Double d : map.keySet()) dMap.add(d);
			Collections.sort(dMap);
			Collections.reverse(dMap);
			for(Double d : dMap)
				if((Minecraft.getMinecraft().world.getBlockState(map.get(d).getPosition()).getBlock() == map.get(d).getState().getBlock() &&
				Minecraft.getMinecraft().world.getBlockState(map.get(d).getPosition()).getBlock().getMetaFromState(Minecraft.getMinecraft().world.getBlockState(map.get(d).getPosition())) == 
						map.get(d).getState().getBlock().getMetaFromState(map.get(d).getState())) || map.get(d).getState().getBlock() == Blocks.BARRIER && Minecraft.getMinecraft()
						.world.getBlockState( map.get(d).getPosition()).getBlock() != Blocks.OBSIDIAN)
					this.erroredPositions.remove(map.get(d));
			else if(map.get(d).getPosition().distanceSq(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.posZ) < 400)
				HarshenClientUtils.renderGhostBlock(map.get(d).getState().getBlock() == Blocks.BARRIER ? Blocks.OBSIDIAN.getDefaultState() : map.get(d).getState(), map.get(d).getPosition(),
						map.get(d).getState().getBlock() == Blocks.BARRIER ? Color.RED : Color.WHITE, true, event.getPartialTicks());
	        GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ZERO);
			GlStateManager.disableBlend();
		}
		GlStateManager.popMatrix();
	}
}