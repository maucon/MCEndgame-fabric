package de.fuballer.mcendgame.main.component.dungeon.generation.encounter.encounters.totem

import kotlin.random.Random

object TotemEncounterSettings {
    const val BASE_PROBABILITY = 0.1 * 10

    fun getEnemyCount(level: Int): Int {
        val random = Random.nextDouble(1 + level * 0.1)
        val additional = random.toInt() + if (Random.nextDouble() < random % 1) 1 else 0
        return 3 + additional
    }
}