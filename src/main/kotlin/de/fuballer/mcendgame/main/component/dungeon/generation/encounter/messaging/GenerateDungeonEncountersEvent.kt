package de.fuballer.mcendgame.main.component.dungeon.generation.encounter.messaging

import de.fuballer.mcendgame.main.component.dungeon.generation.encounter.EncounterType
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3i

data class GenerateDungeonEncountersEvent(
    val world: ServerWorld,
    val availablePositions: MutableList<Vec3i>,
    val aspects: Map<AspectItem, Int>,
    val encounters: Map<EncounterType, Int> = mapOf(),
) {
    fun takePositions(count: Int): List<Vec3i> {
        if (count > availablePositions.size) return listOf()

        val chosen = availablePositions.shuffled().take(count)
        availablePositions.removeAll(chosen)
        return chosen
    }
}
