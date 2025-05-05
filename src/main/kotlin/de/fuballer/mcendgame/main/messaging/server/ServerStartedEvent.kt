package de.fuballer.mcendgame.main.messaging.server

import net.minecraft.server.MinecraftServer

data class ServerStartedEvent(
    val server: MinecraftServer
)