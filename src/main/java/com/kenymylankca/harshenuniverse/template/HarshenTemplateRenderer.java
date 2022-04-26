package com.kenymylankca.harshenuniverse.template;

import java.util.HashMap;

import kenymylankca.harshenuniverse.HarshenClientUtils;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.Template;

public class HarshenTemplateRenderer extends HarshenTemplate
{
	public HarshenTemplateRenderer(ResourceLocation location) {
		super(location);
	}
	
    public void renderIntoWorld(World world, BlockPos basePos, float partialTicks)
    {
    	if(!world.isRemote)
    		return;
    	if(blocks.size() > 30000)
            return;
    	for(Template.BlockInfo block : blocks){
    		if(block.blockState.getBlock() != Blocks.AIR)
    			HarshenClientUtils.renderGhostBlock(block.blockState, basePos.add(block.pos), false, partialTicks);
    	}
    }
    
    protected static HashMap<ResourceLocation, HarshenTemplateRenderer> templateMap = new HashMap<>();
    
    public static HarshenTemplateRenderer getTemplate(ResourceLocation location)
    {
    	if(templateMap.containsKey(location))
    		return templateMap.get(location);
    	HarshenTemplateRenderer renderer = new HarshenTemplateRenderer(location);
    	templateMap.put(location, renderer);
    	return renderer;
    }
}