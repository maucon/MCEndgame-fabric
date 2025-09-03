package de.fuballer.mcendgame.main.component.item.custom.armor.item.wither_rose

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.util.Colors

class WitherRoseHelmet(
    settings: Settings,
) : UniqueAttributesItem(settings) {
    override fun getNameColor() = Colors.RED

    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(CustomAttributeTypes.SHOOT_WITHER_SKULL_WHEN_HIT_BY_PROJECTILE, 0, DoubleBounds(0.3, 0.6))
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.HEAD
}