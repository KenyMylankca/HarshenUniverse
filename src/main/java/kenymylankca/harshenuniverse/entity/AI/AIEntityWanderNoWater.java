package kenymylankca.harshenuniverse.entity.AI;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class AIEntityWanderNoWater extends EntityAIWanderAvoidWater
{
	protected final float probability;

    public AIEntityWanderNoWater(EntityCreature creature, double speed)
    {
        this(creature, speed, 0.001F);
    }

    public AIEntityWanderNoWater(EntityCreature creature, double speed, float probalitity)
    {
        super(creature, speed);
        this.probability = probalitity;
    }
    
    @Override
    public boolean shouldExecute() {
    	if (!this.mustUpdate)
        {
            if (this.entity.getIdleTime() >= 100)
            {
                return false;
            }
            if (this.entity.getRNG().nextInt(10) != 0)
            {
                return false;
            }
        }

        Vec3d vec3d = this.getPosition();
        if (vec3d == null)
        {
            return false;
        }
        else
        {
            this.x = vec3d.x;
            this.y = vec3d.y;
            this.z = vec3d.z;
            this.mustUpdate = false;
            return true;
        }
    }

    @Nullable
    protected Vec3d getPosition()
    {
        if (this.entity.isInWater())
        {
            Vec3d vec3d = RandomPositionGenerator.getLandPos(this.entity, 15, 7);
            return vec3d == null ? super.getPosition() : vec3d;
        }
        else
        {
            return this.entity.getRNG().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.entity, 10, 7) : super.getPosition();
        }
    }
}