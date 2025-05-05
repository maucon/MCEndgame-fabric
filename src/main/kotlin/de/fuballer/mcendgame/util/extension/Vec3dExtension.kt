package de.fuballer.mcendgame.util.extension

import net.minecraft.util.math.Vec3d
import kotlin.math.PI
import kotlin.math.acos

object Vec3dExtension {
    private fun Vec3d.horizontalAngleDeg(to: Vec3d): Double {
        val horizontal = horizontal
        val toHorizontal = to.horizontal

        val dot = horizontal.dotProduct(toHorizontal)
        val lengthProduct = horizontal.length() * toHorizontal.length()
        if (lengthProduct == 0.0) return 0.0

        val cos = (dot / lengthProduct).coerceIn(-1.0, 1.0)
        val rad = acos(cos)
        return rad * 180 / PI
    }

    fun Vec3d.getYaw(): Double {
        val angle = horizontalAngleDeg(Vec3d(0.0, 0.0, 1.0))
        return if (x > 0) -angle else angle
    }
}