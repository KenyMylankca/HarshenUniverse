package com.kenymylankca.harshenuniverse.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AkzeniaSoup extends ItemFood
{
	public AkzeniaSoup()
	{
		super(3, 7f, true);
		setUnlocalizedName("akzenia_soup");
		setRegistryName("akzenia_soup");
		this.setAlwaysEdible();
	}

	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
	{
		if (!worldIn.isRemote)
		{           
			player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 444, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 4970, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 1, 0));
		}
	}
		
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(Items.BOWL);
    }
}