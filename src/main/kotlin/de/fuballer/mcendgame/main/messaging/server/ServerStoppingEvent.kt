package de.fuballer.mcendgame.main.messaging.server

import net.minecraft.server.MinecraftServer

data class ServerStoppingEvent(
    val server: MinecraftServer
)