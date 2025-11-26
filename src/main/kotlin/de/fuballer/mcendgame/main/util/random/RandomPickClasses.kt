package de.fuballer.mcendgame.main.util.random

/**
 * Represents a weighted option for random selection.
 *
 * @param weight The probability weight used for selection. Higher values increase chance.
 * @param option The wrapped value represented by this weighted entry.
 */
open class RandomOption<T>(
    val weight: Int,
    val option: T,
)

/**
 * A weighted option that also includes a tier value.
 *
 * Higher tiers are considered better and are used for "best-of-rolls" selection.
 * Tiers should be unique within a single option set.
 *
 * @param weight Probability weight.
 * @param tier Higher value means better option.
 * @param option Underlying option value.
 */
open class SortableRandomOption<T>(
    weight: Int,
    /** Higher tier = better option. Should be unique in the option list. */
    val tier: Int,
    option: T,
) : RandomOption<T>(weight, option)

/**
 * A weighted, tiered option that unlocks only once the required level is reached.
 *
 * @param weight Probability weight.
 * @param tier Tier used for multi-roll weighted selection.
 * @param requiredLevel The minimum level required for this option to be available.
 * @param option The underlying value.
 */
class LevelRestrictedRandomOption<T>(
    weight: Int,
    tier: Int,
    val requiredLevel: Int,
    option: T,
) : SortableRandomOption<T>(weight, tier, option) {
    /**
     * Checks whether this option is locked for the given current level.
     *
     * @param currentLevel The level being checked.
     * @return `true` if the option is still locked; `false` if available.
     */
    fun isLocked(currentLevel: Int): Boolean =
        currentLevel < requiredLevel
}