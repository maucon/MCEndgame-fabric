package de.fuballer.mcendgame.main.component.dungeon.generation.data

import net.minecraft.util.math.Vec3i
import kotlin.math.atan2
import kotlin.math.roundToInt

data class EncounterLocation(
    val location: Vec3i,
    val doorLocation: Vec3i,
) {
    fun getRotation16(): Int {
        val dx = (doorLocation.x - location.x).toDouble()
        val dz = (doorLocation.z - location.z).toDouble()
        val angle = Math.toDegrees(atan2(dz, dx))
        val rotation = (angle / 22.5).roundToInt()
        return (rotation + 20) % 16
    }
}