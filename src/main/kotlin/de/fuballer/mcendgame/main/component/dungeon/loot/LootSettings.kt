package de.fuballer.mcendgame.main.component.dungeon.loot

import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItems
import de.fuballer.mcendgame.main.component.item.custom.crystal.CrystalItems
import de.fuballer.mcendgame.main.util.random.LevelLockedRandomOption
import de.fuballer.mcendgame.main.util.random.RandomOption
import kotlin.random.Random

object LootSettings {
    const val ITEMS_DROP_PROBABILITY = 0.039

    const val ITEMS_DROP_PROBABILITY_DIAMOND = 0.015

    const val ITEMS_DROP_PROBABILITY_NETHERITE = 0.009

    fun calculateMagicFindDropProbabilityFactor(magicFind: Int) = 1 + magicFind / 100.0

    val ASPECTS = listOf(
        RandomOption(100, AspectItems.ASPECT_OF_HORDES),
        RandomOption(80, AspectItems.ASPECT_OF_TYRANNY),
        RandomOption(80, AspectItems.ASPECT_OF_DOMINION),
        RandomOption(100, AspectItems.ASPECT_OF_IMPATIENCE),
        RandomOption(50, AspectItems.ASPECT_OF_ZEAL),
        RandomOption(80, AspectItems.ASPECT_OF_CURIO),
        RandomOption(100, AspectItems.ASPECT_OF_GREED),
        RandomOption(50, AspectItems.ASPECT_OF_FORTUNE),
        //RandomOption(5, AspectItems.ASPECT_OF_GHOSTS), doesn't drop default [AspectOfGhostsService]
        RandomOption(75, AspectItems.ASPECT_OF_FORTITUDE),
        RandomOption(75, AspectItems.ASPECT_OF_SAVAGERY),
        RandomOption(15, AspectItems.ASPECT_OF_EMINENCE),
        RandomOption(50, AspectItems.ASPECT_OF_ANCESTORS),
    )

    fun getBossBaseCrystalCount(dungeonLevel: Int) = Random.nextDouble(dungeonLevel.toDouble() / 2)

    val CRYSTALS = listOf(
        LevelLockedRandomOption(10, tier = 0, level = 0, CrystalItems.PERMUTATION_CRYSTAL),
        LevelLockedRandomOption(10, tier = 0, level = 2, CrystalItems.CALIBRATION_CRYSTAL),
        LevelLockedRandomOption(5, tier = 0, level = 4, CrystalItems.REFORGE_CRYSTAL),
        LevelLockedRandomOption(5, tier = 0, level = 6, CrystalItems.CORRUPTION_CRYSTAL),
        LevelLockedRandomOption(5, tier = 0, level = 8, CrystalItems.SACRIFICIAL_CRYSTAL),
    )
}