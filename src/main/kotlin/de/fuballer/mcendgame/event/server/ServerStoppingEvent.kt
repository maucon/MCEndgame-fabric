package de.fuballer.mcendgame.event.server

import net.minecraft.server.MinecraftServer

data class ServerStoppingEvent(
    val server: MinecraftServer
)