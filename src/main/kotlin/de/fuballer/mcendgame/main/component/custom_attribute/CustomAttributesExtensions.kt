package de.fuballer.mcendgame.main.component.custom_attribute

import de.fuballer.mcendgame.main.MCEndgame
import de.fuballer.mcendgame.main.component.custom_attribute.data.*
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.util.extension.SlotExtension.isOrIsChildOf
import de.fuballer.mcendgame.main.util.minecraft.IdentifierUtil
import de.fuballer.mcendgame.main.util.minecraft.RegistryUtil
import de.maucon.mauconframework.di.annotation.Injectable
import net.minecraft.component.ComponentType
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.component.type.AttributeModifiersComponent
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ItemStack

@Injectable
object CustomAttributesExtensions {
    private val COMPONENT_TYPE: ComponentType<List<CustomAttribute>> =
        RegistryUtil.registerDataComponentType(
            ComponentType.builder<List<CustomAttribute>>()
                .codec(CustomAttribute.CODEC.listOf())
                .build(),
            "custom_attributes"
        )

    //TODO #86 change how attributes slots are handled
    fun ItemStack.setCustomAttributes(
        customAttributes: List<CustomAttribute>,
        slot: AttributeModifierSlot,
    ) {
        set(COMPONENT_TYPE, customAttributes)

        val attributeModifierComponent = getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT)

        val attributeComponentBuilder = AttributeModifiersComponent.builder()
        addNonModAttributes(attributeModifierComponent, attributeComponentBuilder)
        addVanillaTypeAttributes(customAttributes, attributeComponentBuilder, slot)

        set(DataComponentTypes.ATTRIBUTE_MODIFIERS, attributeComponentBuilder.build())
    }

    /**
     * Automatically uses the slot of given attributes or defaults to [AttributeModifierSlot.ANY] if empty
     */
    fun ItemStack.updateCustomAttributes(
        customAttributes: List<CustomAttribute>,
    ) {
        val slot = if (customAttributes.isEmpty()) AttributeModifierSlot.ANY else customAttributes[0].slot
        return setCustomAttributes(customAttributes, slot)
    }

    fun ItemStack.getCustomAttributes(): List<CustomAttribute> {
        return get(COMPONENT_TYPE)
            ?: return emptyList()
    }

    fun LivingEntity.getAllCustomAttributes(): Map<CustomAttributeType, List<CustomAttribute>> {
        // TODO entity based attributes
        val customAttributes = mutableListOf<CustomAttribute>()

        val feetItem = this.getEquippedStack(EquipmentSlot.FEET)
        val feetAttributes = feetItem.getCustomAttributes().filter { AttributeModifierSlot.FEET.isOrIsChildOf(it.slot) }
        customAttributes.addAll(feetAttributes)
        val legsItem = this.getEquippedStack(EquipmentSlot.LEGS)
        val legsAttributes = legsItem.getCustomAttributes().filter { AttributeModifierSlot.LEGS.isOrIsChildOf(it.slot) }
        customAttributes.addAll(legsAttributes)
        val chestItem = this.getEquippedStack(EquipmentSlot.CHEST)
        val chestAttributes = chestItem.getCustomAttributes().filter { AttributeModifierSlot.CHEST.isOrIsChildOf(it.slot) }
        customAttributes.addAll(chestAttributes)
        val headItem = this.getEquippedStack(EquipmentSlot.HEAD)
        val headAttributes = headItem.getCustomAttributes().filter { AttributeModifierSlot.HEAD.isOrIsChildOf(it.slot) }
        customAttributes.addAll(headAttributes)

        val mainHandItem = this.getEquippedStack(EquipmentSlot.MAINHAND)
        val mainHandAttributes = mainHandItem.getCustomAttributes().filter { AttributeModifierSlot.MAINHAND.isOrIsChildOf(it.slot) }
        customAttributes.addAll(mainHandAttributes)
        val offHandItem = this.getEquippedStack(EquipmentSlot.OFFHAND)
        val offHandAttributes = offHandItem.getCustomAttributes().filter { AttributeModifierSlot.OFFHAND.isOrIsChildOf(it.slot) }
        customAttributes.addAll(offHandAttributes)

        return customAttributes
            .filter { it.type is CustomAttributeType }
            .groupBy { it.type as CustomAttributeType }
    }

    fun LivingEntity.isGhostly() = getAllCustomAttributes().contains(CustomAttributeTypes.GHOSTLY_APPEARANCE)
    fun LivingEntity.isEntityPhasing() = getAllCustomAttributes().contains(CustomAttributeTypes.ENTITY_PHASING)
    fun LivingEntity.isBlockPhasing() = getAllCustomAttributes().contains(CustomAttributeTypes.BLOCK_PHASING)

    fun AttributeRoll<*>.asDoubleRoll() = this as DoubleRoll
    fun AttributeRoll<*>.asStringRoll() = this as StringRoll
    fun AttributeRoll<*>.asIntRoll() = this as IntRoll
    fun AttributeBounds<*>.asDoubleBounds() = this as DoubleBounds
    fun AttributeBounds<*>.asStringBounds() = this as StringBounds
    fun AttributeBounds<*>.asIntBounds() = this as IntBounds

    private fun addNonModAttributes(attributeModifierComponent: AttributeModifiersComponent, newA: AttributeModifiersComponent.Builder) {
        for (modifier in attributeModifierComponent.modifiers) {
            if (modifier.modifier.id.namespace == MCEndgame.MOD_ID) continue

            newA.add(modifier.attribute, modifier.modifier, modifier.slot)
        }
    }

    private fun addVanillaTypeAttributes(
        customAttributes: List<CustomAttribute>,
        newA: AttributeModifiersComponent.Builder,
        slot: AttributeModifierSlot
    ) {
        customAttributes
            .filter { it.type is VanillaAttributeType }
            .forEach {
                val vanillaAttributeType = it.type as VanillaAttributeType
                val attribute = vanillaAttributeType.attribute
                val modifier = EntityAttributeModifier(IdentifierUtil.defaultRandom(), it.rolls[0].asDoubleRoll().getValue(), vanillaAttributeType.scaleType)
                newA.add(attribute, modifier, slot)
            }
    }
}