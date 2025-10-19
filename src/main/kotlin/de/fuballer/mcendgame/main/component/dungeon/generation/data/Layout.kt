package de.fuballer.mcendgame.main.component.dungeon.generation.data

data class Layout(
    val spawnPos: SpawnPosition,
    val rooms: List<PlaceableRoom>,
    val enemySpawnPos: List<SpawnPosition>,
    val bossSpawnPos: List<SpawnPosition>,
    val encounterPos: MutableList<EncounterLocation>,
)