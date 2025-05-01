package de.fuballer.mcendgame.framework.event.server

import net.minecraft.server.MinecraftServer

data class ServerEndTickEvent(
    val server: MinecraftServer
)