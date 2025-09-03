package de.fuballer.mcendgame.main.component.item.custom.armor.item.geistergaloschen

import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot

class Geistergaloschen(
    settings: Settings,
) : UniqueAttributesItem(settings) {
    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(CustomAttributeTypes.GHOSTLY_APPEARANCE, 0),
        RollableCustomAttribute(CustomAttributeTypes.ENTITY_PHASING, 0),
        RollableCustomAttribute(CustomAttributeTypes.BLOCK_PHASING, 0),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.FEET
}