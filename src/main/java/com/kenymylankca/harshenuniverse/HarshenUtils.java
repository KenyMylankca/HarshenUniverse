package com.kenymylankca.harshenuniverse;

import java.util.List;

import com.kenymylankca.harshenuniverse.network.packets.MessagePlaySound;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.network.PacketDistributor;

public class HarshenUtils
{
    public static void splashBlood(BlockPos pos, Level level, int amount)
    {
    	if(level.isClientSide)
    		return;
        int rolls = 12;

        if (amount > 8)
            amount = 8;

        for(int i = 0; i < rolls; i++)
        {
            for (int a=0; a<amount; a++)
                for(BlockPos blockPos : BlockPos.betweenClosed(pos.north().west(), pos.south().east()))
                    for(int fallRange=0; fallRange<15; fallRange++)
                        if((level.getBlockState(blockPos.below(fallRange)).isAir()) && level.getBlockState(blockPos.below(fallRange+1)).isSolidRender(level, pos))
                        {
                            BlockPos bloodPos = blockPos.below(fallRange);

                            if(level.getBlockState(bloodPos).canSurvive(level, bloodPos) && amount > 0)
                            	if(level.random.nextFloat() < 0.14)
                                {
                                	level.setBlock(bloodPos, HarshenBlocks.BLOOD.get().defaultBlockState(), 3);
                                	PacketDistributor.sendToPlayersNear((ServerLevel)level, null, bloodPos.getX(), bloodPos.getY(), bloodPos.getZ(), 10,
                                			new MessagePlaySound(HarshenSounds.BLOOD_SPLASH.get(), SoundSource.AMBIENT, bloodPos, 1, 1));
                                    amount--;
                                    break;
                                }
                        }
            if(amount == 0)
                break;
        }
    }
    
    public static List<Player> getPlayersInDistance(Level level, int distance, BlockPos pos)
    {
    	AABB area = new AABB(pos).deflate(distance);
    	return level.getEntitiesOfClass(Player.class, area);
    }
}