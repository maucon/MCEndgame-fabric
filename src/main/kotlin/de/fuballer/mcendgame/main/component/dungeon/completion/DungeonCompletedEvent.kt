package de.fuballer.mcendgame.main.component.dungeon.completion

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld

data class DungeonCompletedEvent(
    val isClient: Boolean,
    val dungeonWorld: ServerWorld,
    val players: List<PlayerEntity>,
)