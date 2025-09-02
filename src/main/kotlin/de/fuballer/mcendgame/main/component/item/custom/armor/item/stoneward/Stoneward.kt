package de.fuballer.mcendgame.main.component.item.custom.armor.item.stoneward

import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.RollableCustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.types.CustomAttributeTypes
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes
import de.fuballer.mcendgame.main.component.item.custom.UniqueAttributesItem
import net.minecraft.component.type.AttributeModifierSlot

class Stoneward(
    settings: Settings,
) : UniqueAttributesItem(settings) {
    override fun getCustomAttributes() = listOf(
        RollableCustomAttribute(CustomAttributeTypes.WARD, 0, DoubleBounds(2.0, 3.0)),
        RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 0, DoubleBounds(2.0, 3.0)),
        RollableCustomAttribute(VanillaAttributeTypes.ARMOR_TOUGHNESS, 0, DoubleBounds(3.0, 5.0)),
        RollableCustomAttribute(VanillaAttributeTypes.MORE_MOVEMENT_SPEED, 0, DoubleBounds(-0.2, -0.2)),
    )

    override fun getAttributeModifierSlot() = AttributeModifierSlot.LEGS
}