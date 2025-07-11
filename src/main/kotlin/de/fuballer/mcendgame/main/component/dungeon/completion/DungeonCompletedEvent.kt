package de.fuballer.mcendgame.main.component.dungeon.completion

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

data class DungeonCompletedEvent(
    val isClient: Boolean,
    val world: World,
    val players: List<PlayerEntity>,
    val dungeonLevel: Int,
)