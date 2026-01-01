package de.fuballer.mcendgame.main.component.dungeon.enemy

import de.fuballer.mcendgame.main.component.custom_attribute.data.CustomAttribute
import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleBounds
import de.fuballer.mcendgame.main.component.custom_attribute.data.DoubleRoll
import de.fuballer.mcendgame.main.component.custom_attribute.types.VanillaAttributeTypes

object EnemyLevelScalingSettings {
    fun getEnemyLevelAttributes(level: Int) = listOf(
        CustomAttribute(VanillaAttributeTypes.ATTACK_DAMAGE, roll = DoubleRoll(DoubleBounds(2.5 * level))),
    )

    fun getBossLevelAttributes(level: Int) = listOf(
        CustomAttribute(VanillaAttributeTypes.MORE_MAX_HEALTH, roll = DoubleRoll(DoubleBounds(0.1 * level))),
    )
}