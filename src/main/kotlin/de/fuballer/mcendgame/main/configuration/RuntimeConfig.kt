package de.fuballer.mcendgame.main.configuration

import com.google.gson.JsonElement
import com.mojang.serialization.JsonOps
import de.maucon.mauconframework.di.annotation.Configuration
import de.maucon.mauconframework.initializer.Initializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.registry.RegistryOps
import net.minecraft.server.MinecraftServer
import net.minecraft.util.WorldSavePath
import xyz.nucleoid.fantasy.Fantasy
import java.nio.file.Path

@Configuration
object RuntimeConfig {
    lateinit var FANTASY: Fantasy
    lateinit var SERVER: MinecraftServer
    lateinit var WORLD_SAVE_PATH: Path
    lateinit var REGISTRY_OPS: RegistryOps<JsonElement>

    @Initializer
    fun init() {
        // let this method use the fabric callback instead of our event system, because fantasy needs to be initiated on server thread
        ServerLifecycleEvents.SERVER_STARTING.register { server ->
            this.SERVER = server
            this.FANTASY = Fantasy.get(server)
            this.WORLD_SAVE_PATH = server.getSavePath(WorldSavePath.ROOT)
            this.REGISTRY_OPS = RegistryOps.of(JsonOps.INSTANCE, server.registryManager)
        }
    }
}