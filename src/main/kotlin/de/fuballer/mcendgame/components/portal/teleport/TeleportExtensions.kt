package de.fuballer.mcendgame.components.portal.teleport

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Vec3d
import net.minecraft.world.TeleportTarget
import xyz.nucleoid.fantasy.mixin.MinecraftServerAccess

object TeleportExtensions {
    fun PlayerEntity.teleportTo(teleportLocation: TeleportLocation): Boolean {
        val world = teleportLocation.world ?: return false
        val doesWorldExist = world.server.worlds
            .map { it.registryKey.value.path }
            .contains(world.registryKey.value.path)

        if (!doesWorldExist) return false

        val result = teleportTo(
            TeleportTarget(
                world,
                teleportLocation.coordinates,
                Vec3d.ZERO,
                teleportLocation.yRot,
                teleportLocation.xRot
            ) { }
        )

        return result != null
    }
}