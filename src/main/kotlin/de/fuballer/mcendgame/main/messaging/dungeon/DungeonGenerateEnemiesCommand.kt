package de.fuballer.mcendgame.main.messaging.dungeon

import de.fuballer.mcendgame.main.component.dungeon.enemy.equipment.EquipmentGenerationSettings
import de.fuballer.mcendgame.main.component.dungeon.generation.data.SpawnPosition
import de.fuballer.mcendgame.main.component.dungeon.world.DungeonWorld
import de.fuballer.mcendgame.main.component.item.custom.aspect.AspectItem

data class DungeonGenerateEnemiesCommand(
    val dungeonWorld: DungeonWorld,
    val aspects: HashMap<AspectItem, Int>,
    val spawnPositions: MutableList<SpawnPosition>,
    val eliteSpawnPositions: MutableList<SpawnPosition>,
    val lootGoblinSpawnPositions: MutableList<SpawnPosition>,
    var uniqueEquipmentChance: Double,
) {
    companion object {
        fun of(
            dungeonWorld: DungeonWorld,
            spawnPositions: MutableList<SpawnPosition>,
        ) = DungeonGenerateEnemiesCommand(
            dungeonWorld,
            dungeonWorld.dungeon.`mcendgame$getAspects`(),
            spawnPositions,
            mutableListOf(),
            mutableListOf(),
            EquipmentGenerationSettings.UNIQUE_EQUIPMENT_PROBABILITY
        )
    }
}