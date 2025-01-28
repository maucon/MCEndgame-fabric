package de.fuballer.mcendgame.util

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3i
import org.joml.Vector3d

object Vec3iExtensions {
    fun Vec3i.clone(): Vec3i = Vec3i(x, y, z)

    fun Vec3i.decrement() = Vec3i(x - 1, y - 1, z - 1)

    fun Vec3i.rotateYRad(rad: Double): Vec3i {
        val vector3d = toVector3d().rotateY(rad).add(0.1, 0.1, 0.1)
        return Vec3i(floor(vector3d.x), floor(vector3d.y), floor(vector3d.z))
    }

    fun Vec3i.rotateYDeg(deg: Double) = rotateYRad(Math.toRadians(deg))

    fun Vec3i.toBlockPos() = BlockPos(x, y, z)

    fun Vec3i.toVector3d(): Vector3d = Vector3d(x.toDouble(), y.toDouble(), z.toDouble())

    fun Vec3i.max(other: Vec3i) = Vec3i(kotlin.math.max(x, other.x), kotlin.math.max(y, other.y), kotlin.math.max(z, other.z))

    private fun floor(value: Double): Int {
        val i = value.toInt()
        return if (value < i.toDouble()) i - 1 else i
    }
}