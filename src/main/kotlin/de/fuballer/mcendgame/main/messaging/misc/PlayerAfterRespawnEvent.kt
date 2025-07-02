package de.fuballer.mcendgame.main.messaging.misc

import net.minecraft.server.network.ServerPlayerEntity

data class PlayerAfterRespawnEvent(
    val oldPlayer: ServerPlayerEntity,
    val newPlayer: ServerPlayerEntity,
    val alive: Boolean,
)