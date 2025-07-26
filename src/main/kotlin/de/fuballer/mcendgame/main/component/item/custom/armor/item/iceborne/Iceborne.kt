package de.fuballer.mcendgame.main.component.item.custom.armor.item.iceborne

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot

class Iceborne(
    settings: Settings,
) : UniqueAttributesItem(settings) {
    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(CustomAttributeTypes.ELEMENTAL_DAMAGE, 0, DoubleBounds(0.05, 0.15))
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.HEAD
}