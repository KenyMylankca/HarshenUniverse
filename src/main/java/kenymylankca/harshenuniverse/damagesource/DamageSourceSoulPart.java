package kenymylankca.harshenuniverse.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DamageSourceSoulPart extends EntityDamageSource
{
	public DamageSourceSoulPart(Entity damageSourceEntityIn) {
		super("entitySoulPart", damageSourceEntityIn);
	}
	
	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
		return new TextComponentTranslation("death.attack.entitySoulPart", entityLivingBaseIn.getDisplayName());
	}
}