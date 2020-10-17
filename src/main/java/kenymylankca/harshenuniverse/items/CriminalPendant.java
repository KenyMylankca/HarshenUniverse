package kenymylankca.harshenuniverse.items;

import java.util.UUID;

import kenymylankca.harshenuniverse.api.EnumAccessoryInventorySlots;
import kenymylankca.harshenuniverse.api.IHarshenAccessoryProvider;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;

public class CriminalPendant extends Item implements IHarshenAccessoryProvider
{
	public CriminalPendant()
	{
		setUnlocalizedName("criminal_pendant");
		setRegistryName("criminal_pendant");
	}
	
	@Override
	public EnumAccessoryInventorySlots getSlot() {
		return EnumAccessoryInventorySlots.NECK;
	}
	
	@Override
	public int toolTipLines() {
		return 1;
	}
	
	@Override
	public void onAdd(EntityPlayer player, int slot)
	{
		IAttributeInstance attributeHealth = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		AttributeModifier modifierHealth = new AttributeModifier(UUID.fromString("72eb8438-8f2b-11e7-bb31-be2e44b06b34"), "criminalPendantHealth6", 8, 0).setSaved(true);
		if(!attributeHealth.hasModifier(modifierHealth))	
			attributeHealth.applyModifier(modifierHealth);
		IHarshenAccessoryProvider.super.onAdd(player, slot);
	}
	
	@Override
	public void onRemove(EntityPlayer player, int slot)
	{
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("72eb8438-8f2b-11e7-bb31-be2e44b06b34"));
		if(player.getMaxHealth() < player.getHealth())
			player.setHealth(player.getMaxHealth());
		IHarshenAccessoryProvider.super.onRemove(player, slot);
	}
	
	@Override
	public void onTick(EntityPlayer player, int slot)
	{
		player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 19, 0, false, false));
		IHarshenAccessoryProvider.super.onTick(player, slot);
	}
}