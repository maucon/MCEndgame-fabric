package de.fuballer.mcendgame.main.component.item.custom.armor.item.suede

import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot

class SuedeHelmet(
    settings: Settings,
) : UniqueAttributesItem(settings) {
    override fun getCustomAttributes() = listOf<RollableCustomAttribute>(
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.HEAD
}