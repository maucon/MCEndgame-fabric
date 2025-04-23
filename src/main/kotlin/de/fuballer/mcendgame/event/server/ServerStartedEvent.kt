package de.fuballer.mcendgame.event.server

import net.minecraft.server.MinecraftServer

data class ServerStartedEvent(
    val server: MinecraftServer
)