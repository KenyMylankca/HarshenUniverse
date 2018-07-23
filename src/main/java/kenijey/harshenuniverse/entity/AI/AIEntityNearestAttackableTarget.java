package kenijey.harshenuniverse.entity.AI;

import com.google.common.base.Predicate;

import kenijey.harshenuniverse.potions.HarshenPotions;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

public class AIEntityNearestAttackableTarget extends EntityAINearestAttackableTarget
{
	public AIEntityNearestAttackableTarget(EntityCreature creature, Class classTarget, int chance, boolean checkSight,
			boolean onlyNearby, Predicate targetSelector) {
		super(creature, classTarget, chance, checkSight, onlyNearby, targetSelector);
	}
	
	@Override
	public boolean shouldExecute() {
		return super.shouldExecute() && this.targetEntity.isPotionActive(HarshenPotions.potionHarshed);
	}
}