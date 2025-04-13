package de.fuballer.mcendgame.components.portal.teleport

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.Vec3d
import net.minecraft.world.TeleportTarget

object TeleportExtensions {
    fun PlayerEntity.teleportTo(teleportLocation: TeleportLocation): Boolean {
        // todo how to detect if worked?

        teleportTo(
            TeleportTarget(
                teleportLocation.world,
                teleportLocation.coordinates,
                Vec3d.ZERO,
                teleportLocation.yRot,
                teleportLocation.xRot
            ) { }
        )

        return true
    }
}