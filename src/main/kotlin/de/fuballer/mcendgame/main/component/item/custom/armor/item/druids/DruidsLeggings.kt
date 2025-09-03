package de.fuballer.mcendgame.main.component.item.custom.armor.item.druids

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.util.Colors

class DruidsLeggings(
    settings: Settings,
) : UniqueAttributesItem(settings) {
    override fun getNameColor() = Colors.GREEN

    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 0, DoubleBounds(1.0, 3.0)),
        RollableCustomAttribute(CustomAttributeTypes.INCREASED_HEALING_RECEIVED, 0, DoubleBounds(0.5, 0.75)),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.LEGS
}