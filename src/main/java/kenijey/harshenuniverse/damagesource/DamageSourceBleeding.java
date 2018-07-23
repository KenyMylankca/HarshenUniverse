package kenijey.harshenuniverse.damagesource;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DamageSourceBleeding extends DamageSource
{
	public DamageSourceBleeding() {
		super("bleeding");
	}
	
	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
		return new TextComponentTranslation("death.attack.bleeding", entityLivingBaseIn.getDisplayName());
	}
}