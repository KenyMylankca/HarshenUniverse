package kenymylankca.harshenuniverse.items;

import java.util.List;

import kenymylankca.harshenuniverse.HarshenItems;
import kenymylankca.harshenuniverse.HarshenUtils;
import kenymylankca.harshenuniverse.base.BaseHarshenScythe;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class RaptorScythe extends BaseHarshenScythe
{
	private static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("raptor_scythe", 3, 1010, 13.5f, 12f, 30);

	public RaptorScythe() 
	{
		super(toolMaterial);
		setUnlocalizedName("raptor_scythe");
		setRegistryName("raptor_scythe");
	}

	@Override
	protected float getSpeed() {
		return 1.44f;
	}

	@Override
	protected Item getRepairItem() {
		return HarshenItems.HARSHEN_SOUL_FRAGMENT;
	}
	
	@Override
	public double getReach() {
		return 6;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if(entity instanceof EntityLivingBase && player.getCooledAttackStrength(1) == 1)
		{
			HarshenUtils.bleedTarget((EntityLivingBase)entity, 150, 1);
			player.heal(1.9f);
		}
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00a73" + new TextComponentTranslation("raptorscythe1").getFormattedText());
		tooltip.add("\u00a73" + new TextComponentTranslation("raptorscythe2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}