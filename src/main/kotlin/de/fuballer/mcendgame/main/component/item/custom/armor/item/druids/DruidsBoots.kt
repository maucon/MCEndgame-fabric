package de.fuballer.mcendgame.main.component.item.custom.armor.item.druids

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.data.StringBounds
import de.fuballer.mcendgame.main.component.custom_attribute.effects.wolf_companion.WolfCompanionType
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot
import net.minecraft.util.Colors

class DruidsBoots(
    settings: Settings,
) : UniqueAttributesItem(settings) {
    override fun getNameColor() = Colors.GREEN

    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(VanillaAttributeTypes.MAX_HEALTH, 0, DoubleBounds(1.0, 3.0)),
        RollableCustomAttribute(VanillaAttributeTypes.INCREASED_MOVEMENT_SPEED, 0, DoubleBounds(0.1, 0.2)),
        RollableCustomAttribute(CustomAttributeTypes.WOLF_COMPANION, 0, StringBounds(WolfCompanionType.getNames())),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.FEET
}