package kenymylankca.harshenuniverse.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ZombieEye extends ItemFood
{
	public ZombieEye()
	{
		super(1, 1, true);
		setUnlocalizedName("zombie_eye");
		setRegistryName("zombie_eye");
	}

	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote)
        {
            
                player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 100, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 310, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 50, 0));
        }
    }
}
