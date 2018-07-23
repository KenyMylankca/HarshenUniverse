package kenijey.harshenuniverse.items;

import java.util.List;

import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.base.BaseHarshenScythe;
import kenijey.harshenuniverse.potions.HarshenPotions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class RaptorScythe extends BaseHarshenScythe
{
	private static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("iron_scythe", 3, 1010, 13.5f, 12f, 30);

	public RaptorScythe() 
	{
		super(toolMaterial);
		setUnlocalizedName("raptor_scythe");
		setRegistryName("raptor_scythe");
	}

	@Override
	protected float getSpeed() {
		return 1.4f;
	}

	@Override
	protected Item getRepairItem() {
		return HarshenItems.SOUL_INFUSED_INGOT;
	}

	@Override
	public double getReach() {
		return 6;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
			if(!HarshenUtils.hasJaguarArmorSet((EntityLivingBase) entity))
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(HarshenPotions.potionBleeding, 150, 1));
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation(" ").getFormattedText());
		tooltip.add("\u00a74" + new TextComponentTranslation("sword" + "1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}