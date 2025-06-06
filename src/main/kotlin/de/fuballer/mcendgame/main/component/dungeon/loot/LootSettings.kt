package de.fuballer.mcendgame.main.component.dungeon.loot

object LootSettings {
    const val ITEMS_DROP_PROBABILITY = 0.015
    const val ITEMS_DROP_PROBABILITY_PER_LOOTING = 0.008

    const val ITEMS_DROP_PROBABILITY_DIAMOND = 0.006
    const val ITEMS_DROP_PROBABILITY_DIAMOND_PER_LOOTING = 0.003

    const val ITEMS_DROP_PROBABILITY_NETHERITE = 0.003
    const val ITEMS_DROP_PROBABILITY_NETHERITE_PER_LOOTING = 0.002

    fun calculateMagicFindDropProbabilityFactor(magicFind: Int) = 1 + magicFind / 100.0
}