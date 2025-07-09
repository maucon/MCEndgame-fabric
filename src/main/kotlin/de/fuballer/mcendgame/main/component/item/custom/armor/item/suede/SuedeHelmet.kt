package de.fuballer.mcendgame.main.component.item.custom.armor.item.suede

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.IntBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot

class SuedeHelmet(
    settings: Settings,
) : UniqueAttributesItem(settings) {
    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(CustomAttributeTypes.MAGIC_FIND, 0, IntBounds(3, 5)),
        RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 0, DoubleBounds(-4.0, -2.0)),
        RollableCustomAttribute(CustomAttributeTypes.DODGE_IF_NOT_DODGED_IN_LAST_SECONDS, 0, DoubleBounds(0.3, 0.4), IntBounds(5, 8)),
        RollableCustomAttribute(CustomAttributeTypes.PROJECTILE_DODGE, 0, DoubleBounds(0.15, 0.25)),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.HEAD
}