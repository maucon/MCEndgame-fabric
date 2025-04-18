package de.fuballer.mcendgame.components.dungeon.boss

import kotlin.math.pow
import kotlin.random.Random

object BossGenerationSettings {
    fun getRandomScale(random: Random) = 1.0 + 0.2 * random.nextDouble().pow(3) * if (random.nextBoolean()) 1 else -1
}