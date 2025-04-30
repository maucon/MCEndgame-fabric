package de.fuballer.mcendgame.framework.event.server

import net.minecraft.server.MinecraftServer

data class ServerStartedEvent(
    val server: MinecraftServer
)