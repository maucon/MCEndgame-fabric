package de.fuballer.mcendgame.main.component.portal.teleport

import com.mojang.logging.LogUtils
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.codecs.RecordCodecBuilder
import de.fuballer.mcendgame.main.configuration.RuntimeConfig
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.Identifier
import net.minecraft.util.math.Vec3d

data class TeleportLocation(
    val world: ServerWorld,
    val coordinates: Vec3d,
    val xRot: Float = 0.0F,
    val yRot: Float = 0.0F
) {
    companion object {
        private val log = LogUtils.getLogger()

        private val WORLD_CODEC: Codec<ServerWorld> = Identifier.CODEC.comapFlatMap(
            { id ->
                val worldKey = RegistryKey.of(RegistryKeys.WORLD, id)
                val world = RuntimeConfig.SERVER.getWorld(worldKey)
                if (world != null) {
                    DataResult.success(world)
                } else {
                    log.warn("World '{}' not found, skipping teleport location", worldKey.value)
                    DataResult.error { "World with key '$worldKey' not found" }
                }
            },
            { world -> world.registryKey.value }
        )

        val CODEC: Codec<TeleportLocation> = RecordCodecBuilder.create { instance ->
            instance.group(
                WORLD_CODEC
                    .fieldOf("World")
                    .forGetter { it.world },

                Vec3d.CODEC
                    .fieldOf("Coordinates")
                    .forGetter { location -> location.coordinates },

                Codec.FLOAT
                    .optionalFieldOf("RotationX", 0.0f)
                    .forGetter { location -> location.xRot },

                Codec.FLOAT
                    .optionalFieldOf("RotationY", 0.0f)
                    .forGetter { location -> location.yRot }

            ).apply(instance, ::TeleportLocation)
        }
    }
}