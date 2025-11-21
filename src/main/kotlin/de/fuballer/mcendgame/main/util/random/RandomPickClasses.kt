package de.fuballer.mcendgame.main.util.random

open class RandomOption<T>(
    val weight: Int,
    val option: T,
)

open class SortableRandomOption<T>(
    weight: Int,
    /** bigger is better, should be unique in set */
    val tier: Int,
    option: T,
) : RandomOption<T>(weight, option)

class LevelLockedRandomOption<T>(
    weight: Int,
    tier: Int,
    val level: Int,
    option: T,
) : SortableRandomOption<T>(weight, tier, option) {
    fun isLocked(level: Int) = this.level > level
}
