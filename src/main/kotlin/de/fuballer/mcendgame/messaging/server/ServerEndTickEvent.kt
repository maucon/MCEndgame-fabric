package de.fuballer.mcendgame.messaging.server

import net.minecraft.server.MinecraftServer

data class ServerEndTickEvent(
    val server: MinecraftServer
)