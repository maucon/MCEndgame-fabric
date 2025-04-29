package de.fuballer.mcendgame.util

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d

object BlockPosExtension {
    fun BlockPos.toVec3d(): Vec3d {
        return Vec3d(x.toDouble(), y.toDouble(), z.toDouble())
    }
}