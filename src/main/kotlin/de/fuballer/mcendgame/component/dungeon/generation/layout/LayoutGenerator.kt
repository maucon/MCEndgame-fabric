package de.fuballer.mcendgame.component.dungeon.generation.layout

import de.fuballer.mcendgame.component.dungeon.generation.data.Layout
import kotlin.random.Random

interface LayoutGenerator {
    fun generateDungeon(random: Random, dungeonLevel: Int, bossCount: Int): Layout
}