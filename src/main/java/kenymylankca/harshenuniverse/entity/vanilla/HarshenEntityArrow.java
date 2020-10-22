package kenymylankca.harshenuniverse.entity.vanilla;

import java.util.Random;

import kenymylankca.harshenuniverse.HarshenItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
	private float arrowBreakChance = 0.4f;
	
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
	
	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn)
    {
		ItemStack stack;
		Random rand = new Random();
		
		if(rand.nextFloat() < arrowBreakChance)
			stack = new ItemStack(HarshenItems.BROKEN_ARROW);
		else
			stack = this.getArrowStack();
		
        if (!this.world.isRemote && this.inGround && this.arrowShake <= 0)
        {
            boolean flag = this.pickupStatus == EntityArrow.PickupStatus.ALLOWED || this.pickupStatus == EntityArrow.PickupStatus.CREATIVE_ONLY && entityIn.capabilities.isCreativeMode;

            if (this.pickupStatus == EntityArrow.PickupStatus.ALLOWED && !entityIn.inventory.addItemStackToInventory(stack))
            {
                flag = false;
            }

            if (flag)
            {
                entityIn.onItemPickup(this, 1);
                this.setDead();
            }
        }
    }
}