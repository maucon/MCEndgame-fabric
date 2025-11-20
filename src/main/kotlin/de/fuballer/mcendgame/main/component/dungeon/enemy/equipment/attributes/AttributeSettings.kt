package de.fuballer.mcendgame.main.component.dungeon.enemy.equipment.attributes

import de.fuballer.mcendgame.main.util.random.RandomUtil
import de.fuballer.mcendgame.main.util.random.SortableRandomOption
import kotlin.random.Random

object AttributeSettings {
    fun getAttributeCount(level: Int, random: Random): Int {
        val filteredOptions = ATTRIBUTE_COUNT.filter { it.barrierLevel <= level }.map { it.option }.toList()
        val rolls = 1 + level / 2 + if (Random.nextDouble() < (level / 2.0) % 1) 1 else 0
        return RandomUtil.pick(filteredOptions, rolls, random).option
    }

    val ATTRIBUTE_COUNT = listOf(
        AttributeCountWithTierBarrier(SortableRandomOption(10000, 0, 0), 0),
        AttributeCountWithTierBarrier(SortableRandomOption(10000, 1, 1), 0),
        AttributeCountWithTierBarrier(SortableRandomOption(2400, 2, 2), 3),
        AttributeCountWithTierBarrier(SortableRandomOption(350, 3, 3), 5),
        AttributeCountWithTierBarrier(SortableRandomOption(50, 4, 4), 10),
        AttributeCountWithTierBarrier(SortableRandomOption(1, 5, 5), 15),
    )

    data class AttributeCountWithTierBarrier(
        val option: SortableRandomOption<Int>,
        val barrierLevel: Int,
    )
}