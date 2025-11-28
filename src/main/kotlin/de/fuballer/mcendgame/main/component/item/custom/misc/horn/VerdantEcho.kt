package de.fuballer.mcendgame.main.component.item.custom.misc.horn

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesHornItem
import net.minecraft.component.type.AttributeModifierSlot

class VerdantEcho(
    settings: Settings,
) : UniqueAttributesHornItem(settings) {
    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, 0, DoubleBounds(1.0, 3.0)),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.HAND
}