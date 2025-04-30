package de.fuballer.mcendgame.framework.event.server

import net.minecraft.server.MinecraftServer

data class ServerStoppingEvent(
    val server: MinecraftServer
)