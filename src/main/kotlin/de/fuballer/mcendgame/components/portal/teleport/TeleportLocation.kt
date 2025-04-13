package de.fuballer.mcendgame.components.portal.teleport

import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.Vec3d

data class TeleportLocation(
    val world: ServerWorld,
    val coordinates: Vec3d,
    val xRot: Float = 0.0F,
    val yRot: Float = 0.0F
)