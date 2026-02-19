package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.server.network.ServerPlayerEntity

data class PlayerDisconnectEvent(
    val player: ServerPlayerEntity,
)