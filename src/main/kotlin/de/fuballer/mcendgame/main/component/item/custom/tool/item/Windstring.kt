package de.fuballer.mcendgame.main.component.item.custom.tool.item

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesBowItem
import net.minecraft.component.type.AttributeModifierSlot

class Windstring(
    settings: Settings,
) : UniqueAttributesBowItem(settings) {
    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(CustomAttributeTypes.INCREASED_DAMAGE, 0, DoubleBounds(0.1, 0.2)),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.HAND
}