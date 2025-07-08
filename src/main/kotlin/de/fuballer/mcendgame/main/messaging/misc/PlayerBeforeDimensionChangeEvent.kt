package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.TeleportTarget

data class PlayerBeforeDimensionChangeEvent(
    val player: PlayerEntity,
    val world: ServerWorld,
    val target: TeleportTarget,
)