package de.fuballer.mcendgame.components.dungeon.generation.data

import de.fuballer.mcendgame.util.Vec3iExtension.toBlockPos
import net.minecraft.util.math.Vec3i

data class SpawnPosition(
    val pos: Vec3i,
    val rot: Double = 0.0,
) {
    fun blockPos() = pos.toBlockPos()
}