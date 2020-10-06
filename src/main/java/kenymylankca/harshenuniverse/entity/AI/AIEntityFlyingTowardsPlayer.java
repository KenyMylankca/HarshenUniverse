package kenymylankca.harshenuniverse.entity.AI;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;

public class AIEntityFlyingTowardsPlayer extends EntityAIBase
{
	private EntityLiving entity;
    public AIEntityFlyingTowardsPlayer(EntityLiving entity)
    {
    	this.entity = entity;
        this.setMutexBits(1);
    }

    public boolean shouldExecute()
    {
        return !entity.getMoveHelper().isUpdating() && entity.getAttackTarget() != null;
    }

    public boolean shouldContinueExecuting()
    {
        return false;
    }

    public void updateTask()
    {
        BlockPos blockpos = new BlockPos(entity.getAttackTarget());
        for (int i = 0; i < 3; ++i)
        {
        	boolean flag = false;
        	BlockPos blockpos1 = blockpos.add(entity.getRNG().nextInt(15) - 7, entity.getRNG().nextInt(11) - 5, entity.getRNG().nextInt(15) - 7);


            if (entity.world.isAirBlock(blockpos1))
            {
            	entity.getMoveHelper().setMoveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 1D);
                if (entity.getAttackTarget() == null)
                {
                	entity.getLookHelper().setLookPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                }

                break;
            }
        }
    }
}