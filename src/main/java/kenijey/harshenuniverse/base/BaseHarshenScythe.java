package kenijey.harshenuniverse.base;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public abstract class BaseHarshenScythe extends ItemSword
{
	public BaseHarshenScythe(ToolMaterial material) {
		super(material);
	}
	
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		final Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(slot, stack);

		if (slot == EntityEquipmentSlot.MAINHAND)
			replaceModifier(modifiers, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, getSpeed());

		return modifiers;
	}
	
	private void replaceModifier(Multimap<String, AttributeModifier> modifierMultimap, IAttribute attribute, UUID id, double multiplier) {

		final Collection<AttributeModifier> modifiers = modifierMultimap.get(attribute.getName());
		final Optional<AttributeModifier> modifierOptional = modifiers.stream().filter(attributeModifier -> attributeModifier.getID().equals(id)).findFirst();

		if (modifierOptional.isPresent()) {
			final AttributeModifier modifier = modifierOptional.get();
			modifiers.remove(modifier);
			modifiers.add(new AttributeModifier(modifier.getID(), modifier.getName(), modifier.getAmount() * multiplier, modifier.getOperation()));
		}
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == getRepairItem();
	}
	
	protected abstract float getSpeed();
	
	@Nullable
	protected abstract Item getRepairItem();
	
	public abstract double getReach();
}