package de.fuballer.mcendgame.components.custom_attributes

import de.fuballer.mcendgame.components.custom_attributes.data.CustomAttribute
import de.fuballer.mcendgame.components.custom_attributes.data.CustomAttributeType
import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.component.ComponentType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
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

    fun ItemStack.setCustomAttributes(customAttributes: List<CustomAttribute>) {
        set(COMPONENT_TYPE, customAttributes)
    }

    fun ItemStack.addCustomAttributes(customAttribute: CustomAttribute) {
        val attributes = getCustomAttributes().toMutableList()
        attributes.add(customAttribute)
        setCustomAttributes(attributes)
    }

    fun ItemStack.getCustomAttributes(): List<CustomAttribute> {
        return get(COMPONENT_TYPE)
            ?: return emptyList()
    }

    fun LivingEntity.getCustomAttributes(): Map<CustomAttributeType, List<CustomAttribute>> {
        // TODO entity based attributes
        val customAttributes = mutableListOf<CustomAttribute>()

        val feetItem = this.getEquippedStack(EquipmentSlot.FEET)
        customAttributes.addAll(feetItem.getCustomAttributes())
        val legsItem = this.getEquippedStack(EquipmentSlot.LEGS)
        customAttributes.addAll(legsItem.getCustomAttributes())
        val bodyItem = this.getEquippedStack(EquipmentSlot.BODY)
        customAttributes.addAll(bodyItem.getCustomAttributes())
        val headItem = this.getEquippedStack(EquipmentSlot.HEAD)
        customAttributes.addAll(headItem.getCustomAttributes())

        val mainHandItem = this.getEquippedStack(EquipmentSlot.MAINHAND)
        customAttributes.addAll(mainHandItem.getCustomAttributes())
        val offHandItem = this.getEquippedStack(EquipmentSlot.OFFHAND)
        customAttributes.addAll(offHandItem.getCustomAttributes())

        return customAttributes
            .filter { it.type is CustomAttributeType }
            .groupBy { it.type as CustomAttributeType }
    }
}