package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld

data class PlayerAfterDimensionChangeEvent(
    val player: PlayerEntity,
    val oldWorld: ServerWorld,
    val newWorld: ServerWorld,
)