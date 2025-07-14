package de.fuballer.mcendgame.main.component.dungeon.loot

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.util.random.RandomOption

object LootSettings {
    const val ITEMS_DROP_PROBABILITY = 0.015
    const val ITEMS_DROP_PROBABILITY_PER_LOOTING = 0.008

    const val ITEMS_DROP_PROBABILITY_DIAMOND = 0.006
    const val ITEMS_DROP_PROBABILITY_DIAMOND_PER_LOOTING = 0.003

    const val ITEMS_DROP_PROBABILITY_NETHERITE = 0.003
    const val ITEMS_DROP_PROBABILITY_NETHERITE_PER_LOOTING = 0.002

    fun calculateMagicFindDropProbabilityFactor(magicFind: Int) = 1 + magicFind / 100.0

    val ASPECTS = listOf(
        RandomOption(10, AspectItems.ASPECT_OF_HORDES),
        RandomOption(8, AspectItems.ASPECT_OF_TYRANNY),
        RandomOption(8, AspectItems.ASPECT_OF_DOMINION),
        RandomOption(10, AspectItems.ASPECT_OF_IMPATIENCE),
        RandomOption(5, AspectItems.ASPECT_OF_ZEAL),
        RandomOption(8, AspectItems.ASPECT_OF_CURIO),
        RandomOption(10, AspectItems.ASPECT_OF_GREED),
        RandomOption(5, AspectItems.ASPECT_OF_FORTUNE),
    )
}