package de.fuballer.mcendgame.component.dungeon.generation.data

data class Layout(
    val spawnPos: SpawnPosition,
    val rooms: List<PlaceableRoom>,
    val enemySpawnPos: List<SpawnPosition>,
    val bossSpawnPos: List<SpawnPosition>
)