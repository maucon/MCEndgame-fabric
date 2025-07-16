package de.fuballer.mcendgame.main.component.dungeon.completion

import de.fuballer.mcendgame.main.component.dungeon.player.DungeonPlayerEntity
import de.fuballer.mcendgame.main.component.dungeon.world.DungeonWorld

data class DungeonCompletedEvent(
    val isClient: Boolean,
    val dungeonWorld: DungeonWorld,
    val dungeonPlayers: List<DungeonPlayerEntity>,
)