package de.fuballer.mcendgame.components.portal.teleport

import net.minecraft.entity.player.PlayerEntity

object TeleportExtensions { // FIXME
    fun PlayerEntity.teleportTo(teleportLocation: TeleportLocation): Boolean = false
//        teleportTo(
//            teleportLocation.world,
//            teleportLocation.coordinates.x,
//            teleportLocation.coordinates.y,
//            teleportLocation.coordinates.z,
//            mutableSetOf(),
//            teleportLocation.yRot,
//            teleportLocation.xRot,
//            true
//        )
}