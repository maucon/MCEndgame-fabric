package de.fuballer.mcendgame.main.component.dungeon.completion

import de.fuballer.mcendgame.main.component.dungeon.world.DungeonWorld
import net.minecraft.entity.player.PlayerEntity

data class DungeonCompletedEvent(
    val isClient: Boolean,
    val dungeonWorld: DungeonWorld,
    val players: List<PlayerEntity>,
)