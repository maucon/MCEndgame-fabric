package de.fuballer.mcendgame.main.component.portal.teleport

import de.fuballer.mcendgame.main.configuration.RuntimeConfig
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Vec3d
import net.minecraft.world.TeleportTarget

object TeleportExtensions {
    fun PlayerEntity.teleportTo(teleportLocation: TeleportLocation): Boolean {
        val worldKey = teleportLocation.world.registryKey
        val world = RuntimeConfig.SERVER.getWorld(worldKey) ?: return false

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