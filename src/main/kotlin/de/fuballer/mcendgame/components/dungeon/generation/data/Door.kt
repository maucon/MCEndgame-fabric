package de.fuballer.mcendgame.components.dungeon.generation.data

import de.fuballer.mcendgame.util.Vec3iExtensions.clone
import de.fuballer.mcendgame.util.Vec3iExtensions.rotateY90
import de.fuballer.mcendgame.util.Vec3iExtensions.rotateYRad
import net.minecraft.util.math.Vec3i

data class Door(
    var pos: Vec3i,
    var dir: Vec3i,
) {
    fun getAdjacentPosition(): Vec3i = pos.clone().add(dir)

    fun getRotated(rad: Double): Door {
        val newPosition = pos.rotateYRad(rad)
        val newDirection = dir.rotateYRad(rad)

        return Door(newPosition, newDirection)
    }

    fun getRotated90(times: Int): Door {
        val newPosition = pos.rotateY90(times)
        val newDirection = dir.rotateY90(times)

        return Door(newPosition, newDirection)
    }

    fun getOffset(vec: Vec3i) = Door(
        Vec3i(pos.x + vec.x, pos.y + vec.y, pos.z + vec.z),
        dir.clone()
    )
}