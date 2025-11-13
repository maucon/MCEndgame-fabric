package de.fuballer.mcendgame.main.component.dungeon.generation.encounter.messaging

import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.EncounterType
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import kotlin.random.Random

data class CollectDungeonEncountersCommand(
    val random: Random,
    val aspects: Map<AspectItem, Int>,
    val encounters: MutableMap<EncounterType, Int> = mutableMapOf(),
) {
    fun add(type: EncounterType, count: Int = 1) {
        encounters[type] = (encounters[type] ?: 0) + count
    }
}