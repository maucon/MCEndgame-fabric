package de.fuballer.mcendgame.components.custom_attributes

import de.fuballer.mcendgame.components.custom_attributes.data.DoubleBounds
import de.fuballer.mcendgame.components.custom_attributes.data.IntBounds
import de.fuballer.mcendgame.components.custom_attributes.data.RollableCustomAttribute
import de.fuballer.mcendgame.components.custom_attributes.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.custom_attributes.types.VanillaAttributeTypes

object CustomAttributes {
    val INCREASED_ELEMENTAL_DAMAGE_T666 = RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 666, DoubleBounds(0.1, 0.3))
    val DAMAGE_AGAINST_FULL_LIFE_T1 = RollableCustomAttribute(CustomAttributeTypes.DAMAGE_AGAINST_FULL_LIFE, 1, DoubleBounds(0.5, 1.5))

    val ARMOR = RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 13, IntBounds(1, 2))
    val ARMOR_T0 = RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 1, IntBounds(-10, -5))
}