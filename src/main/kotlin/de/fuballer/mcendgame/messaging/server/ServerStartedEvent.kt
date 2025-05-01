package de.fuballer.mcendgame.messaging.server

import net.minecraft.server.MinecraftServer

data class ServerStartedEvent(
    val server: MinecraftServer
)