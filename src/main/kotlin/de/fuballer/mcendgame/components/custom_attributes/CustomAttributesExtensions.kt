package de.fuballer.mcendgame.components.custom_attributes

import de.fuballer.mcendgame.components.custom_attributes.data.CustomAttribute
import de.fuballer.mcendgame.util.RegistryUtil
import de.maucon.mauconframework.annotation.Injectable
import net.minecraft.component.ComponentType
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
}