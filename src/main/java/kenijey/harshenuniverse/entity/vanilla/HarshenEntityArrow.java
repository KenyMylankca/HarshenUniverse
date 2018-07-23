package kenijey.harshenuniverse.entity.vanilla;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;

public class HarshenEntityArrow extends EntityTippedArrow
{
	SoundEvent hitSound;
	private float chanceThatArrowBreaks = 0.3f;
	
	public HarshenEntityArrow(EntityArrow arrow, SoundEvent hitSoundIn) {
		super(arrow.world, (EntityLivingBase) arrow.shootingEntity);
		setPotionEffect(new ItemStack(Items.ARROW));
		hitSound = hitSoundIn;
	}
	
	@Override
	public void playSound(SoundEvent soundIn, float volume, float pitch) {
		if(soundIn == SoundEvents.ENTITY_ARROW_HIT && playCustomSound)	
			soundIn = hitSound;
		playCustomSound = false;
		super.playSound(soundIn, volume, pitch);
	}
	
	private boolean playCustomSound;
	
	@Override
	protected void onHit(RayTraceResult ray) {
		if(ray.entityHit != null || world.getTileEntity(ray.getBlockPos()) != null || world.getBlockState(ray.getBlockPos()) != null)
			playCustomSound = true;
		super.onHit(ray);
	}
}