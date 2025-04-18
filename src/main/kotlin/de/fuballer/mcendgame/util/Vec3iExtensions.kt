package de.fuballer.mcendgame.util

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3i

object Vec3iExtensions {
    fun Vec3i.clone(): Vec3i = Vec3i(x, y, z)

    fun Vec3i.stepTowardsZero() = Vec3i(
        x + if (x > 0) -1 else if (x == 0) 0 else 1,
        y + if (y > 0) -1 else if (y == 0) 0 else 1,
        z + if (z > 0) -1 else if (z == 0) 0 else 1,
    )

    fun Vec3i.rotateY90(times: Int): Vec3i {
        val steps = ((times % 4) + 4) % 4
        return when (steps) {
            0 -> this
            1 -> Vec3i(-z, y, x)
            2 -> Vec3i(-x, y, -z)
            3 -> Vec3i(z, y, -x)
            else -> this
        }
    }

    fun Vec3i.toBlockPos() = BlockPos(x, y, z)

    fun Vec3i.max(other: Vec3i) = Vec3i(kotlin.math.max(x, other.x), kotlin.math.max(y, other.y), kotlin.math.max(z, other.z))
}