package de.fuballer.mcendgame.main.component.dungeon.enemy.equipment.attributes

import de.fuballer.mcendgame.main.util.random.LevelLockedRandomOption
import de.fuballer.mcendgame.main.util.random.RandomUtil
import kotlin.random.Random

object AttributeSettings {
    fun getAttributeCount(level: Int, random: Random): Int {
        val rolls = 1 + level / 2 + if (random.nextDouble() < (level / 2.0) % 1) 1 else 0
        return RandomUtil.pickLevelLocked(ATTRIBUTE_COUNT, rolls, level, random)
    }

    val ATTRIBUTE_COUNT = listOf(
        LevelLockedRandomOption(weight = 10000, tier = 0, level = 0, option = 0),
        LevelLockedRandomOption(weight = 10000, tier = 1, level = 0, option = 1),
        LevelLockedRandomOption(weight = 2400, tier = 2, level = 3, option = 2),
        LevelLockedRandomOption(weight = 350, tier = 3, level = 5, option = 3),
        LevelLockedRandomOption(weight = 50, tier = 4, level = 10, option = 4),
        LevelLockedRandomOption(weight = 1, tier = 5, level = 15, option = 5),
    )

    fun getAttributeTierRolls(level: Int, random: Random) = 1 + level / 5 + if (random.nextDouble() < (level / 5.0) % 1) 1 else 0
}