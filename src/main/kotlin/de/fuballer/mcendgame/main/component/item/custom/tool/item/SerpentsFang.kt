package de.fuballer.mcendgame.main.component.item.custom.tool.item

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.util.Colors

class SerpentsFang(
    settings: Settings,
) : UniqueAttributesItem(settings) {
    override fun getNameColor() = Colors.GREEN

    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(VanillaAttributeTypes.INCREASED_ATTACK_SPEED, 0, DoubleBounds(0.1, 0.2)),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.HAND
}