package de.fuballer.mcendgame.main.component.item.custom.armor.item.suede

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.armor.interfaces.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot

class SuedeBoots(
    settings: Settings,
) : UniqueAttributesItem(settings) {
    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(VanillaAttributeTypes.INCREASED_MOVEMENT_SPEED, 0, DoubleBounds(0.1, 0.2))
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.FEET
}