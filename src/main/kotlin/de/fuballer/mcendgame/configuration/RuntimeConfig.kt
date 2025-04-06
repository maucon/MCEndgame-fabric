package de.fuballer.mcendgame.configuration

import de.maucon.mauconframework.di.annotation.Configuration
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.server.MinecraftServer
import xyz.nucleoid.fantasy.Fantasy

@Configuration
object RuntimeConfig {
    lateinit var FANTASY: Fantasy
    lateinit var SERVER: MinecraftServer

    @Initializer
    fun init() {
        ServerLifecycleEvents.SERVER_STARTING.register { server ->
            this.SERVER = server
            this.FANTASY = Fantasy.get(server)
        }
    }
}