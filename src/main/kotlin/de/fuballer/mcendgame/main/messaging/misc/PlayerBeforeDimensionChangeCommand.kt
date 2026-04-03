package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.TeleportTarget

data class PlayerBeforeDimensionChangeCommand(
    val player: PlayerEntity,
    val world: ServerWorld,
    val target: TeleportTarget,
)