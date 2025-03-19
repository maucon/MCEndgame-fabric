package de.fuballer.mcendgame.components.custom_attributes

import de.fuballer.mcendgame.components.custom_attributes.data.DoubleBounds
import de.fuballer.mcendgame.components.custom_attributes.data.IntBounds
import de.fuballer.mcendgame.components.custom_attributes.data.RollableCustomAttribute
import de.fuballer.mcendgame.components.custom_attributes.types.CustomAttributeTypes
import de.fuballer.mcendgame.components.custom_attributes.types.VanillaAttributeTypes

object CustomAttributes {
    val INCREASED_PROJECTILE_DAMAGE_T1 = RollableCustomAttribute(CustomAttributeTypes.INCREASED_PROJECTILE_DAMAGE, 1, DoubleBounds(0.05, 0.1))
    val INCREASED_PROJECTILE_DAMAGE_T2 = RollableCustomAttribute(CustomAttributeTypes.INCREASED_PROJECTILE_DAMAGE, 2, DoubleBounds(0.1, 0.3))
    val INCREASED_ELEMENTAL_DAMAGE_T666 = RollableCustomAttribute(CustomAttributeTypes.INCREASED_ELEMENTAL_DAMAGE, 666, DoubleBounds(0.1, 0.3))
    val FLAT_ELEMENTAL_DAMAGE_T6 = RollableCustomAttribute(CustomAttributeTypes.FLAT_ELEMENTAL_DAMAGE, 6, IntBounds(0, 3), IntBounds(8, 12))
    val DAMAGE_AGAINST_FULL_LIFE_T1 = RollableCustomAttribute(CustomAttributeTypes.DAMAGE_AGAINST_FULL_LIFE, 1, DoubleBounds(0.5, 1.5))

    val ARMOR = RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 13, IntBounds(1, 2))
    val ARMOR_T0 = RollableCustomAttribute(VanillaAttributeTypes.ARMOR, 1, IntBounds(-10, -5))
}