package de.fuballer.mcendgame.main.util

import net.minecraft.util.BlockRotation

object RotationUtil {
    fun getAsRotation(degree: Double) = when (degree) {
        90.0 -> BlockRotation.CLOCKWISE_90
        180.0 -> BlockRotation.CLOCKWISE_180
        270.0 -> BlockRotation.COUNTERCLOCKWISE_90
        else -> BlockRotation.NONE
    }
}