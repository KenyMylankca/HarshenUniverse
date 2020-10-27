package kenymylankca.harshenuniverse.damagesource;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DamageSourceJacobDraining extends DamageSource
{
	public DamageSourceJacobDraining() {
		super("drained_by_jacob");
	}
	
	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
		return new TextComponentTranslation("death.drained_by_jacob", entityLivingBaseIn.getDisplayName());
	}
}