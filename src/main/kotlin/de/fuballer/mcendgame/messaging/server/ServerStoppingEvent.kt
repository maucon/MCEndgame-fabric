package de.fuballer.mcendgame.messaging.server

import net.minecraft.server.MinecraftServer

data class ServerStoppingEvent(
    val server: MinecraftServer
)