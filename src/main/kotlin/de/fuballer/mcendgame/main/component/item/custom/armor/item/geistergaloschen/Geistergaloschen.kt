package de.fuballer.mcendgame.main.component.item.custom.armor.item.geistergaloschen

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot

class Geistergaloschen(
    settings: Settings,
) : UniqueAttributesItem(settings) {
    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(CustomAttributeTypes.GHOSTLY_APPEARANCE, 0),
        RollableCustomAttribute(VanillaAttributeTypes.INCREASED_MOVEMENT_SPEED, 0, DoubleBounds(0.05, 0.15)),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.FEET
}