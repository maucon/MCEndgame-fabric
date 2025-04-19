package de.fuballer.mcendgame.components.portal.teleport

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import de.fuballer.mcendgame.configuration.RuntimeConfig
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
        val CODEC: Codec<TeleportLocation> = RecordCodecBuilder.create { instance ->
            instance.group(
                Identifier.CODEC
                    .fieldOf("World")
                    .forGetter { location -> location.world.registryKey.value },

                Vec3d.CODEC
                    .fieldOf("Coordinates")
                    .forGetter { location -> location.coordinates },

                Codec.FLOAT
                    .optionalFieldOf("RotationX", 0.0f)
                    .forGetter { location -> location.xRot },

                Codec.FLOAT
                    .optionalFieldOf("RotationY", 0.0f)
                    .forGetter { location -> location.yRot }

            ).apply(instance) { worldId, vec3d, xRot, yRot ->
                val worldKey = RegistryKey.of(RegistryKeys.WORLD, worldId)
                val world = RuntimeConfig.SERVER.getWorld(worldKey)
                    ?: throw IllegalArgumentException("world with key '$worldKey' not found")

                TeleportLocation(world, vec3d, xRot, yRot)
            }
        }
    }
}